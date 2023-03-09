package kr.human.anihospital.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class KakaoUserInfoVO {

	public Long id;
	@JsonProperty("connected_at")
	public String connectedAt;
	public Properties properties;
	@JsonProperty("kakao_account")
	public KakaoAccount kakaoAccount;

	@Data
	public class Properties {
		public String nickname;
	}

	@Data
	public class KakaoAccount {
		@JsonProperty("profile_nickname_needs_agreement")
		public Boolean profileNicknameNeedsAgreement;
		public Profile profile;
		@JsonProperty("has_email")
		public Boolean hasEmail;
		@JsonProperty("email_needs_agreement")
		public Boolean emailNeedsAgreement;
		@JsonProperty("is_email_valid")
		public Boolean isEmailValid;
		@JsonProperty("is_email_verified")
		public Boolean isEmailVerified;
		public String email;
		@JsonProperty("has_birthday")
		public Boolean hasBirthday;
		@JsonProperty("birthday_needs_agreement")
		public Boolean birthdayNeedsAgreement;
		public String birthday;
		@JsonProperty("birthday_type")
		public String birthdayType;
		@JsonProperty("has_gender")
		public Boolean hasGender;
		@JsonProperty("gender_needs_agreement")
		public Boolean genderNeedsAgreement;
		public String gender;

		@Data
		public class Profile {
			public String nickname;
		}
	}

}
