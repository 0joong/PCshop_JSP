<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>주문 상세</title>
    <!-- MDB Bootstrap CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.0/mdb.min.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="/include/header.jsp" />

    <div class="container mt-5">
        <h2 class="text-center mb-4">주문 상세 정보</h2>

        <!-- 주문 정보 -->
        <div class="card mb-4">
            <div class="card-body">
                <h4>주문 정보</h4>
                <ul class="list-group">
                    <li class="list-group-item"><strong>주문 번호:</strong> ${order.orderId}</li>
                    <li class="list-group-item"><strong>결제 ID:</strong> ${order.tid}</li>
                    <li class="list-group-item"><strong>총 금액:</strong> ${order.totalPrice}원</li>
                    <li class="list-group-item"><strong>결제 수단:</strong> ${order.paymentMethod}</li>
                    <li class="list-group-item"><strong>주문 상태:</strong> ${order.status}</li>
                    <li class="list-group-item"><strong>주문일:</strong> ${order.createdAt}</li>
                </ul>
            </div>
        </div>

        <!-- 배송 정보 -->
        <div class="card mb-4">
            <div class="card-body">
                <h4>배송 정보</h4>
                <ul class="list-group">
                    <li class="list-group-item"><strong>수신인:</strong> ${order.address.name}</li>
                    <li class="list-group-item"><strong>주소:</strong> ${order.address.roadAddr}, ${order.address.detailAddr}</li>
                    <li class="list-group-item"><strong>우편번호:</strong> ${order.address.postalCode}</li>
                    <li class="list-group-item"><strong>전화번호:</strong> ${order.address.phone}</li>
                </ul>
            </div>
        </div>

        <!-- 주문 상품 목록 -->
        <div class="card mb-4">
            <div class="card-body">
                <h4>주문 상품 목록</h4>
                <table class="table">
                    <thead>
                        <tr>
                            <th>상품 코드</th>
                            <th>상품 이름</th>
                            <th>수량</th>
                            <th>단가</th>
                            <th>총 금액</th>
                            <th>배송 상태</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${order.items}">
                            <tr>
                                <td>${item.itemCd}</td>
                                <td>${item.name}</td> 
                                <td>${item.qty}</td>
                                <td>${item.price}원</td>
                                <td>${item.price * item.qty}원</td>
                                <td>${item.shippingStatus}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <jsp:include page="/include/footer.jsp" />

    <!-- MDB Bootstrap JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.0/mdb.min.js"></script>
</body>
</html>
