package kr.human.anihospital.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.human.anihospital.vo.ProAnimalListVO;
import kr.human.anihospital.vo.ProDiaMedicineVO;
import kr.human.anihospital.vo.ProDiagnosisVO;
import kr.human.anihospital.vo.ProMyPageDetailVO;

@Mapper
public interface ProtectorPageMapper {
	//----------------------------------------------------------------------------------------------------
	// 보호자 정보 페이지에서 보호자,환자 정보를 화면에 표시해줄 메서드
	//----------------------------------------------------------------------------------------------------
	List<ProMyPageDetailVO> selectAllProMypageVOList(int seqMember) throws Exception;
	
	//----------------------------------------------------------------------------------------------------
	// 보호자 정보 페이지에서 환자의 진료리스트를 화면에 표시해줄 메서드
	//----------------------------------------------------------------------------------------------------
	List<ProMyPageDetailVO> selectAllProDiagnosisList(int seqMember) throws Exception;
	
	//----------------------------------------------------------------------------------------------------
	// 보호자 정보를 수정하는 메서드
	//----------------------------------------------------------------------------------------------------
	void updateProMypage(Map<String, Object> updateMap) throws Exception;
	
	//----------------------------------------------------------------------------------------------------
	// 보호자가 환자를 추가하는 메서드
	//----------------------------------------------------------------------------------------------------
	void insertProPatient(Map<String, Object> insertPatientMap) throws Exception;
	
	//----------------------------------------------------------------------------------------------------
	// 보호자가 환자 정보를 수정하는 메서드
	//----------------------------------------------------------------------------------------------------
	void updateProPatient(Map<String, Object> updatePatientMap) throws Exception;
	
	//----------------------------------------------------------------------------------------------------
	// 한 명의 보호자에 따른 환자의 진료내역 리스트를 화면에 표시해줄 메서드
	//----------------------------------------------------------------------------------------------------
	List<ProAnimalListVO> selectAllProAnimalListVO(int seqMember) throws Exception;

	//----------------------------------------------------------------------------------------------------
	// 한 명의 보호자에 따른 환자의 1개의 상세진료 내역을 화면에 표시해줄 메서드
	//----------------------------------------------------------------------------------------------------
	ProDiagnosisVO selectOneProDiagnosisVO(Map<String, Object> diagnosisMap) throws Exception;
	
	//----------------------------------------------------------------------------------------------------
	// 한 명의 보호자에 따른 환자의 상세진료 내역의 처방목록을 화면에 표시해줄 메서드
	//----------------------------------------------------------------------------------------------------
	List<ProDiaMedicineVO> selectListProDiaMedicineVO(Map<String, Object> diagnosisMap) throws Exception;

}
