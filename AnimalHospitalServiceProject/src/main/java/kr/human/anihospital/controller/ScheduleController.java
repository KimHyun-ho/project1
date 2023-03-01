package kr.human.anihospital.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
}
