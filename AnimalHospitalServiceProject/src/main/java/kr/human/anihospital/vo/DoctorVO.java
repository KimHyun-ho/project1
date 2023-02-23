package kr.human.anihospital.vo;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorVO {
	//-----------------------------------------------------------
	// 의사 정보를 가지고 있는 VO
	//-----------------------------------------------------------
	private int seqDoctor;
	private String doctorId;
	private String doctorPassword;
	private String doctorName;
	private Date doctorWorkYear; // 면허취득일
	private Date doctorAge; // 생년월일
	private boolean doctorGender;
	private String doctorAddress;
	private String doctorPhoneNo;
	private int doctorRegistrationNumber;
	private String doctorPicture;
	private String doctorSay;
	private Date doctorJoinDate; // 회원가입일
	private boolean doctorRole;
	private String doctorWorkSpace;
	private String doctorEducation;
	private Date doctorUpdateDate; // 회원정보 수정일
	private String filePath;
}
