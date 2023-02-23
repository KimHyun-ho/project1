package kr.human.anihospital.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class postscriptOneDiagnosisVO {
	//-----------------------------------------------------------
	// 진료화면에 띄워줄 후기 내용을 가지고 있는 VO
	//-----------------------------------------------------------
	private int seqDiagnosis;
	private int seqPostscript;
	private String postscriptUpdateDate;
	private String animalHospitalName;
	private int postscriptSatisfaction;
	private String postscriptContent;
}
