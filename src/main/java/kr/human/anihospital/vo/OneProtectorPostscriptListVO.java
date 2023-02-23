package kr.human.anihospital.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OneProtectorPostscriptListVO {
	//-----------------------------------------------------------
	// 한 보호자의 환자(들)의 후기(들)의 내용을 가지고 있는 VO
	//----------------------------------------------------------
	private String animalName; 
	private int postscriptSatisfaction;
	private String animalHospitalname;
	private Date diagnosisDate;
}
