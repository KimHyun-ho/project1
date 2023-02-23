package kr.human.anihospital.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import kr.human.anihospital.vo.DoctorVO;
import kr.human.anihospital.vo.ProtectorVO;

// 회원가입을 위한 Mapper
@Mapper
public interface MemberJoinMapper {

	// 보호자 회원가입
	void protectorMemberJoin(ProtectorVO protectorVO) throws SQLException;

	// 의사 회원가입
	void doctorMemberJoin(DoctorVO doctorVO) throws SQLException;
}
