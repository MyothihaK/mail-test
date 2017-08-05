package com.mail;

import java.util.Date;
import java.util.List;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil {
	/**
	 * Utility method to send simple HTML email
	 * 
	 * @param session
	 * @param toEmail
	 * @param subject
	 * @param body
	 */
	public static void sendEmail(Session session, String toEmail, String subject, String body) {
		try {
			final long startTime = System.currentTimeMillis();
			System.out.println("Sending emails started.");
			MimeMessage msg = new MimeMessage(session);

			// set message headers
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");

			msg.setFrom(new InternetAddress("no_reply@myothihak.com", "NoReply-Indy"));
			msg.setSubject(subject, "UTF-8");
			msg.setText(body, "UTF-8");
			msg.setSentDate(new Date());

			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
			System.out.println("Message is ready");
			Transport.send(msg);

			System.out.println("Sending email finished.!!");
			final long endTime = System.currentTimeMillis();
			System.out.println("Total execution time: " + ((endTime - startTime) / 1000) + "s.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void sendEmail(Session session, List<String> toEmails, String subject, String body) {
		try {
			final long startTime = System.currentTimeMillis();
			System.out.println("Sending emails started.");

			MimeMessage msg = new MimeMessage(session);
			// set message headers
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");

			msg.setFrom(new InternetAddress("no_reply@myothihak.com", "NoReply-Indy"));
			msg.setReplyTo(InternetAddress.parse("no_reply@journaldev.com", false));
			msg.setSubject(subject, "UTF-8");
			msg.setText(body, "UTF-8");
			msg.setSentDate(new Date());

			for (String toEmail : toEmails) {
				msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(toEmail, false));
			}

			System.out.println("Message is ready");
			Transport.send(msg);

			System.out.println("Sending emails finished.!!");
			final long endTime = System.currentTimeMillis();
			System.out.println("Total execution time: " + ((endTime - startTime) / 1000) + "s.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
