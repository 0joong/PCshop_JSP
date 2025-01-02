<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/include/header.jsp" />

<head>
    <title>사용자 관리</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.4.0/mdb.min.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.4.0/mdb.min.js"></script>
</head>

<div class="container mt-5">
    <h1 class="text-center mb-4">사용자 관리</h1>
    
    <!-- Error Message Alert -->
    <c:if test="${not empty resultMsg}">
        <script>
            alert('${resultMsg}');
        </script>
    </c:if>

    <!-- Search Bar -->
    <form action="userManagement.do" method="get" class="mb-4">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <input type="text" name="searchKeyword" class="form-control" placeholder="사용자 검색 (ID 또는 이름)" />
            </div>
            <div class="col-md-2">
                <button type="submit" class="btn btn-primary">검색</button>
            </div>
        </div>
    </form>

    <!-- User Table -->
    <div class="table-responsive">
        <table class="table table-hover align-middle">
            <thead class="table-dark">
                <tr>
                    <th>사용자 ID</th>
                    <th>이름</th>
                    <th>이메일</th>
                    <th>전화번호</th>
                    <th>가입일</th>
                    <th>관리</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${userList}">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.name}</td>
                        <td>${user.email}</td>
                        <td>${user.phone}</td>
                        <td>${user.regDate}</td>
                        <td>
<%--                             <a href="/PCshop/admin/userDetail.do?userId=${user.id}" class="btn btn-info btn-sm">상세보기</a> --%>
                            <a href="/PCshop/admin/deleteUser.do?userId=${user.id}" class="btn btn-danger btn-sm">삭제</a>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty userList}">
                    <tr>
                        <td colspan="6" class="text-center">사용자가 없습니다.</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="/include/footer.jsp" />
