package kr.human.anihospital.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.human.anihospital.service.HospitalService;
import kr.human.anihospital.vo.AnimalHospitalVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HospitalController {

	@Autowired
	HospitalService hospitalService;
	//----------------------------------------------------------------------------------------------------
	// 병원정보를 추가할 메서드
	//----------------------------------------------------------------------------------------------------
	@PostMapping("/hospitalAddOk")
	public String insertAnimalHospital(@RequestParam Map<String, String> animalHospitalMap, Model model)
			throws Exception {
		// 병원 추가 화면 - insert처리 시작-----------------------------------------------------------------
		
		// 화면에서 넘어온 데이터 찍어보기
		log.info("받은 값 : {}", animalHospitalMap);
		// 화면에서 수정한 값 그릇에 담아 넘기기
		AnimalHospitalVO animalHospitalVO = new AnimalHospitalVO();
		animalHospitalVO.setAnimalHospitalName(animalHospitalMap.get("animalHospitalName"));
		animalHospitalVO.setAnimalHospitalAddress(
				animalHospitalMap.get("animalHospitalAddress") + animalHospitalMap.get("animalHospitalAddressDetail"));
		animalHospitalVO.setAnimalHospitalPhoneNo(animalHospitalMap.get("animalHospitalPhoneNo"));
		animalHospitalVO.setHospitalLocationLatitude(animalHospitalMap.get("hospitalLocationLatitude"));
		animalHospitalVO.setHospitalLocationLongitude(animalHospitalMap.get("hospitalLocationLongitude"));
		hospitalService.insertAnimalHospitalInfo(animalHospitalVO);
		
		// 병원 추가 화면 - insert처리 종료-----------------------------------------------------------------
		return "redirect:hospitalMap";
	}

	//----------------------------------------------------------------------------------------------------
	// 병원정보를 보여줄 메서드(seq)
	//----------------------------------------------------------------------------------------------------
	@PostMapping("/hospitalEdit")
	public String hospitalEdit(@RequestParam int seq, Model model) {
		try {
			model.addAttribute("model", hospitalService.selectHospital(seq));
			model.addAttribute("seq", seq);
			
			// 화면에서 넘어온 데이터 찍어보기
			log.info("seq로 읽은 값 : {}", hospitalService.selectHospital(seq));
			log.info("seq : {}", seq);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "hospitalEdit";
	}
	
	//----------------------------------------------------------------------------------------------------
	// 병원정보를 수정해줄 메서드
	//----------------------------------------------------------------------------------------------------
	@PostMapping("/hospitalEditOk")
	public String updateAnimalHospital(@RequestParam Map<String, String> animalHospitalMap, Model model)
			throws Exception {
		// 병원정보 수정 화면 - 수정화면에서 수정할 값을 받아 update처리 시작-------------------------------------
		
		// 화면에서 넘어온 데이터 찍어보기
		log.info("받은 값 : {}", animalHospitalMap);
		
		// 화면에서 수정한 값 그릇에 담아 넘기기
		AnimalHospitalVO animalHospitalVO = new AnimalHospitalVO();
		animalHospitalVO.setSeqAnimalHospital(Integer.parseInt(animalHospitalMap.get("seq")));
		animalHospitalVO.setAnimalHospitalName(animalHospitalMap.get("animalHospitalName"));
		animalHospitalVO.setAnimalHospitalAddress(
				animalHospitalMap.get("animalHospitalAddress") + animalHospitalMap.get("animalHospitalAddressDetail"));
		animalHospitalVO.setAnimalHospitalPhoneNo(animalHospitalMap.get("animalHospitalPhoneNo"));
		animalHospitalVO.setHospitalLocationLatitude(animalHospitalMap.get("hospitalLocationLatitude"));
		animalHospitalVO.setHospitalLocationLongitude(animalHospitalMap.get("hospitalLocationLongitude"));
		hospitalService.updateAnimalHospitalInfo(animalHospitalVO);
		
		// 병원정보 수정 화면 - 수정화면에서 수정할 값을 받아 update처리 종료-------------------------------------
		return "redirect:hospitalMap";
	}
}
