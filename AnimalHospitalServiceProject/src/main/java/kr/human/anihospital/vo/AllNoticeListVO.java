package kr.human.anihospital.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllNoticeListVO {
	//-----------------------------------------------------------
	// 모든 공지 내용을 가지고 있는 VO
	//-----------------------------------------------------------
	private int seqNotice;
	private int seqDoctor;
	private int seqAnimalHospital;
	private String noticeSubject;
	private String noticeContent;
	private String noticeWriteDate;
	private String noticeUpdateDate;
	private String animalHospitalName;
	private String doctorName;
}
