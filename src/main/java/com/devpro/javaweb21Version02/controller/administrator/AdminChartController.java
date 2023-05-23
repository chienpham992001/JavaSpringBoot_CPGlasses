package com.devpro.javaweb21Version02.controller.administrator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devpro.javaweb21Version02.model.Categories;
import com.devpro.javaweb21Version02.model.Product;
import com.devpro.javaweb21Version02.model.SaleOrder;
import com.devpro.javaweb21Version02.model.SaleOrderProducts;
import com.devpro.javaweb21Version02.model.User;
import com.devpro.javaweb21Version02.services.CategoriesService;
import com.devpro.javaweb21Version02.services.ProductService;
import com.devpro.javaweb21Version02.services.SaleOrderProductService;
import com.devpro.javaweb21Version02.services.SaleOrderService;
import com.devpro.javaweb21Version02.services.UserService;

@Controller
public class AdminChartController {
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoriesService categoriesService;
	@Autowired
	private UserService userService;
	@Autowired
	private SaleOrderService saleOrderService;

	@Autowired
	private SaleOrderProductService saleOrderProductService;

	@RequestMapping(value = { "/admin/chart" }, method = RequestMethod.GET)
	public String chart(final Model model, final HttpServletRequest request, final HttpServletResponse response) {
		Calendar cal = Calendar.getInstance();
		Integer month = cal.get(Calendar.MONTH)+1;
		List<Product> products = productService.findAll();
		List<Categories> cates = categoriesService.findAll();
		
		model.addAttribute("categoryQuanlity", cates.size());
		model.addAttribute("productQuanlity", products.size());

		List<SaleOrder> saleOrdersSuccess = saleOrderService.getEntitiesByNativeSQL("SELECT * FROM tbl_saleorder where  status_shipping='Giao thành công'");
		List<SaleOrder> saleOrdersWaiting = saleOrderService.getEntitiesByNativeSQL("SELECT * FROM tbl_saleorder where  status_shipping='Chờ xác nhận'");
		List<SaleOrder> saleOrdersCancel = saleOrderService.getEntitiesByNativeSQL("SELECT * FROM tbl_saleorder where  status_shipping='Hoàn đơn'");
		List<SaleOrder> saleOrdersDelivering = saleOrderService.getEntitiesByNativeSQL("SELECT * FROM tbl_saleorder where  status_shipping='Đang giao'");
		model.addAttribute("saleOrdersSuccess", saleOrdersSuccess.size());
		model.addAttribute("saleOrdersWaiting", saleOrdersWaiting.size());
		model.addAttribute("saleOrdersDelivering", saleOrdersDelivering.size());
		model.addAttribute("saleOrdersCancel", saleOrdersCancel.size());
		
		List<User> admins = userService.getEntitiesByNativeSQL("SELECT * FROM tbl_users,tbl_users_roles where tbl_users.id = tbl_users_roles.user_id and role_id=16");
		List<User> customers = userService.getEntitiesByNativeSQL("SELECT * FROM tbl_users,tbl_users_roles where tbl_users.id = tbl_users_roles.user_id and role_id=17");
		model.addAttribute("admins", admins.size());
		model.addAttribute("customers", customers.size());
		
		List<BigDecimal> incomeArr = new ArrayList<>();
		for(int i = 1;i<=12;i++) {
			List<SaleOrderProducts> x = saleOrderProductService.getEntitiesByNativeSQL("SELECT * FROM tbl_saleorder_products,tbl_saleorder WHERE tbl_saleorder.id = tbl_saleorder_products.saleorder_id And month(tbl_saleorder.created_date) ="+i);
			BigDecimal total = new BigDecimal("0");;
			for (SaleOrderProducts saleOrderProducts : x) {
				total=total.add(saleOrderProducts.getProduct().getPrice().multiply(new BigDecimal(saleOrderProducts.getQuanlity())));
			}
			incomeArr.add(total);
		}

		model.addAttribute("incomeArr", incomeArr);
		return "administrator/chart";
	}
}
