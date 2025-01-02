<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시글 수정</title>
    <!-- MDB Bootstrap CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.0/mdb.min.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="/include/header.jsp" />
    
    <div class="container mt-5">
        <h2 class="text-center">게시글 수정</h2>
        <form action="edit.do" method="post">
            <input type="hidden" name="postId" value="${board.postId}" />
            <div class="mb-3">
                <label for="title" class="form-label">제목</label>
                <input type="text" id="title" name="title" class="form-control" value="${board.title}" required />
            </div>
            <div class="mb-3">
                <label for="content" class="form-label">내용</label>
                <textarea id="content" name="content" class="form-control" rows="5" required>${board.content}</textarea>
            </div>
            <div class="text-end">
                <button type="submit" class="btn btn-primary">수정하기</button>
                <a href="detail.do?postId=${board.postId}" class="btn btn-secondary">취소</a>
            </div>
        </form>
    </div>

    <jsp:include page="/include/footer.jsp" />
</body>
</html>
