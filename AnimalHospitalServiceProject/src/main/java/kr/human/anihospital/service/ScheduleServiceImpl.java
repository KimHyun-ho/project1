package kr.human.anihospital.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.human.anihospital.mapper.ScheduleMapper;
import lombok.extern.slf4j.Slf4j;

@Service("ScheduleService")
@Slf4j
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	ScheduleMapper scheduleMapper;
	
	//----------------------------------------------------------------------------------------------------
	// 의사 스케줄을 풀캘린더에 표시해줄 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public List<Map<String, Object>> selectScheduleDoctorArrJson(int seqDoctor) {
		// 데이터를 담을 그릇 준비
		List<Map<String, Object>> scheduleDoctorList = null;
		try {
			// 데이터 조회를 위해 mapper호출
			scheduleDoctorList = scheduleMapper.selectScheduleDoctorArrJson(seqDoctor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 데이터가 잘 담겨있는지 로그찍어보기
		log.info("selectScheduleDoctorArrJson실행 매퍼에서 돌아온 값(서비스) : {}", scheduleDoctorList);
		// 컨트롤러에 데이터 전달
		return scheduleDoctorList;
	}

	//----------------------------------------------------------------------------------------------------
	// 보호자 스케줄을 풀캘린더에 표시해줄 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public List<Map<String, Object>> selectAllProSchedule(int seqMember) {
		List<Map<String, Object>> scheduleProtectorList = null;
		try {
			scheduleProtectorList = scheduleMapper.selectAllProSchedule(seqMember);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 데이터가 잘 담겨있는지 로그찍어보기
		log.info("selectAllProSchedule실행 매퍼에서 돌아온 값(서비스) : {}", scheduleProtectorList);
		// 컨트롤러에 데이터 전달
		return scheduleProtectorList;
	}

	//----------------------------------------------------------------------------------------------------
	// 의사 스케줄을 풀캘린더에 추가해줄 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public void insertScheduleDoctor(Map<String, Object> scheduleDoctorMap) {
		log.info("insertScheduleDoctor실행, 넘어온 값(서비스) : {}", scheduleDoctorMap);
		try {
			scheduleMapper.insertScheduleDoctor(scheduleDoctorMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
