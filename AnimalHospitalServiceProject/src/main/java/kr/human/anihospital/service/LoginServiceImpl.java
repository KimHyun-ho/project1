package kr.human.anihospital.service;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
	public int selectNaverLoginId(String memberEmailId) throws Exception {
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

	//----------------------------------------------------------------------------------------------------
	// 로그인시 이메일 아이디로 seqMember,memberRole을 찾아줄 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public MemberVO selectNaverFindSession(String memberEmailId) {
		MemberVO memberVO = new MemberVO();
		try {
			memberVO = loginMapper.selectNaverFindSession(memberEmailId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memberVO;
	}
	
	//----------------------------------------------------------------------------------------------------
	// 의사일 경우 세션에 의사seq를 넣어주기 위해 값을 가져올 쿼리
	//----------------------------------------------------------------------------------------------------
	@Override
	public Map<String, Object> selectFindDoctorSeq(int seqMember) {
		Map<String, Object> seqDoctorMap = null;
		try {
			seqDoctorMap = loginMapper.selectFindDoctorSeq(seqMember);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return seqDoctorMap;
	}
	
	//----------------------------------------------------------------------------------------------------
	// 의사 최초 로그인시 정보를 insert해줄 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public void insertNaverDoctorInfo(Map<String, Object> naverDoctorJoinMap, MultipartFile doctorPicture) {
		// 저장할 경로를 지정
		String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
		// UUID(식별자)를 사용해 사용해 랜덤으로 이름 만들어줌
		UUID uuid = UUID.randomUUID();

		// 랜덤식별자_원래 파일 이름 = 저장될 파일이름 지정
		String fileName = uuid + "_" + doctorPicture.getOriginalFilename();
		
		// File이 생성되며, 이름은 "name", projectPath 라는 경로에 담긴다
		File saveFile = new File(projectPath, fileName);
		
		try {
			doctorPicture.transferTo(saveFile);
		} catch (IllegalStateException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		// DB에 파일 넣기
		naverDoctorJoinMap.put("doctorPicture", fileName);
		// 저장되는 경로 설정
		naverDoctorJoinMap.put("doctorPicturePath", "/files/" + fileName);
		try {
			loginMapper.insertNaverDoctorInfo(naverDoctorJoinMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
