<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<jsp:include page="/WEB-INF/views/common/variables.jsp"></jsp:include>

<title>Management Page - CP Glasses</title>
<link rel="shortcut icon" type="image/png"
	href="${base}/img/icon/favicon.ico">
<link rel="stylesheet" href="${base}/css/bootstrap.min.css">
<link rel="stylesheet" href="${base}/css/font-awesome.min.css">
<link rel="stylesheet" href="${base}/css/themify-icons.css">
<link rel="stylesheet" href="${base}/css/metisMenu.css">
<link rel="stylesheet" href="${base}/css/owl.carousel.min.css">
<link rel="stylesheet" href="${base}/css/slicknav.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
<!-- amcharts css -->
<link rel="stylesheet"
	href="https://www.amcharts.com/lib/3/plugins/export/export.css"
	type="text/css" media="all" />
<!-- Start datatable css -->
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.18/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/responsive/2.2.3/css/responsive.bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/responsive/2.2.3/css/responsive.jqueryui.min.css">
<!-- style css -->
<link rel="stylesheet" href="${base}/css/typography.css">
<link rel="stylesheet" href="${base}/css/default-css.css">
<link rel="stylesheet" href="${base}/css/styles.css">
<link rel="stylesheet" href="${base}/css/responsive.css">
<!-- modernizr css -->
<script src="${base}/js/vendor/modernizr-2.8.3.min.js"></script>

<!-- Sumernote -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<!--Simple page  -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/simplePagination.js/1.4/simplePagination.css" />
</head>

