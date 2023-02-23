package kr.human.anihospital.service;

import java.util.List;

import kr.human.anihospital.vo.PatientListVO;

public interface PatientListService {
	//----------------------------------------------------------------------------------------------------
	// 환자 정보를 리스트로 화면에 표시해줄 메서드
	//----------------------------------------------------------------------------------------------------
	List<PatientListVO> selectPatientList() throws Exception;
}
