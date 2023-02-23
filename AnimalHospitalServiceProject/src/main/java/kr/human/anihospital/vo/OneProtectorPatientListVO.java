package kr.human.anihospital.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OneProtectorPatientListVO {
	//-----------------------------------------------------------
	// 한 보호자의 환자(들)의 내용을 가지고 있는 VO
	//-----------------------------------------------------------
	private String animalName;
	private int animalAge;
	private String animalType;
	private String animalSize;
	private int animalWeight;  
	private String protectorId;
	private String protectorName; 
	private String protectorPhone;
	private String protectorAddress;

}
