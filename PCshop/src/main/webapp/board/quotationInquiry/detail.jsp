<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>문의 상세보기</title>
    <!-- MDBootstrap CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.0/mdb.min.css" rel="stylesheet">
</head>
<body>
    <!-- Header -->
    <jsp:include page="/include/header.jsp" />

    <div class="container mt-5">
        <h1 class="text-center mb-4">문의 상세보기</h1>

        <!-- 문의 상세 정보 -->
        <div class="card p-4">
            <h4>${inquiry.title}</h4>
            <div class="text-muted mb-2">
                작성자: ${inquiry.userId} | 작성일: ${inquiry.createdAt}
            </div>
            <hr>
            <p>${inquiry.content}</p>
        </div>

        <!-- 수정 및 삭제 버튼 (작성자와 현재 세션 아이디가 같을 경우) -->
        <c:if test="${inquiry.userId == sessionScope.user.id}">
            <div class="mt-3 text-end">
                <a href="edit.do?postId=${inquiry.postId}" class="btn btn-warning">수정</a>
                <a href="delete.do?postId=${inquiry.postId}" class="btn btn-danger"
                   onclick="return confirm('정말 삭제하시겠습니까?');">삭제</a>
            </div>
        </c:if>

        <!-- 목록으로 돌아가기 버튼 -->
        <div class="mt-3 text-end">
            <a href="list.do" class="btn btn-secondary">목록으로</a>
        </div>

        <!-- 답변 목록 -->
        <div class="mt-5">
            <h4>답변</h4>
            <c:if test="${empty answerList}">
                <p class="text-muted">아직 등록된 답변이 없습니다.</p>
            </c:if>
            <c:forEach var="answer" items="${answerList}">
                <div class="card mb-3">
                    <div class="card-body">
                        <p class="card-text">${answer.content}</p>
                        <div class="text-muted text-end">
                            작성자: ${answer.userId} | 작성일: ${answer.createdAt}
                        </div>
                        <!-- 답변 삭제 버튼 (관리자 또는 답변 작성자만 삭제 가능) -->
                        <c:if test="${fn:toLowerCase(sessionScope.user.role) == 'admin' || sessionScope.user.id == answer.userId}">
                            <div class="text-end mt-2">
                                <a href="answerDelete.do?answerId=${answer.answerId}&inquiryId=${inquiry.postId}" class="btn btn-danger btn-sm"
                                   onclick="return confirm('정말 삭제하시겠습니까?');">답변 삭제</a>
                            </div>
                        </c:if>
                    </div>
                </div>
            </c:forEach>
        </div>

        <!-- 관리자 답변 작성 폼 -->
        <c:if test="${fn:toLowerCase(sessionScope.user.role) == 'admin'}">
            <div class="mt-5">
                <h5>답변 작성</h5>
                <form action="answerWrite.do" method="post">
                    <input type="hidden" name="inquiryId" value="${inquiry.postId}">
                    <input type="hidden" name="userId" value="${sessionScope.user.id}">
                    <div class="mb-3">
                        <textarea class="form-control" name="content" rows="5" placeholder="답변 내용을 입력하세요" required></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary">답변 등록</button>
                </form>
            </div>
        </c:if>
    </div>

    <!-- Footer -->
    <jsp:include page="/include/footer.jsp" />

    <!-- MDBootstrap JS -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.0/mdb.min.js"></script>
</body>
</html>
