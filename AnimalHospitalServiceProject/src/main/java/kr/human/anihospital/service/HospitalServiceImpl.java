package kr.human.anihospital.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

import kr.human.anihospital.mapper.HospitalMapper;
import kr.human.anihospital.vo.AnimalHospitalVO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HospitalServiceImpl implements HospitalService {
	@Autowired
	HospitalMapper hospitalMapper;
	
	@Autowired
	PlatformTransactionManager manager;

	//----------------------------------------------------------------------------------------------------
	// 병원정보를 추가해줄 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public void insertAnimalHospitalInfo(AnimalHospitalVO animalHospitalVO) throws Exception {
		// insert를 실행할 mapper부르기
		hospitalMapper.insertAnimalHospital(animalHospitalVO);
		// insert하려는 데이터가 잘 넘어오고 있는지 로그로 확인해보기
		log.info(animalHospitalVO.getAnimalHospitalName()
				+ animalHospitalVO.getAnimalHospitalAddress() + animalHospitalVO.getAnimalHospitalPhoneNo());
	}
	
	//----------------------------------------------------------------------------------------------------
	// 병원정보를 수정해줄 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public void updateAnimalHospitalInfo(AnimalHospitalVO animalHospitalVO) throws Exception {
		// update를 실행할 mapper부르기
		hospitalMapper.updateAnimalHospital(animalHospitalVO);
		// update하려는 데이터가 잘 넘어오고 있는지 로그로 확인해보기
		log.info(animalHospitalVO.getAnimalHospitalName()
				+ animalHospitalVO.getAnimalHospitalAddress() + animalHospitalVO.getAnimalHospitalPhoneNo());
	}

	//----------------------------------------------------------------------------------------------------
	// 병원정보를 보여줄 메서드(seq)
	//----------------------------------------------------------------------------------------------------
	@Override
	public AnimalHospitalVO selectHospital(int seq) throws Exception {
		AnimalHospitalVO animalHospitalVO = new AnimalHospitalVO();
		animalHospitalVO = hospitalMapper.selectHospital(seq);
		return animalHospitalVO;
	}

}
