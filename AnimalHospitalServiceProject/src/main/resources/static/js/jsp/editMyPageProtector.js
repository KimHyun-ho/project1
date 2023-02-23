/**
* Project Name : AnimalHospital 
* Page Name : editMyPageProtector.js
* Author: Shin Hye Su
*/
/* 유효성 검사 및 보호자 정보 수정 ajax */
$(function(){
	$('#updateMyInfo').on('click', function() {
		var address1 = $('#address1').val();
		var address2 = $('#address2').val();
		var pw = $('#pw').val();
		//var pwRegEx = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,20}$/;
		var phoneNum = $('#phoneNum').val();
		var cNum = $('#cNum').val();
		if(pw==null||pw.trim().length==0){
			$('#pw').attr('style','border-color: #dc3545;');
			$('#pw').val('');
			$('#passMessage').html('<div id="warning1"><b style="color: red;">수정할 비밀번호를 입력해주세요.</b></div>');
			$('#pw').focus();
		}else if(phoneNum==null||phoneNum.trim().length==0){
			$('#phoneNum').attr('style','border-color: #dc3545;');
			$('#phoneNum').val('');
			$('#phoneMessage').html('<div id="warning2"><b style="color: red;">수정할 전화번호를 입력해주세요.</b></div>');
			$('#phoneNum').focus();
		}else if(cNum==null||cNum.trim().length==0){
			$('#cNum').attr('style','border-color: #dc3545;');
			$('#cNum').val('');
			$('#cNumMessage').html('<div id="warning3"><b style="color: red;">인증번호를 입력해주세요.</b></div>');
			$('#cNum').focus();
		}else{
			var result = window.confirm('작성한 내용을 수정하시겠습니까?');
			if(result) {
		     	alert('정상적으로 수정되었습니다.');
		     	var seqProtector = $("input[name='seqProtector']").val();
		     	var protectorPassword = $("input[name='password']").val();
				var protectorPhoneNo = $("input[name='phone']").val();
				$.ajax({
					url: "/editMyPageProtectorOk",
					dataType: "text",
					type: "post",
					data: {
						seqProtector : seqProtector,
						protectorPassword : protectorPassword,
						protectorPhoneNo : protectorPhoneNo,
					},
					success: function(data) {
						location.href='/myPageProtector';
					},
					error: function(data) {
						alert("Error");
					}
				});
			}
		}
	});
});
$(function(){
	$('#pw').on('input',function(){
		if($('#pw').val() !=''){
			$('#pw').attr('style','border-color : #ced4da;');
			$('#warning1').remove();
		}
	});
	$('#phoneNum').on('input',function(){
		if($('#phoneNum').val() !=''){
			$('#phoneNum').attr('style','border-color : #ced4da;');
			$('#warning2').remove();
		}
	});
	$('#cNum').on('input',function(){
		if($('#cNum').val() !=''){
			$('#cNum').attr('style','border-color : #ced4da;');
			$('#warning3').remove();
		}
	});
});