package kr.human.anihospital.service;

import java.util.Map;

import kr.human.anihospital.vo.AnimalVO;

public interface PatientInfoEditDoctorService {
	// 환자 수정 화면에서 수정 전 보여줄 데이터
	AnimalVO patientInfoDoctor(int seqAnimal) throws Exception;
	// 의사의 환자 정보 업데이트
	void updateAnimalDoctor(Map<String, Object> map) throws Exception;
}
