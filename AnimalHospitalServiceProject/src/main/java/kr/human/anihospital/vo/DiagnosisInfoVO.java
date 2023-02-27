package kr.human.anihospital.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiagnosisInfoVO {

	private int seqDiagnosis;
	private int seqDoctor;
	private int seqAnimal;
	private int seqAnimalHospital;
	private String animalHospitalName;
	private Date diagnosisDate;
	private String diagnosisSymptom;
	private String diagnosisMedicationWay;
}
