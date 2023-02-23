package kr.human.anihospital.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PharmacyLocationVO {
	//-----------------------------------------------------------
	// 약국의 정보를 가지고 있는 VO
	//-----------------------------------------------------------
	private int seqPharmacyLocation;
	private String animalPharmacyName;
	private String animalPharmacyAddress;
	private String animalPharmacyPhoneNo;
	private String pharmacyLocationLatitude;
	private String pharmacyLocationLongitude;
}
