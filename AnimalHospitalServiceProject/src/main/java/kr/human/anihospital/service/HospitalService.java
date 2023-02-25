package kr.human.anihospital.service;

import kr.human.anihospital.vo.AnimalHospitalVO;

public interface HospitalService {
	//----------------------------------------------------------------------------------------------------
	// 병원정보를 가져올 메서드
	//----------------------------------------------------------------------------------------------------
	AnimalHospitalVO selectHospital(int seq) throws Exception;
	//----------------------------------------------------------------------------------------------------
	// 병원정보를 추가할 메서드
	//----------------------------------------------------------------------------------------------------
	void insertAnimalHospitalInfo(AnimalHospitalVO animalHospitalVO) throws Exception;
	//----------------------------------------------------------------------------------------------------
	// 병원정보를 수정할 메서드
	//----------------------------------------------------------------------------------------------------
	void updateAnimalHospitalInfo(AnimalHospitalVO animalHospitalVO) throws Exception;
}
