/**
* Project Name : AnimalHospital 
* Page Name : diagnosisAdd.js
* Author: Park Ji Young
*/

/* 엑셀 파일 업로드시 JSON파일로 변환 */
function upload(event){
	// 추가 버튼으로 추가된 엑셀 파일을 변수 안에 담는다.
    var input = event.target;
    // 파일을 읽을 리더를 준비
    var reader = new FileReader();
    // 리더에서 읽어서 JSON으로 변환
    reader.onload = function(){
        var fdata = reader.result;
        var read_buffer = XLSX.read(fdata, {type : 'binary'});
        read_buffer.SheetNames.forEach(function(sheetName){
            var rowdata =XLSX.utils.sheet_to_json(read_buffer.Sheets[sheetName]);
            console.log(JSON.stringify(rowdata));
            // JSON으로 변환된 데이터 컨트롤러로 전송
			$.ajax({
				url : '/diagnosisAddFeedOk',
				type : 'post',
				data : {data : rowdata},
				dataType: 'text',
				success : function() {
					alert("추천 사료 추가가 완료 되었습니다.");
				},
				error : function() {
					alert("추천 사료 전송 Error");
				}
			});
        })
    };
    
    /* 화면에 업로드한 엑셀 파일의 파일이름과 확장자 표시처리 */
    // 엑셀 파일의 내용물만 반환
    reader.readAsBinaryString(input.files[0]);
    // 추가한 엑셀 파일의 이름과 확장자를 담을 그릇 준비
    const addValue = input.files[0].name;
    // 추가하고자 하는 li태그 준비
    const li = document.createElement("li");
    // li에 id 속성 추가 
  	li.setAttribute('id',addValue);
  	// li에 text node 추가 
  	const textNode = document.createTextNode(addValue);
  	li.appendChild(textNode);
  	// 생성된 li를 ul에 추가
  	document.getElementById('recoFeed_list').appendChild(li);
}

/* 엑셀 파일 업로드시 JSON파일로 변환 */
function uploadSupplements(event){
	// 추가 버튼으로 추가된 엑셀 파일을 변수 안에 담는다.
    var input = event.target;
    // 파일을 읽을 리더를 준비
    var reader = new FileReader();
    // 리더에서 읽어서 JSON으로 변환
    reader.onload = function(){
        var fdata = reader.result;
        var read_buffer = XLSX.read(fdata, {type : 'binary'});
        read_buffer.SheetNames.forEach(function(sheetName){
            var rowdata =XLSX.utils.sheet_to_json(read_buffer.Sheets[sheetName]);
            console.log(JSON.stringify(rowdata));
            // JSON으로 변환된 데이터 컨트롤러로 전송
			$.ajax({
				url : '/diagnosisAddSupplementsOk',
				type : 'post',
				data : {data : rowdata},
				dataType: 'text',
				success : function() {
					alert("영양제 추가가 완료 되었습니다.");
				},
				error : function() {
					alert("영양제 전송 Error");
				}
			});
        })
    };
    
    /* 화면에 업로드한 엑셀 파일의 파일이름과 확장자 표시처리 */
    // 엑셀 파일의 내용물만 반환
    reader.readAsBinaryString(input.files[0]);
    // 추가한 엑셀 파일의 이름과 확장자를 담을 그릇 준비
    const addValue = input.files[0].name;
    // 추가하고자 하는 li태그 준비
    const li = document.createElement("li");
    // li에 id 속성 추가 
  	li.setAttribute('id',addValue);
  	// li에 text node 추가 
  	const textNode = document.createTextNode(addValue);
  	li.appendChild(textNode);
  	// 생성된 li를 ul에 추가
  	document.getElementById('recoSupplements_list').appendChild(li);
}

/* 업로드할 엑셀 양식 다운로드 */
function s2ab(s) { 
    var buf = new ArrayBuffer(s.length);
    var view = new Uint8Array(buf);
    for (var i=0; i<s.length; i++) view[i] = s.charCodeAt(i) & 0xFF;
    return buf;    
}

// 양식 다운로드 버튼이 눌리면
$(document).ready(function() { 
	$("#recoFeed_excel_download").click(function exportExcel(){
	    // step 1. workbook 생성
	    var wb = XLSX.utils.book_new();
	    
	    var excelHandler = {
		    getExcelFileName : function(){
		        return '추천 사료.xlsx';
		    },
		    getSheetName : function(){
		        return '추천 사료';
		    },
		    getExcelData : function(){
		        return [{'사료 이름':'', '제조회사':'', '성분':'', '알레르기 증상':'', 'DB추가일':''}]; 
		    },
		    getWorksheet : function(){
		        return XLSX.utils.json_to_sheet(this.getExcelData());
		    }
		}
	
	    // step 2. 시트 만들기 
	    var newWorksheet = excelHandler.getWorksheet();
	    
	    // step 3. workbook에 새로만든 워크시트에 이름을 주고 붙인다.  
	    XLSX.utils.book_append_sheet(wb, newWorksheet, excelHandler.getSheetName());
	
	    // step 4. 엑셀 파일 만들기 
	    var wbout = XLSX.write(wb, {bookType:'xlsx',  type: 'binary'});
	
	    // step 5. 엑셀 파일 내보내기 
	    saveAs(new Blob([s2ab(wbout)],{type:"application/octet-stream"}), excelHandler.getExcelFileName());
	});
	
	$("#recoSupplements_excel_download").click(function exportExcel(){
	    // step 1. workbook 생성
	    var wb = XLSX.utils.book_new();
	    
	    var excelHandler = {
		    getExcelFileName : function(){
		        return '추천 영양제.xlsx';
		    },
		    getSheetName : function(){
		        return '추천 영양제';
		    },
		    getExcelData : function(){
		        return [{'영양제 이름':'', '제조회사':'', '알레르기 증상':'', 'DB추가일':''}]; 
		    },
		    getWorksheet : function(){
		        return XLSX.utils.json_to_sheet(this.getExcelData());
		    }
		}
	
	    // step 2. 시트 만들기 
	    var newWorksheet = excelHandler.getWorksheet();
	    
	    // step 3. workbook에 새로만든 워크시트에 이름을 주고 붙인다.  
	    XLSX.utils.book_append_sheet(wb, newWorksheet, excelHandler.getSheetName());
	
	    // step 4. 엑셀 파일 만들기 
	    var wbout = XLSX.write(wb, {bookType:'xlsx',  type: 'binary'});
	
	    // step 5. 엑셀 파일 내보내기 
	    saveAs(new Blob([s2ab(wbout)],{type:"application/octet-stream"}), excelHandler.getExcelFileName());
	});
});