#!/bin/bash


if [ $# -eq 0 ]; then
    echo "Usage: $0 <TEST_CASE_ID>"
    echo "Example: $0 DOT-TC-1836"
    exit 1
fi

TEST_CASE_ID=$1
TIMESTAMP=$(date +%s)
BRANCH_NAME="devin/${TIMESTAMP}-${TEST_CASE_ID,,}"

echo "Generating test for AIO test case: $TEST_CASE_ID"
echo "Creating branch: $BRANCH_NAME"

git checkout -b "$BRANCH_NAME"

echo "Compiling framework..."
mvn clean compile -DskipTests

if [ $? -ne 0 ]; then
    echo "Compilation failed. Aborting."
    exit 1
fi

echo "Generating test class from AIO..."
java -cp "target/classes:$(mvn dependency:build-classpath -Dmdep.outputFile=/dev/stdout -q)" \
    com.cutanddry.qa.utils.aio.testcase.AioTestCaseGenerator "$TEST_CASE_ID"

if [ $? -ne 0 ]; then
    echo "Test generation failed. Aborting."
    exit 1
fi

echo "Compiling with generated test..."
mvn clean compile -DskipTests

if [ $? -ne 0 ]; then
    echo "Generated test compilation failed. Please review the generated code."
    exit 1
fi

echo "Test generation completed successfully!"
echo "Generated test class for $TEST_CASE_ID"
echo "Branch: $BRANCH_NAME"
echo ""
echo "Next steps:"
echo "1. Review the generated test class"
echo "2. Run the test: mvn test -Dtest=<GeneratedTestClassName> -Drun.headless=true -Dtest.env=uat"
echo "3. Commit and push changes"
echo "4. Create pull request"
