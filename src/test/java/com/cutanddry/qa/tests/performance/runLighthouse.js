import fs from 'fs';
import path from 'path';
import { launch } from 'chrome-launcher';

// Dynamically import Lighthouse as an ES module
const lighthouse = (await import('lighthouse')).default;

async function runLighthouse(url, cookies) {
    const reportFolder = path.join(process.cwd(), 'lighthouse_reports');
    if (!fs.existsSync(reportFolder)) {
        fs.mkdirSync(reportFolder, { recursive: true });
    }

    try {
        // Launch Chrome for Lighthouse profiling
        const chrome = await launch({
            port: 9222,
            chromeFlags: ['--headless', '--disable-gpu', '--remote-debugging-port=9222', '--window-size=1920,1080']
        });

        console.log(`Chrome launched on port: ${chrome.port}`);

        // Lighthouse options
        const options = {
            port: chrome.port,
            extraHeaders: {
                Cookie: cookies,  // Pass authentication cookies
            },
            output: 'html',
        };

        // Run Lighthouse audit
        const results = await lighthouse(url, options);
        const reportHtml = results.report;

        // Time to Interactive (TTI) calculation
        const tti = results.lhr.audits['interactive'].numericValue / 1000;
        console.log(`Time to Interactive (TTI): ${tti} seconds`);

        // Save report with timestamp
        const timestamp = new Date().toISOString().replace(/[:.]/g, '-');
        const reportPath = path.join(reportFolder, `lighthouse_report_${timestamp}.html`);
        fs.writeFileSync(reportPath, reportHtml);

        console.log(`Lighthouse report saved at: ${reportPath}`);

        await chrome.kill();  // Ensure Chrome is properly closed
    } catch (error) {
        console.error('Lighthouse execution failed:', error);
    }
}

// Get arguments from Java ProcessBuilder
const [,, url, cookies] = process.argv;
if (url && cookies) {
    runLighthouse(url, cookies);
} else {
    console.error("Usage: node runLighthouse.js <url> <cookies>");
    process.exit(1);
}