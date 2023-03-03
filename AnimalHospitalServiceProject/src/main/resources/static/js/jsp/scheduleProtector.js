/**
* Project Name : AnimalHospital 
* Page Name : scheduleProtector.js
* Author: Shin Hye su
*/
/* 의사 seq받아서 스케줄 풀 캘린더 띄우기 */
document.addEventListener('DOMContentLoaded', function() {
	$(function() {
		var request = $.ajax({
					    url: "/proScheduleOk", 
					    method: "post",
					    dataType: "json"
					});
	  		request.done(function(data) {
			    // 로그 찍어보기
			    console.log(data);
			  
				var calendarEl = document.getElementById('calendar');
				
				var calendar = new FullCalendar.Calendar(calendarEl, {
				    // 캘린더 사용을 위해 발급 받은 API Key(Google Calendar전용 Key)
				    googleCalendarApiKey: 'AIzaSyBZqlqGpA_CWEYa-S0zSasKdiZW5GTGQao',
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
				    events: data,
					// 풀캘린더에 표시할 공.개.된 구글캘린더 정보
				    /*eventSources: [
				      {
				          googleCalendarId: '01eb943a198c2d1b3946f74b933a302aed241cad6729e8371d86f112503a600e@group.calendar.google.com',
				          className: '동물병원 통합의료 시스템',
				          color: '#be5683' 
				      }
				    ]*/
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

function hospital() {
	$('.removeDoctor').remove();
	var animalHospitalName = $('#animalHospitalName').val();
	$('#doctorName').val('');
	$.ajax({
		url: "/proScheduleDoctorList",
		type: "post",
		data: {
			animalHospitalName : animalHospitalName
		},
		success: function(doctorList) {
			for(let i = 0; i < doctorList.length; i++){ //  value="'+ doctorList[i].memberName+'"
				$('#doctor_name_option').append('<option class="removeDoctor" id="'+doctorList[i].seqDoctor+'" value="'+ doctorList[i].memberName+'">'+ doctorList[i].memberName + '</option>');
			}
		},
		error: function() {
			alert("Error");
		}
	});
};

$(function(){
	$('#selectDoctortBtn').on('click', function() {
		var animalHospitalName = $('#animalHospitalName').val();
		var doctorName = $('#doctorName').val();
		var seqDoctor = $('option[value="'+doctorName+'"]').attr('id');
		console.log(seqDoctor);
		console.log(doctorName);
		if(animalHospitalName=='' || animalHospitalName.trim().length==0){
			alert('병원을 선택해 주세요');
		}else if(doctorName==null||doctorName.trim().length==0){
			alert('의사를 선택해 주세요');
		}else{
			var request = $.ajax({
			    url: "/proDoctorSchedule", 
			    method: "post",
			    data: {
					seqDoctor : seqDoctor
				},
			    dataType: "json"
			});
	  		request.done(function(data) {
			    // 로그 찍어보기
			    console.log(data);
			  
				var calendarEl = document.getElementById('calendar');
				
				var calendar = new FullCalendar.Calendar(calendarEl, {
				    // 캘린더 사용을 위해 발급 받은 API Key(Google Calendar전용 Key)
				    googleCalendarApiKey: 'AIzaSyBZqlqGpA_CWEYa-S0zSasKdiZW5GTGQao',
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
				    events: data,
					// 풀캘린더에 표시할 공.개.된 구글캘린더 정보
				    /*eventSources: [
				      {
				          googleCalendarId: '01eb943a198c2d1b3946f74b933a302aed241cad6729e8371d86f112503a600e@group.calendar.google.com',
				          className: '동물병원 통합의료 시스템',
				          color: '#be5683' 
				      }
				    ]*/
			  });
			  // 캘린더 화면에 띄우기
			  calendar.render();
		  });
		  // 데이터 전송 실패시 경고창 띄우기
		  request.fail(function( jqXHR, textStatus) {
                alert( "전송 실패: " + textStatus );
          });
		}
	});
});
