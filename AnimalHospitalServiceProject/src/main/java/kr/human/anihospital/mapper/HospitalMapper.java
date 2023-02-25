package kr.human.anihospital.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import kr.human.anihospital.vo.AnimalHospitalVO;

@Mapper
public interface HospitalMapper {
	//----------------------------------------------------------------------------------------------------
	// 병원정보를 가져올 메서드
	//----------------------------------------------------------------------------------------------------
	AnimalHospitalVO selectHospital(int seq) throws SQLException;
	//----------------------------------------------------------------------------------------------------
	// 병원정보를 추가할 메서드
	//----------------------------------------------------------------------------------------------------
	void insertAnimalHospital(AnimalHospitalVO animalHospitalVO) throws Exception;
	//----------------------------------------------------------------------------------------------------
	// 병원정보를 수정할 메서드
	//----------------------------------------------------------------------------------------------------
	void updateAnimalHospital(AnimalHospitalVO animalHospitalVO) throws Exception;
}
