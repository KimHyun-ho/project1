package kr.human.anihospital.controller;

import java.text.SimpleDateFormat;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.human.anihospital.service.PatientInfoEditProtectorService;
import kr.human.anihospital.vo.AnimalVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class PatientInfoEditProtectorController {

	@Autowired
	PatientInfoEditProtectorService patientInfoEditProtectorService;
	
	//----------------------------------------------------------------------------------------------------
	// 환자 정보 수정시 보여줄 정보 조회 메서드
	//----------------------------------------------------------------------------------------------------
	@GetMapping("patientInfoEditProtector")
	public String PatientInfoEditProtectorSelect(Model model) {
		AnimalVO animalVO = new AnimalVO();
		animalVO = patientInfoEditProtectorService.selectPatient();
		log.info("animalVO : {}",animalVO);
		model.addAttribute("animalVO", animalVO);
		return "patientInfoEditProtector";
	}
	
	//----------------------------------------------------------------------------------------------------
	// 환자 정보 수정 후 확인 눌렀을때 수정 처리해줄 메서드
	//----------------------------------------------------------------------------------------------------	
	@PostMapping("patientInfoEditProtectorOk")
	public String PatientInfoEditProtectorOk(@RequestParam Map<String, Object> map) {
		log.info("수정값 : {}", map);
		patientInfoEditProtectorService.updatePatient(map);
		return "redirect:myPageProtector";
	}
	
	//----------------------------------------------------------------------------------------------------
	// 환자 정보 추가 메서드 -- seq 작업 필요
	//----------------------------------------------------------------------------------------------------
	@PostMapping("/patientAdd")
    //여기에도 MultipartFile file,file 받아준 후 //예외처리
	public String insertPatient(@RequestParam Map<String, String> patientAddMap, Model model, MultipartFile file,  MultipartFile vidfile ) throws Exception{
		log.info("받은 값 : {}", patientAddMap);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		AnimalVO animalVO = new AnimalVO();
		animalVO.setSeqProtector(1); // 추후 session값에서 가져오기
		animalVO.setSeqDoctor(1); // seqDoctor 추후 삭제
		animalVO.setAnimalName(patientAddMap.get("animalName"));
		animalVO.setAnimalType(patientAddMap.get("animalType"));
		animalVO.setAnimalSize(patientAddMap.get("animalSize"));
		animalVO.setAnimalWeight(Float.parseFloat(patientAddMap.get("animalWeight")));
		animalVO.setAnimalAge(sdf.parse(patientAddMap.get("animalAge")));
		animalVO.setAnimalGender(Boolean.parseBoolean(patientAddMap.get("animalGender")));
		animalVO.setAnimalImportantSymptom(patientAddMap.get("animalImportantSymptom"));
		
		animalVO.setAnimalPicture(patientAddMap.get("animalPicture"));
		animalVO.setAnimalVideo(patientAddMap.get("animalVideo"));
		animalVO.setFilePath(patientAddMap.get("filePath"));
		animalVO.setVidfilePath(patientAddMap.get("vidfilePath"));
		
		patientInfoEditProtectorService.insertPatient(animalVO,file,vidfile);
		
		return "animallookup";
	}
}