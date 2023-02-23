package kr.human.anihospital.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientListVO {
	//-----------------------------------------------------------
	// 환자 정보를 가지고 있는 VO
	//-----------------------------------------------------------
	private int seqAnimal;
	private int seqProtector;
	private int seqDoctor;
	private String protectorName;
	private String animalName;
	private String animalType;
	private String animalSize;
	private float animalWeight;
	private int animalAge;
	private boolean animalGender;
	private String animalImportantSymptom;
}