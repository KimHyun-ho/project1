package kr.human.anihospital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.human.anihospital.service.NaverMapService;
import kr.human.anihospital.vo.AnimalHospitalVO;
import kr.human.anihospital.vo.PharmacyLocationVO;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class NaverMapController {

	@Autowired
	NaverMapService naverMapService;

	//----------------------------------------------------------------------------------------------------
	// 병원 지도 띄우기 
	//----------------------------------------------------------------------------------------------------
	@GetMapping("/hospitalMapRest")
	public List<AnimalHospitalVO> hospitalMapRest() {
		// 병원 정보 담을 그릇 만들기
		List<AnimalHospitalVO> hospitalList = null;
		// 그릇에 지도에 표시할 정보 담기
		hospitalList = naverMapService.selectHospitalMap();
		// 화면에 돌려주기
		log.info("selectHospitalMap 실행값 : {}",hospitalList);
		return hospitalList;
	}

	//----------------------------------------------------------------------------------------------------
	// 약국 지도 띄우기 
	//----------------------------------------------------------------------------------------------------
	@GetMapping("/pharmacyMapRest")
	public List<PharmacyLocationVO> pharmacyMapRest() {
		// 약국 정보 담을 그릇 만들어 받아온 값 담기
		List<PharmacyLocationVO> pharmacyList = naverMapService.selectPharmacyMap();
		// 화면에 돌려주기
		return pharmacyList;
	}
}
