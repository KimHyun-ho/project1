package kr.human.anihospital.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticeOneDetailVO {
	//-----------------------------------------------------------
	// 공지 상세 내용을 가지고 있는 VO
	//-----------------------------------------------------------
	private int seqNotice;
	private String doctorName;
	private String animalHospitalName;
	private String noticeUpdateDate;
	private String noticeSubject;
	private String noticeContent;
}
