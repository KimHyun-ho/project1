/**
* Project Name : AnimalHospital 
* Page Name : patientInfoList.js
* Author: Park Ji Young
*/
/* 환자 리스트 검색 기능 추가 */
function selectOneAnimalPatientInfoList(){
	const animalNameVar = $('#searchPatient').val();
	$.ajax({
		url : '/patientInfoListOk',
		type : 'post',
		data : {
					animalName : animalNameVar
				},
		dataType: 'text',
		success : function() {
			alert("해당하는 환자의 검색이 끝났습니다.");
		},
		error : function() {
			alert("해당하는 환자가 존재하지 않습니다.");
		}
	});
}