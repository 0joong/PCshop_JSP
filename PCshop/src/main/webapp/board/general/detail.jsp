<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시글 상세보기</title>
    <!-- MDB Bootstrap CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.0/mdb.min.css" rel="stylesheet">
    <style>
        body {
            padding-top: 56px;
        }
        .content {
            white-space: pre-line; /* 줄바꿈 및 공백 유지 */
        }
    </style>
</head>

<body>
    <!-- Navbar -->
    <jsp:include page="/include/header.jsp" />

    <!-- 게시글 상세 정보 -->
    <div class="container mt-5">
        <h2 class="text-center mb-4">${board.title}</h2>

        <div class="card mb-4">
            <div class="card-body">
                <!-- 작성자 정보 -->
                <div class="mb-3">
                    <span class="text-muted">작성자:</span> ${board.userId}
                </div>
                <!-- 작성일 -->
                <div class="mb-3">
                    <span class="text-muted">작성일:</span> ${board.createdAt}
                </div>
                <!-- 조회수 -->
                <div class="mb-3">
                    <span class="text-muted">조회수:</span> ${board.viewCnt}
                </div>
                <!-- 좋아요 -->
                <div class="mb-3">
                    <span class="text-muted">좋아요:</span> ${board.likes}
                </div>
                <!-- 첨부 파일 -->
                <c:if test="${not empty board.fileUrl}">
                    <div class="mb-3">
                        <span class="text-muted">첨부 파일:</span> 
                        <a href="${board.fileUrl}" target="_blank">${board.fileUrl}</a>
                        <img src="${ board.fileUrl }">
                    </div>
                </c:if>
                <!-- 게시글 내용 -->
                <div class="content mt-4">
                    ${board.content}
                </div>
            </div>
        </div>

        <!-- 버튼 -->
        <div class="d-flex justify-content-between">
            <a href="list.jsp" class="btn btn-secondary">목록으로</a>
            <div>
            	<c:if test="${sessionScope.user.id == board.userId}">
	                <a href="edit.do?postId=${board.postId}" class="btn btn-primary">수정</a>
	                <a href="delete.do?postId=${board.postId}" class="btn btn-danger"
    onclick="return confirm('정말 이 게시글을 삭제하시겠습니까?');">삭제</a>

                </c:if>
            </div>
        </div>
    </div>

    <!-- MDB Bootstrap JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.0/mdb.min.js"></script>
    <jsp:include page="/include/footer.jsp" />
</body>
</html>
