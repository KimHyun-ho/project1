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
		// 데이터 담을 그릇 준비
		List<Map<String, Object>> scheduleProtectorList = null;
		try {
			// 데이터 조회를 위해 mapper호출
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
		// 데이터가 잘 넘어오는지 로그 찍어보기
		log.info("insertScheduleDoctor실행, 넘어온 값(서비스) : {}", scheduleDoctorMap);
		try {
			// mapper를 불러 insert처리하기
			scheduleMapper.insertScheduleDoctor(scheduleDoctorMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//----------------------------------------------------------------------------------------------------
	// TO DO LIST를 추가해줄 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public void insertTodolist(Map<String, Object> todolistMap){
		// 데이터가 잘 넘어오는지 로그 찍어보기
		log.info("insertTodolist실행, 넘어온 값(서비스) : {}", todolistMap);
		try {
			// mapper를 불러 insert처리하기
			scheduleMapper.insertTodolist(todolistMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//----------------------------------------------------------------------------------------------------
	// TO DO LIST를 화면에 뿌려줄 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public List<Map<String, Object>> selectAllTodolist(int seqDoctor) {
		// 데이터담을 그릇 준비
		List<Map<String, Object>> allTodolist = null;
		try {
			// mapper를 불러 select처리하기
			allTodolist = scheduleMapper.selectAllTodolist(seqDoctor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("selectAllTodolist실행, 메퍼에서 넘어온 값(서비스) : {}", allTodolist);
		return allTodolist;
	}

	//----------------------------------------------------------------------------------------------------
	// TO DO LIST를 수정해줄 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public void updateTodolist(Map<String, Object> todolistMap) {
		// 데이터가 잘 넘어오는지 로그 찍어보기
		log.info("updateTodolist실행, 넘어온 값(서비스) : {}", todolistMap);
		try {
			scheduleMapper.updateTodolist(todolistMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//----------------------------------------------------------------------------------------------------
	// TO DO LIST를 삭제해줄 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public void deleteTodolist(int seqTodo) {
		// 데이터가 잘 넘어오는지 로그 찍어보기
		log.info("deleteTodolist실행, 넘어온 값(서비스) : {}", seqTodo);
		try {
			scheduleMapper.deleteTodolist(seqTodo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
