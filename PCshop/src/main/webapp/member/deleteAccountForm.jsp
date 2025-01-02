<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원 탈퇴</title>
    <!-- MDB Bootstrap CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.0/mdb.min.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="/include/header.jsp" />
    
    <c:if test="${ not empty errMsg }">
    	<script>
    		alert('${errMsg}');
    	</script>
    </c:if>

    <div class="container mt-5">
        <h1 class="text-center text-danger mb-4">회원 탈퇴</h1>
        <div class="card">
            <div class="card-body">
                <p class="text-warning">회원 탈퇴를 진행하시기 전에 아래 사항을 반드시 확인해주세요:</p>
                <ul>
                    <li>탈퇴 시 모든 데이터가 삭제되며 복구가 불가능합니다.</li>
                    <li>남아 있는 결제 및 배송 정보는 모두 소멸됩니다.</li>
                    <li>다시 가입하시더라도 이전 데이터를 복원할 수 없습니다.</li>
                </ul>
                <div class="text-center mt-4">
                    <form action="/member/deleteAccount.do" method="post">
                        <button type="submit" class="btn btn-danger">회원 탈퇴</button>
                    </form>
                    <a href="/index.jsp" class="btn btn-secondary mt-2">홈으로 가기</a>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="/include/footer.jsp" />
    <!-- MDB Bootstrap JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.0/mdb.min.js"></script>
</body>
</html>
