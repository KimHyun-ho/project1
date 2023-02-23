package kr.human.anihospital.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientInfoVO {
	private int seqAnimal;
	private int seqProtector;
	private String animalName;
	private String animalType;
	private String animalSize;
	private float animalWeight;
	private int animalAge;
	private String animalGender;
	private String animalImportantSymptom;
}
