package kr.human.anihospital.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.human.anihospital.mapper.ProtectorPageMapper;
import kr.human.anihospital.vo.ProAnimalListVO;
import kr.human.anihospital.vo.ProDiaMedicineVO;
import kr.human.anihospital.vo.ProDiagnosisVO;
import kr.human.anihospital.vo.ProMyPageDetailVO;
import lombok.extern.slf4j.Slf4j;

@Service("ProtectorPageService")
@Slf4j
public class ProtectorPageServiceImpl implements ProtectorPageService{
	
	@Autowired
	ProtectorPageMapper protectorPageMapper;

	//----------------------------------------------------------------------------------------------------
	// 보호자 정보 페이지에서 보호자,환자 정보를 화면에 표시해줄 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public List<ProMyPageDetailVO> selectAllProMypageVOList(int seqMember) {
		// 보호자 정보 페이지의 보호자,환자 정보를 Mapper에 넘겨주기
		List<ProMyPageDetailVO> proMyPageDetailVOList = null;
		try {
			// Mapper에 SQL 실행 시 필요한 데이터 넘겨주기 및 실행할 메서드 부르기
			proMyPageDetailVOList = protectorPageMapper.selectAllProMypageVOList(seqMember);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Mapper가 실행되고 나서 가져온 데이터 확인하기
		log.info("selectAllProMypageVOList Mapper에서 넘어온 값(서비스) : {}", proMyPageDetailVOList);
		return proMyPageDetailVOList;
	}
	
	//----------------------------------------------------------------------------------------------------
	// 보호자 정보 페이지에서 환자의 진료리스트를 화면에 표시해줄 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public List<ProMyPageDetailVO> selectAllProDiagnosisList(int seqMember){
		// 보호자 정보 페이지의 환자 진료 리스트를 Mapper에 넘겨주기
		List<ProMyPageDetailVO> proMyPageDianosisList = null;
		try {
			// Mapper에 SQL 실행 시 필요한 데이터 넘겨주기 및 실행할 메서드 부르기
			proMyPageDianosisList = protectorPageMapper.selectAllProDiagnosisList(seqMember);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Mapper가 실행되고 나서 가져온 데이터 확인하기
		log.info("selectAllProDiagnosisList Mapper에서 넘어온 값(서비스) : {}", proMyPageDianosisList);
		return proMyPageDianosisList;
	}
	
	//----------------------------------------------------------------------------------------------------
	// 보호자 정보를 수정하는 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public void updateProMypage(Map<String, Object> updateMap){
		try {
			protectorPageMapper.updateProMypage(updateMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//----------------------------------------------------------------------------------------------------
	// 보호자가 환자를 추가하는 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public void insertProPatient(Map<String, Object> insertPatientMap){
		try {
			protectorPageMapper.insertProPatient(insertPatientMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	//----------------------------------------------------------------------------------------------------
	// 보호자가 환자 정보를 수정하는 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public void updateProPatient(Map<String, Object> updatePatientMap){
		try {
			protectorPageMapper.updateProPatient(updatePatientMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//----------------------------------------------------------------------------------------------------
	// 한 명의 보호자에 따른 환자의 진료내역 리스트를 화면에 표시해줄 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public List<ProAnimalListVO> selectAllProAnimalListVO(int seqMember) {
		// 환자 진료내역 리스트를 Mapper에 넘겨주기
		List<ProAnimalListVO> proAnimalList = null;
		try {
			// Mapper에 SQL 실행 시 필요한 데이터 넘겨주기 및 실행할 메서드 부르기
			proAnimalList = protectorPageMapper.selectAllProAnimalListVO(seqMember);
		}catch (Exception e) {
			e.printStackTrace();
		}
		// Mapper가 실행되고 나서 가져온 데이터 확인하기
		log.info("selectAllProAnimalListVO Mapper에서 넘어온 값(서비스) : {}", proAnimalList);
		return proAnimalList;
	}
	
	//----------------------------------------------------------------------------------------------------
	// 한 명의 보호자에 따른 환자의 진료내역 리스트를 화면에 표시해줄 메서드
	//----------------------------------------------------------------------------------------------------	
	@Override
	public ProDiagnosisVO selectOneProDiagnosisVO(Map<String, Object> diagnosisMap) {
		// 환자의 상세 진료내역 리스트를 Mapper에 넘겨주기
		ProDiagnosisVO proDiagnosisVO = null;
		try {
			// Mapper에 SQL 실행 시 필요한 데이터 넘겨주기 및 실행할 메서드 부르기
			proDiagnosisVO = protectorPageMapper.selectOneProDiagnosisVO(diagnosisMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Mapper가 실행되고 나서 가져온 데이터 확인하기
		log.info("selectOneProDiagnosisVO Mapper에서 넘어온 값(서비스) : {}", proDiagnosisVO);
		return proDiagnosisVO;
	}

	//----------------------------------------------------------------------------------------------------
	// 한 명의 보호자에 따른 환자의 상세진료 내역의 처방목록을 화면에 표시해줄 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public List<ProDiaMedicineVO> selectListProDiaMedicineVO(Map<String, Object> diagnosisMap) {
		// 환자의 상세 진료내역 리스트를 Mapper에 넘겨주기
		List<ProDiaMedicineVO> proDiaMedicineVOList = null;
		try {
			// Mapper에 SQL 실행 시 필요한 데이터 넘겨주기 및 실행할 메서드 부르기
			proDiaMedicineVOList = protectorPageMapper.selectListProDiaMedicineVO(diagnosisMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Mapper가 실행되고 나서 가져온 데이터 확인하기
		log.info("selectListProDiaMedicineVO Mapper에서 넘어온 값(서비스) : {}", proDiaMedicineVOList);
		return proDiaMedicineVOList;
	}
}

