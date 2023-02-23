package kr.human.anihospital.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.human.anihospital.vo.PatientInfoVO;
import kr.human.anihospital.vo.patientInfoDiagnosisListVO;

@Mapper
public interface PatientInfoMapper {
	List<patientInfoDiagnosisListVO> selectPatientInfoDiagnosis() throws Exception;
	PatientInfoVO selectPatientInfo(int seqAnimal) throws Exception;
}
