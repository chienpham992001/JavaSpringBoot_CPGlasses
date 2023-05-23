<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="col-sm-6 clearfix">
	<div class="user-profile pull-right">
		<img class="avatar user-thumb" src="${base}/img/author/avatar.png"
			alt="avatar">
		<h4 class="user-name dropdown-toggle" data-toggle="dropdown">
			${userLogined.fullname } <i class="bi bi-caret-down-fill"></i>
		</h4>
		<div class="dropdown-menu">
			<c:forEach items="${userLogined.roles }" var="role">

				<c:if test="${role.name == 'EMPLOYEE'}">
					<a class="dropdown-item"
						href="${base }/admin/employee/edit_profile?id=${userLogined.id}">Trang
						cá nhân</a>
				</c:if>
			</c:forEach>
			<a class="dropdown-item" href="/logout">Đăng xuất</a>
		</div>

	</div>
</div>