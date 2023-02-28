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
	
	//----------------------------------------------------------------------------------------------------
	// Ajax로 후기작성하는 메서드
	//----------------------------------------------------------------------------------------------------
	@PostMapping("/proPostscriptAddOk")
	public String proPostscriptAddOk(@RequestParam Map<String, Object> postScriptMap) throws Exception {
		log.info("proPostscript에서 넘어온 후기 작성 정보 : {}", postScriptMap);
		protectorPageService.insertProPostScript(postScriptMap);
		return "성공";
	}
	
	//----------------------------------------------------------------------------------------------------
	// Ajax로 후기 수정하는 메서드
	//----------------------------------------------------------------------------------------------------
	@PostMapping("/proPostscriptEditOk")
	public String proPostscriptEditOk(@RequestParam Map<String, Object> updatePostScriptMap) throws Exception {
		log.info("proPostscriptEdit에서 넘어온 후기 수정 정보 : {}", updatePostScriptMap);
		protectorPageService.updatePostscript(updatePostScriptMap);
		return "성공";
	}
	
	//----------------------------------------------------------------------------------------------------
	// 후기 삭제 하는 메서드
	//----------------------------------------------------------------------------------------------------
	@PostMapping("/deletePostscript")
	public String deletePostscript(@RequestParam int seqPostscript) throws Exception {
		log.info("deletePostscript에서 넘어온  seqPostscript : {}", seqPostscript);
		protectorPageService.deletePostscript(seqPostscript);
		return "성공";
	}

}
