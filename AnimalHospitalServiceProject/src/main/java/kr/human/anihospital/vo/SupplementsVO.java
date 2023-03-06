package kr.human.anihospital.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplementsVO {
	private String supplementsName;
	private String supplementsCompany;
	private String supplementsSideEffect;
	private String supplementsUpdateDate;
}
