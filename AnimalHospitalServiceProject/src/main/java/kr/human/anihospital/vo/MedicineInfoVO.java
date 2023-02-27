package kr.human.anihospital.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicineInfoVO {

	private int seqMedicine;
	private String medicineCompany;
	private String medicineName;
	private String medicationGuide;
	private String medicineSymptom;
	private String medicineSideEffect;

}
