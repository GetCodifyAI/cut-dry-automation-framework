#!/bin/bash


set -e

RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

log() {
    echo -e "${GREEN}[$(date +'%Y-%m-%d %H:%M:%S')] $1${NC}"
}

warn() {
    echo -e "${YELLOW}[$(date +'%Y-%m-%d %H:%M:%S')] WARNING: $1${NC}"
}

error() {
    echo -e "${RED}[$(date +'%Y-%m-%d %H:%M:%S')] ERROR: $1${NC}"
    exit 1
}

log "Updating system packages..."
sudo yum update -y

log "Installing Java 17..."
sudo amazon-linux-extras install java-openjdk17 -y

java -version

log "Installing Jenkins..."
sudo wget -O /etc/yum.repos.d/jenkins.repo https://pkg.jenkins.io/redhat-stable/jenkins.repo
sudo rpm --import https://pkg.jenkins.io/redhat-stable/jenkins.io.key
sudo yum install jenkins -y

log "Installing Docker..."
sudo yum install docker -y
sudo systemctl start docker
sudo systemctl enable docker
sudo usermod -a -G docker jenkins

log "Installing AWS CLI v2..."
curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
sudo ./aws/install
rm -rf aws awscliv2.zip

log "Installing Git..."
sudo yum install git -y

log "Installing additional tools..."
sudo yum install -y wget curl unzip bc

log "Configuring Jenkins..."

sudo mkdir -p /var/lib/jenkins/plugins
sudo mkdir -p /var/lib/jenkins/jobs
sudo chown -R jenkins:jenkins /var/lib/jenkins

log "Starting Jenkins..."
sudo systemctl start jenkins
sudo systemctl enable jenkins

log "Waiting for Jenkins to start..."
sleep 30

if sudo systemctl is-active --quiet jenkins; then
    log "Jenkins is running successfully!"
else
    error "Jenkins failed to start"
fi

log "Getting Jenkins initial admin password..."
if [ -f /var/lib/jenkins/secrets/initialAdminPassword ]; then
    INITIAL_PASSWORD=$(sudo cat /var/lib/jenkins/secrets/initialAdminPassword)
    log "Jenkins initial admin password: $INITIAL_PASSWORD"
else
    warn "Initial admin password file not found. Jenkins may still be starting up."
fi

if sudo systemctl is-active --quiet firewalld; then
    log "Configuring firewall..."
    sudo firewall-cmd --permanent --add-port=8080/tcp
    sudo firewall-cmd --permanent --add-port=50000/tcp
    sudo firewall-cmd --reload
fi

cat > /tmp/setup-jenkins-cli.sh << 'EOF'
#!/bin/bash
wget http://localhost:8080/jnlpJars/jenkins-cli.jar -O /var/lib/jenkins/jenkins-cli.jar
chown jenkins:jenkins /var/lib/jenkins/jenkins-cli.jar
EOF

sudo mv /tmp/setup-jenkins-cli.sh /var/lib/jenkins/setup-jenkins-cli.sh
sudo chmod +x /var/lib/jenkins/setup-jenkins-cli.sh
sudo chown jenkins:jenkins /var/lib/jenkins/setup-jenkins-cli.sh

cat > /tmp/install-plugins.sh << 'EOF'
#!/bin/bash

JENKINS_URL="http://localhost:8080"
JENKINS_CLI="/var/lib/jenkins/jenkins-cli.jar"

while ! curl -s $JENKINS_URL/login > /dev/null; do
    echo "Waiting for Jenkins to be ready..."
    sleep 10
done

PLUGINS=(
    "pipeline-stage-view"
    "ec2"
    "testng-results"
    "slack"
    "build-timeout"
    "timestamper"
    "ws-cleanup"
    "matrix-auth"
    "git"
    "github"
    "pipeline-github-lib"
    "workflow-aggregator"
    "blueocean"
    "docker-workflow"
    "parallel-test-executor"
)

for plugin in "${PLUGINS[@]}"; do
    echo "Installing plugin: $plugin"
    java -jar $JENKINS_CLI -s $JENKINS_URL install-plugin $plugin -restart
done

echo "Plugin installation completed!"
EOF

sudo mv /tmp/install-plugins.sh /var/lib/jenkins/install-plugins.sh
sudo chmod +x /var/lib/jenkins/install-plugins.sh
sudo chown jenkins:jenkins /var/lib/jenkins/install-plugins.sh

cat > /tmp/backup-jenkins.sh << 'EOF'
#!/bin/bash

BACKUP_DIR="/var/lib/jenkins/backups"
DATE=$(date +%Y%m%d_%H%M%S)
BACKUP_FILE="jenkins_backup_$DATE.tar.gz"

mkdir -p $BACKUP_DIR

tar -czf $BACKUP_DIR/$BACKUP_FILE \
    --exclude='workspace' \
    --exclude='builds/*/workspace' \
    --exclude='jobs/*/workspace' \
    --exclude='jobs/*/builds/*/workspace' \
    /var/lib/jenkins/

echo "Backup created: $BACKUP_DIR/$BACKUP_FILE"

find $BACKUP_DIR -name "jenkins_backup_*.tar.gz" -type f -mtime +7 -delete
EOF

sudo mv /tmp/backup-jenkins.sh /var/lib/jenkins/backup-jenkins.sh
sudo chmod +x /var/lib/jenkins/backup-jenkins.sh
sudo chown jenkins:jenkins /var/lib/jenkins/backup-jenkins.sh

(sudo crontab -u jenkins -l 2>/dev/null; echo "0 2 * * * /var/lib/jenkins/backup-jenkins.sh") | sudo crontab -u jenkins -

log "Jenkins master installation completed!"
echo
echo "=== JENKINS MASTER SETUP COMPLETE ==="
echo "Jenkins URL: http://$(curl -s http://169.254.169.254/latest/meta-data/public-ipv4):8080"
echo "Initial admin password: $INITIAL_PASSWORD"
echo
echo "=== NEXT STEPS ==="
echo "1. Access Jenkins web interface"
echo "2. Complete initial setup wizard"
echo "3. Install suggested plugins or run: sudo -u jenkins /var/lib/jenkins/install-plugins.sh"
echo "4. Create admin user"
echo "5. Configure system settings"
echo "6. Set up EC2 cloud configuration"
echo "7. Create your first pipeline job"
echo
echo "=== USEFUL COMMANDS ==="
echo "Check Jenkins status: sudo systemctl status jenkins"
echo "View Jenkins logs: sudo journalctl -u jenkins -f"
echo "Restart Jenkins: sudo systemctl restart jenkins"
echo "Backup Jenkins: sudo -u jenkins /var/lib/jenkins/backup-jenkins.sh"
echo
