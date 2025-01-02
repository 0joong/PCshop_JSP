<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link rel="icon" href="/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>결제 성공</title>
<!-- MDB Bootstrap CSS -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.0/mdb.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container mt-5">
		<h2 class="text-center">결제가 성공적으로 완료되었습니다!</h2>
		<div class="card mt-4">
			<div class="card-body">
				<h4>결제 상세 정보</h4>
				<p>
					<strong>상품명:</strong> ${approvalVO.itemName}
				</p>
				<p>
 					<strong>결제 금액:</strong> ${approvalVO.amount.total} 원
				</p>
<!-- 				<p> -->
<%-- 					<strong>주문 번호:</strong> ${order.orderId} --%>
<!-- 				</p> -->
				<p>
					<strong>거래 ID:</strong> ${approvalVO.tid}
				</p>
			</div>
		</div>
		<div class="text-center mt-4">
			<a href="/PCshop/index.jsp" class="btn btn-primary">홈으로 이동</a>
		</div>
	</div>
</body>
</html>
