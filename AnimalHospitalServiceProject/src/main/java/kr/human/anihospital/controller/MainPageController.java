package kr.human.anihospital.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.human.anihospital.service.MainPageService;
import kr.human.anihospital.vo.DoctorInfoVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MainPageController {

	@Autowired
	MainPageService mainPageService;
	
	// 메인 페이지
	@GetMapping("/")
	public String home(Model model) throws Exception {
		// 의사 인원
		int doctorCount = mainPageService.selectCountDoctorCount();
		model.addAttribute("doctorCount",doctorCount);
		// 보호자 인원 
		int ProtectorCount = mainPageService.selectCountProCount();
		model.addAttribute("ProtectorCount",ProtectorCount);
		// 병원 수
		int hospitalCount = mainPageService.selectCountHospitalCount();
		model.addAttribute("hospitalCount",hospitalCount);
		// 약국 수
		int pharmacyCount = mainPageService.selectCountPharmacyCount();
		model.addAttribute("pharmacyCount",pharmacyCount);
		// 환자가 가장 많은 의사 4명
		List<DoctorInfoVO> bestDoctorList = mainPageService.selectBestDoctor();
		log.info("bestDoctorList : {}", bestDoctorList);
		model.addAttribute("bestDoctorList",bestDoctorList);
		// 최근 후기 3개 
		List<Map<String, Object>> recentPostscriptList = mainPageService.selectRecentPostscript();
		log.info("bestDoctorList : {}", recentPostscriptList);
		model.addAttribute("recentPostscriptList",recentPostscriptList);
		// 랜덤으로 사료 1개를 가져오는 메서드
		Map<String, Object> selectFeedMap = mainPageService.selectOneFeed();
		model.addAttribute("feedMap",selectFeedMap);
		// 랜덤으로 영양제 1개를 가져오는 메서드
		Map<String, Object> selectSupplementsMap = mainPageService.selectOneSupplements();
		model.addAttribute("supplementsMap",selectSupplementsMap);
		
		return "index";
	}
}
