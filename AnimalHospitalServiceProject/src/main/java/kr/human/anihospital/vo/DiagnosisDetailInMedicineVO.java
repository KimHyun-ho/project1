package kr.human.anihospital.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiagnosisDetailInMedicineVO {
	//-----------------------------------------------------------
	// 진료내역 화면에서 상세 진료내역 정보를 가지고 있는 VO
	//-----------------------------------------------------------
	private int seqDiagnosis;
	private String diagnosisDate;
	private String animalHospitalName;
	private int seqDoctor;
	private String doctorName;
	private String animalName;
	private String diagnosisSymptom;
	private String medicineName;
	private String medicineManufactureCompany;
	private String medicineManufactureDate;
	private String medicineDetailName;
	private String medicineMedicationGuide;
	private String medicineSideEffect;
}
