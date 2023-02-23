/**
* Project Name : AnimalHospital 
* Page Name : roleCheck.jsp
* Author: Shin Hye Su
*/
/* 체크박스 한개만 선택 */
function clickOnlyOne(itself){
	const checkboxes = document.getElementsByName("checkRole");
	checkboxes.forEach((checkbox) =>{
		checkbox.checked =false;
	})
	itself.checked = true;
}