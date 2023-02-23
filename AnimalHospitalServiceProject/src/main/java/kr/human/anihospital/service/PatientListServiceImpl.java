package kr.human.anihospital.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

import kr.human.anihospital.mapper.PatientListMapper;
import kr.human.anihospital.vo.PatientListVO;
import lombok.extern.slf4j.Slf4j;

@Service("PatientListService")
@Slf4j
public class PatientListServiceImpl implements PatientListService {

	@Autowired
	PatientListMapper patientListMapper;

	//----------------------------------------------------------------------------------------------------
	// 환자 정보를 리스트로 화면에 표시해줄 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public List<PatientListVO> selectPatientList() {
		// 해당 환자 정보를 리스트로 Mapper에 넘겨주기
		List<PatientListVO> patientList = null;
		try {
			// Mapper에 SQL 실행 시 필요한 데이터 넘겨주기 및 실행할 메서드 부르기
			patientList = patientListMapper.selectPatientList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Mapper가 실행되고 나서 가져온 데이터 확인하기
		log.info("selectPatientList Mapper에서 넘어온 값(서비스) : {}", patientList);
		return patientList;
	}

}
