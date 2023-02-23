package kr.human.anihospital.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProtectorVO {
	private int seqProtector;
	private String protectorId;
	private String protectorPassword;
	private String protectorName;
	private Date protectorAge;
	private boolean protectorGender;
	private String protectorAddress;
	private String protectorPhoneNo;
	private String protectorImportantPoint;
	private Date protectorJoinDate;
	private boolean protectorRole;
	private Date protectorUpdateDate;
	private boolean protectorLeaveFlag;
	
}
