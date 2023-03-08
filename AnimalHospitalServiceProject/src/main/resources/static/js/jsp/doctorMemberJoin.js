/**
* Project Name : AnimalHospital 
* Page Name : doctorMemberJoin.js
* Author: Shin Hye su
*/

/* 병원 이름 검색 및 유효성 검사 */

window.onload = function() {
		item = document.getElementsByClassName("item");
		for (var i = 0; i < item.length; i++) {
			item[i].style.display = "none";
		}
	};
	function filter() {
		var value, name, item, i;
		value = document.getElementById("value").value.toUpperCase();
		item = document.getElementsByClassName("item");
		if (value.length == 0 || value == "") {
			for (var i = 0; i < item.length; i++) {
				item[i].style.display = "none";
			}
			return false;
		}
		for (i = 0; i < item.length; i++) {
			name = item[i].getElementsByClassName("name");
			if (name[0].innerHTML.toUpperCase().indexOf(value) > -1) {
				item[i].style.display = "flex";
			} else {
				item[i].style.display = "none";
			}
		}
	}
	function getValue(e) {
		var seqAnimalHospital = e;
		const vavo = $("#animalHospitalName" + e).text();
		//$('#value').attr('th:text','vavo');
		$('#value').val(vavo);										  
		$('#value').html('<input type="hidden" id="seqAnimalHospital" name="seqAnimalHospital" value="'+seqAnimalHospital+'"/>');
		var value, name, item, i;
		value = document.getElementById("value").value.toUpperCase();
		item = document.getElementsByClassName("item");
		for (var i = 0; i < item.length; i++) {
			item[i].style.display = "none";
		}
		return false;
	}
	$(function() {
		$("#doctorImgTd").hide();
		//사진 미리보기
		$("#doctorFileUp").on("change", function(event) {
			$("#doctorImgTd").show();
			var doctorImg = event.target.files[0];
			var reader = new FileReader();
			reader.onload = function(e) {
				$("#no_content_img").hide();
				$("#doctorImg").attr("src", e.target.result);
			}
			reader.readAsDataURL(doctorImg);
		})
	});
	$(function() {
		$('#join_membership_doctor').on('click',function() {
			var hospitalValue = $('#value').val();
			var doctor_license_seq = $('#doctor_license_seq').val();
			var doctorLicenseSeqRegEx = /^[0-9]{4,6}$/;
			if (hospitalValue == null || hospitalValue.trim().length == 0) {
				$('#value').focus();
				$('#value').attr('style','border-color: #dc3545;');
				$('#value').val('');
				$('#value_val').html('<div id="value_warning"><b style="color: red; font-size:10pt;">올바른 소속병원을 입력해주세요.</b></div>');
			}else if(!doctorLicenseSeqRegEx.test(doctor_license_seq)){
				$('#doctor_license_seq').focus();
				$('#doctor_license_seq').attr('style','border-color: #dc3545;');
				$('#doctor_license_seq').val('');
				$('#doctor_license_seq_val').html('<div id="doctor_license_seq_warning"><b style="color: red; font-size:10pt;">올바른 의사 등록번호를 입력해주세요.</b></div>');
			} else {
				var result = window.confirm('작성한 내용대로 가입하시겠습니까?');
				if (result) {
					$('#doctorMemberJoinOk').submit();
					alert('정상적으로 처리되었습니다.');
				} else {
					alert('취소되었습니다.');
					return false;
				}
			}
		});
	});
	$(function(){
		$('#value').on('input', function() {
			if ($('#value').val() != '') {
				$('#value').attr('style', 'border-color : #ced4da;');
				$('#value_warning').remove();
			}
		})
	});
	$(function(){
		$('#doctor_license_seq').on('input', function() {
			if ($('#doctor_license_seq').val() != '') {
				$('#doctor_license_seq').attr('style', 'border-color : #ced4da;');
				$('#doctor_license_seq_warning').remove();
			}
		})
	});

	// 나이 달력 이벤트 및 게산
	$(function() {
		// 생년월일 기본값 현재날짜로 설정, 현재날짜 이후는 선택 불가능하게 함
		today = new Date();
		today = today.toISOString().slice(0, 10);
		bir = document.getElementById("doctor_license_date");
		bir.value = today;
		document.getElementById("doctor_license_date").setAttribute("max",
				today);
	});