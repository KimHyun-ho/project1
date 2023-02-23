package kr.human.anihospital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.human.anihospital.service.PatientInfoService;
import kr.human.anihospital.vo.PatientInfoVO;
import kr.human.anihospital.vo.patientInfoDiagnosisListVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class PatientInfoController {
	
	// 환자 정보 selectAll
	@Autowired
	PatientInfoService patientInfoService;
	
	//----------------------------------------------------------------------------------------------------
	// 환자 정보 리스트 띄우기 
	//----------------------------------------------------------------------------------------------------
	@GetMapping(value = "/patientInfo")
	public String selectPatientInfoDiagnosis(@RequestParam int seqAnimal,Model model) throws Exception {
		// 화면에서 seq값이 잘 넘어오고 있는지 찍어보기
		log.info("PatientList에서 받은 seqAnimal : {}",seqAnimal);
		// 데이터 담을 그릇 준비
		List<patientInfoDiagnosisListVO> pidliat = null;
		PatientInfoVO patientInfoVO = null;
		// 화면에 넘겨야 할 데이터 그릇에 담기위해 서비스 호출
		pidliat = patientInfoService.selectPatientInfoDiagnosis();
		patientInfoVO = patientInfoService.selectPatientInfo(seqAnimal);
		// 담긴 데이터 화면에 돌려주기
		model.addAttribute("pidliat", pidliat);
		model.addAttribute("patientInfoVO", patientInfoVO);
		// 돌려줄 데이터가 잘 담겨 있는지 로그로 찍어보기
		log.info("selectPatientInfoDiagnosis메서드 호출(컨트롤러) : {}",  pidliat);
		log.info("selectPatientInfo메서드 호출(컨트롤러) : {}",  patientInfoVO);
		// 화면에 돌려주기
		return "patientInfo";
	}
}
