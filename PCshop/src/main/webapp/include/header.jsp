<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<header>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container">
			<a class="navbar-brand" href="/PCshop/index.jsp"> <img
				src="https://pcshop1.s3.ap-northeast-2.amazonaws.com/logo.png"
				alt="폴리PC" height="40">
			</a>
			<button class="navbar-toggler" type="button"
				data-mdb-toggle="collapse" data-mdb-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<i class="fas fa-bars"></i>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="/PCshop/board/estimate/list.do">PC견적</a></li>
					<li class="nav-item"><a class="nav-link" href="/PCshop/board/notice/list.jsp">공지사항</a></li>
					<li class="nav-item"><a class="nav-link"
						href="/PCshop/board/general/list.do">자유게시판</a></li>
					<li class="nav-item"><a class="nav-link" href="/PCshop/board/quotationInquiry/list.do">견적문의</a></li>
<!-- 					<li class="nav-item"><a class="nav-link" href="/PCshop/board/cs/list.do">고객센터</a></li> -->
				</ul>
				<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
					<c:choose>
						<c:when test="${not empty sessionScope.user}">
								${ user.name }님 환영합니다. ${ user.id }
								<li class="nav-item"><a class="nav-link"
								href="/PCshop/cart/cart.do">장바구니</a></li>
							<li class="nav-item"><a class="nav-link" href="/PCshop/member/myPage.do">마이페이지</a></li>
							<li class="nav-item"><a class="nav-link" href="/PCshop/admin/mainPage.do">관리페이지</a></li>
							<li class="nav-item"><a class="nav-link"
								href="/PCshop/logout.do">로그아웃</a></li>
						</c:when>
						<c:otherwise>
							<li class="nav-item"><a class="nav-link"
								href="/PCshop/loginForm.do">로그인</a></li>
							<li class="nav-item"><a class="nav-link"
								href="/PCshop/registerForm.do">회원가입</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
	</nav>
</header>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>