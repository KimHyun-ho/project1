package kr.human.anihospital.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.human.anihospital.mapper.PatientInfoMapper;
import kr.human.anihospital.vo.PatientInfoVO;
import kr.human.anihospital.vo.patientInfoDiagnosisListVO;
import lombok.extern.slf4j.Slf4j;

@Service("PatientInfoService")
@Slf4j
public class PatientInfoServiceImpl implements PatientInfoService {
	
	@Autowired
	PatientInfoMapper patientInfoMapper;
	
	@Override
	public List<patientInfoDiagnosisListVO> selectPatientInfoDiagnosis() {
		List<patientInfoDiagnosisListVO> pidliat = null;
		try {
			pidliat = patientInfoMapper.selectPatientInfoDiagnosis();
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("selectPatientInfoDiagnosis 실행한 값(서비스) : {}", pidliat);
		return pidliat;
	}

	@Override
	public PatientInfoVO selectPatientInfo(int seqAnimal) {
		PatientInfoVO patientInfoVO = null;
		try {
			patientInfoVO = patientInfoMapper.selectPatientInfo(seqAnimal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("selectPatientInfo 실행한 값(서비스) : {}", patientInfoVO);
		return patientInfoVO;
	}
}
