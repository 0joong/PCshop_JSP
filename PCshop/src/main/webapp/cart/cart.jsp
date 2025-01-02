<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>장바구니</title>
<!-- MDB Bootstrap CSS -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.0/mdb.min.css"
	rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
	rel="stylesheet">
</head>
<body>
	<!-- Navbar -->
	<jsp:include page="/include/header.jsp" />

	<!-- Main Content -->
	<div class="container mt-5">
		<h2 class="text-center mb-4">장바구니</h2>

		<!-- Cart Table -->
		<div class="card">
			<div class="card-body">
				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>상품명</th>
								<th>카테고리</th>
								<th>가격</th>
								<th>수량</th>
								<th>총합계</th>
								<th>삭제</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${cartItems}">
								<tr>
									<td>${item.name}</td>
									<td>${item.category}</td>
									<td>${item.price}원</td>
									<td>
										<div class="input-group quantity-group">
											<button class="btn btn-outline-secondary btn-sm"
												type="button"
												onclick="updateQuantity('${item.itemCd}', ${item.quantity - 1})">
												<i class="fas fa-minus"></i>
											</button>
											<input type="text"
												class="form-control text-center quantity-input"
												value="${item.quantity}" readonly />
											<button class="btn btn-outline-secondary btn-sm"
												type="button"
												onclick="updateQuantity('${item.itemCd}', ${item.quantity + 1})">
												<i class="fas fa-plus"></i>
											</button>
										</div>
									</td>
									<td>${item.price * item.quantity}원</td>
									<td>
										<form action="/PCshop/cart/deleteItem.do" method="post">
											<input type="hidden" name="itemCd" value="${item.itemCd}" />
											<button type="submit" class="btn btn-danger btn-sm">삭제</button>
										</form>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

				<!-- Cart Total & Checkout Button -->
				<div class="d-flex justify-content-between align-items-center mt-4">
					<h4 class="mb-0">
						총 합계:<c:out value="${cartTotal}" />원
					</h4>
					<a href="/PCshop/order/checkout.do" class="btn btn-primary">결제하기</a>
				</div>
			</div>
		</div>
	</div>

	<!-- Footer -->
	<jsp:include page="/include/footer.jsp" />

	<!-- MDB Bootstrap JS -->
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.0/mdb.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script>
    function updateQuantity(itemCd, newQuantity) {
    	console.log(itemCd);
    	console.log(newQuantity);
      // 수량이 1 이하로 내려가지 않도록 처리
      if (newQuantity < 1) {
        alert("수량은 1 이상이어야 합니다.");
        return;
      }

      $.ajax({
        url: '/PCshop/cart/updateQuantity.do',
        type: 'POST',
        data: { itemCd: itemCd, quantity: newQuantity },
        success: function(response) {
            console.log("서버 응답:", response); // 서버에서 반환된 응답을 콘솔에 출력
            if (response.trim() === 'success') {
              location.reload(); // 페이지를 새로고침하여 업데이트된 수량 반영
            } else {
              alert("수량 업데이트 중 오류가 발생했습니다.");
            }
        },
        error: function() {
          alert("수량 업데이트 중 오류가 발생했습니다.");
        }
      });
    }
  </script>
</body>
</html>
