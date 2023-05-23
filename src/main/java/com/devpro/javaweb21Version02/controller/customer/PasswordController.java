package com.devpro.javaweb21Version02.controller.customer;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.devpro.javaweb21Version02.model.Role;
import com.devpro.javaweb21Version02.model.User;
import com.devpro.javaweb21Version02.services.MailjetService;
import com.devpro.javaweb21Version02.services.UserService;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;

@Controller
public class PasswordController {
	@Autowired
	private UserService userService;

	@Autowired
	private MailjetService mailjetService;
	
	@GetMapping("/forget_password")
	public String forgotPassword(final Model model, final HttpServletRequest request, final HttpServletResponse response) {

		return "forget_password";
	}
	
	@PostMapping("/forget_password")
	public String getNewPassword(final Model model, final HttpServletRequest request,
			final HttpServletResponse response)
			throws IOException, MailjetException, MailjetSocketTimeoutException {
		String account = request.getParameter("username");
		User user = userService.findByUsername(account);
		if(user!=null) {
			String[] arr = account.split("@");
			String newPass = arr[0]+ThreadLocalRandom.current().nextInt(100,999);
			Set<Role> listRoles = user.getRoles();
			Role role = listRoles.stream().findFirst().get();
			System.out.println(role.getName());
			user.setPassword(newPass);
			if(role.getName().equals("ADMIN")) userService.saveAdmin(user);
			if(role.getName().equals("EMPLOYEE")) userService.saveEmployee(user);
			if(role.getName().equals("GUEST")) userService.saveGuest(user);
			
			String content = "Mật khẩu mới của bạn là : "+ newPass;
			mailjetService.sendEmail(account, "Mật khẩu mới", content);

			model.addAttribute("success","success");
			return "forget_password";
		}
		else {
			model.addAttribute("notExist","notExist");
			return "forget_password";
		}
		
	}
}
