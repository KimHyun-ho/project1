package kr.human.anihospital.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProMyPageDetailVO {
	//-----------------------------------------------------------
	// ProMypage (보호자 정보 페이지)에서 사용할 VO - 추후 후기 추가해야됨
	//-----------------------------------------------------------
	private int seqMember;
	private int seqAnimal;
	private int seqAnimalHospital;
	private int seqDiagnosis;
	private String memberEmailId;
	private String memberName;
	private String memberPhoneNo;
	private boolean memberGender;
	private String memberBirthdate;
	private String animalName;
	private String animalAge;
	private boolean animalGender;
	private String animalType;
	private String animalSize;
	private String animalWeight;
	private String animalHospitalName;
	private String diagnosisDate;
	private String diagnosisSymptom;
	private String animalPicture;
	private String animalVideo;
	private String animalPicturePath;
	private String animalVideoPath;
}
