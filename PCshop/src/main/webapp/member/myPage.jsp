<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>마이페이지</title>
<!-- MDB Bootstrap CSS -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.0/mdb.min.css"
	rel="stylesheet">
	<script>
	document.addEventListener("DOMContentLoaded", function () {
        const activeTab = "${sessionScope.activeTab}";
        if (activeTab) {
            const tabElement = document.querySelector(`a[href="#${activeTab}"]`);
            if (tabElement) {
                new bootstrap.Tab(tabElement).show();
            }
        }
    });
	</script>
</head>
<body>
	<jsp:include page="/include/header.jsp" />

	<c:if test="${not empty errMsg}">
		<script>
			alert('${errMsg}');
		</script>
		<c:remove var="errMsg" scope="session" />
	</c:if>


	<c:if test="${not empty resultMsg}">
		<script>
			alert('${resultMsg}')
		</script>
		<c:remove var="resultMsg" scope="session" />
	</c:if>

	<div class="container mt-5">
		<h2 class="text-center mb-4">마이페이지</h2>

		<!-- 탭 메뉴 -->
		<ul class="nav nav-tabs" id="myPageTabs" role="tablist">
			<li class="nav-item" role="presentation">
				<button class="nav-link active" id="myInfo-tab"
					data-mdb-toggle="tab" data-mdb-target="#myInfo" type="button"
					role="tab" aria-controls="myInfo" aria-selected="true">내
					정보</button>
			</li>
			<li class="nav-item" role="presentation">
				<button class="nav-link" id="myOrders-tab" data-mdb-toggle="tab"
					data-mdb-target="#myOrders" type="button" role="tab"
					aria-controls="myOrders" aria-selected="false">주문목록</button>
			</li>
			<li class="nav-item" role="presentation">
				<button class="nav-link text-danger" id="deleteAccount-tab"
					data-mdb-toggle="tab" data-mdb-target="#deleteAccount"
					type="button" role="tab" aria-controls="deleteAccount"
					aria-selected="false">회원탈퇴</button>
			</li>
		</ul>

		<!-- 탭 콘텐츠 -->
		<div class="tab-content mt-4">
			<!-- 내 정보 -->
			<div class="tab-pane fade show active" id="myInfo" role="tabpanel"
				aria-labelledby="myInfo-tab">
				<h3>내 정보</h3>
				<form action="updateUserInfo.do" method="post">
					<div class="mb-3">
						<label for="username" class="form-label">아이디</label> <input
							type="text" id="username" name="username"
							class="form-control bg-light" value="${member.id}" readonly />
					</div>
					<div class="mb-3">
						<label for="role" class="form-label">역할</label> <input type="text"
							id="role" name="role" class="form-control bg-light"
							value="${member.role}" readonly />
					</div>
					<div class="mb-3">
						<label for="regDate" class="form-label">가입일</label> <input
							type="text" id="regDate" name="regDate"
							class="form-control bg-light" value="${member.regDate}" readonly />
					</div>
					<div class="mb-3">
						<label for="currentPassword" class="form-label">현재 비밀번호</label> <input
							type="password" id="currentPassword" name="currentPassword"
							class="form-control" placeholder="현재 비밀번호를 입력하세요" required />
					</div>
					<div class="mb-3">
						<label for="newPassword" class="form-label">새 비밀번호</label> <input
							type="password" id="newPassword" name="newPassword"
							class="form-control" placeholder="새 비밀번호를 입력하세요" />
					</div>
					<div class="mb-3">
						<label for="confirmPassword" class="form-label">비밀번호 확인</label> <input
							type="password" id="confirmPassword" name="confirmPassword"
							class="form-control" placeholder="새 비밀번호를 다시 입력하세요" />
					</div>
					<div class="mb-3">
						<label for="email" class="form-label">이메일</label> <input
							type="email" id="email" name="email" class="form-control"
							value="${member.email}" required />
					</div>
					<div class="mb-3">
						<label for="phone" class="form-label">전화번호</label> <input
							type="text" id="phone" name="phone" class="form-control"
							value="${member.phone}" required />
					</div>
					<button type="submit" class="btn btn-primary">정보 수정</button>
				</form>
			</div>

			<!-- 주문목록 -->
			<div class="tab-pane fade" id="myOrders" role="tabpanel"
				aria-labelledby="myOrders-tab">
				<h3>주문목록</h3>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>주문번호</th>
							<th>날짜</th>
							<th>총 금액</th>
							<th>상태</th>
							<th>상세보기</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${not empty orderList}">
							<c:forEach var="order" items="${orderList}">
								<tr>
									<td>${order.orderId}</td>
									<td>${order.createdAt}</td>
									<td>${order.totalPrice}원</td>
									<td>${order.status}</td>
									<td><a
										href="/PCshop/order/detail.do?orderId=${order.orderId}"
										class="btn btn-sm btn-info">상세보기</a></td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty orderList}">
							<tr>
								<td colspan="5" class="text-center">주문 내역이 없습니다.</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>

			<!-- 회원탈퇴 -->
			<div class="tab-pane fade" id="deleteAccount" role="tabpanel"
				aria-labelledby="deleteAccount-tab">
				<h3 class="text-danger">회원탈퇴</h3>
				<p class="text-warning">회원 탈퇴를 진행하시기 전에 아래 사항을 반드시 확인해주세요:</p>
				<ul>
					<li>탈퇴 시 모든 데이터가 삭제되며 복구가 불가능합니다.</li>
					<li>남아 있는 결제 및 배송 정보는 모두 소멸됩니다.</li>
					<li>다시 가입하시더라도 이전 데이터를 복원할 수 없습니다.</li>
				</ul>
				<form action="/PCshop/member/deleteAccount.do" method="post">
					<div class="mb-3">
						<label for="password" class="form-label">비밀번호 확인</label> <input
							type="password" id="password" name="password"
							class="form-control" placeholder="비밀번호를 입력해주세요" required />
					</div>
					<div class="text-center mt-4">
						<button type="submit" class="btn btn-danger">회원 탈퇴</button>
						<a href="/index.jsp" class="btn btn-secondary mt-2">홈으로 가기</a>
					</div>
				</form>
			</div>
		</div>
	</div>

	<jsp:include page="/include/footer.jsp" />

	<!-- MDB Bootstrap JS -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.0/mdb.min.js"></script>
	<!-- jQuery (필요 시) -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</body>
</html>
