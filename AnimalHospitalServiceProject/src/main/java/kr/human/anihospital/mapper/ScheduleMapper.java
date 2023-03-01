package kr.human.anihospital.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ScheduleMapper {
	//----------------------------------------------------------------------------------------------------
	// 의사 스케줄을 풀캘린더에 표시해줄 메서드
	//----------------------------------------------------------------------------------------------------
	List<Map<String, Object>> selectScheduleDoctorArrJson(int seqDoctor) throws Exception;
}
