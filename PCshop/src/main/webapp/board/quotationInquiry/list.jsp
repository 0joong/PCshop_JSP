<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>문의 목록</title>
    <!-- MDB Bootstrap CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.0/mdb.min.css" rel="stylesheet">
</head>
<body>
    <!-- Header -->
    <jsp:include page="/include/header.jsp" />

    <div class="container mt-5">
        <h1 class="text-center mb-4">문의 목록</h1>

        <!-- 글쓰기 버튼 -->
        <div class="d-flex justify-content-end mb-3">
            <a href="write.do" class="btn btn-primary">글쓰기</a>
        </div>

        <!-- 문의 목록 테이블 -->
        <table class="table table-hover">
            <thead class="table-dark">
                <tr>
                    <th scope="col">번호</th>
                    <th scope="col">제목</th>
                    <th scope="col">작성자</th>
                    <th scope="col">작성일</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="inquiry" items="${inquiryList}">
                    <tr>
                        <td>${inquiry.postId}</td>
                        <td>
                            <a href="detail.do?postId=${inquiry.postId}">
                                ${inquiry.title}
                            </a>
                        </td>
                        <td>${inquiry.userId}</td>
                        <td>${inquiry.createdAt}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- Footer -->
    <jsp:include page="/include/footer.jsp" />

    <!-- MDB Bootstrap JS -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.0/mdb.min.js"></script>
</body>
</html>