<body>
	<!--[if lt IE 8]>
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->
	<!-- preloader area start -->
	<!-- <div id="preloader">
		<div class="loader"></div>
	</div> -->
	<!-- preloader area end -->
	<!-- page container area start -->
	<div class="page-container">
		<!-- sidebar menu area start -->
		<jsp:include page="/WEB-INF/views/administrator/layout/menu.jsp"></jsp:include>
		<!-- sidebar menu area end -->

		<!-- main content area start -->
		<div class="main-content">
			<!-- header area start -->

			<jsp:include page="/WEB-INF/views/administrator/layout/header.jsp"></jsp:include>
			<!-- header area end -->
			<!-- page title area start -->
			<div class="page-title-area">
				<div class="row align-items-center">
					<div class="col-sm-6">
						<div class="breadcrumbs-area clearfix">
							<h4 class="page-title pull-left">Quản lý người quản lý</h4>
							<ul class="breadcrumbs pull-left">
								<li><a href="index.html">Trang chủ</a></li>
								<li><span>Quản lý</span></li>
							</ul>
						</div>
					</div>
					<jsp:include
						page="/WEB-INF/views/administrator/layout/user_profile.jsp"></jsp:include>
				</div>
			</div>
			<!-- page title area end -->
			<div class="main-content-inner">
				<div class="row">
					<!-- Primary table start -->
					<div class="col-12 mt-3">
						<div class="card">
							<div class="card-body">
								<h1 class="header-title">Quản lý</h1>

								<!-- tìm kiếm sản phẩm trên danh sách -->
								<form action="${base}/admin/admins/manage_admins"
									method="get">
									<div
										class="d-flex flex-row mb-3 search-form justify-content-between">
										<a href="/admin/admins/addOrEdit_admin">
											<button type="button" class="btn btn-rounded btn-primary ">Thêm
												người quản lý</button>
										</a>
										<div class="d-flex flex-row ">
											<input hidden="hidden" id="page" name="page"
												class="form-control">

											<!-- tìm kiếm theo tên sản phẩm -->
											<input type="text" id="keyword" name="keyword"
												class="form-control" placeholder="Search"
												style="margin-right: 5px;" value="${searchModel.keyword }">

											<!-- <select class="form-control" name="keyoption" id="keyoption"
												style="margin-right: 5px;">
												<option value="">All</option>
												<option value="16">Admin</option>
												<option value="17">Khách hàng</option>
											</select> -->

											<button type="submit" id="btnSearch" name="btnSearch"
												value="Search" class="btn btn-danger">Seach</button>
										</div>

									</div>
									<div class="table-responsive">
										<table class="table text-center">
											<thead class="text-uppercase bg-primary">
												<tr class="text-white">
													<th>STT</th>
													<th>Mã người dùng</th>
													<th>Họ và tên</th>
													<th>Tên tài khoản</th>
													<!-- <th>Mật khẩu</th> -->
													<th>Số điện thoại</th>
													<th>Trạng thái</th>
													<th>Hoạt động</th>
												</tr>
											</thead>

											<tbody style="line-height: 100px !important;">
												<c:set var="stt" value="1"></c:set>
												<c:forEach items="${admins.data}" var="admins"
													varStatus="loop">
													<tr>
														<td>${stt}</td>
														<td>${admins.id}</td>
														<td>${admins.fullname}</td>
														<td>${admins.username}</td>
														<%-- <td>${admins.password}</td> --%>
														<td>${admins.phone}</td>
														<c:choose>
															<c:when test="${admins.status==false}">
																<td><span class="false_status">Vô hiệu hóa</span></td>

																<td><a
																	href="${base }/admin/edit_user?id=${admins.id}"><i
																		class="bi bi-pencil-square "></i> </a> <a> <i
																		class="bi bi-person-check-fill" role="button"
																		data-toggle="modal"
																		data-target="#modalActive-${admins.id}"></i>
																</a></td>
															</c:when>
															<c:otherwise>
																<td><span class="wait_status">Kích hoạt</span></td>

																<td><a
																	href="${base }/admin/edit_user?id=${admins.id}"><i
																		class="bi bi-pencil-square "></i> </a> <a> <i
																		class="bi bi-trash" role="button" data-toggle="modal"
																		data-target="#modalDelete-${admins.id}"></i>
																</a></td>
															</c:otherwise>
														</c:choose>



														<c:set var="stt" value="${stt+1}"></c:set>
													</tr>
													<!--Modal xóa  -->
													<div class="modal fade" id="modalDelete-${admins.id}">
														<div class="modal-dialog modal-dialog-centered"
															role="document">
															<div class="modal-content">
																<div class="modal-header">
																	<h5 class="modal-title">Vô hiệu hóa</h5>
																	<button type="button" class="close"
																		data-dismiss="modal">
																		<span>&times;</span>
																	</button>
																</div>
																<div class="modal-body">
																	<p>
																		Bạn có chắc chắn muốn vô hiệu hóa tài khoản <span
																			style="color: red">${admins.username}</span> khỏi
																		hệ thống
																	</p>
																</div>

																<div class="modal-footer">
																	<button type="button" class="btn btn-secondary"
																		data-dismiss="modal">Hủy</button>
																	<button type="button" class="btn btn-primary"
																		onclick="disableAdmin(${admins.id})">Vô
																		hiệu hóa</button>
																</div>
															</div>
														</div>
													</div>
													<!--Modal kích hoạt  -->
													<div class="modal fade" id="modalActive-${admins.id}">
														<div class="modal-dialog modal-dialog-centered"
															role="document">
															<div class="modal-content">
																<div class="modal-header">
																	<h5 class="modal-title">Kích hoạt</h5>
																	<button type="button" class="close"
																		data-dismiss="modal">
																		<span>&times;</span>
																	</button>
																</div>
																<div class="modal-body">
																	<p>
																		Bạn có chắc chắn muốn kích hoạt tài khoản <span
																			style="color: red">${admins.username}</span>
																	</p>
																</div>

																<div class="modal-footer">
																	<button type="button" class="btn btn-secondary"
																		data-dismiss="modal">Hủy</button>
																	<button type="button" class="btn btn-primary"
																		onclick="activeadmins(${admins.id})">Kích
																		hoạt</button>
																</div>
															</div>
														</div>
													</div>
												</c:forEach>

											</tbody>
										</table>

										<!--Phân trang-->

									</div>
								</form>
								<div class="row">
									<div class="col-12 d-flex justify-content-center">
										<div id="paging"></div>
									</div>
								</div>

							</div>
							<!-- Primary table end -->
						</div>
					</div>
				</div>
				<!-- main content area end -->
				<!-- footer area start-->
			</div>
			<jsp:include page="/WEB-INF/views/administrator/layout/footer.jsp"></jsp:include>
			<!-- footer area end-->
		</div>
		<!-- page container area end -->

		<!-- sidebar menu area end -->


		<script type="text/javascript">
	 function disableAdmin(adminId) {
			var data = {
					id : adminId,
			};

			jQuery.ajax({
				url: "${base}/admin/admins/manage_admins/delete", 	   	   
				type: "delete",					 
				contentType: "application/json",   
				data: JSON.stringify(data), 

				dataType: "json", 				   
				success: function(jsonResult) {
					location.reload();
				},
				error: function(jqXhr, textStatus, errorMessage) { 
					alert("error");
				}
			});
		}
	 function activeadmins(adminId) {
			var data = {
					id : adminId,
			};

			jQuery.ajax({
				url: "${base}/admin/admins/manage_admins/active", 	   	   
				type: "post",					 
				contentType: "application/json",   
				data: JSON.stringify(data), 

				dataType: "json", 				   
				success: function(jsonResult) {
					location.reload();
				},
				error: function(jqXhr, textStatus, errorMessage) { 
					alert("error");
				}
			});
		}
	
		$(document).ready(function() {
			
			
			$("#paging").pagination({
				currentPage:${admins.currentPage}, //trang hiện tại
				items : ${admins.totalItems},
				itemsOnPage : ${admins.sizeOfPage},
				cssStyle : 'compact-theme',
				onPageClick: function(pageNumber,event){
					$('#page').val(pageNumber);
					$('#btnSearch').trigger('click');
				}
			});
		});
	</script>
		<jsp:include page="/WEB-INF/views/administrator/layout/js.jsp"></jsp:include>
</body>
</html>