<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>파일 업로드 테스트</title>
</head>
<body>
    <h1>Amazon S3 파일 업로드</h1>
    <form action="upload.jsp" method="post" enctype="multipart/form-data">
        <input type="file" name="file" />
        <button type="submit">업로드</button>
    </form>
</body>
</html>
</html>