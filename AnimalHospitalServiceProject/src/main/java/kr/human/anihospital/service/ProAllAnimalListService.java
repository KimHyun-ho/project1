package kr.human.anihospital.service;

import java.util.List;

import kr.human.anihospital.vo.ProAllAnimalListVO;

public interface ProAllAnimalListService {
	//----------------------------------------------------------------------------------------------------
	// 한 명의 보호자에 따른 환자의 진료내역 리스트를 화면에 표시해줄 메서드
	//----------------------------------------------------------------------------------------------------
	List<ProAllAnimalListVO> selectAnimalList(int seqProtector) throws Exception;
	
}
