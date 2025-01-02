<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>고객센터 게시판</title>
    <!-- MDB Bootstrap CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.0/mdb.min.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="/include/header.jsp" />

    <div class="container mt-5">
        <h2 class="text-center">고객센터 게시판</h2>

        <!-- 검색 폼 -->
        <form action="list.do" method="get" class="d-flex mb-4">
            <input type="text" name="searchKeyword" class="form-control me-2" placeholder="검색어를 입력하세요" value="${param.searchKeyword}">
            <button type="submit" class="btn btn-primary">검색</button>
        </form>

        <!-- 게시글 목록 -->
        <table class="table table-hover">
            <thead class="table-light">
                <tr>
                    <th scope="col">번호</th>
                    <th scope="col">제목</th>
                    <th scope="col">작성자</th>
                    <th scope="col">작성일</th>
                    <th scope="col">조회수</th>
                </tr>
            </thead>
            <tbody>
                <c:if test="${not empty boardList}">
                    <c:forEach var="board" items="${boardList}">
                        <tr>
                            <td>${board.boardId}</td>
                            <td><a href="detail.do?boardId=${board.boardId}">${board.title}</a></td>
                            <td>${board.writer}</td>
                            <td>${board.createdAt}</td>
                            <td>${board.views}</td>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${empty boardList}">
                    <tr>
                        <td colspan="5" class="text-center">등록된 게시글이 없습니다.</td>
                    </tr>
                </c:if>
            </tbody>
        </table>

        <!-- 페이징 -->
        <c:if test="${not empty paging}">
            <nav aria-label="Page navigation">
                <ul class="pagination justify-content-center">
                    <li class="page-item ${paging.prevPage ? '' : 'disabled'}">
                        <a class="page-link" href="?page=${paging.currentPage - 1}&searchKeyword=${param.searchKeyword}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                    <c:forEach var="page" items="${paging.pageList}">
                        <li class="page-item ${page == paging.currentPage ? 'active' : ''}">
                            <a class="page-link" href="?page=${page}&searchKeyword=${param.searchKeyword}">${page}</a>
                        </li>
                    </c:forEach>
                    <li class="page-item ${paging.nextPage ? '' : 'disabled'}">
                        <a class="page-link" href="?page=${paging.currentPage + 1}&searchKeyword=${param.searchKeyword}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </ul>
            </nav>
        </c:if>
    </div>

    <jsp:include page="/include/footer.jsp" />

    <!-- MDB Bootstrap JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.0/mdb.min.js"></script>
</body>
</html>