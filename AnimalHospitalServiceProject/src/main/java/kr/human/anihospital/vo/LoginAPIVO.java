package kr.human.anihospital.vo;

import lombok.Getter;

@Getter
public class LoginAPIVO {
	
	// 네이버 로그인 API RESOURCE
	private final String naverClientId = "jjZSe4Dzr_Kpcinyf4bH";
	private final String naverRedirectURI =  "http://localhost:8080/naverCallback";
	private final String naverClientSecret = "TNFD4HY8OF";
	
	
	
	
	// 카카오 로그인 API RESOURCE
	private final String kakaoGrantType = "authorization_code";
	private final String kakaoClientId = "f559846a6918516d5fb1be1cc8032c5a";
	private final String kakaoRedirectUri = "http://localhost:8080/auth/kakao/callback";
	private final String kakaoRequestTokenURI = "https://kauth.kakao.com/oauth/token";
	private final String kakaoRequestUserInfoURI = "https://kapi.kakao.com/v2/user/me";
}
