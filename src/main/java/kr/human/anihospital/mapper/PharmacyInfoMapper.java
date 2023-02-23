package kr.human.anihospital.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import kr.human.anihospital.vo.PharmacyLocationVO;

@Mapper
public interface PharmacyInfoMapper {

	//-----------------------------------------------------------
	// seq를 받아 해당 seq의 데이터를 반환하는 메소드
	//-----------------------------------------------------------
	PharmacyLocationVO selectPharmacy(int seq) throws SQLException;

	//-----------------------------------------------------------
	// 약국 정보를 등록하는 sql 메소드
	//-----------------------------------------------------------
	void insertPharmacy(PharmacyLocationVO pharmacyLocationVO) throws SQLException;

	//-----------------------------------------------------------
	// 약국 정보를 수정하는 sql 메소드
	//-----------------------------------------------------------
	void updatePharmacy(PharmacyLocationVO pharmacyLocationVO) throws SQLException;
}
