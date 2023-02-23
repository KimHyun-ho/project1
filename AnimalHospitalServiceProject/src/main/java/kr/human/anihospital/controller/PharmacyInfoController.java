package kr.human.anihospital.controller;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.human.anihospital.service.PharmacyInfoService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class PharmacyInfoController {

	// selectAll 서비스
	@Autowired
	PharmacyInfoService pharmacyInfoService;

	//----------------------------------------------------------------------------------------------------
	// 약국 내용을 추가할 메서드
	//----------------------------------------------------------------------------------------------------
	@PostMapping("/pharmacyAddOk")
	public String insertPharmacy(@RequestParam Map<String, String> map, Model model) {
		// insert 처리 시작-------------------------------------------------------------
		
		// 제대로 데이터가 담겨 있는지 로그에 찍어보기
		log.info("받은 값 : {}", map);
		
		// 추가처리를 위해 서비스 호출하기
		pharmacyInfoService.insertPharmacy(map);
		
		// insert 처리 종료-------------------------------------------------------------
		return "redirect:pharmacyMap";
	}

	//----------------------------------------------------------------------------------------------------
	// 약국 정보수정화면으로 이동했을때 표시할 메서드
	//----------------------------------------------------------------------------------------------------
	@PostMapping("/pharmacyInfoEdit")
	public String pharmacyInfoEdit(@RequestParam int seq, Model model) {
		// 화면에 수정할 값 표시 시작--------------------------------------------------
		
		// 받아온 데이터 화면에 넘겨주기
		model.addAttribute("model", pharmacyInfoService.selectPharmacy(seq));
		model.addAttribute("seq", seq);
		
		// 제대로 데이터가 담겨 있는지 로그에 찍어보기
		log.info("약국정보 : {}", pharmacyInfoService.selectPharmacy(seq));
		
		// 화면에 수정할 값 표시 종료--------------------------------------------------
		return "pharmacyInfoEdit";
	}

	//----------------------------------------------------------------------------------------------------
	// 약국정보를 수정할 메서드
	//----------------------------------------------------------------------------------------------------
	@PostMapping("/pharmacyEditOk")
	public String updatePharmacy(@RequestParam Map<String, String> map, Model model) {
		// 수정화면에서 수정할 값을 받아 업데이트 처리 시작-------------------------------------
		
		// 제대로 데이터가 담겨 있는지 로그에 찍어보기
		log.info("받은 값 : {}", map);
		
		// 화면에서 수정한 값 그릇에 담아 넘기기
		int seq = Integer.parseInt(map.get("seqPharmacy")); // 추후 지도 기능 구현시 seq를 웹에서 받아와야함.
		pharmacyInfoService.updatePharmacy(map, seq);
		
		// 수정화면에서 수정할 값을 받아 업데이트 처리 종료-------------------------------------
		return "redirect:pharmacyMap";
	}
}
