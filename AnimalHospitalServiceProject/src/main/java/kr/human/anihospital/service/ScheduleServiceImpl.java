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
	// 의사 스케줄을 풀캘린더에 수정해줄 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public void updateScheduleDoctor(Map<String, Object> scheduleDoctorMap) {
		// 데이터가 잘 넘어오는지 로그 찍어보기
		log.info("updateScheduleDoctor실행, 넘어온 값(서비스) : {}", scheduleDoctorMap);
		try {
			scheduleMapper.updateScheduleDoctor(scheduleDoctorMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//----------------------------------------------------------------------------------------------------
	// 의사 스케줄을 풀캘린더에 삭제해줄 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public void deleteDoctorSchedule(int seqDoctorSchedule) {
		// 데이터가 잘 넘어오는지 로그 찍어보기
		log.info("seqDoctorSchedule(서비스) : {}", seqDoctorSchedule);
		try {
			scheduleMapper.deleteDoctorSchedule(seqDoctorSchedule);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//----------------------------------------------------------------------------------------------------
	// 의사 스케줄을 수정, 삭제하기 위한 seq값 조회하는 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public int selectSeqDoctorSchedule(Map<String, String> startEndMap) {
		// 결과값 담을 그릇 준비 및 초기화
		int seqDoctorSchedule = 0;
		try {
			// 그릇에 실행 결과 담기
			seqDoctorSchedule = scheduleMapper.selectSeqDoctorSchedule(startEndMap);
			// 데이터가 잘 넘어오는지 로그 찍어보기
			log.info("selectSeqDoctorSchedule실행, 넘어온 값(서비스) : {}", seqDoctorSchedule);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return seqDoctorSchedule;
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
	// 보호자 스케줄을 풀캘린더에 추가해줄 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public void insertScheduleProtector(Map<String, Object> scheduleProtectorMap) {
		// 데이터가 잘 넘어오는지 로그 찍어보기
		log.info("insertScheduleDoctor실행, 넘어온 값(서비스) : {}", scheduleProtectorMap);
		try {
			// mapper를 불러 insert처리하기
			scheduleMapper.insertScheduleProtector(scheduleProtectorMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//----------------------------------------------------------------------------------------------------
	// 보호자 스케줄을 추가하기 위해 해당 환자seq를 조회하는 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public int selectSeqAnimalForInsertScheduleProtector(Map<String, Object> seqMemAniNameMap) {
		// 데이터 담을 그릇 준비
		int seqAnimal = 0;
		try {
			// mapper를 불러 데이터 담아오기
			seqAnimal = scheduleMapper.selectSeqAnimalForInsertScheduleProtector(seqMemAniNameMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 서비스로 데이터 보내기
		return seqAnimal;
	}
	
	//----------------------------------------------------------------------------------------------------
	// 보호자 스케줄을 풀캘린더에 수정해줄 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public void updateScheduleProtector(Map<String, Object> scheduleDoctorMap) {
		try {
			// update를 위해 mapper호출
			scheduleMapper.updateScheduleProtector(scheduleDoctorMap);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	//----------------------------------------------------------------------------------------------------
	// 보호자 스케줄을 수정, 삭제하기 위한 seq값 조회하는 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public int selectSeqProtectorSchedule(Map<String, String> startEndMap) {
		int seqProtectorSchedule = 0;
		try {
			seqProtectorSchedule = scheduleMapper.selectSeqProtectorSchedule(startEndMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return seqProtectorSchedule;
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
