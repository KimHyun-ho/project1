package kr.human.anihospital.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorInfoVO {

	private int seqMember;
	private String memberEmailId;
	private String memberName;
	private boolean memberGender; // DataBase => 0 : false, 남자 / 1 : true, 여자
	private Date memberBirthDay;
	private String memberPhoneNo;
	private boolean memberRole; // 회원의 종류 => 0 : false, 의사 / 1 : true, 보호자
	private Date memberJoinDate;
	private Date memberUpdateDate;
	private boolean memberLeaveFlag; // 회원 탈퇴 여부 => 0 : false, 탈퇴 X / 1 : true, 탈퇴 O
	private String animalHospitalName;
	private int seqDoctor;
	private Date doctorWorkYear; // 수의사 면허 취득일
	private String doctorRegNo; // 수의사 면허 번호
	private Date doctorUpdateDate;
	private String doctorWorkSpace;
	private String doctorSay;
	private String doctorEducation;
	private String doctorPicture;
	private String doctorPicturePath;
}
