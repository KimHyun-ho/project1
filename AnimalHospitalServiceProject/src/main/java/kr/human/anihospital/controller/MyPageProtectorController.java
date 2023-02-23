package kr.human.anihospital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.human.anihospital.service.MyPageProtectorService;
import kr.human.anihospital.vo.OneProtectorPatientListVO;
import kr.human.anihospital.vo.OneProtectorPostscriptListVO;
import kr.human.anihospital.vo.PatientDiagnosisListVO;
import kr.human.anihospital.vo.ProtectorVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MyPageProtectorController {

	@Autowired
	MyPageProtectorService myPageProtectorService;
	
	// 나중에 seq 처리 해줘야함
	@GetMapping(value = "/myPageProtector")
	public String myPageProtector(@RequestParam(required = false, defaultValue = "1") int seqProtector, Model model) {
		// myPageProtector에 표시할 값들 가져오기 
		
		//----------------------------------------------------------------------------------------------------
		// 보호자 정보 
		//----------------------------------------------------------------------------------------------------
		ProtectorVO protectInfo = myPageProtectorService.selectProtector(seqProtector);
		// 받아온 데이터 화면에 넘겨주기
		model.addAttribute("protectInfo", protectInfo);
		// 제대로 데이터가 담겨 있는지 로그에 찍어보기
		log.info("service에서 넘어온 보호자 정보 값 {} ", protectInfo);
		
		//----------------------------------------------------------------------------------------------------
		// 이전 진료 내역
		//----------------------------------------------------------------------------------------------------
		List<PatientDiagnosisListVO> patientDiagnosisList = myPageProtectorService.selectPatientDiagnosisList(seqProtector);
		//받아온 데이터 화면에 넘겨주기
		model.addAttribute("patientDiagnosisList", patientDiagnosisList);
		// 제대로 데이터가 담겨 있는지 로그에 찍어보기
		log.info("service에서 넘어온 이전 진료 값 {} ", patientDiagnosisList);
	
		//----------------------------------------------------------------------------------------------------
		// 환자 정보
		//----------------------------------------------------------------------------------------------------
		// 화면에 넘길 데이터를 담을 그릇 준비
		List<OneProtectorPatientListVO> oneProtectorPatientListVO = null;
		// 데이터 그릇에 담기
		oneProtectorPatientListVO = myPageProtectorService.selectOneProtectorPatientList(2);
		// 받아온 데이터 화면에 넘겨주기
		model.addAttribute("oneProtectorPatient", oneProtectorPatientListVO);
		// 제대로 데이터가 담겨 있는지 로그에 찍어보기
		log.info("service에서 넘어온 값 {} ", oneProtectorPatientListVO);
		
		//----------------------------------------------------------------------------------------------------
		// 작성 한 후기 리스트
		//----------------------------------------------------------------------------------------------------
		// 화면에 넘길 데이터를 담을 그릇 준비
		List<OneProtectorPostscriptListVO> oneProtectorPostscriptListVO = null;
		// 데이터 그릇에 담기
		oneProtectorPostscriptListVO = myPageProtectorService.selectOneProtectorPostcriptList(2);
		// 받아온 데이터 화면에 넘겨주기
		model.addAttribute("postscriptList",oneProtectorPostscriptListVO);
		// 제대로 데이터가 담겨 있는지 로그에 찍어보기
		log.info("service에서 넘어온 값 {}" ,oneProtectorPostscriptListVO);
		// myPageProtector화면에 표시할 값 표시 종료
		return "myPageProtector";
		
	}
	// 나중에 seq 처리 해줘야함(보호자 로그인 정보)
	//----------------------------------------------------------------------------------------------------
	// 보호자 정보 수정할때 수정되기 전 정보를 조회
	//----------------------------------------------------------------------------------------------------
	@GetMapping(value = "/editMyPageProtector")
	public String editMyPageProtector(@RequestParam int seqProtector, Model model) {
		log.info("받은 seq {} : ", seqProtector);
		// 데이터 그릇에 담기
		ProtectorVO vo = myPageProtectorService.selectProtector(seqProtector);
		// 제대로 데이터가 담겨 있는지 로그에 찍어보기
		log.info("받은값 {}" ,vo);
		// 받아온 데이터 화면에 넘겨주기
		model.addAttribute("protectInfo", vo);
		// editMyPageProtector화면에 수정할 값 표시 종료
		return "editMyPageProtector";
	}
}