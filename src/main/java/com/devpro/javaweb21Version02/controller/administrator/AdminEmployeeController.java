package com.devpro.javaweb21Version02.controller.administrator;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.devpro.javaweb21Version02.model.Role;
import com.devpro.javaweb21Version02.model.SearchModel;
import com.devpro.javaweb21Version02.model.User;
import com.devpro.javaweb21Version02.services.PagerData;
import com.devpro.javaweb21Version02.services.RoleService;
import com.devpro.javaweb21Version02.services.UserService;

@Controller
public class AdminEmployeeController extends BaseController {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	@RequestMapping(value = { "/admin/employee/manage_employees" }, method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String manage_users(final Model model, final HttpServletRequest request,
			final HttpServletResponse response) {

		String keyword = request.getParameter("keyword");
		String keyoption = request.getParameter("keyoption");
		SearchModel searchModel = new SearchModel();
		searchModel.setKeyword(keyword);
		searchModel.setKeyoption(keyoption);
		searchModel.setPage(getCurrentPage(request));
		PagerData<User> employees = userService.searchEmployee(searchModel);
		model.addAttribute("employees", employees);

		model.addAttribute("searchModel", searchModel);
		return "administrator/manage_employee";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/admin/employee/addOrEdit_employee")
	public String addOrEdit_user_GET(final Model model, final HttpServletRequest request,
			final HttpServletResponse response) throws IOException {

		User user = new User();
		model.addAttribute("user", user);
		return "administrator/addOrEdit_employee";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = { "/admin/employee/addOrEdit_employee" }, method = RequestMethod.POST)
	public String addEmployee(final Model model, final HttpServletRequest request, final HttpServletResponse response,
			@Valid @ModelAttribute("user") User user, BindingResult result) throws IOException {

		List<User> users = userService.findAll();
		boolean flag = false;
		if (result.hasErrors()) {
			return "administrator/addOrEdit_employee";
		} else {
			for (User x : users) {
				if (x.getUsername().compareToIgnoreCase(user.getUsername()) == 0)
					flag = true;
				model.addAttribute("existUsername", "Tài khoản đã tồn tại trong hệ thống");
			}
			if (!flag) {
				// Role role = roleService.getById(18);

				userService.saveEmployee(user);
				return "redirect:/admin/employee/manage_employees";
			}
			return "administrator/addOrEdit_employee";
		}
	}

	// ============================Sửa

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = { "/admin/employee/edit_employee" }, method = RequestMethod.GET)
	public String getEmployee(final Model model, final HttpServletRequest request, final HttpServletResponse response)
			throws IOException {
		// Lấy product trong db theo ID

		Integer employeeId = getInteger(request, "id");
		User employeeInDTB = userService.findById(employeeId);

		List<Role> listRoles = userService.getRoles();
		model.addAttribute("user", employeeInDTB);
		model.addAttribute("role", listRoles);

		return "administrator/edit_employee";
	}

	// @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = { "/admin/employee/edit_employee",
			"/admin/employee/edit_profile" }, method = RequestMethod.POST)
	public String editEmployee(final Model model, final HttpServletRequest request, final HttpServletResponse response,
			@Valid @ModelAttribute("user") User user, BindingResult result) throws IOException {

		AdminController adminController = new AdminController();
		User user2 = adminController.getUserLogined();
		Set<Role> listRoles = user2.getRoles();
		Role role = listRoles.stream().findFirst().get();
		int roleId = role.getId();
		if (result.hasErrors()) {
			return "administrator/edit_employee";
		} else {

			User employeeInDTB = userService.findById(user.getId());
			if (employeeInDTB.getPassword().equals(user.getPassword())) {
				userService.updateEmployee(user);
			} else {
				userService.saveEmployee(user);
			}
			if (roleId == 16)
				return "redirect:/admin/employee/manage_employees";
			else {
				return "redirect:/admin";
			}
		}
	}

	@DeleteMapping("/admin/employee/manage_employees/delete")
	public ResponseEntity<Map<String, Object>> adminDisableEmployee(final Model model, final HttpServletRequest request,
			final HttpServletResponse response, final @RequestBody User user) throws IOException {

		User employeeInDTB = userService.findById(user.getId());
		employeeInDTB.setStatus(false);
		userService.updateEmployee(employeeInDTB);
		Map<String, Object> jsonResult = new HashMap<String, Object>();
		jsonResult.put("code", 200);
		jsonResult.put("message", "Vô hiệu hóa thành công");
		return ResponseEntity.ok(jsonResult);
	}

	@PostMapping("/admin/employee/manage_employees/active")
	public ResponseEntity<Map<String, Object>> adminActiveEmployee(final Model model, final HttpServletRequest request,
			final HttpServletResponse response, final @RequestBody User user) throws IOException {

		User employeeInDTB = userService.findById(user.getId());
		employeeInDTB.setStatus(true);
		userService.updateEmployee(employeeInDTB);
		Map<String, Object> jsonResult = new HashMap<String, Object>();
		jsonResult.put("code", 200);
		jsonResult.put("message", "Kích hoạt thành công");
		return ResponseEntity.ok(jsonResult);
	}

	@GetMapping("/admin/employee/edit_profile")
	public String showEmployeeProfile(final Model model, final HttpServletRequest request,
			final HttpServletResponse response) throws IOException {
		// Lấy product trong db theo ID

		Integer employeeId = getInteger(request, "id");
		User employeeInDTB = userService.findById(employeeId);

		List<Role> listRoles = userService.getRoles();
		model.addAttribute("user", employeeInDTB);
		model.addAttribute("role", listRoles);

		return "administrator/edit_employee";
	}

//	@RequestMapping(value = { "/admin/employee/edit_profile" }, method = RequestMethod.POST)
//	public String editEmployeeProfile(final Model model, final HttpServletRequest request, final HttpServletResponse response,
//			@Valid @ModelAttribute("user") User user, BindingResult result) throws IOException {
//
//		if (result.hasErrors()) {
//			return "administrator/edit_employee";
//		} else {
//
//			User employeeInDTB = userService.findById(user.getId());
//			if (employeeInDTB.getPassword().equals(user.getPassword())) {
//				userService.updateEmployee(user);
//			} else {
//				userService.saveEmployee(user);
//			}
//			return "redirect:/admin";
//
//		}
//	}
}
