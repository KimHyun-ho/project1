package kr.human.anihospital.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.human.anihospital.vo.PatientListVO;

@Mapper
public interface PatientListMapper {
	//----------------------------------------------------------------------------------------------------
	// 환자 정보를 리스트로 화면에 표시해줄 메서드
	//----------------------------------------------------------------------------------------------------
	List<PatientListVO> selectPatientList() throws Exception;
}
