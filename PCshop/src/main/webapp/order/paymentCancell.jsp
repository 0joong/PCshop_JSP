<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>결제 취소</title>
    <!-- MDB Bootstrap CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.0/mdb.min.css" rel="stylesheet">
</head>
<body>
    <!-- Navbar -->
    <jsp:include page="/include/header.jsp" />

    <div class="container mt-5">
        <div class="alert alert-warning text-center">
            <h3>${cancelMessage}</h3>
        </div>
        <div class="text-center mt-4">
            <a href="/PCshop/index.jsp" class="btn btn-primary">메인 화면으로 이동</a>
        </div>
    </div>
    <!-- Footer -->
    <jsp:include page="/include/footer.jsp" />
</body>
</html>
