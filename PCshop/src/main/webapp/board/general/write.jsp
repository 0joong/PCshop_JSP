<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시글 작성</title>
    <!-- MDB Bootstrap CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.0/mdb.min.css" rel="stylesheet">
</head>

<body>
    <!-- Navbar -->
    <jsp:include page="/include/header.jsp" />

    <div class="container mt-5">
        <h2 class="text-center mb-4">게시글 작성</h2>

        <!-- 게시글 작성 Form -->
        <form action="write.do" method="post" enctype="multipart/form-data">
            <!-- 제목 -->
            <div class="form-outline mb-4">
                <input type="text" id="title" name="title" class="form-control" required />
                <label class="form-label" for="title">제목</label>
            </div>

            <!-- 내용 -->
            <div class="form-outline mb-4">
                <textarea id="content" name="content" class="form-control" rows="5" required></textarea>
                <label class="form-label" for="content">내용</label>
            </div>

            <!-- 파일 업로드 -->
            <div class="mb-4">
                <label for="file" class="form-label">첨부파일</label>
                <input type="file" id="file" name="file" class="form-control" />
            </div>

            <!-- 제출 버튼 -->
            <div class="text-center">
                <button type="submit" class="btn btn-primary btn-lg">등록</button>
                <a href="list.do" class="btn btn-secondary btn-lg">취소</a>
            </div>
        </form>
    </div>

    <!-- Footer -->
    <jsp:include page="/include/footer.jsp" />

    <!-- MDB Bootstrap JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.0/mdb.min.js"></script>
</body>

</html>
