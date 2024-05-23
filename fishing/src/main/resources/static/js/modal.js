
// 사용자
// 모달폼 띄우기
fnModalShow = function(paramTitle, paramContent) {
	$("#title").html(paramTitle);
	$("#modalFormOneBtnContent").html(paramContent);
	$("#modalFormOneBtn").modal('show');
}