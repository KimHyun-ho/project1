package kr.human.anihospital.mapper;


import org.apache.ibatis.annotations.Mapper;

import kr.human.anihospital.vo.DiagnosisAddVO;
import kr.human.anihospital.vo.MedicineVO;
import kr.human.anihospital.vo.PatientInfoVO;

@Mapper
public interface DiagnosisAddMapper {
	// ----------------------------------------------------------------------------------------------
	// 환자 정보을 가져올 메서드
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
