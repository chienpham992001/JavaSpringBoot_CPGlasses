package com.devpro.javaweb21Version02.services;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devpro.javaweb21Version02.dto.MyConstants;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.resource.Emailv31;

@Service
public class MailjetService {

	@Autowired
	private MailjetClient mailjetClient;

	public void sendEmail(String to, String subject, String text)
			throws MailjetException, MailjetSocketTimeoutException {
		MailjetRequest request;
		MailjetResponse response;
		
		String content = MyConstants.HEADER + text + MyConstants.FOOTER;
		
		request = new MailjetRequest(Emailv31.resource)
				.property(Emailv31.MESSAGES,
						new JSONArray().put(new JSONObject()
								.put(Emailv31.Message.FROM,
										new JSONObject().put("Email", MyConstants.MY_EMAIL).put("Name",
												"CP Eyes Glasses"))
								.put(Emailv31.Message.TO,
										new JSONArray().put(new JSONObject().put("Email", to).put("Name", to)))
								.put(Emailv31.Message.SUBJECT, subject)
								.put(Emailv31.Message.HTMLPART, content)));
		response = mailjetClient.post(request);
		System.out.println(response.getStatus());
		System.out.println(response.getData());

	}

}