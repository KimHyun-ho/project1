/**
* Project Name : AnimalHospital 
* Page Name : myPageProtector.js
* Author: Park Ji Young
*/
/* 탈퇴 메세지 출력 */
window.onload = function() {
		var withdrawal = document.getElementById('withdrawal');
		withdrawal.addEventListener('click', function () {
			var result = window.confirm('탈퇴하시면 모든 내역이 사라집니다.\r\n정말로 회원 탈퇴하시겠습니까?');
			
			if(result) {
				alert('정상적으로 처리되었습니다.');
				location.href='/';
			}
		});
	}