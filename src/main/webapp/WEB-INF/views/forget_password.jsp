<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Quên mật khẩu | CP Glasses</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" />
<link rel="stylesheet" href="../css/style.css">
<link href="../css/bootstrap.min.css" rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Jost:ital,wght@0,400;0,500;0,600;0,700;1,200&display=swap"
	rel="stylesheet">
</head>

<body class="lg-rg">
	<div class="container">
		<div class="row">
			<div class="card-lg-rg">
				<div class=" card-img-left col-lg-5">
					<img src="${base }/img/banner/banner_login.jpg" alt="">
				</div>
				<div class="card-body col-lg-7 col-12">
					<h3>Quên mật khẩu</h3>
					<form method="POST" action="${base}/forget_password"
						class="form-signin">

						<div class="form-floating">
							<input type="text" name="username" class="form-control"
								id="floatingInputEmail" placeholder="name@example.com">
							<label for="floatingInputEmail">Tài khoản</label>
						</div>
						<c:if test="${not empty notExist}">
							<div class="alert alert-danger" role="alert">Tài khoản
								không tồn tại trong hệ thống</div>
						</c:if>

						<div class="d-grid">
							<button class="btn-register btn-login " type="submit">Xác
								nhận</button>
						</div>
						<a class="rotate-login" href="${base}/login"> Đăng nhập </a> <br>
						<a class="rotate-login" href="${base }/register"> Đăng ký tài
							khoản mới </a>


					</form>
				</div>
			</div>
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script type="text/javascript">
		if(${success!=null}){
			alert("Mật khẩu được cập nhật thành công.\nVui lòng kiểm tra email của bạn.")
			window.location="${base}/login";
		}
	</script>
</body>
</html>