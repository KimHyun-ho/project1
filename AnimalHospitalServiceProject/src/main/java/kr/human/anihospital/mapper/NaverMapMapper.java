package kr.human.anihospital.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.human.anihospital.vo.AnimalHospitalVO;
import kr.human.anihospital.vo.PharmacyLocationVO;

@Mapper
public interface NaverMapMapper {
	List<AnimalHospitalVO> selectHospitalMap() throws SQLException;

	List<PharmacyLocationVO> selectPharmacyMap() throws SQLException;
}
