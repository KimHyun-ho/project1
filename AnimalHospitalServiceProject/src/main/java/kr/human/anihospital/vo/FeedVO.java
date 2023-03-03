package kr.human.anihospital.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedVO {
	private String feedName;
	private String feedCompany;
	private String feedAllergySymptom;
	private String feedSideEffect;
	private String feedUpdateDate;
}
