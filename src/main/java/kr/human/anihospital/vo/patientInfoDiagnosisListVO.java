package kr.human.anihospital.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class patientInfoDiagnosisListVO {
	private String animalName;
	private String animalHospitalName;
	private String diagnosisDate;
	private String diagnosisSymptom;
}
