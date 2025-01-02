<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>관리자 - 상품 관리</title>
<!-- MDB Bootstrap CSS -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.0/mdb.min.css"
	rel="stylesheet">
<style>
body {
	padding: 20px;
}
</style>
</head>
<body>
	<!-- Header -->
	<jsp:include page="/include/header.jsp" />

	<div class="container mt-5">
		<h1 class="text-center mb-4">상품 관리</h1>
		<button class="btn btn-primary mb-3" data-mdb-toggle="modal"
			data-mdb-target="#addProductModal">상품 추가</button>

		<!-- Product Table -->
		<div class="table-responsive">
			<table class="table table-hover">
				<thead class="table-light">
					<tr>
						<th>썸네일</th>
						<th>상품코드</th>
						<th>카테고리</th>
						<th>상품명</th>
						<th>브랜드</th>
						<th>가격</th>
						<th>재고</th>
						<th>상세이미지</th>
						<th></th>
					</tr>
				</thead>
				<tbody id="productTableBody">
					<!-- Dynamic rows will be added here -->
				</tbody>
			</table>
		</div>
	</div>

	<jsp:include page="modal.html" />

	<jsp:include page="/include/footer.jsp" />
	<!-- MDB Bootstrap JS -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.0/mdb.min.js"></script>
	<script>
        window.onload = function () {
            loadProducts();
            console.log(typeof mdb);

        };

        // 서버로부터 상품 로드
        function loadProducts() {
        fetch('/PCshop/admin/product/getAll.do')
            .then(response => response.json())
            .then(data => {
                const tbody = document.getElementById('productTableBody');
                tbody.innerHTML = '';
                data.forEach(product => {
                    const row = `
                        <tr>
                            <td>
                                <img src=${"${product.thumbImgUrl}"} alt="썸네일" style="width: 50px; height: 50px; object-fit: cover;">
                            </td>
                            <td>${"${product.itemCd}"}</td>
                            <td>${"${product.category}"}</td>
                            <td>${"${product.name}"}</td>
                            <td>${"${product.brand}"}</td>
                            <td>${"${product.price.toLocaleString()}"} 원</td>
                            <td>${"${product.stockQty}"}</td>
                            <td>
                            <button class="btn btn-sm btn-info" onclick="viewDetailImage('${"${product.detailImgUrl}"}')">보기</button>

                            </td>
                            <td>
                                <button class="btn btn-sm btn-warning" data-mdb-toggle="modal" data-mdb-target="#editProductModal" onclick="loadEditProduct(${'${product.itemCd}'})">수정</button>
                                <button class="btn btn-sm btn-danger" onclick="deleteProduct(${'${product.itemCd}'})">삭제</button>
                            </td>
                        </tr>
                    `;
                    tbody.innerHTML += row;
                });
            })
            .catch(error => console.error('Error loading products:', error));
    }

    // 상세 이미지 보기
    function viewDetailImage(url) {
        const newWindow = window.open("", "_blank");
        newWindow.document.write(`<img src="${'${url}'}" alt="상세 이미지" style="max-width: 100%; height: auto;">`);
    }

    function addProduct() {
        const form = document.getElementById('addProductForm');
        const formData = new FormData(form); // FormData로 처리

        fetch('/PCshop/admin/product/add.do', {
            method: 'POST',
            body: formData // JSON.stringify 대신 FormData를 그대로 전달
        })
        .then(response => {
            if (response.ok) {
                alert('상품 추가 성공');
                form.reset(); // 폼 초기화
                const modalInstance = mdb.Modal.getInstance(document.getElementById('addProductModal'));
                console.log(mdb.Modal.getInstance(document.getElementById('addProductModal')));

                modalInstance.hide(); // 모달 닫기
                loadProducts(); // 상품 목록 갱신
                location.reload();
            } else {
                alert('상품 추가 실패');
            }
        })
        .catch(error => console.error('Error : ', error));
    }


        // 수정을 위한 상품 정보 로드
        function loadEditProduct(id) {
            fetch(`/PCshop/admin/product/get.do?id=${"${id}"}`)
                .then(response => response.json())
                .then(product => {
                    document.getElementById('editId').value = product.itemCd;
                    document.getElementById('editCategory').value = product.category;
                    document.getElementById('editName').value = product.name;
                    document.getElementById('editBrand').value = product.brand;
                    document.getElementById('editPrice').value = product.price;
                    document.getElementById('editStock').value = product.stockQty;
                })
                .catch(error => console.error('Error :', error));
        }

        function updateProduct() {
            const form = document.getElementById('editProductForm');
            const formData = new FormData(form);
            const id = document.getElementById('editId').value;

            fetch(`/PCshop/admin/product/update.do?id=${"${id}"}`, {
                method: 'POST',
                body: formData, // FormData 객체를 그대로 전달
            })
            .then(response => {
                if (response.ok) {
                    alert('상품 업데이트 성공');
                    const modalInstance = mdb.Modal.getInstance(document.getElementById('editProductModal'));
                    modalInstance.hide();
                    loadProducts();
                } else {
                    alert('상품 업데이트 실패');
                }
            })
            .catch(error => console.error('Error :', error));
        }

        // 상품 삭제
        function deleteProduct(id) {
            if (confirm('정말 삭제하시겠습니까?')) {
                fetch(`/PCshop/admin/product/delete.do?id=${"${id}"}`, {
                    method: 'DELETE'
                })
                .then(response => {
                    if (response.ok) {
                        alert('상품이 성공적으로 삭제되었습니다.');
                        loadProducts();
                    } else {
                        alert('상품을 삭제하는데 실패하였습니다.');
                    }
                })
                .catch(error => console.error('error : ', error));
            }
        }
    </script>
</body>
</html>
