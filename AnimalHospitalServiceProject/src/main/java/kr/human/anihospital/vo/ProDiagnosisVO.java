package kr.human.anihospital.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProDiagnosisVO {
	//-----------------------------------------------------------
	// 보호자에 따른 환자의 상세 진료페이지 정보를 가지고 있는 VO
	//-----------------------------------------------------------
	private int seqDiagnosis;
	private int seqMember;
	private int seqAnimal;
	private String diagnosisDate;
	private String animalHospitalName;
	private int seqDoctor;
	private String memberName;
	private String animalName;
	private String animalAge;
	private String animalType;
	private String animalSize;
	private String animalWeight;
	private String diagnosisSymptom;
	private String diagnosisMedicationWay;
	
	private int seqPostscript;
	private String postscriptUpdateDate;
	private String postscriptSatisfaction;
	private String postscriptContent;
}
