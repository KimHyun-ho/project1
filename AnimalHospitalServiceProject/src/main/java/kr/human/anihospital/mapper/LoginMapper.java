package kr.human.anihospital.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.human.anihospital.vo.MemberVO;

@Mapper
public interface LoginMapper {
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
}