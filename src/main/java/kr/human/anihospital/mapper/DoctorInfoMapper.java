package kr.human.anihospital.mapper;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.human.anihospital.vo.DoctorVO;

@Mapper
public interface DoctorInfoMapper {
	//----------------------------------------------------------------------------------------------------
	// 의사 정보를 화면에 표시해줄 메서드
	//----------------------------------------------------------------------------------------------------
	DoctorVO viewMyInfoDoctor(int seq) throws SQLException;

	//----------------------------------------------------------------------------------------------------
	// 수정한 의사 정보를 화면에 표시해줄 메서드
	//----------------------------------------------------------------------------------------------------
	void editMyInfoDoctor(Map<String, Object> map) throws SQLException;
}
