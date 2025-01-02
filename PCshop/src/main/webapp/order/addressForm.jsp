<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<h5>새 배송지 추가</h5>

	<!-- 수신자 이름 -->
	<input type="text" id="recipientName" placeholder="수신자 이름"
		class="form-control mb-2">

	<!-- 수신자 전화번호 -->
	<input type="text" id="recipientPhone" placeholder="수신자 전화번호"
		class="form-control mb-2">

	<!-- 우편번호 및 주소 -->
	<input type="text" id="sample4_postcode" placeholder="우편번호"
		class="form-control mb-2" readonly> <input type="button"
		class="btn btn-outline-primary mb-2"
		onclick="sample4_execDaumPostcode()" value="우편번호 찾기"> <input
		type="text" id="sample4_roadAddress" placeholder="도로명주소"
		class="form-control mb-2" readonly> <input type="text"
		id="sample4_detailAddress" placeholder="상세주소 (예: 아파트 호수)"
		class="form-control mb-2"> <input type="text"
		id="sample4_extraAddress" placeholder="참고항목" class="form-control mb-2"
		readonly>

	<!-- 주소 별칭 -->
	<input type="text" id="addressAlias" placeholder="주소 별칭 (예: 집, 회사)"
		class="form-control mb-2">

	<!-- 기본 배송지 설정 -->
	<div class="form-check">
		<input type="checkbox" id="isDefaultAddress" class="form-check-input">
		<label for="isDefaultAddress" class="form-check-label">기본 배송지로
			설정</label>
	</div>

	<span id="guide" style="color: #999; display: none"></span>
	<button type="button" class="btn btn-primary"
		onclick="saveNewAddress()">저장</button>
	<button type="button" class="btn btn-secondary"
		onclick="closeAddressForm()">닫기</button>

<!-- Daum Postcode Script -->
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	function sample4_execDaumPostcode() {
		new daum.Postcode(
				{
					oncomplete : function(data) {
						var roadAddr = data.roadAddress; // 도로명 주소 변수
						var extraRoadAddr = ''; // 참고 항목 변수

						if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
							extraRoadAddr += data.bname;
						}
						if (data.buildingName !== '' && data.apartment === 'Y') {
							extraRoadAddr += (extraRoadAddr !== '' ? ', '
									+ data.buildingName : data.buildingName);
						}
						if (extraRoadAddr !== '') {
							extraRoadAddr = ' (' + extraRoadAddr + ')';
						}

						document.getElementById('sample4_postcode').value = data.zonecode;
						document.getElementById("sample4_roadAddress").value = roadAddr;

						if (roadAddr !== '') {
							document.getElementById("sample4_extraAddress").value = extraRoadAddr;
						} else {
							document.getElementById("sample4_extraAddress").value = '';
						}

						var guideTextBox = document.getElementById("guide");
						if (data.autoRoadAddress) {
							var expRoadAddr = data.autoRoadAddress
									+ extraRoadAddr;
							guideTextBox.innerHTML = '(예상 도로명 주소 : '
									+ expRoadAddr + ')';
							guideTextBox.style.display = 'block';
						} else {
							guideTextBox.innerHTML = '';
							guideTextBox.style.display = 'none';
						}
					}
				}).open();
	}

	function saveNewAddress() {
		var addressData = {
			name : $('#recipientName').val(),
			phone : $('#recipientPhone').val(),
			postcode : $('#sample4_postcode').val(),
			roadAddress : $('#sample4_roadAddress').val(),
			detailAddress : $('#sample4_detailAddress').val(),
			extraAddress : $('#sample4_extraAddress').val(),
			alias : $('#addressAlias').val(),
			isDefault : $('#isDefaultAddress').is(':checked') ? 'T' : 'F'
		};

		console.log("전송 데이터:", addressData); // 데이터 확인

		if (!addressData.name || !addressData.phone || !addressData.postcode
				|| !addressData.roadAddress || !addressData.detailAddress) {
			alert("모든 필수 정보를 입력해주세요.");
			return;
		}
		
		if (!/^\d{10,11}$/.test(addressData.phone)) {
		    alert("올바른 전화번호를 입력해주세요.");
		    return;
		}


		$.ajax({
			url : '/PCshop/address/add.do',
			type : 'POST',
			contentType : 'application/json',
			data : JSON.stringify(addressData),
			success : function(response) {
				console.log("서버 응답:", response); // 서버 응답 확인
				if (response === 'success') {
					alert("새로운 배송지가 추가되었습니다.");
					location.reload();
				} else {
					alert("배송지 저장에 실패했습니다. 다시 시도해주세요.");
				}
			},
			error : function(xhr, status, error) {
				console.error("AJAX 오류:", error); // 오류 출력
				alert("서버와 통신 중 오류가 발생했습니다.");
			}
		});
	}

	function showAddressForm() {
		document.getElementById('newAddressForm').style.display = 'block';
	}

	function closeAddressForm() {
		document.getElementById('newAddressForm').style.display = 'none';
	}
</script>
