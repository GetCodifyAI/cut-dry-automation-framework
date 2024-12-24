package com.cutanddry.qa.utils;

import jakarta.mail.*;
import jakarta.mail.search.SubjectTerm;

import java.util.Properties;

public class EmailVerifier {

    // This method checks if an email with the given subject is received
    public static boolean verifyEmail(String email, String password, String subject) {
        String host = "imap.gmail.com"; // Replace with the correct IMAP server
        Properties properties = new Properties();
        properties.put("mail.imap.host", host);
        properties.put("mail.imap.port", "993");
        properties.put("mail.imap.ssl.enable", "true");

        // Set up the mail session
        Session session = Session.getInstance(properties);
        try {
            // Connect to the IMAP store
            Store store = session.getStore("imap");
            store.connect(email, password); // Use app password or OAuth for Gmail

            // Access the inbox folder
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            // Search for emails with the specified subject
            Message[] messages = inbox.search(new SubjectTerm(subject));

            // Check if any matching emails are found
            boolean emailFound = messages.length > 0;

            // Clean up and close the connection
            inbox.close(false);
            store.close();

            return emailFound;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}