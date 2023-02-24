package kr.human.anihospital.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.human.anihospital.service.NoticeService;
import kr.human.anihospital.vo.AllNoticeListVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class NoticeController {
	
	// 공지와 관련된 모든 처리를 해줄 서비스
	@Autowired
	NoticeService noticeService;
	
	//----------------------------------------------------------------------------------------------------
	// 모든 공지 내용을 가져올 메서드
	//----------------------------------------------------------------------------------------------------
	@GetMapping(value = "/notice")
	public String selectAllNoticeList(Model model) throws Exception {
		// notice화면에 표시할 값 가져오기 시작-------------------------------------------------------------
		
		// 모든 공지를 담을 그릇 준비
		List<AllNoticeListVO> allNoticeListVO = null;
		// 준비한 그릇에 화면에 표시할 공지 내용을 담는다.
		allNoticeListVO = noticeService.selectAllNoticeListVO();
		// 받아온 데이터 화면에 넘겨주기
		model.addAttribute("allNoticeList", allNoticeListVO);
		// 제대로 데이터가 담겨 있는지 로그에 찍어보기
		log.info("selectAllNoticeList 메서드 호출(컨트롤러) : {}", allNoticeListVO);
		
		// notice화면에 표시할 값 가져오기 시작-------------------------------------------------------------
		return "notice";
	}
	
	//----------------------------------------------------------------------------------------------------
	// 공지 상세 내용을 가져올 메서드
	//----------------------------------------------------------------------------------------------------
	@GetMapping(value = "/noticeDetail")
	public String selectOneDetailNoticeVO(@RequestParam int seqNotice, Model model) throws Exception{
		// 공지상세 화면에 표시할 select 시작--------------------------------------------------
		
		// 화면에 넘길 데이터를 담을 그릇 준비
		AllNoticeListVO allNoticeListVO = null;
		// 화면에서 넘어온 seq값 확인하기
		log.info("공지 상세 화면에서 넘겨준 seq확인 : {}", seqNotice);
		// 데이터 그릇에 담기
		allNoticeListVO = noticeService.selectOneDetailNoticeVO(seqNotice);
		// 받아온 데이터 화면에 넘겨주기
		model.addAttribute("noticeDetailList", allNoticeListVO);
		// 제대로 데이터가 담겨 있는지 로그에 찍어보기
		log.info("selectOneDetailNoticeVO 메서드 호출(컨트롤러) : {}", allNoticeListVO);
		
		// 공지상세 화면에 표시할 select 종료--------------------------------------------------
		return "noticeDetail";
	}
	
	//----------------------------------------------------------------------------------------------------
	// insert화면에 로그인 한 의사 정보 뿌려줄 메서드
	//----------------------------------------------------------------------------------------------------
	@GetMapping("/noticeAdd")
	public String selectInsertNoticeWriterInfo(Model model) throws Exception {
		// insert화면에 로그인 한 의사 정보 표시할 select 시작-----------------------------------------------
		
		// session으로 받아와야 하는 값에 임시 값 넣어두기
		int seqDoctor = 1;
		int seqAnimalHospital = 1;
		// 데이터 받아올 그릇 준비
		Map<String, Object> insertWriterInfoMap = new HashMap<>();
		// 그릇에 데이터 받아오기
		insertWriterInfoMap = noticeService.selectInsertNoticeWriterInfo(seqDoctor, seqAnimalHospital);
		log.info("selectInsertNoticeWriterInfo 메서드 호출(컨트롤러) : {}", insertWriterInfoMap);
		// 데이터 화면에 넘겨주기
		model.addAttribute("insertWriterInfoMap", insertWriterInfoMap);
		
		// insert화면에 로그인 한 의사 정보 표시할 select 종료-----------------------------------------------
		return "noticeAdd";
	}
	
	//----------------------------------------------------------------------------------------------------
	// 공지를 추가할 메서드
	//----------------------------------------------------------------------------------------------------
	@PostMapping("/noticeAdd")
	public String insertOneNotice(@RequestParam Map<String, String> insertNoticeMap, Model model) throws Exception{
		// 공지 추가 화면 - insert처리 시작-----------------------------------------------------------------
		
		// 화면에서 넘어온 데이터 찍어보기
		log.info("insertOneNotice 화면에서 넘어온 값(컨트롤러) : {}", insertNoticeMap);
		// 화면에서 수정한 값 서비스에 넘기기
		noticeService.insertOneNotice(insertNoticeMap);
		// 공지 추가 화면 - insert처리 종료-----------------------------------------------------------------
		return "notice";
	}
	
	@GetMapping("/noticeEdit")
	public String updateOneNotice(@RequestParam int seqNotice, Model model) throws Exception{
		// 공지상세 화면에 표시할 select 시작--------------------------------------------------
		
		// 화면에 넘길 데이터를 담을 그릇 준비
		AllNoticeListVO allNoticeListVO = null;
		// 화면에서 넘어온 seq값 확인하기
		log.info("공지 상세 화면에서 넘겨준 seq확인 : {}", seqNotice);
		// 데이터 그릇에 담기
		allNoticeListVO = noticeService.selectOneDetailNoticeVO(seqNotice);
		// 받아온 데이터 화면에 넘겨주기
		model.addAttribute("noticeDetailList", allNoticeListVO);
		// 제대로 데이터가 담겨 있는지 로그에 찍어보기
		log.info("selectOneDetailNoticeVO 메서드 호출(컨트롤러) : {}", allNoticeListVO);
		
		// 공지수정 화면에 표시할 select 종료--------------------------------------------------
		return "noticeEdit";
	}
	
	//----------------------------------------------------------------------------------------------------
	// 공지를 수정할 메서드
	//----------------------------------------------------------------------------------------------------
	@PostMapping("/noticeEdit")
	public String updateOneNotice(@RequestParam Map<String, String> updateNoticeMap, Model model) throws Exception{
		// 공지 추가 화면 - insert처리 시작-----------------------------------------------------------------
		
		// 화면에서 넘어온 데이터 찍어보기
		log.info("updateOneNotice 화면에서 넘어온 값(컨트롤러) : {}", updateNoticeMap);
		// 화면에서 수정한 값 서비스에 넘기기
		noticeService.updateOneNotice(updateNoticeMap);
		
		// 공지 추가 화면 - insert처리 종료-----------------------------------------------------------------
		return "notice";
	}
	
	//----------------------------------------------------------------------------------------------------
	// 공지를 삭제해줄 메서드
	//----------------------------------------------------------------------------------------------------
	@PostMapping("/noticeDetail")
	public String deleteOneNotice(@RequestParam int seqNotice) throws Exception {
		// 공지 상세 화면 - 화면에서 삭제할 데이터의 seq값을 받아 delete처리 시작------------------------------
		
		// 화면에서 넘겨받은 seq값 로그로 찍어보기
		log.info("deleteOneNotice 화면에서 넘어온 값(컨트롤러) : {}", seqNotice);
		// 삭제처리를 위해 서비스 호출하기
		noticeService.deleteOneNotice(seqNotice);
		
		// 공지 상세 화면 - 화면에서 삭제할 데이터의 seq값을 받아 delete처리 시작------------------------------
		return "notice";
	}
}
