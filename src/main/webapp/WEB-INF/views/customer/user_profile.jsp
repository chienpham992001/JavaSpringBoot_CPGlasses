<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<jsp:include page="/WEB-INF/views/common/variables.jsp"></jsp:include>

<title>CP Glasses | Profile</title>

<jsp:include page="/WEB-INF/views/customer/layout/css_js_head.jsp"></jsp:include>
</head>

<body>

	<!--Header-->
	<jsp:include page="/WEB-INF/views/customer/layout/header.jsp"></jsp:include>

	<main>
		<div class="page_banner">
			<div class="page_title">
				<h1>Thông tin cá nhân</h1>
				<ol class="bread_crumb">
					<li><a href="">Trang chủ</a></li>
					<li><a href="">Thông tin cá nhân</a></li>
				</ol>
			</div>
		</div>
		
		<div class="container mt-4">
			<div class="tabs">
				<div class="tab-2">
					<label for="tab2-1">Thông tin cá nhân</label> <input id="tab2-1"
						name="tabs-two" type="radio" checked="checked">
					<div>
						<div class="row">
							<div class="col-md-12">
								<div class="contact_info">
									<h2>Thông tin cá nhân</h2>

									<div class="info_find">
										<div class="icon-box-wrapper">
											<div>
												<h5>Họ tên</h5>
												<p style="font-size: 20px; color: #ff871d;">${user.fullname }</p>
											</div>
										</div>

										<div class="icon-box-wrapper">
											<div>
												<h5>Email</h5>
												<p style="font-size: 20px; color: #ff871d;">${user.username }</p>
											</div>
										</div>
										<div class="icon-box-wrapper">
											<div>
												<h5>Số điện thoại</h5>
												<p style="font-size: 20px; color: #ff871d;">${user.phone }</p>
											</div>
										</div>
									</div>
								</div>
								<div class="changeInfo">
									<button style="background-color: #ff871d; padding: 10px;font-size: 18px" type="button"
										data-toggle="modal" data-target="#modalChangeInfo"
										class="btn btn-info">Thay đổi thông tin cá nhân</button>
									<div class="modal fade vote_modal" id="modalChangeInfo">
										<div class="modal-dialog modal-dialog-centered minusTop"
											role="document">
											<div class="modal-content">
												<div class="modal-header">
													<h4 class="modal-title">Thay đổi thông tin khách hàng</h4>
													<button type="button" class="close" data-dismiss="modal">
														<span>x</span>
													</button>
												</div>
												<div class="modal-body">
													<sf:form action="${base}/user/update_info" method="POST"
														modelAttribute="user" class="formInfo">
														<sf:hidden path="id" class="form-control" name="id" />

														<label class="mt-3"><h6>Họ và tên</h6></label>

														<sf:input path="fullname" class="form-control"
															name="fullname" />
														<sf:errors path="fullname" cssClass="alert alert-danger"
															element="div"></sf:errors>
														<label class="mt-2"><h6>Tài khoản</h6></label>
														<sf:input path="username" class="form-control"
															name="username" readonly="true" />
														<sf:errors path="username" cssClass="alert alert-danger"
															element="div"></sf:errors>
															
														<label class="mt-2"><h6>Mật khẩu</h6></label>
														<sf:input path="password" type="password"
															class="form-control" name="password" />
														<sf:errors path="password" cssClass="alert alert-danger"
															element="div"></sf:errors>

														<label class="mt-2"><h6>Số điện thoại</h6></label>
														<sf:input path="phone" class="form-control" name="phone" />
														<sf:errors path="phone" cssClass="alert alert-danger"
															element="div"></sf:errors>
															
														<div class="modal-footer ">
															<button type="button" class="btn btn-secondary"
																data-dismiss="modal">Hủy</button>
															<button type="submit" class="btn btn-primary">Gửi</button>
														</div>
													</sf:form>
												</div>
											</div>
										</div>
									</div>
								</div>

							</div>
						</div>
					</div>
				</div>
				<div class="tab-2">
					<label for="tab2-2">Lịch sử mua hàng</label> <input id="tab2-2"
						name="tabs-two" type="radio">
					<div>
						<div class="row justify-content-center">
							<div class="col-lg-9 col-md-9 content-cart-page ">
								<h2 style="color: #ff871d;">
									<center>Hóa đơn khách hàng</center>
								</h2>
								<table>
									<thead>
										<tr>
											<th width="15%">Thời gian</th>
											<!-- <th width="10%">Mã sp</th> -->
											<th colspan="2">Sản phẩm</th>
											<th>Giá</th>
											<th width="12%">Số lượng</th>
											<th>Thành tiền</th>
											<th width="10%">Đánh giá</th>
										</tr>
									</thead>
									<tbody>
										<c:set var="total" value="0"></c:set>
										<c:forEach items="${saleorderproducts}" var="hd">
											<tr class="cart_item">

												<c:set var="total_price" value="${sp.priceUnit*sp.quanlity}"></c:set>
												<td class="cart_item_name" >
													<div class="glass_name">${hd.saleOrder.createdDate}</div>
												</td>
												<%-- <td class="cart_item_name">
													<div class="glass_name">${hd.product.id}</div>
												</td> --%>
												<td class="cart_item_img"><a href="product_detail.html">
														<img src="${base }/upload/${hd.product.avatar}" alt="">
												</a></td>

												<td class="cart_item_name">
													<div class="glass_name">
														<a href="/product_detail?id=${hd.product.id}">${hd.product.title}</a>
													</div>
												</td>
												<td class="cart_item_price"><fmt:setLocale
														value="vi_VN" /> <strong><fmt:formatNumber
															value="${hd.product.price }" type="currency" /></strong></td>
												<td class="cart_item_quantity">
													<div class="quantity-icon-action">
														<span class="quantity-number" id="quantity-number">${hd.quanlity }</span>
													</div>
												</td>
												<td class="cart_item_price"><fmt:setLocale
														value="vi_VN" /> <strong><fmt:formatNumber
															value="${hd.product.price * hd.quanlity}" type="currency" /></strong></td>
												<td><c:choose>
														<c:when test="${hd.feedback_status!=null}">

															<div class="glass_name" style="color: #ff871d">Đã
																đánh giá</div>

														</c:when>
														<c:otherwise>
															<button style="background-color: #ff871d;" type="button"
																data-toggle="modal" data-target="#exampleModalCenter"
																class="btn btn-info"
																onclick="send_feedback(${hd.id},${hd.product.id})">Đánh
																giá</button>
														</c:otherwise>
													</c:choose></td>
											</tr>


										</c:forEach>
									</tbody>
								</table>
							</div>
							<div class="modal fade vote_modal" id="exampleModalCenter">
								<div class="modal-dialog modal-dialog-centered minusTop" role="document">
									<div class="modal-content">
										<div class="modal-header">
											<h4 class="modal-title">Đánh giá sản phẩm</h4>
											<button type="button" class="close" data-dismiss="modal">
												<span>x</span>
											</button>
										</div>
										<div class="modal-body">
											<form action="${base}/user/send_feedback" method="POST">
												<input type="hidden" id="saleOrderProductId"
													class="form-control" name="saleOrderProductId"> <input
													type="hidden" id="productId" class="form-control"
													name="productId"> <label class="mt-3"><h5>Mức
														độ hài lòng</h5></label>
												<div class="rate d-flex flex-row-reverse">
													<input type="radio" id="star5" name="rate" value="5" /> <label
														for="star5" title="text">5 stars</label> <input
														type="radio" id="star4" name="rate" value="4" /> <label
														for="star4" title="text">4 stars</label> <input
														type="radio" id="star3" name="rate" value="3" /> <label
														for="star3" title="text">3 stars</label> <input
														type="radio" id="star2" name="rate" value="2" /> <label
														for="star2" title="text">2 stars</label> <input
														type="radio" id="star1" name="rate" value="1" /> <label
														for="star1" title="text">1 star</label>
												</div>
												<label class="mt-1"><h5>Nội dung</h5></label>
												<textarea name="feedback" id="" cols="30" rows="5"
													class="form-control"></textarea>
												<div class="modal-footer ">
													<button type="button" class="btn btn-secondary"
														data-dismiss="modal">Hủy</button>
													<button type="submit" class="btn btn-primary">Gửi</button>
												</div>
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>

		</div>
	</main>

	<!--Footer-->
	<jsp:include page="/WEB-INF/views/customer/layout/footer.jsp"></jsp:include>

	<script type="text/javascript">
	if(${error!=null}){
		alert("Vui lòng kiểm tra lại thông tin");
	}
	if(${success!=null}){
		alert("Cập nhật thông tin thành công");
	}

	function send_feedback(saleOrderProductId,productId) {
		$('#saleOrderProductId').val(saleOrderProductId);
		$('#productId').val(productId);
	}
	</script>
	<script src="${base}/js/script.js"></script>

</body>

</html>