package kr.human.anihospital.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import kr.human.anihospital.vo.AnimalVO;

@Mapper
public interface PatientAddMapper {
	//----------------------------------------------------------------------------------------------------
	// 환자등록을 해줄 메서드
	//----------------------------------------------------------------------------------------------------
	void insertPatient(AnimalVO animalVO) throws SQLException;
}