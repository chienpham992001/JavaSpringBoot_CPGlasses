package com.devpro.javaweb21Version02.controller.administrator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devpro.javaweb21Version02.controller.BaseController;
import com.devpro.javaweb21Version02.model.Contact;
import com.devpro.javaweb21Version02.model.SearchModel;
import com.devpro.javaweb21Version02.services.ContactService;
import com.devpro.javaweb21Version02.services.MailjetService;
import com.devpro.javaweb21Version02.services.PagerData;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;

@Controller
public class AdminContactController extends BaseController {
	@Autowired
	private ContactService contactService;


	@Autowired
	private MailjetService mailjetService;

	@RequestMapping(value = { "/admin/manage_contacts" }, method = RequestMethod.GET)
	public String manage_contact(final Model model, final HttpServletRequest request,
			final HttpServletResponse response) {

		String keyword = request.getParameter("keyword");
		SearchModel searchModel = new SearchModel();
		searchModel.setKeyword(keyword);
		searchModel.setPage(getCurrentPage(request));
		PagerData<Contact> contacts = contactService.search(searchModel);
		model.addAttribute("contacts", contacts);
		model.addAttribute("searchModel", searchModel);
		return "administrator/manage_contacts";

	}

	@RequestMapping(value = { "/admin/send_feedback" }, method = RequestMethod.POST)
	public String send_email_contact(final Model model, final HttpServletRequest request,
			final HttpServletResponse response) throws MailjetException, MailjetSocketTimeoutException {

        String email_receiver =request.getParameter("email_receiver");
        String subject = request.getParameter("subject");
        String content = request.getParameter("content");

		mailjetService.sendEmail(email_receiver, subject, content);
		return "redirect:/admin/manage_contacts";
	}
}
