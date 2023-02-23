package kr.human.anihospital.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticeVO {
	private int seqDoctor;
	private int seqAnimalHospital;
	private String noticeSubject;
	private String noticeContent;
}
