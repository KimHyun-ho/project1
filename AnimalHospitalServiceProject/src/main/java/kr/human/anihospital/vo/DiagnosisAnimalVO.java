package kr.human.anihospital.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiagnosisAnimalVO {
	//-----------------------------------------------------------
	// 진료내역 화면에서 환자 정보를 가지고 있는 VO
	//-----------------------------------------------------------
	private int seqDiagnosis;
	private int seqAnimal;
	private String animalPicture;
	private String animalName;
	private int animalAge;
	private String animalType;
	private String animalSize;
	private boolean animalGender;
	private float animalWeight;
	
}
