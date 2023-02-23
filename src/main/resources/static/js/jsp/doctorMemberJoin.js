/**
* Project Name : AnimalHospital 
* Page Name : doctorMemberJoin.jsp
* Author: Shin Hye Su
*/
/* 주소검색 */
window.onload = function() {
	document.getElementById("searching_address")
		.addEventListener("click", function() { //주소입력칸을 클릭하면
			//카카오 지도 발생
			var width = 500; //팝업의 너비
			var height = 600; //팝업의 높이
			new daum.Postcode(
				{
					width : width, //생성자에 크기 값을 명시적으로 지정
					height : height,
					oncomplete : function(data) { //선택시 입력값 세팅
						document
								.getElementById("doctor_address").value = data.address; // 주소 넣기
								$('#doctor_address').attr('style','border-color : #ced4da;');
								$('#doctor_address_warning').remove();
					}
				}).open({
				    left: (window.screen.width / 2) - (width / 2),
				    top: (window.screen.height / 2) - (height / 2)
				});
		});
}