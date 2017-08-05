package com.mailguntest;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.multipart.FormDataMultiPart;

public class MGSendSimpleSMTP {

	public static void main(String args[]) throws Exception {
		MGSendSimpleSMTP mgSendSimpleSMTP = new MGSendSimpleSMTP();
		String receiptMail = "myothihaktest@gmail.com";
		String receiptMail2 = "myothihaktest02@gmail.com";
		mgSendSimpleSMTP.sendComplexMessage(receiptMail, receiptMail2);
	}

	// send mail
	private void sendComplexMessage(String recipient, String recipient2) {
		String MAILGUN_API_KEY = "key-07f21cfb94e8979bdffc46c1938885eb";
		String MAILGUN_DOMAIN_NAME = "sandbox74fd332cb0dd40679dd63db61ee996cb.mailgun.org";

		final long startTime = System.currentTimeMillis();
		System.out.println("Sending emails started.");
		Client client = Client.create();
		client.addFilter(new HTTPBasicAuthFilter("api", MAILGUN_API_KEY));
		WebResource webResource = client.resource("https://api.mailgun.net/v3/" + MAILGUN_DOMAIN_NAME + "/messages");
		FormDataMultiPart formData = new FormDataMultiPart();

		formData.field("from", "Mailgun User <mailgun@" + MAILGUN_DOMAIN_NAME + ">");
		formData.field("to", recipient);
		formData.field("to", recipient2);
		formData.field("subject", "Complex Mailgun Example");
		formData.field("html", "<html>HTML <strong>content</strong></html>");
		formData.field("o:testmode", "true");
		System.out.println("Sending email finished.!!");

		webResource.type(MediaType.MULTIPART_FORM_DATA_TYPE).post(ClientResponse.class, formData);
		final long endTime = System.currentTimeMillis();
		System.out.println("Total execution time: " + ((endTime - startTime) / 1000) + "s.");
	}
}
