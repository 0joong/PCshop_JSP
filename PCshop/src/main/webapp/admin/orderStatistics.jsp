<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/include/header.jsp" />

<head>
	<title>주문 통계</title>
	<link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.4.0/mdb.min.css" rel="stylesheet">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.4.0/mdb.min.js"></script>
</head>
<div class="container mt-5">
    <h1 class="text-center mb-4">주문 통계</h1>

    <!-- Error Message Alert -->
    <c:if test="${not empty errMsg}">
        <script>
            alert('${errMsg}');
        </script>
    </c:if>

    <!-- Category and Date Selection -->
    <form action="orderStatistics.do" method="get" class="mb-4">
        <div class="row justify-content-center">
            <div class="col-md-3">
                <select name="category" class="form-select">
                    <option value="cpu">CPU</option>
                    <option value="mainboard">Mainboard</option>
                </select>
            </div>
            <div class="col-md-3">
                <input type="date" id="startDate" name="startDate" class="form-control" placeholder="시작 날짜" required />
            </div>
            <div class="col-md-3">
                <input type="date" id="endDate" name="endDate" class="form-control" placeholder="종료 날짜" required />
            </div>
            <div class="col-md-2">
                <button type="submit" class="btn btn-primary">조회</button>
            </div>
        </div>
        <div class="row justify-content-center mt-3">
            <div class="col-md-2">
                <button type="button" class="btn btn-outline-secondary" onclick="setDateRange(1)">1일</button>
            </div>
            <div class="col-md-2">
                <button type="button" class="btn btn-outline-secondary" onclick="setDateRange(7)">1주일</button>
            </div>
            <div class="col-md-2">
                <button type="button" class="btn btn-outline-secondary" onclick="setDateRange(30)">1달</button>
            </div>
            <div class="col-md-2">
                <button type="button" class="btn btn-outline-secondary" onclick="setDateRange(365)">1년</button>
            </div>
        </div>
    </form>

    <!-- Sales Statistics Table -->
    <div class="table-responsive">
        <c:choose>
            <c:when test="${not empty salesList && salesList.size() > 0}">
                <table class="table table-hover align-middle">
                    <thead class="table-dark">
                        <tr>
                            <th>상품명</th>
                            <th>판매 수량</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${salesList}">
                            <tr>
                                <td>${item.itemNm}</td>
                                <td>${item.sumQty}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p class="text-center mt-4">기간 내 판매된 상품이 없습니다.</p>
            </c:otherwise>
        </c:choose>
    </div>
</div>

<jsp:include page="/include/footer.jsp" />

<script>
    function setDateRange(days) {
        const today = new Date();
        const startDate = new Date();
        startDate.setDate(today.getDate() - days);

        document.getElementById('startDate').value = startDate.toISOString().split('T')[0];
        document.getElementById('endDate').value = today.toISOString().split('T')[0];
    }
</script>
