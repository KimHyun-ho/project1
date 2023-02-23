/**
* Project Name : AnimalHospital 
* Page Name : pharmacyInfoAdd.js
* Author: Kim Hyun-ho
*/
/* 약국 정보 등록 */
window.onload = function() {
	document
		.getElementById("pharmacy_address")
		.addEventListener(
			"click",
			function() { //주소입력칸을 클릭하면
				//카카오 지도 발생
				var width = 500; //팝업의 너비
				var height = 600; //팝업의 높이
				new daum.Postcode(
					{
						width: width, //생성자에 크기 값을 명시적으로 지정
						height: height,
						oncomplete: function(data) { //선택시 입력값 세팅
							document.getElementById("input_pharmacy_address").value = data.address; // 주소 넣기
							$('#input_pharmacy_address').attr('style', 'border-color : #ced4da;');
							$('#warning2').remove();
						}
					}).open({
						left: (window.screen.width / 2) - (width / 2),
						top: (window.screen.height / 2) - (height / 2)
					});
			});
}

$(function() {
	$('#hospital_info_add').on('click', function() {
		var pharmacy_name = $('#pharmacy_name').val();
		var input_pharmacy_address = $('#input_pharmacy_address').val();
		var pharmacy_address_detail = $('#pharmacy_address_detail').val();
		var pharmacy_phone = $('#pharmacy_phone').val();
		var pharmacy_location_latitude = $('#pharmacy_location_latitude').val();
		var pharmacy_location_longitude = $('#pharmacy_location_longitude').val();

		if (pharmacy_name == null || pharmacy_name.trim().length == 0) {
			$('#pharmacy_name').attr('style', 'border-color: #dc3545;');
			$('#pharmacy_name').val('');
			$('#warningMessage1').html('<div id="warning1"><b style="color: red;">약국 이름을 입력해주세요</b></div>');
			$('#pharmacy_name').focus();
		} else if (input_pharmacy_address == null || input_pharmacy_address.trim().length == 0) {
			$('#input_pharmacy_address').attr('style', 'border-color: #dc3545;');
			$('#input_pharmacy_address').val('');
			$('#warningMessage2').html('<div id="warning2"><b style="color: red;">약국 주소를 입력해주세요</b></div>');
			$('#input_pharmacy_address').focus();
		} else if (pharmacy_address_detail == null || pharmacy_address_detail.trim().length == 0) {
			$('#pharmacy_address_detail').attr('style', 'border-color: #dc3545;');
			$('#pharmacy_address_detail').val('');
			$('#warningMessage3').html('<div id="warning3"><b style="color: red;">약국 상세 주소를 입력해주세요</b></div>');
			$('#pharmacy_address_detail').focus();
		} else if (pharmacy_phone == null || pharmacy_phone.trim().length == 0) {
			$('#pharmacy_phone').attr('style', 'border-color: #dc3545;');
			$('#pharmacy_phone').val('');
			$('#warningMessage4').html('<div id="warning4"><b style="color: red;">약국 전화번호를 입력해주세요</b></div>');
			$('#pharmacy_phone').focus();
		} else if (pharmacy_location_latitude == null || pharmacy_location_latitude.trim().length == 0) {
			$('#pharmacy_location_latitude').attr('style', 'border-color: #dc3545;');
			$('#pharmacy_location_latitude').val('');
			$('#warningMessage5').html('<div id="warning5"><b style="color: red;">정확한 위도를 입력해주세요</b></div>');
			$('#pharmacy_location_latitude').focus();
		} else if (pharmacy_location_longitude == null || pharmacy_location_longitude.trim().length == 0) {
			$('#pharmacy_location_longitude').attr('style', 'border-color: #dc3545;');
			$('#pharmacy_location_longitude').val('');
			$('#warningMessage6').html('<div id="warning6"><b style="color: red;">정확한 위도를 입력해주세요</b></div>');
			$('#pharmacy_location_longitude').focus();
		} else {
			var result = window.confirm('입력하신 내용대로 약국을 등록 하시겠습니까?');
			if (result) {
				$('#pharmacyAddSubmit').submit();
				alert('정상적으로 처리되었습니다.');
			} else if (!result) {
				alert('취소되었습니다.');
				return false;
			}
		}
	});
});

$('#pharmacy_name').on('input', function() {
	if ($('#pharmacy_name').val() != '') {
		$('#pharmacy_name').attr('style', 'border-color : #ced4da;');
		$('#warning1').remove();
	}
});
$('#input_pharmacy_address').on('input', function() {
	if ($('#input_pharmacy_address').val() != '') {
		$('#input_pharmacy_address').attr('style', 'border-color : #ced4da;');
		$('#warning2').remove();
	}
});
$('#pharmacy_address_detail').on('input', function() {
	if ($('#pharmacy_address_detail').val() != '') {
		$('#pharmacy_address_detail').attr('style', 'border-color : #ced4da;');
		$('#warning3').remove();
	}
});
$('#pharmacy_phone').on('input', function() {
	if ($('#pharmacy_phone').val() != '') {
		$('#pharmacy_phone').attr('style', 'border-color : #ced4da;');
		$('#warning4').remove();
	}
});
$('#pharmacy_location_latitude').on('input', function() {
	if ($('#pharmacy_location_latitude').val() != '') {
		$('#pharmacy_location_latitude').attr('style', 'border-color : #ced4da;');
		$('#warning5').remove();
	}
});
$('#pharmacy_location_longitude').on('input', function() {
	if ($('#pharmacy_location_longitude').val() != '') {
		$('#pharmacy_location_longitude').attr('style', 'border-color : #ced4da;');
		$('#warning6').remove();
	}
});