package kr.human.anihospital.controller;

import java.util.HashMap;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.human.anihospital.service.DoctorInfoService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class DoctorInfoController {

	@Autowired
	DoctorInfoService doctorInfoService;

	//----------------------------------------------------------------------------------------------------
	// 의사 정보를 화면에 표시해줄 메서드
	//----------------------------------------------------------------------------------------------------
	
	@GetMapping("/doctorInfo")
	public String viewMyInfoDoctor(Model model) {
		//----------------------------------------------------------------------------------------------------
		// 해당 진료내역 정보를 리스트로 화면에 띄울 값 표시 시작
		
		// 화면에 넘길 데이터를 담을 그릇 준비
		Map<String, Object> map = new HashMap<>();
		// **** 추후 seq는 반드시 웹에서 회원정보를 통해 받아야합니다.
		int seq = 6;
		// 데이터 그릇에 담기
		map = doctorInfoService.viewMyInfoDoctor(seq);
		// 받아온 데이터 화면에 넘겨주기
		model.addAttribute("map", map);
		
		// 제대로 데이터가 담겨 있는지 로그에 찍어보기
		log.info("viewMyInfoDoctor 서비스에서 넘어온 값(컨트롤러) : {}", map);
		
		return "doctorInfo";
	}
	//----------------------------------------------------------------------------------------------------
	// 수정한 의사 정보를 화면에 보내줄 메서드
	//----------------------------------------------------------------------------------------------------
	@PostMapping("/doctorInfoEdit")
	public String sendMyInfoDoctor(@RequestParam Map<String, Object> map, Model model) {
		//----------------------------------------------------------------------------------------------------
		// 수정한 의사 정보를 화면에 띄울 값 표시 시작
		
		// 제대로 데이터가 넘어 가는지 로그에 찍어보기
		log.info("sendMyInfoDoctor 컨트롤러 받은값 : {}", map);
		
		// 받아온 데이터 화면에 넘겨주기
		model.addAttribute("map", map);
		
		// 제대로 데이터가 담겨 있는지 로그에 찍어보기
		log.info("sendMyInfoDoctor 서비스에서 넘어온 값(컨트롤러) : {}", map);
		
		// 수정한 의사 정보를 화면에 띄울 값 표시 종료
		//----------------------------------------------------------------------------------------------------
		
		return "doctorInfoEdit";
	}

	//----------------------------------------------------------------------------------------------------
	// 수정한 의사 정보를 화면에 표시해줄 메서드
	//----------------------------------------------------------------------------------------------------
	@PostMapping("/doctorInfoEditOk")
	public String editMyInfoDoctor(@RequestParam Map<String, Object> map) {
		//----------------------------------------------------------------------------------------------------
		// 수정한 의사 정보를 화면에 띄울 값 표시 시작
		
		// 제대로 데이터가 넘어 가는지 로그에 찍어보기
		log.info("editMyInfoDoctor 컨트롤러 받은값  : {}", map);
		
		// 데이터 그릇에 담아 화면에 넘겨주기
		doctorInfoService.editMyInfoDoctor(map);
		
		// 제대로 데이터가 담겨 있는지 로그에 찍어보기
		log.info("editMyInfoDoctor 서비스에서 넘어온 값(컨트롤러) : {}", map);
		
		// 수정한 의사 정보를 화면에 띄울 값 표시 종료
		//----------------------------------------------------------------------------------------------------
		
		return "redirect:doctorInfo";
	}

}
