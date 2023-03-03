package kr.human.anihospital.service;

import java.util.List;
import java.util.Map;

public interface ScheduleService {
	//----------------------------------------------------------------------------------------------------
	// 의사 스케줄을 풀캘린더에 표시해줄 메서드
	//----------------------------------------------------------------------------------------------------
	List<Map<String, Object>> selectScheduleDoctorArrJson(int seqDoctor) throws Exception;
	
	//----------------------------------------------------------------------------------------------------
	// 보호자 스케줄을 풀캘린더에 표시해줄 메서드
	//----------------------------------------------------------------------------------------------------
	List<Map<String, Object>> selectAllProSchedule(int seqMember) throws Exception;
}
