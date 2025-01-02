<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" href="/PCshop/logo.png" type="image/x-icon">
<link rel="shortcut icon" href="/PCshop/logo.png" type="image/x-icon">
<title>폴리PC</title>
<!-- MDB Bootstrap CSS -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.0/mdb.min.css"
	rel="stylesheet">
<%

%>
<style>
.card-img-top {
	width: 100%;
	height: 250px;
	object-fit: cover;
}
</style>
<script>
    window.onload = function() {
        <%String errMsg = (String) request.getAttribute("errMsg");
if (errMsg == null) {
	errMsg = (String) session.getAttribute("errMsg");
}%>
        var errMsg = "<%=errMsg != null ? errMsg : ""%>
	";

		if (errMsg) {
			alert(errMsg);
		}
	};
</script>

</head>
<body>
	<jsp:include page="./include/header.jsp" />

	<c:if test="${not empty param.resultMsg}">
		<script>
        	alert('${param.resultMsg}');
   		</script>
	</c:if>


	<!-- Main Banner -->
	<div class="p-5 text-center bg-image"
		style="background-image: url('https://pcshop1.s3.ap-northeast-2.amazonaws.com/formd.jpg'); height: 400px;">
		<div class="mask" style="background-color: rgba(0, 0, 0, 0.6);">
			<div class="d-flex justify-content-center align-items-center h-100">
				<div class="text-white">
					<h1 class="mb-3">폴리PC에 어서오세요</h1>
					<h4 class="mb-3">조립pc</h4>
					<a class="btn btn-outline-light btn-lg" href="#products"
						role="button">Shop Now</a>
				</div>
			</div>
		</div>
	</div>

	<!-- Products Section -->
	<section class="container my-5" id="products">
		<h2 class="text-center mb-4">신상품</h2>
		<div class="row">
			<!-- Product Card -->
			<div class="col-lg-4 col-md-6 mb-4">
				<div class="card">
					<div class="bg-image hover-zoom">
						<img
							src="https://pcshop1.s3.ap-northeast-2.amazonaws.com/intel_ultra9.png"
							class="card-img-top" alt="intel_ultra9_285k" />
					</div>
					<div class="card-body">
						<h5 class="card-title">인텔 울트라9 시리즈2 285K</h5>
						<p class="card-text">인텔의 고성능 CPU</p>
						<a href="#" class="btn btn-primary">상세정보</a>
					</div>
				</div>
			</div>
			<!-- More Product Cards -->
			<div class="col-lg-4 col-md-6 mb-4">
				<div class="card">
					<div class="bg-image hover-zoom">
						<img
							src="https://pcshop1.s3.ap-northeast-2.amazonaws.com/amd_ryzen_9600x.jpg"
							class="card-img-top" alt="Mechanical Keyboard" />
					</div>
					<div class="card-body">
						<h5 class="card-title">AMD 라이젠 6세대 9600X</h5>
						<p class="card-text">AMD의 메인스트림 CPU</p>
						<a href="#" class="btn btn-primary">상세보기</a>
					</div>
				</div>
			</div>
			<div class="col-lg-4 col-md-6 mb-4">
				<div class="card">
					<div class="bg-image hover-zoom">
						<img
							src="https://pcshop1.s3.ap-northeast-2.amazonaws.com/msi_mag_z890.jpg"
							class="card-img-top" alt="Graphics Card" />
					</div>
					<div class="card-body">
						<h5 class="card-title">MSI MAG Z890</h5>
						<p class="card-text">인텔 울트라 시리즈 완벽 지원 메인보드</p>
						<a href="#" class="btn btn-primary">상세보기</a>
					</div>
				</div>
			</div>
		</div>
	</section>

	<jsp:include page="./include/footer.jsp" />
	<!-- MDB Bootstrap JS -->
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.0/mdb.min.js"></script>
</body>
</html>
