package kr.human.anihospital.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.human.anihospital.service.PatientListService;
import kr.human.anihospital.vo.PatientListVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class PatientListController {
	
	@Autowired
	PatientListService patientListService;
	
	//----------------------------------------------------------------------------------------------------
	// 환자 정보를 리스트로 화면에 표시해줄 메서드
	//----------------------------------------------------------------------------------------------------
	@GetMapping(value = "/patientList")
	public String selectPatientList(Model model) throws Exception {
		//----------------------------------------------------------------------------------------------------
		// 해당 진료내역 정보를 리스트로 화면에 띄울 값 표시 시작
		
		// 화면에 넘길 데이터를 담을 그릇 준비
		List<PatientListVO> patientList = null;
		// 데이터 그릇에 담기
		patientList = patientListService.selectPatientList();
		// 받아온 데이터 화면에 넘겨주기
		model.addAttribute("patientList", patientList);
		
		// 제대로 데이터가 담겨 있는지 로그에 찍어보기
		log.info("selectPatientList 서비스에서 넘어온 값(컨트롤러) : {}",  patientList);
		
		// 해당 진료내역 정보를 리스트로 화면에 띄울 값 표시 종료
		//----------------------------------------------------------------------------------------------------
		return "patientList";
	}
}
