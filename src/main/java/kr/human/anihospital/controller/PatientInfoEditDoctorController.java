package kr.human.anihospital.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.human.anihospital.service.PatientInfoEditDoctorService;
import kr.human.anihospital.vo.AnimalVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class PatientInfoEditDoctorController {

	@Autowired
	PatientInfoEditDoctorService patientInfoEditDoctorService;

	//----------------------------------------------------------------------------------------------------
	// 환자 정보 의사가 수정하기
	//----------------------------------------------------------------------------------------------------
	@PostMapping("/patientInfoEditDoctor")
	public String patientInfoEditDoctor(@RequestParam int seqAnimal, Model model) throws Exception {
		// 수정이 필요한 데이터를 화면에 뿌려주기
		AnimalVO animalVO = new AnimalVO();
		// 수정할 데이터 담아오기
		animalVO = patientInfoEditDoctorService.patientInfoDoctor(seqAnimal);
		// 조회할 데이터의 seq값이 잘 넘어오고 있는지 확인하기
		log.info("patientInfo에서 받은 seq : {}", seqAnimal);
		// 화면에 가져온 데이터 넘겨주기
		model.addAttribute("animalVO", animalVO);
		// 데이터를 잘 가지고 오고 있는지 찍어보기
		log.info("animalVO : {}", animalVO);
		// 화면에 전송
		return "patientInfoEditDoctor";
	}

	//----------------------------------------------------------------------------------------------------
	// 환자 정보 보호자가 수정하기
	//----------------------------------------------------------------------------------------------------
	@PostMapping("/patientInfoEditDoctorOk")
	public String patientInfoEditDoctorOk(@RequestParam Map<String, Object> map) throws Exception {
		// 화면에서 수정할 데이터들이 잘 넘어오고 있는지 찍어보기
		log.info("수정값 : {} ", map);
		// 넘어온 데이터로 update실행
		patientInfoEditDoctorService.updateAnimalDoctor(map);
		// 수정한 데이터의 seq값 받아오기
		int seqAnimal = Integer.parseInt(String.valueOf(map.get("seqAnimal")));
		// seq값과 함께 넘겨 수정한 데이터 화면에 뿌리기
		return "redirect:patientInfo?seqAnimal="+seqAnimal;
	}
}
