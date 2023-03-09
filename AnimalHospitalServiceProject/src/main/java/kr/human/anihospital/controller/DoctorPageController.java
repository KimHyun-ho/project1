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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import kr.human.anihospital.service.DoctorPageService;
import kr.human.anihospital.vo.DiagnosisInfoVO;
import kr.human.anihospital.vo.DocPatientInfoVO;
import kr.human.anihospital.vo.DoctorInfoVO;
import kr.human.anihospital.vo.MedicineInfoVO;
import kr.human.anihospital.vo.PagingVO;
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
	public String doctorInfo(Model model, HttpSession session) {
		// #########################################################
		// ## seqMember는 로그인 정보 Session에서 받아와야 합니다.##
		// #########################################################
		int seqMember = (int) session.getAttribute("seqMember");
		DoctorInfoVO doctorInfoVO = doctorPageService.selectOneDoctorInfoVO(seqMember);
		model.addAttribute("doctorInfo", doctorInfoVO);
		log.info("의사정보 : {}", doctorInfoVO);
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
	public String doctorInfoEditOk(@ModelAttribute DoctorInfoVO doctorInfoVO, MultipartFile file) {
		log.info("doctorInfoVO 받은 값 : {} {}", doctorInfoVO, file);
		doctorPageService.updateOneDoctorInfoVO(doctorInfoVO, file);
		return "redirect:doctorInfo";
	}

	// #############################################
	// #### 수의사 전용 페이지 -> 진료내역 조회 ####
	// #############################################
	@GetMapping("/patientInfoList")
	public String patientInfoList(@RequestParam(defaultValue = "1") int c, @RequestParam(defaultValue = "10") int p,
			@RequestParam(defaultValue = "10") int b, @RequestParam(defaultValue = "") String animalName, Model model) {
		// #########################################################
		// ## seqDoctor는 로그인 정보 Session에서 받아와야 합니다.##
		// #########################################################
		int seqDoctor = 1;
		if (animalName == null || animalName.equals("") || animalName.trim().length() == 0) {
			PagingVO<DocPatientInfoVO> pagingVO = doctorPageService.selectAllPatientInfoVO(seqDoctor, c, p, b);
			model.addAttribute("patientInfoList", pagingVO.getList());
			model.addAttribute("info", pagingVO.getInfo());
			model.addAttribute("list", pagingVO.getPageList());
			log.info("controller 에서 보내는 값 : {}", model);
		} else {
			PagingVO<DocPatientInfoVO> pagingVO = doctorPageService.selectOneAnimalPatientInfoListVO(seqDoctor, c, p, b,
					animalName);
			model.addAttribute("patientInfoList", pagingVO.getList());
			model.addAttribute("info", pagingVO.getInfo());
			model.addAttribute("list", pagingVO.getPageList());
		}
		return "patientInfoList";
	}

	// ###############################################################
	// #### 수의사 전용 페이지 -> 진료내역 조회 -> 환자 정보 조회 ####
	// ###############################################################
	@GetMapping("/patientInfo")
	public String patientInfo(@RequestParam int seqAnimal, Model model) {
		DocPatientInfoVO patientInfoVO = doctorPageService.selectOnePatientInfoVO(seqAnimal);
		List<DiagnosisInfoVO> diagnosisInfoVOs = doctorPageService.selectAllPatientDiaRecord(seqAnimal);
		model.addAttribute("patientInfoVO", patientInfoVO);
		model.addAttribute("diaRecords", diagnosisInfoVOs);
		log.info("환자정보 : {}", patientInfoVO);
		log.info("환자 이전 진료 내역 정보 : {}", diagnosisInfoVOs);
		return "patientInfo";
	}

	// ################################################################################
	// #### 수의사 전용 페이지 -> 진료내역 조회 -> 환자 정보 조회 -> 환자정보 수정 ####
	// ################################################################################
	@PostMapping("/patientInfoEditDoctor")
	public String patientInfoEditDoctor(@RequestParam int seqAnimal, Model model) {
		log.info("환자정보에서 받은 seqAnimal 값 : {}", seqAnimal);
		DocPatientInfoVO docPatientInfoVO = doctorPageService.selectOnePatientInfoVO(seqAnimal);
		log.info("수정 화면에 보내줄 환자 개인정보 : {}", docPatientInfoVO);
		model.addAttribute("seqAnimal", seqAnimal);
		model.addAttribute("patientInfo", docPatientInfoVO);
		return "patientInfoEditDoctor";
	}

	// ########################################################################################
	// #### 수의사 전용 페이지 -> 진료내역 조회 -> 환자 정보 조회 -> 환자정보 수정 -> 확인 ####
	// ########################################################################################
	@PostMapping("/patientInfoEditDoctorOk")
	public String patientInfoEditDoctorOk(@ModelAttribute DocPatientInfoVO docPatientInfoVO) {
		log.info("받은 수정값 : {}", docPatientInfoVO);
		doctorPageService.updateOnePatientInfo(docPatientInfoVO);
		return "redirect:patientInfo?seqAnimal=" + docPatientInfoVO.getSeqAnimal();
	}

	@GetMapping("/diagnosisAdd")
	public String diagnosisAdd(@RequestParam int seqAnimal, Model model) {
		log.info("받은 seqAnimal 값 : {}", seqAnimal);
		DocPatientInfoVO docPatientInfoVO = doctorPageService.selectOnePatientInfoVO(seqAnimal);
		List<MedicineInfoVO> medicineInfoVOs = doctorPageService.selectAllMedicineInfo();
		model.addAttribute("patientInfoVO", docPatientInfoVO);
		model.addAttribute("medicineInfoVOs", medicineInfoVOs);
		log.info("보내는 환자정보 값 : {}", docPatientInfoVO);
		return "diagnosisAdd";
	}

	@ResponseBody
	@PostMapping("/mediInfo")
	public MedicineInfoVO mediInfo(String medicineName) {
		MedicineInfoVO medicineInfoVO = doctorPageService.selectOneMedicineInfo(medicineName);
		return medicineInfoVO;
	}

	@PostMapping("/diagnosisAddOk")
	public String diagnosisAddOk(@RequestParam Map<String, Object> map, @RequestParam List<Integer> seqMedicineList,
			@RequestParam List<String> medicineNameList, @RequestParam List<String> medicationGuideList) {
		// #########################################################
		// ## seqDoctor는 로그인 정보 Session에서 받아와야 합니다.##
		// #########################################################
		int seqDoctor = 1;
		int seqAnimalHospital = doctorPageService.selectOneDoctorInfoVO(seqDoctor).getSeqAnimalHospital();
		map.put("seqDoctor", seqDoctor);
		map.put("seqAnimalHospital", seqAnimalHospital);
		log.info("받은 진료내용 값 : {}", map);
		doctorPageService.insertOneDiagnosisInfoAndDiagnosisMedicine(map, seqMedicineList, medicineNameList,
				medicationGuideList);
		return "redirect:patientInfo?seqAnimal=" + map.get("seqAnimal");
	}

	// #####################################################################################################
	// #### 수의사 전용 페이지 -> 진료내역 조회 -> 환자 정보및 이전 진료 내역 -> 진료내용 작성(추천사료 추가) ####
	// #####################################################################################################
	@PostMapping("/diagnosisAddFeedOk")
	@ResponseBody
	public String insertFeedExcelUpload(@RequestParam Map<String, String> feedJsonMap) {
		// 화면에서 추가한 엑셀 데이터가 잘 넘어왔는지 찍어보기
		log.info("insertFeedExcelUpload 실행, 화면에서 넘어온 값(컨트롤러) : {} {}", feedJsonMap);
		// 값 서비스에 넘기기
		doctorPageService.insertFeedExcelUpload(feedJsonMap);
		return "diagnosisAdd";
	}
	
	// #####################################################################################################
	// #### 수의사 전용 페이지 -> 진료내역 조회 -> 환자 정보및 이전 진료 내역 -> 진료내용 작성(추천사료 추가) ####
	// #####################################################################################################
	@PostMapping("/diagnosisAddSupplementsOk")
	@ResponseBody
	public String insertSupplementsExcelUpload(@RequestParam Map<String, String> SupplementsJsonMap) {
		// 화면에서 추가한 엑셀 데이터가 잘 넘어왔는지 찍어보기
		log.info("insertSupplementsExcelUpload 실행, 화면에서 넘어온 값(컨트롤러) : {} {}", SupplementsJsonMap);
		// 값 서비스에 넘기기
		doctorPageService.insertSupplementsExcelUpload(SupplementsJsonMap);
		return "diagnosisAdd";
	}

}
