package kr.human.anihospital.service;

import kr.human.anihospital.vo.DiagnosisAddVO;
import kr.human.anihospital.vo.MedicineVO;
import kr.human.anihospital.vo.PatientInfoVO;

public interface DiagnosisAddService {
	// ----------------------------------------------------------------------------------------------
	// 환자 정보을 표시해줄 메서드
	// ----------------------------------------------------------------------------------------------
	PatientInfoVO selectPatientInfo(int seqAnimal) throws Exception;
	
	// ----------------------------------------------------------------------------------------------
	// 진료내용을 추가해줄 메서드
	// ----------------------------------------------------------------------------------------------
	void insertDiagnosis(DiagnosisAddVO diagnosisAddVO) throws Exception;
	
	// ----------------------------------------------------------------------------------------------
	// 처방약을 조회할 내용을 가져올 메서드
	// ----------------------------------------------------------------------------------------------
	MedicineVO selectMedicine() throws Exception;
	
}
