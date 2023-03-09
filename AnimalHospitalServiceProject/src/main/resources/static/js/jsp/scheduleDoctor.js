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
				    locale: 'ko',
				    // 하루에 생성 가능한 일정 수 조절
					views: {
						timeGrid: {
							dayMaxEventRows: 6
						}
					},
                    // -------------------------------------------------------
				    // 변경된 일정 수정하기
                    // -------------------------------------------------------
				    eventChange: function(info){
                        console.log(info);
                        if(confirm("'일정을 수정하시겠습니까 ?")){
							// Json 데이터를 받기 위한 배열 선언
	                        var events = new Array();
	                        var obj = new Object();
	 						// 데이터 담기
	                        obj.title = info.event._def.title;
	                        obj.start = info.event._instance.range.start;
	                        obj.end = info.event._instance.range.end;
	                        events.push(obj);
	                        console.log(events);
	                        // 수정 처리를 해줄 컨트롤러로 데이터 전송
	                        $(function updateData() {
	                            $.ajax({
	                                 url: "/scheduleDoctorUpdateOk",
	                                 method: "post",
	                                 dataType: "text",
	                                 data: JSON.stringify(events),
	                                 contentType: 'application/json',
	                             })
	                             	.done(function() {
	                                    alert("일정이 수정 되었습니다.");
	                                    location.reload();
	                                })
	                                .fail(function(error) {
	                                     alert("데이터 수정 실패 : " + error);
	                                });
	                         })
                         } else {
							 alert("수정 작업이 취소 되었습니다.");
							 location.reload();
						 }
                    },
                    // -------------------------------------------------------
                    // 원하는 시간 드래그로 지정 후 일정 추가하기
                    // -------------------------------------------------------
				    select: function (arg) { 
                        var title = prompt('일정을 입력해주세요.');
                        if (title) {
                            calendar.addEvent({
                                title: title,
                                start: arg.start,
                                end: arg.end,
                                allDay: arg.allDay,
                            })
                        } else if (!title) {
							alert('취소되었습니다.');
							return false;
						}
						// Json 데이터를 받기 위한 배열 선언
                        var events = new Array();
                        // Json 을 담기 위해 Object 선언
                        var obj = new Object();
                            obj.title = title;
                            obj.start = arg.start;
                            obj.end = arg.end;
                            events.push(obj);
                        var jsondata = JSON.stringify(events);
                        console.log(jsondata);
						// 작성한 데이터 저장
                        $(function saveData(jsondata) {
                            $.ajax({
                                url: "/scheduleDoctorInsertOk",
                                method: "POST",
                                dataType: "text",
                                data: JSON.stringify(events),
                                contentType: 'application/json',
                            })
                                .done(function() {
                                    alert("새 일정이 저장 되었습니다. : " + result);
                                })
                                .fail(function(error) {
                                     alert("insert 데이터 전송 실패 : " + error);
                                });
                            calendar.unselect()
                        });
                    },
                    // -------------------------------------------------------
                    // 선택한 일정 삭제하기
                    // -------------------------------------------------------
                    eventClick: function (info){
                        if(confirm("일정을 삭제하시겠습니까?")){
                            // 확인 클릭 시
                            info.event.remove();
	                        console.log(info.event);
	                        var events = new Array();
	                        var obj = new Object();
	                        	obj.title = info.event._def.title;
	                        	obj.start = info.event._instance.range.start;
	                        	events.push(obj);
	                        console.log(events);
	                        $(function deleteData(){
	                            $.ajax({
	                                url: "/scheduleDoctorDeleteOk",
	                                method: "Post",
	                                dataType: "text",
	                                data: JSON.stringify(events),
	                                contentType: 'application/json',
	                            })
	                               .done(function() {
	                                    alert("일정이 삭제 되었습니다.");
	                                    location.reload();
		                           })
		                           .fail(function(error) {
		                                alert("데이터 삭제 실패 : " + error);
		                           });
		                        calendar.unselect()
	                        });
                        } else {
							alert("삭제 작업이 취소 되었습니다.");
						}
                    },
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