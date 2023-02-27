package kr.human.anihospital.vo;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocPatientInfoVO {

	private int seqMember;
	private String memberName;
	private int seqAnimal;
	private String animalName;
	private Date animalBirthDay;
	private String animalType;
	private String animalSize;
	private boolean animalGender; // 0 : false, 수컷 / 1 : true, 암컷
	private String animalWeight;
	private Date animalJoinDate;
	private Date animalUpdateDate;
	private String animalImportantSymptom;
	private String animalPicture;
	private String animalVideo;
	private String animalPicturePath;
	private String animalVideoPath;

	private int seqDiagnosis;
	private Date diagnosisDate;
}
