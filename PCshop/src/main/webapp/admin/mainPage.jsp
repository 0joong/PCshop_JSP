<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관리자 전용 페이지</title>
    <!-- MDB Bootstrap CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.0/mdb.min.css" rel="stylesheet">
    <style>
        body {
            padding-top: 56px;
        }
    </style>
</head>
<body>
    <!-- Navbar -->
    <jsp:include page="/include/header.jsp"/>

    <!-- Main Content -->
    <div class="container mt-5">
        <h1 class="text-center mb-4">관리자 전용 페이지</h1>
        <div class="row">
            <!-- 주문관리 카드 -->
            <div class="col-lg-6 col-md-6 mb-4">
                <div class="card text-center">
                    <div class="card-body">
                        <h5 class="card-title">주문관리</h5>
                        <p class="card-text">주문 정보를 확인하고 관리할 수 있습니다.</p>
                        <a href="orderManagement.do" class="btn btn-primary">주문관리</a>
                    </div>
                </div>
            </div>

            <!-- 사용자관리 카드 -->
            <div class="col-lg-6 col-md-6 mb-4">
                <div class="card text-center">
                    <div class="card-body">
                        <h5 class="card-title">사용자관리</h5>
                        <p class="card-text">사용자 정보를 확인하고 관리할 수 있습니다.</p>
                        <a href="userManagement.do" class="btn btn-primary">사용자관리</a>
                    </div>
                </div>
            </div>
            
            <!-- 상품관리 카드 -->
            <div class="col-lg-6 col-md-6 mb-4">
                <div class="card text-center">
                    <div class="card-body">
                        <h5 class="card-title">상품관리</h5>
                        <p class="card-text">상품 정보를 확인하고 관리할 수 있습니다.</p>
                        <a href="productManagement.do" class="btn btn-primary">상품관리</a>
                    </div>
                </div>
            </div>

            <!-- 판매 통계 카드 -->
            <div class="col-lg-6 col-md-6 mb-4">
                <div class="card text-center">
                    <div class="card-body">
                        <h5 class="card-title">판매 통계</h5>
                        <p class="card-text">판매 데이터를 확인하고 통계를 분석할 수 있습니다.</p>
                        <a href="orderStatistics.do" class="btn btn-primary">판매 통계</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
	<jsp:include page="/include/footer.jsp"/>
    <!-- MDB Bootstrap JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.0/mdb.min.js"></script>
</body>
</html>
