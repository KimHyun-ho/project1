/**
* Project Name : AnimalHospital 
* Page Name : scheduleDoctor.js
* Author: Park Ji Young
*/
/* 풀 캘린더 띄우기 */
document.addEventListener('DOMContentLoaded', function() {
  $(function() {
		var request = $.ajax({
		    url: "/scheduleDoctorOk", // 변경하기
		    method: "GET",
		    dataType: "json"
		});
  		
  		request.done(function(data) {
			    // 로그 찍어보기
			    console.log(data);
			  
				var calendarEl = document.getElementById('calendar');
				
				var calendar = new FullCalendar.Calendar(calendarEl, {
				    // 달력 로드시 기본으로 띄울 설정(월, 주, 일 등 선택가능)
					initialView: 'timeGridWeek', // dayGridMonth(월), timeGridWeek(주)
					// 달력의 헤더 설정, 설정이 없으면 기본 today만 선택 가능
					headerToolbar: {
				                    left: 'prev,next today',
				                    center: 'title',
				                    right: 'dayGridMonth,timeGridWeek,timeGridDay,listWeek'
				    },
				    // 주간캘린더 일때 날짜 선택 가능
				    navLinks: true,
				    // 달력 드레그 설정
					selectable: true,
					// 달력 수정 권한 설정
					editable: true,
					// 일정 삭제 권한 설정
				    droppable: true,
				    // 월간 캘린더에 more 생성
				    dayMaxEventRows: true,
				    // 하루에 생성 가능한 일정 수 조절
					/*views: {
						timeGrid: {
							dayMaxEventRows: 6
						}
					},*/
					// 일정 삭제시 선택한 일정의 ID값 넘겨받기
					// drop: function (arg) {
				        /*if (document.getElementById('drop-remove').checked) {
				            arg.draggedEl.parentNode.removeChild(arg.draggedEl);
				        }*/
				    // },
				    // JSON으로 값 넘겨주기
				    events: data
			  });
			  // 캘린더 화면에 띄우기
			  calendar.render();
		  });
		  // 데이터 전송 실패시 경고창 띄우기
		  request.fail(function( jqXHR, textStatus) {
                alert( "전송 실패: " + textStatus );
          });
	  });
});

