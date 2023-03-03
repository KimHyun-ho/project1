package kr.human.anihospital.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
		return jsonArr;
	}
	
	@GetMapping("/scheduleDoctor")
	public String selectScheduleDoctor() {
		return "scheduleDoctor";
		
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
				log.info("jsonArrCheck: {}", jsonArr);
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
}
