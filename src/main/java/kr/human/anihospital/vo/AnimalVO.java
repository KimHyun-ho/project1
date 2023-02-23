package kr.human.anihospital.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalVO {
	private int seqAnimal;
	private int seqProtector;
	private int seqDoctor;
	private String animalName;
	private String animalType;
	private String animalSize;
	private float animalWeight;
	private Date animalAge;
	private boolean animalGender;
	private String animalImportantSymptom;
	private Date animalJoinDate;
	private String animalPicture;
	private String animalVideo;
	private String filePath;
	private String vidfilePath;
	private Date animalUpdateDate;
}