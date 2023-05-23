package com.devpro.javaweb21Version02.controller.administrator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.devpro.javaweb21Version02.controller.BaseController;
import com.devpro.javaweb21Version02.model.Product;
import com.devpro.javaweb21Version02.model.Role;
import com.devpro.javaweb21Version02.model.SearchModel;
import com.devpro.javaweb21Version02.model.User;
import com.devpro.javaweb21Version02.services.PagerData;
import com.devpro.javaweb21Version02.services.RoleService;
import com.devpro.javaweb21Version02.services.UserService;

@Controller
public class AdminUserController extends BaseController {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	@RequestMapping(value = { "/admin/manage_users" }, method = RequestMethod.GET)
	public String manage_users(final Model model, final HttpServletRequest request,
			final HttpServletResponse response) {

		String keyword = request.getParameter("keyword");
		String keyoption = request.getParameter("keyoption");
		SearchModel searchModel = new SearchModel();
		searchModel.setKeyword(keyword);
		searchModel.setKeyoption(keyoption);
		searchModel.setPage(getCurrentPage(request));
		PagerData<User> users = userService.search(searchModel);
		model.addAttribute("users", users);

		model.addAttribute("searchModel", searchModel);
		return "administrator/manage_users";
	}

	@RequestMapping(value = { "/admin/addOrEdit_user" }, method = RequestMethod.GET)
	public String addOrEdit_user_GET(final Model model, final HttpServletRequest request,
			final HttpServletResponse response) throws IOException {

		User user = new User();
		model.addAttribute("user", user);

		/*
		 * List<Role> roles = roleService.findAll(); for (Role role : roles) {
		 * System.out.println(role.getName()); }
		 */
		/* model.addAttribute("roles", roles); */

		return "administrator/addOrEdit_user";
	}

	@RequestMapping(value = { "/admin/addOrEdit_user" }, method = RequestMethod.POST)
	public String addOrEdit_user_POST(final Model model, final HttpServletRequest request,
			final HttpServletResponse response, @Valid @ModelAttribute("user") User user, BindingResult result)
			throws IOException {

		List<User> users = userService.findAll();
		boolean flag = false;
		if (result.hasErrors()) {
			return "administrator/addOrEdit_user";
		} else {
			for (User x : users) {
				if (x.getUsername().compareToIgnoreCase(user.getUsername()) == 0)
					flag = true;
				model.addAttribute("existUsername", "Tài khoản đã tồn tại trong hệ thống");
			}
			if (!flag) {
				// Role role = roleService.getById(17);
				// user.setPassword(new BCryptPasswordEncoder(4).encode(user.getPassword()));
				userService.saveGuest(user);
				return "redirect:/admin/manage_users";
			}
			return "administrator/addOrEdit_user";
		}
	}

	@RequestMapping(value = { "/admin/edit_user" }, method = RequestMethod.GET)
	public String edit_user_GET(final Model model, final HttpServletRequest request, final HttpServletResponse response)
			throws IOException {
		// Lấy product trong db theo ID

		Integer userId = getInteger(request, "id");
		User userInDTB = userService.getById(userId);
		List<Role> listRoles = userService.getRoles();
		model.addAttribute("user", userInDTB);
		model.addAttribute("role", listRoles);
		return "administrator/edit_user";
	}

	// Sửa thông tin user
	@RequestMapping(value = { "/admin/edit_user" }, method = RequestMethod.POST)
	public String edit_user_POST(final Model model, final HttpServletRequest request,
			final HttpServletResponse response, @Valid @ModelAttribute("user") User user, BindingResult result)
			throws IOException {

		if (result.hasErrors()) {
			return "administrator/edit_user";
		} else {
			User userInDTB = userService.findById(user.getId());

			Set<Role> listRoles = userInDTB.getRoles();
			Role role = listRoles.stream().findFirst().get();
			// Role role = listRoles.getClass()
			if (role.getId() == 17) {

				userService.saveGuest(user);
			}
			if (role.getId() == 16) {

				userService.saveAdmin(user);
				return "redirect:/admin/admins/manage_admins";
			}

			return "redirect:/admin/manage_users";
		}
	}

}
