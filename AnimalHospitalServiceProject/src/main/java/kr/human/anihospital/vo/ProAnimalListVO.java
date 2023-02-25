package kr.human.anihospital.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProAnimalListVO {
	//-----------------------------------------------------------
	// 보호자에 따른 환자의 진료내역 리스트를 가지고 있는 VO
	//-----------------------------------------------------------
	private int seqMember;
	private int seqAnimal;
	private int seqDiagnosis;
	private int seqAnimalHospital;
	private String animalName;
	private String diagnosisSymptom;
	private String animalHospitalName;
	private String diagnosisDate;
}
