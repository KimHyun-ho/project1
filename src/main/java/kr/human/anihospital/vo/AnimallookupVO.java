package kr.human.anihospital.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimallookupVO {
	//-----------------------------------------------------------
	// 보호자에 따른 환자의 진료내역 리스트를 가지고 있는 VO
	// 보호자, 환자에 따른 특정 환자의 진료내역 리스트를 가지고 있는 VO
	//-----------------------------------------------------------
	private int seqAnimal;
	private int seqProtector;
	private int seqDiagnosis;
	private String animalName;
	private String diagnosisSymptom;
	private String animalHospitalName;
	private String diagnosisDate;
}
