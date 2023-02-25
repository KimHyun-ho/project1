package kr.human.anihospital.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalHospitalVO {
	private int seqAnimalHospital;
	private String animalHospitalName;
	private String animalHospitalAddress;
	private String animalHospitalPhoneNo;
	private String hospitalLocationLatitude;
	private String hospitalLocationLongitude;
}
