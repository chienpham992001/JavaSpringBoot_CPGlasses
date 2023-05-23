<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<!-- amchart css -->
<link rel="stylesheet"
	href="https://www.amcharts.com/lib/3/plugins/export/export.css"
	type="text/css" media="all" />
<!-- others css -->
<link rel="stylesheet" href="${base}/css/typography.css">
<link rel="stylesheet" href="${base}/css/default-css.css">
<link rel="stylesheet" href="${base}/css/styles.css">
<link rel="stylesheet" href="${base}/css/responsive.css">
<!-- modernizr css -->
<script src="${base}/js/vendor/modernizr-2.8.3.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
</head>

<body>
	<!--[if lt IE 8]>
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->
	<!-- preloader area start -->
	<div id="preloader">
		<div class="loader"></div>
	</div>
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
							<h4 class="page-title pull-left">Thống kê</h4>
							<ul class="breadcrumbs pull-left">
								<li><a href="${base }">Trang chủ</a></li>
								<li><span>Thống kê</span></li>
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
					<!-- Đơn hàng -->
					<div class="col-lg-6 mt-5">
						<div class="card h-full">
							<div class="card-body">
								<h4 class="header-title">Thống kê số lượng đơn trong năm
									này</h4>
								<canvas id="seolinechart8" height="233"></canvas>
							</div>
						</div>
					</div>

					<div class="col-lg-6 mt-5">
						<div class="card">
							<div class="card-body">
								<h4 class="header-title">Thống kê doanh thu trong năm</h4>
								<div id="ambarchart2"></div>
							</div>
						</div>
					</div>

					<!-- Advertising area end -->


				</div>
				<!-- testimonial area end -->
			</div>
		</div>
	</div>
	<!-- main content area end -->
	<!-- footer area start-->
	<jsp:include page="/WEB-INF/views/administrator/layout/footer.jsp"></jsp:include>


	<jsp:include page="/WEB-INF/views/administrator/layout/js.jsp"></jsp:include>
	<script type="text/javascript">
	if ($('#ambarchart2').length) {
	    var chart = AmCharts.makeChart("ambarchart2", {
	        "type": "serial",
	        "addClassNames": true,
	        "theme": "light",
	        "autoMargins": false,
	        "marginLeft": 30,
	        "marginRight": 8,
	        "marginTop": 10,
	        "marginBottom": 26,
	        "balloon": {
	            "adjustBorderColor": false,
	            "horizontalPadding": 10,
	            "verticalPadding": 8,
	            "color": "#ffffff"
	        },

	        "dataProvider": [{
	            "month": 'Tháng 1',
	            "expenses": 21.1,
	            "color": "#7474f0"
	        }],
	        "valueAxes": [{
	            "axisAlpha": 0,
	            "position": "left"
	        }],
	        "startDuration": 1,
	        "graphs": [{
	            "alphaField": "alpha",
	            "balloonText": "<span style='font-size:12px;'>[[title]] in [[category]]:<br><span style='font-size:20px;'>[[value]]</span> [[additional]]</span>",
	            "fillAlphas": 1,
	            "fillColorsField": "color",
	            "title": "Income",
	            "type": "column",
	            "valueField": "income",
	            "dashLengthField": "dashLengthColumn"
	        }, {
	            "id": "graph2",
	            "balloonText": "<span style='font-size:12px;'>[[title]] in [[category]]:<br><span style='font-size:20px;'>[[value]]</span> [[additional]]</span>",
	            "bullet": "round",
	            "lineThickness": 3,
	            "bulletSize": 7,
	            "bulletBorderAlpha": 1,
	            "bulletColor": "#FFFFFF",
	            "lineColor": "#AA59FE",
	            "useLineColorForBulletBorder": true,
	            "bulletBorderThickness": 3,
	            "fillAlphas": 0,
	            "lineAlpha": 1,
	            "title": "Expenses",
	            "valueField": "expenses",
	            "dashLengthField": "dashLengthLine"
	        }],
	        "categoryField": "month",
	        "categoryAxis": {
	            "gridPosition": "start",
	            "axisAlpha": 0,
	            "tickLength": 0
	        },
	        "export": {
	            "enabled": false
	        }
	    });
		
		chart.dataProvider=[];
		
		for(var i = 1;i<=12;i++){
			let revenueByMonth={
		            "month": 'Tháng '+i,
		            "income": ${incomeArr}[i-1],
		            "expenses": ${incomeArr}[i-1],
		            "color": "#7474f0"
		        };
			chart.dataProvider.push(revenueByMonth);
		}
	}
	
	
		var ctx = document.getElementById("seolinechart8").getContext('2d');
	    var chart = new Chart(ctx, {
	        // The type of chart we want to create
	        type: 'doughnut',
	        // The data for our dataset
	        data: {
	            labels: ["Đã bán", "Đang giao", "Chờ xác nhận", "Hoàn đơn"],
	            datasets: [{
	                backgroundColor: [
	                    "#12C498",
	                    "#8919FE",
	                    "#F8CB3F",
	                    "#E36D68"
	                ],
	                borderColor: '#fff',
	                data: [${saleOrdersSuccess}, ${saleOrdersDelivering}, ${saleOrdersWaiting}, ${saleOrdersCancel}],
	            }]
	        },
	        // Configuration options go here
	        options: {
	            legend: {
	                display: true
	            },
	            animation: {
	                easing: "easeInOutBack"
	            }
	        }
	    });
	
    
	</script>
</body>
</html>