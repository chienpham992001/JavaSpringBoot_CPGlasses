package com.devpro.javaweb21Version02.controller.administrator;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devpro.javaweb21Version02.controller.BaseController;
import com.devpro.javaweb21Version02.model.SearchModel;
import com.devpro.javaweb21Version02.model.User;
import com.devpro.javaweb21Version02.services.PagerData;
import com.devpro.javaweb21Version02.services.RoleService;
import com.devpro.javaweb21Version02.services.UserService;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController extends BaseController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = { "/admin/admins/manage_admins" }, method = RequestMethod.GET)
	
	public String manage_users(final Model model, final HttpServletRequest request,
			final HttpServletResponse response) {

		String keyword = request.getParameter("keyword");
		String keyoption = request.getParameter("keyoption");
		SearchModel searchModel = new SearchModel();
		searchModel.setKeyword(keyword);
		searchModel.setKeyoption(keyoption);
		searchModel.setPage(getCurrentPage(request));
		PagerData<User> admins = userService.searchAdmin(searchModel);
		model.addAttribute("admins", admins);

		model.addAttribute("searchModel", searchModel);
		return "administrator/manage_admins";
	}

	@GetMapping("/admin/admins/addOrEdit_admin")
	public String addAdmin_GET(final Model model, final HttpServletRequest request,
			final HttpServletResponse response) throws IOException {

		User user = new User();
		model.addAttribute("user", user);
		return "administrator/addOrEdit_admin";
	}
	
	@RequestMapping(value = { "/admin/admins/addOrEdit_admin" }, method = RequestMethod.POST)
	public String addAdmin(final Model model, final HttpServletRequest request, final HttpServletResponse response,
			@Valid @ModelAttribute("user") User user, BindingResult result) throws IOException {

		List<User> users = userService.findAll();
		boolean flag = false;
		if (result.hasErrors()) {
			return "administrator/addOrEdit_admin";
		} else {
			for (User x : users) {
				if (x.getUsername().compareToIgnoreCase(user.getUsername()) == 0)
					flag = true;
				model.addAttribute("existUsername", "Tài khoản đã tồn tại trong hệ thống");
			}
			if (!flag) {
				userService.saveAdmin(user);
				return "redirect:/admin/admins/manage_admins";
			}
			return "administrator/addOrEdit_admin";
		}
	}
	
	@DeleteMapping("/admin/admins/manage_admins/delete")
	public ResponseEntity<Map<String, Object>> adminDisableAdmin(final Model model, final HttpServletRequest request,
			final HttpServletResponse response, final @RequestBody User user) throws IOException {

		User employeeInDTB = userService.findById(user.getId());
		employeeInDTB.setStatus(false);
		userService.updateAdmin(employeeInDTB);
		Map<String, Object> jsonResult = new HashMap<String, Object>();
		jsonResult.put("code", 200);
		jsonResult.put("message", "Vô hiệu hóa thành công");
		return ResponseEntity.ok(jsonResult);
	}

	@PostMapping("/admin/admins/manage_admins/active")
	public ResponseEntity<Map<String, Object>> adminActiveAdmin(final Model model, final HttpServletRequest request,
			final HttpServletResponse response, final @RequestBody User user) throws IOException {

		User employeeInDTB = userService.findById(user.getId());
		employeeInDTB.setStatus(true);
		userService.updateAdmin(employeeInDTB);
		Map<String, Object> jsonResult = new HashMap<String, Object>();
		jsonResult.put("code", 200);
		jsonResult.put("message", "Kích hoạt thành công");
		return ResponseEntity.ok(jsonResult);
	}
}
