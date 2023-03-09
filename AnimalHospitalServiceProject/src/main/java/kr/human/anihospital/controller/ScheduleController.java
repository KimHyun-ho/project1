package kr.human.anihospital.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.human.anihospital.service.ScheduleService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ScheduleController {
	
	@Autowired
	ScheduleService scheduleService;
	
	//----------------------------------------------------------------------------------------------------
	// 의사 스케줄을 풀캘린더에 표시해줄 메서드
	//----------------------------------------------------------------------------------------------------
	@GetMapping("/scheduleDoctorOk")
	@ResponseBody
	public List<Map<String, Object>> selectScheduleDoctorArrJson() throws Exception {
		// 화면에서 설정된 값을 받아야 하지만 임시로 1번을 넣어둠
		int seqDoctor = 1;
		// 서비스에서 넘어온 데이터 담을 그릇 준비
		List<Map<String, Object>> scheduleDoctorList = null;
		// 데이터를 받기 위해 서비스 호출
		scheduleDoctorList = scheduleService.selectScheduleDoctorArrJson(seqDoctor);
		// 데이터가 잘 담겨 오는지 찍어보기
		log.info("selectScheduleDoctorArrJson실행, 서비스에서 돌아온 값 : {}", scheduleDoctorList);
		// 받은 데이터를 json형식으로 변환하기 위해 그릇 만들기
		JSONObject jsonObj = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		HashMap<String, Object> scheduleDoctorHash = new HashMap<String, Object>();

		for(int i=0; i < scheduleDoctorList.size(); i++) {
			scheduleDoctorHash.put("seqDoctorSchedule", scheduleDoctorList.get(i).get("seqDoctorSchedule"));
			scheduleDoctorHash.put("seqDoctor", scheduleDoctorList.get(i).get("seqDoctor"));
			scheduleDoctorHash.put("title", scheduleDoctorList.get(i).get("scheduleDoctorContent")); // 제목
			scheduleDoctorHash.put("start", scheduleDoctorList.get(i).get("scheduleDoctorHolidayStart")); // 시작일자
			scheduleDoctorHash.put("end", scheduleDoctorList.get(i).get("scheduleDoctorHolidayEnd")); // 종료일자
			scheduleDoctorHash.put("scheduleDoctorWriteDate", scheduleDoctorList.get(i).get("scheduleDoctorWriteDate")); // 스케줄 작성 일자
			scheduleDoctorHash.put("scheduleDoctorUpdateDate", scheduleDoctorList.get(i).get("scheduleDoctorUpdateDate")); // 스케줄 수정 일자
			
			// 중괄호 {key:value , key:value, key:value}
			jsonObj = new JSONObject(scheduleDoctorHash);
			// 대괄호 안에 넣어주기[{key:value , key:value, key:value},{key:value , key:value, key:value}]
			jsonArr.add(jsonObj);
			// log.info("jsonArrCheck: {}", jsonArr);
		}
		return jsonArr;
	}
	
	//----------------------------------------------------------------------------------------------------
	// 의사 스케줄을 풀캘린더에 추가해줄 메서드
	//----------------------------------------------------------------------------------------------------
	@PostMapping("/scheduleDoctorInsertOk")
	@ResponseBody
	public String insertScheduleDoctor(@RequestBody List<Map<String, Object>> scheduleDoctorList) throws Exception {
		// 화면에서 추가한 캘린더에 추가한 데이터가 잘 넘어왔는지 찍어보기
		log.info("insertScheduleDoctor 실행, 화면에서 넘어온 값(컨트롤러) : {}", scheduleDoctorList);
		// 한국 표준 시간으로 시간 포멧 설정
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.KOREA);
		// 맵에서 시작, 종료 시간 꺼내 변수에 담아두기
		LocalDateTime startDate = null;
		LocalDateTime endDate = null;
		for (Map<String, Object> list : scheduleDoctorList) {
			String startDateString = (String) list.get("start");
	        String endDateString = (String) list.get("end");
	        // 한국 표준 시간으로 시간 포멧 설정
			startDate = LocalDateTime.parse(startDateString, dateTimeFormatter).plusHours(9);
	        endDate = LocalDateTime.parse(endDateString, dateTimeFormatter).plusHours(9);
		}
		int seqDoctor = 1;
		// 수정한 값들 새로운 그릇에 담기
		Map<String, Object> scheduleDoctorMap = new HashMap<>();
		for(int i = 0; i < scheduleDoctorList.size(); i++) {
			scheduleDoctorMap.put("seqDoctor", seqDoctor);
			scheduleDoctorMap.put("scheduleDoctorHolidayStart", startDate);
			scheduleDoctorMap.put("scheduleDoctorHolidayEnd", endDate);
			scheduleDoctorMap.put("scheduleDoctorContent", scheduleDoctorList.get(i).get("title"));
		}
		// insert를 수행해줄 서비스 호출
		scheduleService.insertScheduleDoctor(scheduleDoctorMap);
		// 수정한 값들이 잘 담겨 있는지 로그 찍어보기
		log.info("insertScheduleDoctor 실행, 서비스에 넘길값(컨트롤러) : {}", scheduleDoctorMap);
		return "scheduleDoctor";
	}
	
	//----------------------------------------------------------------------------------------------------
	// 의사 스케줄을 풀캘린더에 수정해줄 메서드
	//----------------------------------------------------------------------------------------------------
	@PostMapping("/scheduleDoctorUpdateOk")
	@ResponseBody
	public String updateScheduleDoctor(@RequestBody List<Map<String, Object>> scheduleDoctorList) throws Exception {
		// 화면에서 추가한 캘린더에 추가한 데이터가 잘 넘어왔는지 찍어보기
		log.info("updateScheduleDoctor 실행, 화면에서 넘어온 값(컨트롤러) : {}", scheduleDoctorList);
		// 시작, 종료 시간 꺼내 변수에 담아두기
		String startDate = null;
		String endDate = null;
		for (Map<String, Object> list : scheduleDoctorList) {
			// 데이터가 JSON DATE Format(yyyy-MM-dd'T'HH:mm:ss.SSS'Z')으로 넘어오기 때문에 
			// DB에서 조건절에 넣으려면 날짜와 시간 포멧을 바꿔줘야 한다.
			String start = (String) list.get("start");
			String end = (String) list.get("end");
			startDate = start.substring(0, 10) + " " + start.subSequence(11, 19);
			endDate = end.substring(0, 10) + " " + end.subSequence(11, 19);
		}
		// seq값을 조회할 조건 시간 맵에 담기
		Map<String, String> startEndMap = new HashMap<>();
		startEndMap.put("startDate", startDate);
		log.info("startEndMap(컨트롤러) : {}", startEndMap);
		
		// 의사 스케줄을 업데이트 해줄 seq값을 가져오기 위해 서비스 호출
		int seqDoctorSchedule = scheduleService.selectSeqDoctorSchedule(startEndMap);
		// 값이 제대로 넘어오는지 로그 찍어보기
		log.info("seqDoctorSchedule(컨트롤러) : {}", seqDoctorSchedule);
		
		int seqDoctor = 1;
		// 수정한 값들 새로운 그릇에 담기
		Map<String, Object> scheduleDoctorMap = new HashMap<>();
		for(int i = 0; i < scheduleDoctorList.size(); i++) {
			scheduleDoctorMap.put("seqDoctorSchedule", seqDoctorSchedule);
			scheduleDoctorMap.put("seqDoctor", seqDoctor);
			scheduleDoctorMap.put("scheduleDoctorHolidayStart", startDate);
			scheduleDoctorMap.put("scheduleDoctorHolidayEnd", endDate);
			scheduleDoctorMap.put("scheduleDoctorContent", scheduleDoctorList.get(i).get("title"));
		}
		// update를 수행해줄 서비스 호출
		scheduleService.updateScheduleDoctor(scheduleDoctorMap);
		// 수정한 값들이 잘 담겨 있는지 로그 찍어보기
		log.info("updateScheduleDoctor 실행, 서비스에 넘길값(컨트롤러) : {}", scheduleDoctorMap);
		return "scheduleDoctor";
	}
	
	//----------------------------------------------------------------------------------------------------
	// 의사 스케줄을 풀캘린더에 삭제해줄 메서드
	//----------------------------------------------------------------------------------------------------
	@PostMapping("/scheduleDoctorDeleteOk")
	@ResponseBody
	public String deleteDoctorSchedule(@RequestBody List<Map<String, Object>> scheduleDoctorList) throws Exception {
		// 화면에서 추가한 캘린더에 추가한 데이터가 잘 넘어왔는지 찍어보기
		log.info("deleteDoctorSchedule 실행, 화면에서 넘어온 값(컨트롤러) : {}", scheduleDoctorList);
		// 시작, 종료 시간 꺼내 변수에 담아두기
		String startDate = null;
		// 데이터가 JSON DATE Format(yyyy-MM-dd'T'HH:mm:ss.SSS'Z')으로 넘어오기 때문에 
		// DB에서 조건절에 넣으려면 날짜와 시간 포멧을 바꿔줘야 한다.
		String start = (String) scheduleDoctorList.get(0).get("start");
		startDate = start.substring(0, 10) + " " + start.subSequence(11, 19);
		// seq값을 조회할 조건 시간 맵에 담기
		Map<String, String> startEndMap = new HashMap<>();
		startEndMap.put("startDate", startDate);
		log.info("startEndMap(컨트롤러) : {}", startEndMap);
		
		// 의사 스케줄을 업데이트 해줄 seq값을 가져오기 위해 서비스 호출
		int seqDoctorSchedule = scheduleService.selectSeqDoctorSchedule(startEndMap);
		// 값이 제대로 넘어오는지 로그 찍어보기
		log.info("seqDoctorSchedule(컨트롤러) : {}", seqDoctorSchedule);
		
		// 삭제 처리를 해줄 서비스 호출
		scheduleService.deleteDoctorSchedule(seqDoctorSchedule);
		return "scheduleDoctorDeleteOk";
	}
	
	//----------------------------------------------------------------------------------------------------
	// 보호자 스케줄 페이지에서 의사와 보호자 스케줄을 풀캘린더에 표시해줄 메서드
	//----------------------------------------------------------------------------------------------------
	@PostMapping("/proDoctorSchedule")
	@ResponseBody
	public List<Map<String, Object>> proDoctorSchedule(@RequestParam int seqDoctor) throws Exception {
		// 보호자가 선택한 의사의 seq 받아왔는지 확인
		log.info("보호자 스케줄 화면에서 선택한 의사의 seq: {}", seqDoctor);
		// 서비스에서 넘어온 데이터 담을 그릇 준비
		List<Map<String, Object>> scheduleDoctorList = null;
		// 데이터를 받기 위해 서비스 호출
		scheduleDoctorList = scheduleService.selectScheduleDoctorArrJson(seqDoctor);
		// 데이터가 잘 담겨 오는지 찍어보기
		log.info("selectScheduleDoctorArrJson실행, 서비스에서 돌아온 값 : {}", scheduleDoctorList);
		// 받은 데이터를 json형식으로 변환하기 위해 그릇 만들기
		JSONObject jsonObj = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		HashMap<String, Object> scheduleDoctorHash = new HashMap<String, Object>();

		for(int i=0; i < scheduleDoctorList.size(); i++) {
			scheduleDoctorHash.put("seqDoctor", scheduleDoctorList.get(i).get("seqDoctor"));
			scheduleDoctorHash.put("title", scheduleDoctorList.get(i).get("scheduleDoctorContent")); // 제목
			scheduleDoctorHash.put("start", scheduleDoctorList.get(i).get("scheduleDoctorHolidayStart")); // 시작일자
			scheduleDoctorHash.put("end", scheduleDoctorList.get(i).get("scheduleDoctorHolidayEnd")); // 종료일자
			scheduleDoctorHash.put("scheduleDoctorWriteDate", scheduleDoctorList.get(i).get("scheduleDoctorWriteDate")); // 스케줄 작성 일자
			scheduleDoctorHash.put("scheduleDoctorUpdateDate", scheduleDoctorList.get(i).get("scheduleDoctorUpdateDate")); // 스케줄 수정 일자
			
			// 중괄호 {key:value , key:value, key:value}
			jsonObj = new JSONObject(scheduleDoctorHash);
			// 대괄호 안에 넣어주기[{key:value , key:value, key:value},{key:value , key:value, key:value}]
			jsonArr.add(jsonObj);
			log.info("jsonArrCheck: {}", jsonArr);
		}
			int seqMember = 4;
			// 서비스에서 넘어온 데이터 담을 그릇 준비
			List<Map<String, Object>> scheduleProtectorList = null;
			// 데이터를 받기 위해 서비스 호출
			scheduleProtectorList = scheduleService.selectAllProSchedule(seqMember);
			// 데이터가 잘 담겨 오는지 찍어보기
			log.info("selectAllProSchedule실행, 서비스에서 돌아온 값 : {}", scheduleProtectorList);
			// 받은 데이터를 json형식으로 변환하기 위해 그릇 만들기
			//JSONObject jsonObj = new JSONObject();
			//JSONArray jsonArr = new JSONArray();
			HashMap<String, Object> scheduleProtectorHash = new HashMap<String, Object>();
			
			for(int j=0; j < scheduleProtectorList.size(); j++) {
				scheduleProtectorHash.put("seqMember", scheduleProtectorList.get(j).get("seqMember"));
				scheduleProtectorHash.put("seqAnimal", scheduleProtectorList.get(j).get("seqAnimal"));
				scheduleProtectorHash.put("seqDoctorSchedule", scheduleProtectorList.get(j).get("seqDoctorSchedule"));
				scheduleProtectorHash.put("title", scheduleProtectorList.get(j).get("scheduleProtectorContent")); // 제목
				scheduleProtectorHash.put("start", scheduleProtectorList.get(j).get("holidayStart")); // 시작일자
				scheduleProtectorHash.put("end", scheduleProtectorList.get(j).get("holidayEnd")); // 종료일자
				scheduleProtectorHash.put("scheduleProtectorWriteDate", scheduleProtectorList.get(j).get("scheduleProtectorWriteDate")); // 스케줄 작성 일자
				scheduleProtectorHash.put("scheduleProtectorUpdateDate", scheduleProtectorList.get(j).get("scheduleProtectorUpdateDate")); // 스케줄 수정 일자
				scheduleProtectorHash.put("color", "#008000"); // 색깔 지정
				
				// 중괄호 {key:value , key:value, key:value}
				jsonObj = new JSONObject(scheduleProtectorHash);
				// 대괄호 안에 넣어주기[{key:value , key:value, key:value},{key:value , key:value, key:value}]
				jsonArr.add(jsonObj);
				// log.info("jsonArrCheck: {}", jsonArr);
			}
			
		return jsonArr;
	}
	
	//----------------------------------------------------------------------------------------------------
	// 보호자 스케줄 페이지에서 의사 스케줄을 풀캘린더에 표시해줄 메서드
	//----------------------------------------------------------------------------------------------------
	@PostMapping("/proScheduleOk")
	@ResponseBody
	public List<Map<String, Object>> proScheduleOk() throws Exception {
		// 세션에서 값을 받아야 하지만 임시로 4번을 넣어둠
		int seqMember = 4;
		// 서비스에서 넘어온 데이터 담을 그릇 준비
		List<Map<String, Object>> scheduleProtectorList = null;
		// 데이터를 받기 위해 서비스 호출
		scheduleProtectorList = scheduleService.selectAllProSchedule(seqMember);
		// 데이터가 잘 담겨 오는지 찍어보기
		log.info("selectAllProSchedule실행, 서비스에서 돌아온 값 : {}", scheduleProtectorList);
		// 받은 데이터를 json형식으로 변환하기 위해 그릇 만들기
		JSONObject jsonObj = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		HashMap<String, Object> scheduleProtectorHash = new HashMap<String, Object>();
		
		for(int i=0; i < scheduleProtectorList.size(); i++) {
			scheduleProtectorHash.put("seqMember", scheduleProtectorList.get(i).get("seqMember"));
			scheduleProtectorHash.put("seqAnimal", scheduleProtectorList.get(i).get("seqAnimal"));
			scheduleProtectorHash.put("seqDoctorSchedule", scheduleProtectorList.get(i).get("seqDoctorSchedule"));
			scheduleProtectorHash.put("title", scheduleProtectorList.get(i).get("scheduleProtectorContent")); // 제목
			scheduleProtectorHash.put("start", scheduleProtectorList.get(i).get("holidayStart")); // 시작일자
			scheduleProtectorHash.put("end", scheduleProtectorList.get(i).get("holidayEnd")); // 종료일자
			scheduleProtectorHash.put("scheduleProtectorWriteDate", scheduleProtectorList.get(i).get("scheduleProtectorWriteDate")); // 스케줄 작성 일자
			scheduleProtectorHash.put("scheduleProtectorUpdateDate", scheduleProtectorList.get(i).get("scheduleProtectorUpdateDate")); // 스케줄 수정 일자
			scheduleProtectorHash.put("color", "#008000"); // 색깔 지정
			
			// 중괄호 {key:value , key:value, key:value}
			jsonObj = new JSONObject(scheduleProtectorHash);
			// 대괄호 안에 넣어주기[{key:value , key:value, key:value},{key:value , key:value, key:value}]
			jsonArr.add(jsonObj);
			log.info("jsonArrCheck: {}", jsonArr);
		}
		return jsonArr;
	}
	
	//----------------------------------------------------------------------------------------------------
	// 보호자 스케줄을 풀캘린더에 추가해줄 메서드
	//----------------------------------------------------------------------------------------------------
	@PostMapping("/scheduleProtectorInsertOk")
	@ResponseBody
	public String insertScheduleProtector(@RequestBody List<Map<String, Object>> scheduleProtectorList) throws Exception {
		// 화면에서 추가한 캘린더에 추가한 데이터가 잘 넘어왔는지 찍어보기
		log.info("insertScheduleProtector 실행, 화면에서 넘어온 값(컨트롤러) : {}", scheduleProtectorList);
		// 한국 표준 시간으로 시간 포멧 설정
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.KOREA);
		// 맵에서 시작, 종료 시간 꺼내 변수에 담아두기
		LocalDateTime startDate = null;
		LocalDateTime endDate = null;
		String startDateToString = null;
		for (Map<String, Object> list : scheduleProtectorList) {
			String startDateString = (String) list.get("start");
	        String endDateString = (String) list.get("end");
	        // 한국 표준 시간으로 시간 포멧 설정
			startDate = LocalDateTime.parse(startDateString, dateTimeFormatter).plusHours(9);
	        endDate = LocalDateTime.parse(endDateString, dateTimeFormatter).plusHours(9);
	        startDateToString = "%" + startDate.toString().substring(0, 10) + " " + startDate.toString().subSequence(11, 16) + "%";
		}
		int seqDoctor = 1;
		// seq조회할 일정 시작시간 포멧 설정
		// 수정한 값들 새로운 그릇에 담기
		Map<String, Object> scheduleDoctororMap = new HashMap<>();
		for(int i = 0; i < scheduleProtectorList.size(); i++) {
			scheduleDoctororMap.put("seqDoctor", seqDoctor);
			scheduleDoctororMap.put("animalName", scheduleProtectorList.get(i).get("animalName"));
			scheduleDoctororMap.put("scheduleDoctorHolidayStart", startDate);
			scheduleDoctororMap.put("scheduleDoctorHolidayEnd", endDate);
			scheduleDoctororMap.put("scheduleDoctorContent", scheduleProtectorList.get(i).get("title"));
		}
		// 추가한 일정을 의사 스케줄 테이블에 insert를 수행해줄 서비스 호출
		scheduleService.insertScheduleDoctor(scheduleDoctororMap);
		
		// 의사 스케줄 seq값을 조회할 조건 시간 맵에 담기
		Map<String, String> startEndMap = new HashMap<>();
		startEndMap.put("startDate", startDateToString);
		log.info("startEndMap(컨트롤러) : {}", startEndMap);
		
		// 의사 스케줄 테이블에 추가후 생성된 seq값 받아오기
		int seqDoctorSchedule = scheduleService.selectSeqDoctorSchedule(startEndMap);
		// 값이 제대로 넘어오는지 로그 찍어보기
		log.info("seqDoctorSchedule(컨트롤러) : {}", seqDoctorSchedule);
		
		int seqMember = 4;
		// 환자 seq번호 조회할 조건 담기
		Map<String, Object> scheduleProtectorMap = new HashMap<>();
		scheduleProtectorMap.put("seqMember", seqMember);
		scheduleProtectorMap.put("animalName", scheduleDoctororMap.get("animalName"));
		// seq번호 조회를 위해 서비스부르기
		int seqAnimal = scheduleService.selectSeqAnimalForInsertScheduleProtector(scheduleProtectorMap);
		log.info("seqAnimal(컨트롤러) : {}", seqAnimal);
		
		// 가져온 값들 포함해서 새 그릇에 담기
		Map<String, Object> insertScheduleProtectorMap = new HashMap<>();
		insertScheduleProtectorMap.put("seqAnimal", seqAnimal);
		insertScheduleProtectorMap.put("seqDoctorSchedule", seqDoctorSchedule);
		insertScheduleProtectorMap.put("scheduleProtectorHolidayStart", scheduleDoctororMap.get("scheduleDoctorHolidayStart"));
		insertScheduleProtectorMap.put("scheduleProtectorHolidayEnd", scheduleDoctororMap.get("scheduleDoctorHolidayEnd"));
		insertScheduleProtectorMap.put("scheduleProtectorContent", scheduleDoctororMap.get("scheduleDoctorContent"));
		
		scheduleService.insertScheduleProtector(insertScheduleProtectorMap);
		// 수정한 값들이 잘 담겨 있는지 로그 찍어보기
		log.info("insertScheduleProtector 실행, 서비스에 넘길값(컨트롤러) : {}", scheduleProtectorMap);
		return "scheduleProtector";
	}
	
	//----------------------------------------------------------------------------------------------------
	// TO DO LIST를 화면에 뿌려줄 메서드
	//----------------------------------------------------------------------------------------------------
	@GetMapping("/scheduleDoctor")
	public String selectScheduleDoctor(Model model) throws Exception {
		// session에서 받아야 할 값
		int seqDoctor = 1;
		// 데이터 담을 그릇 준비
		List<Map<String, Object>> allTodolist = null;
		// 데이터 담아오기
		allTodolist = scheduleService.selectAllTodolist(seqDoctor);
		log.info("selectScheduleDoctor실행, 서비스에서 넘어온 값(컨트롤러) : {}", allTodolist);
		// 데이터 화면에 넘겨주기
		model.addAttribute("allTodolist", allTodolist);
		return "scheduleDoctor";
	}
	
	//----------------------------------------------------------------------------------------------------
	// TO DO LIST를 추가해줄 메서드
	//----------------------------------------------------------------------------------------------------
	@PostMapping("/todolistInsertOk")
	public String insertTodolist(@RequestParam Map<String, Object> todolistMap) throws Exception {
		// 화면에서 값이 잘 넘어오는지 로그 찍어보기
		log.info("insertTodolist실행, 화면에서 넘어온 값(컨트롤러) : {}", todolistMap);
		int seqDoctor = 1;
		Map<String, Object> todolistInsertMap = new HashMap<>();
		todolistInsertMap.put("seqDoctor", seqDoctor);
		todolistInsertMap.put("todoSubject", todolistMap.get("toDoSubject"));
		todolistInsertMap.put("todoContent", todolistMap.get("toDoContent"));
		// map에 잘 담겼는지 확인하기
		log.info("insertTodolist실행, 서비스에 넘겨줄 값(컨트롤러) : {}", todolistInsertMap);
		// insert하기 위해 서비스 호출
		scheduleService.insertTodolist(todolistInsertMap);
		return "scheduleDoctor";
	}
	
	//----------------------------------------------------------------------------------------------------
	// TO DO LIST를 수정해줄 메서드
	//----------------------------------------------------------------------------------------------------
	@PostMapping("/todolistUpdateOk")
	public String updateTodolist(@RequestParam Map<String, Object> todolistMap) throws Exception {
		// 화면에서 값이 잘 넘어오는지 로그 찍어보기
		log.info("updateTodolist실행, 화면에서 넘어온 값(컨트롤러) : {}", todolistMap);
		Map<String, Object> todolistUpdateMap = new HashMap<>();
		todolistUpdateMap.put("seqTodo", todolistMap.get("seqTodo"));
		todolistUpdateMap.put("todoSubject", todolistMap.get("toDoSubject"));
		todolistUpdateMap.put("todoContent", todolistMap.get("toDoContent"));
		// map에 잘 담겼는지 확인하기
		log.info("updateTodolist실행, 서비스에 넘겨줄 값(컨트롤러) : {}", todolistUpdateMap);
		// update하기 위해 서비스 호출
		scheduleService.updateTodolist(todolistUpdateMap);
		return "scheduleDoctor";
	}
	
	//----------------------------------------------------------------------------------------------------
	// TO DO LIST를 삭제해줄 메서드
	//----------------------------------------------------------------------------------------------------
	@PostMapping("/todolistDeleteOk")
	public String deleteTodolist(@RequestParam int seqTodo) throws Exception {
		// 화면에서 값이 잘 넘어오는지 로그 찍어보기
		log.info("deleteTodolist실행, 화면에서 넘어온 값(컨트롤러) : {}", seqTodo);
		// delete하기 위해 서비스 호출
		scheduleService.deleteTodolist(seqTodo);
		return "scheduleDoctor";
	}
}
