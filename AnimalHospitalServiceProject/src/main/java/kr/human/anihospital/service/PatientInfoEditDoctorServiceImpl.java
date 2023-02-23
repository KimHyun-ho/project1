package kr.human.anihospital.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.human.anihospital.mapper.PatientInfoEditDoctorMapper;
import kr.human.anihospital.vo.AnimalVO;
import lombok.extern.slf4j.Slf4j;

@Service("PatientInfoEditDoctorService")
@Slf4j
public class PatientInfoEditDoctorServiceImpl implements PatientInfoEditDoctorService {

	@Autowired
	PatientInfoEditDoctorMapper patientInfoEditDoctorMapper;

	// 의사의 환자 정보 수정 화면에서 보여줄 정보 조회
	@Override
	public AnimalVO patientInfoDoctor(int seqAnimal) {
		AnimalVO animalVO = new AnimalVO();
		try {
			animalVO = patientInfoEditDoctorMapper.patientInfoDoctor(seqAnimal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return animalVO;
	}

	// 의사가 환자 정보를 수정
	@Override
	public void updateAnimalDoctor(Map<String, Object> map) {
		log.info("PatientInfoEditDoctorService 수정값 : {}", map);
		try {
			patientInfoEditDoctorMapper.updateAnimalDoctor(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
