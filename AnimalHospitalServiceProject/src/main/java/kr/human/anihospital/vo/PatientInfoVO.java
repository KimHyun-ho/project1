package kr.human.anihospital.vo;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientInfoVO {

	private int seqMember;
	private String memberName;
	private int seqAnimal;
	private String animalName;
	private Date animalBirthDay;
	private String animalType;
	private String animalSize;
	private boolean animalGender; // 0 : false, 수컷 / 1 : true, 암컷
	private String animalWeight;
	private int seqDiagnosis;
	private Date diagnosisDate;
	private List<PatientDiaRecord> patientDiaRecords;

	// /pageInfoList에서 환자를 클릭시 나오는 정보 중 이전진료 내역을 가져오는 내부 Class
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class PatientDiaRecord {
		private String animalHospitalName;
		private String diagnosisSymptom;
		private Date diagnosisDate;
	}
}
