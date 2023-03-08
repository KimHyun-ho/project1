package kr.human.anihospital.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import kr.human.anihospital.vo.MemberVO;

public interface LoginService {
	//----------------------------------------------------------------------------------------------------
	// 아이디가 존재하는지 비교할 메서드
	//----------------------------------------------------------------------------------------------------
	int selectNaverLoginId(String memberEmailId) throws Exception;
	
	//----------------------------------------------------------------------------------------------------
	// 최초의 네이버 로그인시 정보 insert
	//----------------------------------------------------------------------------------------------------
	void insertNaverLogin(MemberVO memberVO) throws Exception;
	
	//----------------------------------------------------------------------------------------------------
	// 의사 회원가입시 아이디를 받아 seqMember를 찾아줄 메서드
	//----------------------------------------------------------------------------------------------------
	int selectNaverFindSeq(String memberEmailId) throws Exception;
	
	//----------------------------------------------------------------------------------------------------
	// 로그인시 이메일 아이디로 seqMember,memberRole을 찾아줄 메서드
	//----------------------------------------------------------------------------------------------------
	MemberVO selectNaverFindSession(String memberEmailId) throws Exception;
	
	//----------------------------------------------------------------------------------------------------
	// 의사일 경우 세션에 의사seq를 넣어주기 위해 값을 가져올 쿼리
	//----------------------------------------------------------------------------------------------------
	Map<String, Object> selectFindDoctorSeq(int seqMember) throws Exception;
	
	//----------------------------------------------------------------------------------------------------
	// 의사 최초 로그인시 정보를 insert해줄 메서드
	//----------------------------------------------------------------------------------------------------
	void insertNaverDoctorInfo(Map<String, Object> naverDoctorJoinMap,MultipartFile doctorPicture) throws Exception;
}
