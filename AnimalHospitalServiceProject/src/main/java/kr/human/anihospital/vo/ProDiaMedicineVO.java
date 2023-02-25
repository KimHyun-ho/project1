package kr.human.anihospital.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProDiaMedicineVO {
	//-----------------------------------------------------------
	// 보호자에 따른 환자의 상세 진료페이지의 처방목록 정보를 가지고 있는 VO
	//-----------------------------------------------------------
	
	// 처방약은 여러개 이므로 List로 받아야 한다.
	private int seqDiagnosis;
	private int seqMedicine;
	private String medicineManufactureCompany;
	private String medicineName;
	private String medicineMedicationGuide;
	private String medicineSideEffect;
}
