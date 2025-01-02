<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/include/header.jsp" />

<link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.4.0/mdb.min.css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.4.0/mdb.min.js"></script>

<div class="container mt-5">
    <h1 class="text-center mb-4">주문 관리</h1>
    <table class="table table-hover align-middle">
        <thead class="table-dark">
            <tr>
                <th>주문 번호</th>
                <th>사용자 ID</th>
                <th>주문 날짜</th>
                <th>주문 상태</th>
                <th>총 금액</th>
                <th>관리</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="order" items="${orderList}">
                <tr>
                    <td>${order.orderId}</td>
                    <td>${order.userId}</td>
                    <td>${order.createdAt}</td>
                    <td>
                        <span class="badge bg-success" style="min-width: 80px;">${order.status}</span>
                    </td>
                    <td>${order.totalPrice}</td>
                    <td>
                        <a href="/PCshop/admin/orderDetail.do?orderId=${order.orderId}" class="btn btn-primary btn-sm">상세보기</a>
                        <a href="/PCshop/admin/orderCancel.do?orderId=${order.orderId}" class="btn btn-danger btn-sm">취소</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<jsp:include page="/include/footer.jsp" />
