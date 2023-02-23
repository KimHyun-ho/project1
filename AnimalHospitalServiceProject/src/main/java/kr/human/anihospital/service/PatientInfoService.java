package kr.human.anihospital.service;

import java.util.List;

import kr.human.anihospital.vo.PatientInfoVO;
import kr.human.anihospital.vo.patientInfoDiagnosisListVO;

public interface PatientInfoService {
	List<patientInfoDiagnosisListVO> selectPatientInfoDiagnosis() throws Exception;
	PatientInfoVO selectPatientInfo(int seqAnimal) throws Exception;
}
