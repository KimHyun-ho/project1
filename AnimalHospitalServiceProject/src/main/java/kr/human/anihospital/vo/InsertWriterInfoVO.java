package kr.human.anihospital.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsertWriterInfoVO {
	private int seqDoctor;
	private int seqAnimalHospital;
	private String doctorName;
	private String animalHospitalName;
	private String today;
}
