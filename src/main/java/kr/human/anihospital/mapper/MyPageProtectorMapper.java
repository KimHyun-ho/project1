package kr.human.anihospital.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.human.anihospital.vo.OneProtectorPatientListVO;
import kr.human.anihospital.vo.PatientDiagnosisListVO;
import kr.human.anihospital.vo.OneProtectorPostscriptListVO;
import kr.human.anihospital.vo.ProtectorVO;

@Mapper
public interface MyPageProtectorMapper {
	//----------------------------------------------------------------------------------------------------
	// 보호자정보 조회하는 메서드 
	//----------------------------------------------------------------------------------------------------
	ProtectorVO selectProtector(int seqProtector) throws Exception;
	
	//----------------------------------------------------------------------------------------------------
	// 환자정보리스트 조회하는 메서드
	//----------------------------------------------------------------------------------------------------
	List<PatientDiagnosisListVO> selectPatientDiagnosisList(int seqProtector) throws Exception;
	
	//----------------------------------------------------------------------------------------------------
	// 보호자 정보 수정하는 메서드
	//----------------------------------------------------------------------------------------------------
	void updateProtector(Map<String, Object> map) throws Exception;
	
	//----------------------------------------------------------------------------------------------------
	// 이전 진료 내역리스트 보여주는 메서드
	//----------------------------------------------------------------------------------------------------
	List<OneProtectorPatientListVO> selectOneProtectorPatientList(int seqProtector) throws Exception;
	
	//----------------------------------------------------------------------------------------------------
	// 후기 리스트 보여주는 메서드
	//----------------------------------------------------------------------------------------------------
	List<OneProtectorPostscriptListVO> selectOneProtectorPostcriptList(int seqProtector) throws Exception;
}
