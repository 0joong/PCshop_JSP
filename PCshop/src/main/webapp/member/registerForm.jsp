<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>회원가입</title>
  <!-- MDB Bootstrap CSS -->
  <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.0/mdb.min.css" rel="stylesheet">
  <link rel="stylesheet" href="css/style.css">
</head>
<body>
  <div class="container d-flex justify-content-center align-items-center" style="min-height: 100vh;">
    <div class="card p-4" style="width: 500px;">
      <h3 class="text-center mb-4">회원가입</h3>
      <form action="/PCshop/register.do" method="post" id="registerForm">
        <div class="form-outline mb-4">
          <input type="text" id="user_id" name="id" class="form-control" required />
          <label class="form-label" for="user_id">아이디</label>
          <button type="button" id="checkIdBtn" class="btn btn-outline-secondary mt-2">아이디 중복 확인</button>
          <div id="idCheckResult" class="mt-2"></div>
        </div>
        <div class="form-outline mb-4">
          <input type="email" id="email" name="email" class="form-control" required />
          <label class="form-label" for="email">이메일</label>
        </div>
        <div class="form-outline mb-4">
          <input type="password" id="password" name="password" class="form-control" required />
          <label class="form-label" for="password">비밀번호</label>
        </div>
        <div class="form-outline mb-4">
          <input type="password" id="confirmPassword" name="confirmPassword" class="form-control" required />
          <label class="form-label" for="confirmPassword">비밀번호 확인</label>
        </div>
        <div class="form-outline mb-4">
          <input type="text" id="name" name="name" class="form-control" required />
          <label class="form-label" for="name">이름</label>
        </div>
        <div class="form-outline mb-4">
          <input type="tel" id="phone" name="phone" class="form-control" required />
          <label class="form-label" for="phone">전화번호</label>
        </div>
        <button type="submit" id="registerBtn" class="btn btn-primary btn-block" disabled>회원가입</button>
        <div class="text-center mt-3">
          <p>이미 계정이 있으신가요? <a href="/PCshop/loginForm.do">로그인</a></p>
        </div>
      </form>
    </div>
  </div>

  <!-- MDB Bootstrap JS -->
  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.0/mdb.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script>
    $(document).ready(function() {
      let isIdAvailable = false;

      // 아이디 중복 확인 버튼 클릭 이벤트
      $('#checkIdBtn').click(function() {
        var userId = $('#user_id').val().trim();
        if (userId === "") {
          $('#idCheckResult').text("아이디를 입력해주세요.").css("color", "red");
          isIdAvailable = false;
          updateRegisterButton();
          return;
        }

        $.ajax({
          url: '/PCshop/LoginCheckId.do',
          type: 'POST',
          data: { id: userId },
          success: function(response) {
            // JSON 형식으로 응답을 받기 때문에 파싱이 필요합니다.
            var result = typeof response === "string" ? JSON.parse(response) : response;
            if (result.result === 'available') {
              $('#idCheckResult').text("사용 가능한 아이디입니다.").css("color", "green");
              isIdAvailable = true; // 아이디 사용 가능
            } else {
              $('#idCheckResult').text("이미 사용 중인 아이디입니다.").css("color", "red");
              isIdAvailable = false; // 아이디 사용 불가
            }
            updateRegisterButton();
          },
          error: function() {
            $('#idCheckResult').text("아이디 중복 확인 중 오류가 발생했습니다.").css("color", "red");
            isIdAvailable = false;
            updateRegisterButton();
          }
        });
      });

      // 회원가입 버튼 활성화 상태 업데이트
      function updateRegisterButton() {
        if (isIdAvailable) {
          $('#registerBtn').prop('disabled', false); // 활성화
        } else {
          $('#registerBtn').prop('disabled', true); // 비활성화
        }
      }
    });
  </script>
</body>
