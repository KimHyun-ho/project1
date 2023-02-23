package kr.human.anihospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.human.anihospital.mapper.DiagnosisAddMapper;
import kr.human.anihospital.vo.DiagnosisAddVO;
import kr.human.anihospital.vo.MedicineVO;
import kr.human.anihospital.vo.PatientInfoVO;
import lombok.extern.slf4j.Slf4j;

@Service("DiagnosisAddService")
@Slf4j
public class DiagnosisAddServiceImpl implements DiagnosisAddService {

	//selectAll 매퍼
	@Autowired
	DiagnosisAddMapper diagnosisAddMapper;
	//----------------------------------------------------------------------------------------------------
	// 환자 정보를 화면에 표시해줄 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public PatientInfoVO selectPatientInfo(int seqAnimal) {
		// 화자 정보를 담을 그릇 준비하기
		PatientInfoVO patientInfoVO = null;
		try {
			// 데이터를 가져올 mapper 부르기
			patientInfoVO = diagnosisAddMapper.selectPatientInfo(seqAnimal);
		}catch (Exception e) {
			e.printStackTrace();
		}
		// 가저온 데이터 로그로 찍어보기
		log.info("selectPatientInfo mapper에서 넘어온 값(서비스) : {}", patientInfoVO);
		return patientInfoVO ;
	}
	
	//----------------------------------------------------------------------------------------------------
	// 진료내용을 추가해줄 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public void insertDiagnosis(DiagnosisAddVO diagnosisAddVO) {
		try {
			// 추가하려는 데이터가 잘 넘어오고 있는지 로그로 확인해보기
			log.info("컨트롤러에서 넘어온 값(서비스) : {}", diagnosisAddVO);
			// insert를 실행할 mapper부르기
			diagnosisAddMapper.insertDiagnosis(diagnosisAddVO);
			log.info("추가할 내용을 mapper에서 넘어온 값(서비스) : {}", diagnosisAddVO);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ----------------------------------------------------------------------------------------------
	// 처방약을 조회할 내용을 가져올 메서드
	// ----------------------------------------------------------------------------------------------
	@Override
	public MedicineVO selectMedicine() throws Exception {
		// 처방약 내용을 담을 그릇 준비하기
		MedicineVO medicineVO = null;
		try {
			// 데이터를 가져올 mapper 부르기
			medicineVO = diagnosisAddMapper.selectMedicine();
		}catch (Exception e) {
			e.printStackTrace();
		}
		// 가져온 데이터 로그로 찍어보기
		log.info("selectMedicine mapper에서 넘어온 값(서비스) : {}", medicineVO);
		return medicineVO ;
	}
}
