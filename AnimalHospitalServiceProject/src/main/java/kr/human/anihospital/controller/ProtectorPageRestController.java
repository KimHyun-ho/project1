package kr.human.anihospital.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.human.anihospital.service.ProtectorPageService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ProtectorPageRestController {
	
	// 보호자 페이지 관련 Service
	@Autowired
	ProtectorPageService protectorPageService;
	
	//----------------------------------------------------------------------------------------------------
	// Ajax로 보호자 정보를 수정하는 메서드
	//----------------------------------------------------------------------------------------------------
	@PostMapping("/proMypageEditOk")
	public String proMypageEditOk(@RequestParam Map<String, Object> updateMap) throws Exception {
		// 보호자 수정 화면에서 수정될 값이 넘어왔는지 로그로 확인
		log.info("proMypageEdit에서 넘어온 보호자 수정 정보 : {}", updateMap);
		// 보호자 정보 수정 실행
		protectorPageService.updateProMypage(updateMap);
		return "성공";
	}
}
