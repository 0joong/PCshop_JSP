<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>결제 - 배송지 정보</title>
<!-- MDB Bootstrap CSS -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.0/mdb.min.css"
	rel="stylesheet">
</head>
<body>
	<!-- Navbar -->
	<jsp:include page="/include/header.jsp" />

	<!-- Main Content -->
	<div class="container mt-5">
		<h2 class="text-center mb-4">결제 및 배송지 정보</h2>
		<input type="hidden" id="userId" value="${user.id}" />

		<!-- Selected Items -->
		<div class="card mb-4">
			<div class="card-body">
				<h4>선택한 상품 목록</h4>
				<ul class="list-group">
					<c:forEach var="item" items="${cartItems}">
						<li
							class="list-group-item d-flex justify-content-between align-items-center cart-item"
							data-name="${item.name}" data-quantity="${item.quantity}"
							data-price="${item.price}">${item.name}- ${item.quantity}개 <span>${item.price * item.quantity}
								원</span>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<!-- Shipping Address -->
		<div class="card mb-4">
			<div class="card-body">
				<h4>배송지 정보</h4>
				<div id="addressList">
					<c:choose>
						<c:when test="${not empty userAddresses}">
							<c:forEach var="address" items="${userAddresses}">
								<div class="form-check mb-3">
									<input class="form-check-input" type="radio"
										name="selectedAddress" id="address${address.addressId}"
										value="${address.addressId}"> <label
										class="form-check-label" for="address${address.addressId}">
										${address.roadAddr}, ${address.detailAddr} (${address.name},
										${address.phone}) </label>
								</div>
							</c:forEach>
							<button type="button" class="btn btn-secondary"
								onclick="showAddressForm()">+ 배송지 추가</button>
						</c:when>

						<c:otherwise>
							<jsp:include page="./addressForm.jsp" />
						</c:otherwise>
					</c:choose>

					<!-- Address Form (Hidden initially if addresses exist) -->
					<div id="newAddressForm" class="mt-4" style="display: none;">
						<jsp:include page="./addressForm.jsp" />
					</div>
				</div>
			</div>
		</div>

		<!-- Payment Method -->
		<div class="card mb-4">
			<div class="card-body">
				<h4>결제수단</h4>
				<div class="form-check">
					<input class="form-check-input" type="radio" name="paymentMethod"
						id="kakaoPay" value="KAKAO" checked> <label
						class="form-check-label" for="kakaoPay"> 카카오페이 </label>
				</div>
			</div>
		</div>

		<!-- Confirm Button -->
		<div class="text-end">
			<button type="button" class="btn btn-primary"
				onclick="proceedToPayment()">결제하기</button>
		</div>
	</div>
	<!-- <div class="container mt-5"> 닫기 -->

	<!-- Footer -->
	<jsp:include page="/include/footer.jsp" />


	<!-- MDB Bootstrap JS -->
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.0/mdb.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script>
		function showAddressForm() {
			document.getElementById('newAddressForm').style.display = 'block';
		}

		function proceedToPayment() {
			const selectedAddress = document
					.querySelector('input[name="selectedAddress"]:checked');
			const selectedPaymentMethod = document
					.querySelector('input[name="paymentMethod"]:checked');

			if (!selectedAddress) {
				alert('배송지를 선택해주세요.');
				return;
			}

			if (!selectedPaymentMethod) {
				alert('결제수단을 선택해주세요.');
				return;
			}
			
			// 사용자 ID 가져오기
		    const userId = document.getElementById('userId').value;

			// 선택한 상품 정보 가져오기
			const cartItems = Array.from(document
					.querySelectorAll('.cart-item'));
			if (cartItems.length === 0) {
				alert('장바구니가 비어 있습니다.');
				return;
			}

			const firstItemName = cartItems[0].dataset.name; // 첫 번째 상품 이름
			const itemCount = cartItems.length; // 총 선택한 상품 수
			const itemNames = itemCount > 1 ? `${"${firstItemName}"} 외 ${"${itemCount - 1}"}`
					: firstItemName;

			// 결제 데이터 구성
			const paymentData = {
				userId : userId,
				addressId : selectedAddress.value,
				paymentMethod : selectedPaymentMethod.value,
				totalPrice : calculateTotalPrice(),
				itemNames : itemNames
			};

			$.ajax({
				url : '/PCshop/payment/kakaoPayReady.do', // 서버에서 처리할 URL
				type : 'POST',
				contentType : 'application/json',
				data : JSON.stringify(paymentData),
				success : function(response) {
					if (response.next_redirect_pc_url) {
						window.location.href = response.next_redirect_pc_url; // 카카오페이 결제 페이지로 이동
					} else {
						alert('결제 준비 중 오류가 발생했습니다.');
					}
				},
				error : function(xhr, status, error) {
					console.error("결제 준비 중 오류:", error);
					alert('결제 준비 중 서버 오류가 발생했습니다.');
				}
			});
		}

		function calculateTotalPrice() {
			let totalPrice = 0;
			document.querySelectorAll('.list-group-item span').forEach(
					function(priceSpan) {
						const priceText = priceSpan.textContent
								.replace('원', '').replace(',', '').trim();
						totalPrice += parseInt(priceText, 10);
					});
			return totalPrice;
		}
	</script>
</body>
</html>
