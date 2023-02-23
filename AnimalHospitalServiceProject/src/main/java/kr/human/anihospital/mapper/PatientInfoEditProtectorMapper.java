package kr.human.anihospital.mapper;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.human.anihospital.vo.AnimalVO;

@Mapper
public interface PatientInfoEditProtectorMapper {
	//----------------------------------------------------------------------------------------------------
	// 환자 정보 수정시 보여줄 정보 조회 SQL
	//----------------------------------------------------------------------------------------------------
	AnimalVO selectPatient() throws SQLException;
	//----------------------------------------------------------------------------------------------------
	// 환자 정보 수정 SQL
	//----------------------------------------------------------------------------------------------------
	void updatePatient(Map<String, Object> map) throws SQLException;
	//----------------------------------------------------------------------------------------------------
	// 환자 등록 SQL
	//----------------------------------------------------------------------------------------------------
	void insertPatient(AnimalVO animalVO) throws SQLException;

}