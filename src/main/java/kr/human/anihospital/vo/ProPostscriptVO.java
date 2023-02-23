package kr.human.anihospital.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProPostscriptVO {
	//-----------------------------------------------------------
	// 후기 내용을 가지고 있는 VO
	//-----------------------------------------------------------
	private int seqPostscript;
	private int seqProtector;
	private int seqDoctor;
	private int seqDiagnosis;
	private int seqAnimal;
	private Date postscriptReportingDate;
	private String postscriptContent;
	private int PostscriptSatisfactionrating;
	private Date postscriptUpdateDate;
}