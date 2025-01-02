<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>자유게시판</title>
    <!-- MDB Bootstrap CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.0/mdb.min.css" rel="stylesheet">
    <style>
        body {
            padding-top: 56px;
        }
        .page-item.active .page-link {
            background-color: #007bff;
            border-color: #007bff;
            color: white;
        }
        .page-item .page-link {
            color: #007bff;
        }
    </style>
</head>

<body>
    <!-- Navbar -->
    <jsp:include page="/include/header.jsp" />

    <!-- Main Content -->
    <div class="container mt-5">
        <h2 class="text-center mb-4">자유게시판</h2>

        <!-- 자유게시판 글쓰기 버튼 -->
        <div class="mb-3 text-end">
            <a href="write.jsp" class="btn btn-primary">글쓰기</a>
        </div>

        <!-- 게시판 목록 -->
        <div class="card">
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>번호</th>
                                <th>제목</th>
                                <th>작성자</th>
                                <th>작성일</th>
                                <th>조회수</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="post" items="${generalBoardList}">
                                <tr>
                                    <td>${post.postId}</td>
                                    <td><a href="detail.do?postId=${post.postId}">${post.title}</a></td>
                                    <td>${post.userId}</td>
                                    <td>${post.createdAt}</td>
                                    <td>${post.viewCnt}</td>
                                </tr>
                            </c:forEach>
                            <c:if test="${empty generalBoardList}">
                                <tr>
                                    <td colspan="5" class="text-center">게시글이 없습니다.</td>
                                </tr>
                            </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <!-- Pagination -->
        <nav aria-label="Page navigation" class="mt-4">
            <ul class="pagination justify-content-center">
                <!-- Previous Button -->
                <c:if test="${page > 1}">
                    <li class="page-item">
                        <a class="page-link" href="list.do?page=${page - 1}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:if>

                <!-- Page Numbers -->
                <c:forEach var="i" begin="1" end="${totalPages}">
                    <li class="page-item ${i == page ? 'active' : ''}">
                        <a class="page-link" href="list.do?page=${i}">${i}</a>
                    </li>
                </c:forEach>

                <!-- Next Button -->
                <c:if test="${page < totalPages}">
                    <li class="page-item">
                        <a class="page-link" href="list.do?page=${page + 1}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </div>

    <!-- MDB Bootstrap JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.0/mdb.min.js"></script>
    <jsp:include page="/include/footer.jsp" />
</body>
</html>
