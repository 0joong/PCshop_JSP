<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>로그인 페이지</title>
  <!-- MDB Bootstrap CSS -->
  <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.0/mdb.min.css" rel="stylesheet">
  <link rel="stylesheet" href="css/style.css">
</head>
<body>
  <div class="container d-flex justify-content-center align-items-center" style="min-height: 100vh;">
    <div class="card p-4" style="width: 400px;">
      <h3 class="text-center mb-4">로그인</h3>
      
      <!-- 에러 메시지 출력 -->
      <c:if test="${not empty requestScope.errMsg}">
        <div class="alert alert-danger text-center">
          ${requestScope.errMsg}
        </div>
      </c:if>
      
      <form action="/PCshop/login.do" method="POST">
        <div class="form-outline mb-4">
          <input type="text" id="username" name="id" class="form-control" required />
          <label class="form-label" for="username">아이디</label>
        </div>
        <div class="form-outline mb-4">
          <input type="password" id="password" name="password" class="form-control" required />
          <label class="form-label" for="password">비밀번호</label>
        </div>
        <button type="submit" class="btn btn-primary btn-block">로그인</button>
        <div class="text-center mt-3">
          <p>계정이 없으신가요? <a href="/PCshop/registerForm.do">회원가입</a></p>
        </div>
      </form>
    </div>
  </div>
  
  <!-- MDB Bootstrap JS -->
  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.0/mdb.min.js"></script>
</body>
</html>
