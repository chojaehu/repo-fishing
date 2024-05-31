
// 차량조회
function fnKeyupSearch() {
	if(window.event.keyCode == 13) {
		fnSearch();
	}
}

// 차량조회
function fnSearch() {
	// controller 호출
	location.href="/낚시터조회페이지?shPlcName="+$("#shPlcName").val();
}