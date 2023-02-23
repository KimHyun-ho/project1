package kr.human.anihospital.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicineVO {
	private int seqMedicine;
	private int seqDoctor;
	private String medicineManufactureCompany;
	private String medicineName;
	private String medicineDetailName;
	private String medicinemedicationGuide;
	private Date medicineManufactureDate;
	private String medicineSymptom;
	private String medicineSideEffect;
}
