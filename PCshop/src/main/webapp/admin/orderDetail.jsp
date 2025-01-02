<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
	<title>주문 상세 정보</title>
</head>
<jsp:include page="/include/header.jsp" />

<link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.4.0/mdb.min.css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.4.0/mdb.min.js"></script>

<div class="container mt-5">
    <h1 class="text-center mb-4">주문 상세 정보</h1>
    <div class="card">
        <div class="card-body">
            <h5 class="card-title">주문 번호: ${order.orderId}</h5>
            <p class="card-text">주문자 ID: ${order.userId}</p>
            <p class="card-text">결제 방법: ${order.paymentMethod}</p>
            <p class="card-text">주문 상태: <span class="badge bg-info">${order.status}</span></p>
            <p class="card-text">주문 생성일: ${order.createdAt}</p>
            <p class="card-text">주문 수정일: ${order.updatedAt}</p>
        </div>
    </div>

    <h2 class="mt-5">주문 상품 목록</h2>
    <table class="table table-hover align-middle">
        <thead class="table-dark">
            <tr>
                <th>상품 ID</th>
                <th>상품명</th>
                <th>수량</th>
                <th>가격</th>
                <th>송장 번호</th>
                <th>송장 번호 입력</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="item" items="${order.items}">
                <tr>
                    <td>${item.itemCd}</td>
                    <td>${item.name}</td>
                    <td>${item.qty}</td>
                    <td>${item.price}</td>
                    <td>${item.trackingNo}</td>
                    <td>
                        <form action="updateTrackingNo.do" method="post">
                            <input type="hidden" name="orderId" value="${order.orderId}" />
                            <input type="hidden" name="itemCd" value="${item.itemCd}" />
                            <input type="text" name="trackingNo" class="form-control" placeholder="송장 번호 입력" />
                            <button type="submit" class="btn btn-primary mt-2">저장</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <div class="mt-4">
        <form action="/PCshop/admin/updateOrderStatus.do" method="post">
            <input type="hidden" name="orderId" value="${order.orderId}" />
            <button type="submit" name="status" value="발송완료" class="btn btn-success">발송 완료</button>
            <button type="submit" name="status" value="취소" class="btn btn-danger">주문 취소</button>
        </form>
    </div>
</div>

<jsp:include page="/include/footer.jsp" />
