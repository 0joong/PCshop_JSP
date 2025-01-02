<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>답변 작성</title>
    <!-- MDBootstrap CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.0/mdb.min.css" rel="stylesheet">
</head>
<body>
    <!-- Header -->
    <jsp:include page="/include/header.jsp" />

    <div class="container mt-5">
        <h1 class="text-center mb-4">답변 작성</h1>
        <form action="/board/quotationAnswer/write.do" method="post">
            <input type="hidden" name="inquiryId" value="${inquiryId}">
            <div class="mb-3">
                <label for="userId" class="form-label">작성자 ID</label>
                <input type="text" class="form-control" id="userId" name="userId" required>
            </div>
            <div class="mb-3">
                <label for="content" class="form-label">내용</label>
                <textarea class="form-control" id="content" name="content" rows="5" required></textarea>
            </div>
            <button type="submit" class="btn btn-primary w-100">등록</button>
        </form>
    </div>

    <!-- Footer -->
    <jsp:include page="/include/footer.jsp" />

    <!-- MDBootstrap JS -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.0/mdb.min.js"></script>
</body>
</html>
