package kr.human.anihospital.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberVO {
	
	private int seqMember;
	private String memberEmailId;
	private String memberName;
	private boolean memberGender;
	private String memberBirthdate;
	private String memberPhoneNo;
	private boolean memberRole;
	private String memberJoinDate;
	private String memberUpdateDate;
	private boolean memberLeaveFlag;
}
