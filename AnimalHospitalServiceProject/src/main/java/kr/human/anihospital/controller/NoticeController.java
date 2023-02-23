package kr.human.anihospital.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.human.anihospital.service.NoticeService;
import kr.human.anihospital.vo.NoticeListVO;
import kr.human.anihospital.vo.NoticeOneDetailVO;
import kr.human.anihospital.vo.NoticeVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class NoticeController {

	// selectAll 서비스
	@Autowired
	NoticeService noticeService;
	
	//----------------------------------------------------------------------------------------------------
	// 모든 공지 내용을 가져올 메서드
	//----------------------------------------------------------------------------------------------------
	@GetMapping(value = "/notice")
	public String selectNoticeList(Model model) throws Exception {
		// notice화면에 표시할 값 가져오기 시작-------------------------------------------------------------
		
		// 모든 공지를 담을 그릇 준비
		List<NoticeListVO> noticeList = null;
		// 준비한 그릇에 화면에 표시할 공지 내용을 담는다.
		noticeList = noticeService.selectNoticeList();
		// 받아온 데이터 화면에 넘겨주기
		model.addAttribute("noticeList", noticeList);
		// 제대로 데이터가 담겨 있는지 로그에 찍어보기
		log.info("selectNoticeList 메서드 호출 : {}", noticeList);
		
		// notice화면에 표시할 값 가져오기 시작-------------------------------------------------------------
		return "notice";
	}
	
	//----------------------------------------------------------------------------------------------------
	// 공지를 추가할 메서드
	//----------------------------------------------------------------------------------------------------
	@PostMapping("/noticeAdd")
	public String insertNotice(@RequestParam Map<String, String> noticeAddMap, Model model) throws Exception{
		// 공지 추가 화면 - insert처리 시작-----------------------------------------------------------------
		
		// 화면에서 넘어온 데이터 찍어보기
		log.info("받은 값 : {}", noticeAddMap);
		// 화면에서 수정한 값 그릇에 담아 넘기기
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setSeqDoctor(1);
		noticeVO.setSeqAnimalHospital(1);
		noticeVO.setNoticeSubject(noticeAddMap.get("noticeSubject"));
		noticeVO.setNoticeContent(noticeAddMap.get("noticeContent"));
		noticeService.insertNotice(noticeVO);
		
		// 공지 추가 화면 - insert처리 종료-----------------------------------------------------------------
		return "notice";
	}
	
	//----------------------------------------------------------------------------------------------------
	// 공지를 삭제해줄 메서드
	//----------------------------------------------------------------------------------------------------
	@PostMapping("/noticeDetail")
	public String deleteNotice(@RequestParam int seqNotice) throws Exception {
		// 공지 상세 화면 - 화면에서 삭제할 데이터의 seq값을 받아 delete처리 시작------------------------------
		
		// 화면에서 넘겨받은 seq값 로그로 찍어보기
		log.info("받은 값 : {}", seqNotice);
		
		// 삭제처리를 위해 서비스 호출하기
		noticeService.deleteNotice(seqNotice);
		
		// 공지 상세 화면 - 화면에서 삭제할 데이터의 seq값을 받아 delete처리 시작------------------------------
		return "notice";
	}
	
	//----------------------------------------------------------------------------------------------------
	// 공지 상세 내용을 가져올 메서드
	//----------------------------------------------------------------------------------------------------
	@GetMapping(value = "/noticeDetail")
	public String selectNotice(@RequestParam Map<String, String> noticeDetailMap, Model model) throws Exception{
		// 수정한 내용을 공지상세 화면에 표시할 select 시작--------------------------------------------------
		
		// 화면에 넘길 데이터를 담을 그릇 준비
		NoticeListVO noticeListVO = null;
		// SQL실행시 필요한 데이터 준비하기
		int seqNotice = Integer.parseInt(noticeDetailMap.get("seqNotice"));
		// 데이터 그릇에 담기
		noticeListVO = noticeService.selectNotice(seqNotice);
		// 받아온 데이터 화면에 넘겨주기
		model.addAttribute("noticeDetailList", noticeListVO);
		// 제대로 데이터가 담겨 있는지 로그에 찍어보기
		log.info("서비스에서 돌아온 값 : {}", noticeListVO);
		
		// 수정한 내용을 공지상세 화면에 표시할 select 종료--------------------------------------------------
		return "noticeDetail";
	}
	
	//----------------------------------------------------------------------------------------------------
	// 공지 수정해줄 메서드
	//----------------------------------------------------------------------------------------------------
	@PostMapping("/noticeEdit")
	public String updateNotice(@RequestParam Map<String, String> noticeEditMap, Model model) throws Exception {
		// 공지 수정 화면 - 수정화면에서 수정할 값을 받아 update처리 시작-------------------------------------
		
		// 화면에서 넘어온 데이터 찍어보기
		log.info("공지 수정화면에서 수정한 값(컨트롤러) : {}", noticeEditMap);
		
		// 화면에서 수정한 값 그릇에 담아 넘기기
		NoticeListVO noticeListVO = new NoticeListVO();
		noticeListVO.setSeqNotice(Integer.parseInt(noticeEditMap.get("seqNotice")));
		noticeListVO.setNoticeSubject(noticeEditMap.get("noticeSubject"));
		noticeListVO.setNoticeContent(noticeEditMap.get("noticeContent"));
		noticeService.updateNotice(noticeListVO);
		// 공지 수정 화면 - 수정화면에서 수정할 값을 받아 update처리 종료-------------------------------------
		return "notice";		
	}
	
	//----------------------------------------------------------------------------------------------------
	// 수정화면으로 이동했을 때 수정할 내용을 화면에 표시해줄 메서드
	//----------------------------------------------------------------------------------------------------
	@GetMapping(value = "/noticeEdit")
	public String selectOneDetailNotice(@RequestParam Map<String, String> noticeOneDatailMap, Model model) throws Exception {
		int seqNotice = Integer.parseInt(noticeOneDatailMap.get("seqNotice"));
		// noticeDetail화면에 수정할 값 표시 시작---------------------------------------------------------
		
		// 화면에 넘길 데이터를 담을 그릇 준비
		List<NoticeOneDetailVO> noticeOneDatailList = null;
		// 데이터 그릇에 담기
		noticeOneDatailList = noticeService.selectOneDetailNotice(seqNotice);
		// 받아온 데이터 화면에 넘겨주기
		model.addAttribute("noticeOneDatailList", noticeOneDatailList);
		// 제대로 데이터가 담겨 있는지 로그에 찍어보기
		log.info("selectOneDetailNotice 서비스 에서 넘어온 값(컨트롤러) : {}", noticeOneDatailList);
		
		// noticeDetail화면에 수정할 값 표시 종료---------------------------------------------------------
		
		return "noticeEdit";
	}
}
