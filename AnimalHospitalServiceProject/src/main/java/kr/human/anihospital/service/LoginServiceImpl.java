package kr.human.anihospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.human.anihospital.mapper.LoginMapper;
import kr.human.anihospital.vo.MemberVO;

@Service("LoginService")
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	LoginMapper loginMapper;
	
	//----------------------------------------------------------------------------------------------------
	// 아이디가 존재하는지 비교할 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public int selectListLoginId(String memberEmailId) throws Exception {
		int count = 0;
		try {
			count = loginMapper.selectNaverLoginId(memberEmailId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	//----------------------------------------------------------------------------------------------------
	// 최초의 네이버 로그인시 정보 insert
	//----------------------------------------------------------------------------------------------------
	@Override
	public void insertNaverLogin(MemberVO memberVO)  {
		try {
			loginMapper.insertNaverLogin(memberVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//----------------------------------------------------------------------------------------------------
	// 의사 회원가입시 아이디를 받아 seqMember를 찾아줄 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public int selectNaverFindSeq(String memberEmailId) {
		int seqMember = 0;
		try {
			seqMember = loginMapper.selectNaverFindSeq(memberEmailId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return seqMember;
	}


}
