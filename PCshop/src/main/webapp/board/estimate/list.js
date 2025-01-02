window.onload = function() {
	const errorElement = document.getElementById('error-message');
	const errMsg = errorElement.dataset.errMsg;
	if (errMsg) {
		alert(errMsg);
		// 에러 메시지를 출력한 후 속성을 제거합니다.
		errorElement.dataset.errMsg = '';
	}
	// CPU 및 Mainboard 데이터 로드
	loadComponents('cpu', 'cpuList', 'selectedCpu');
	loadComponents('mainboard', 'mainboardList', 'selectedMainboard');
};

// 선택된 항목의 가격을 저장하는 객체
const selectedPrices = {
	selectedCpu: 0,
	selectedMainboard: 0
};

function loadComponents(type, listId, selectedId) {
	const list = document.getElementById(listId);

	fetch(`/PCshop/board/estimate/getComponents.do?type=${"${type}"}`)
		.then(response => response.json())
		.then(data => {
			list.innerHTML = ''; // 기존 데이터 초기화
			data.forEach(component => {
				const li = document.createElement('li');
				li.className = 'list-group-item d-flex justify-content-between align-items-center';
				li.innerHTML = `
                        <div class="d-flex align-items-center">
                            <img src=${"${component.thumbImgUrl}"} alt=${"${component.name}"} class="me-3" style="width: 50px; height: 50px; object-fit: cover;">
                            <div>
                                <span>${"${component.name}"}</span>
                                <br>
                                <small>${"${component.price.toLocaleString()}"} 원</small>
                            </div>
                        </div>
                        <div>
                        <button class="btn btn-sm btn-primary me-2" 
                            onclick="selectComponent('${"${selectedId}"}', '${"${component.name}"}', ${"${component.price}"}, ${"${component.itemCd}"})">
                            선택
                        </button>

                            <button class="btn btn-sm btn-info" onclick="viewDetailImage('${"${component.detailImgUrl}"}')">상세 보기</button>
                        </div>
                    `;
				list.appendChild(li);
			});
		})
		.catch(error => {
			console.error('Error loading components:', error);
			const errorItem = document.createElement('li');
			errorItem.className = 'list-group-item text-danger';
			errorItem.textContent = '부품을 불러오는 데 실패했습니다.';
			list.appendChild(errorItem);
		});
}

function selectComponent(selectedId, componentName, componentPrice, componentId) {
	// 선택된 컴포넌트 이름 표시
	const selectedElement = document.getElementById(selectedId);
	selectedElement.textContent = componentName;
	console.log(selectedElement.textContent)
	console.log(componentId)

	// 선택된 컴포넌트 ID 저장
	if (selectedId === "selectedCpu") {
		document.getElementById("selectedCpuId").value = componentId;
	} else if (selectedId === "selectedMainboard") {
		document.getElementById("selectedMainboardId").value = componentId;
	}

	// 총 가격 업데이트
	const totalPriceElement = document.getElementById("totalPrice");
	const currentPrice = parseInt(totalPriceElement.textContent.replace(/,/g, ""), 10) || 0;
	const previousPrice = selectedPrices[selectedId] || 0;
	const updatedPrice = currentPrice - previousPrice + componentPrice;

	selectedPrices[selectedId] = componentPrice;
	totalPriceElement.textContent = updatedPrice.toLocaleString();
}



function viewDetailImage(url) {
	if (url) {
		const imageWindow = window.open('', '_blank');
		imageWindow.document.write(`
                <html>
                <head>
                    <title>상세 이미지</title>
                    <style>
                        body {
                            margin: 0;
                            display: flex;
                            justify-content: center;
                            align-items: center;
                            height: 100vh;
                            background-color: #f4f4f4;
                        }
                        img {
                            max-width: 100%;
                            max-height: 100%;
                        }
                    </style>
                </head>
                <body>
                    <img src=${"${url}"} alt="상세 이미지">
                </body>
                </html>
            `);
	} else {
		alert('상세 이미지가 없습니다.');
	}
}

function checkCompatibility() {
	const cpuId = document.getElementById("selectedCpuId").value;
	const mainboardId = document.getElementById("selectedMainboardId").value;
	console.log(cpuId);
	console.log(mainboardId);
	// 선택된 ID가 없는 경우 경고
	if (!cpuId || !mainboardId) {
		alert("CPU 또는 메인보드가 선택되지 않았습니다.");
		return;
	}

	fetch(`/PCshop/board/estimate/checkCompatibility.do?cpuId=${"${cpuId}"}&mainboardId=${"${mainboardId}"}`)
		.then(response => response.json())
		.then(data => {
			if (data.compatible) {
				alert("선택한 CPU와 메인보드는 호환 가능합니다.");
			} else {
				alert("선택한 CPU와 메인보드는 호환되지 않습니다.");
			}
		})
		.catch(error => console.error("호환성 검사 오류:", error));
}

function addToCart() {
	// 선택된 컴포넌트 정보 수집
	const selectedCpuId = document.getElementById("selectedCpuId").value;
	const selectedMainboardId = document.getElementById("selectedMainboardId").value;

	// 장바구니에 담기 요청을 서버로 보냄
	const form = document.createElement("form");
	form.method = "post";
	form.action = "/PCshop/cart/addToCart.do";

	// 선택된 CPU 정보
	const cpuInput = document.createElement("input");
	cpuInput.type = "hidden";
	cpuInput.name = "cpuId";
	cpuInput.value = selectedCpuId;
	form.appendChild(cpuInput);

	// 선택된 메인보드 정보
	const mainboardInput = document.createElement("input");
	mainboardInput.type = "hidden";
	mainboardInput.name = "mainboardId";
	mainboardInput.value = selectedMainboardId;
	form.appendChild(mainboardInput);

	// 폼을 body에 추가하고 제출
	document.body.appendChild(form);
	form.submit();
}