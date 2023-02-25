package kr.human.anihospital.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.human.anihospital.service.DoctorPageService;
import kr.human.anihospital.vo.DoctorInfoVO;
import kr.human.anihospital.vo.PatientInfoVO;
import kr.human.anihospital.vo.PatientInfoVO.PatientDiaRecord;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class DoctorPageController {

	@Autowired
	DoctorPageService doctorPageService;

	// #############################################
	// ### 수의사 전용 페이지 -> 나의 정보 조회 ####
	// #############################################
	@GetMapping("/doctorInfo")
	public String doctorInfo(Model model) {
		// #########################################################
		// ## seqMember는 로그인 정보 Session에서 받아와야 합니다.##
		// #########################################################
		int seqMember = 1;
		model.addAttribute("doctorInfo", doctorPageService.selectOneDoctorInfoVO(seqMember));
		return "doctorInfo";
	}

	// #########################################################
	// ### 수의사 전용 페이지 -> 나의 정보 조회 -> 수정하기 ####
	// #########################################################
	@PostMapping("/doctorInfoEdit")
	public String doctorInfoEdit(@RequestParam Map<String, String> map, Model model) {
		log.info("받은 map : {}", map);
		model.addAttribute("map", map);
		return "doctorInfoEdit";
	}

	// #####################################################################
	// ### 수의사 전용 페이지 -> 나의 정보 조회 -> 수정하기 -> 수정하기 ####
	// #####################################################################
	@PostMapping("/doctorInfoEditOk")
	public String doctorInfoEditOk(@ModelAttribute DoctorInfoVO doctorInfoVO) {
		log.info("doctorInfoVO 받은 값 : {}", doctorInfoVO);
		doctorPageService.updateOneDoctorInfoVO(doctorInfoVO);
		return "redirect:doctorInfo";
	}

	// #############################################
	// #### 수의사 전용 페이지 -> 전체환자 조회 ####
	// #############################################
	@GetMapping("/patientInfoList")
	public String patientInfoList(Model model) {
		// #########################################################
		// ## seqDoctor는 로그인 정보 Session에서 받아와야 합니다.##
		// #########################################################
		int seqDoctor = 1;
		model.addAttribute("patientInfoList", doctorPageService.selectAllPatientInfoVO(seqDoctor));
		log.info("controller 에서 보내는 값 : {}", model);
		return "patientInfoList";
	}

	@GetMapping("/patientInfo")
	public String patientInfo(@RequestParam int seqAnimal, Model model) {
		PatientInfoVO patientInfoVO = doctorPageService.selectOnePatientInfoVO(seqAnimal);
		List<PatientDiaRecord> patientDiaRecords = doctorPageService.selectAllPatientDiaRecord(seqAnimal);
		model.addAttribute("patientInfoVO", patientInfoVO);
		model.addAttribute("diaRecords", patientDiaRecords);
		log.info("환자정보 : {}", doctorPageService.selectOnePatientInfoVO(seqAnimal));
		log.info("환자 이전 진료 내역 정보 : {}", doctorPageService.selectAllPatientDiaRecord(seqAnimal));
		return "patientInfo";
	}
}
