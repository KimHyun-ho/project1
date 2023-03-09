package kr.human.anihospital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("inner")
	public String inner() {
		return "inner";
	}

	// 후기 작성
	@GetMapping("/ProPostscriptAdd")
	public String postscriptSave() {
		return "ProPostscriptAdd";
	}

	// 후기 수정
	@GetMapping("/ProPostscriptEdit")
	public String postscriptUpdate() {
		return "ProPostscriptEdit";
	}

	// 약관동의 및 역할 체크
	@GetMapping("/roleCheck")
	public String roleCheck() {
		return "roleCheck";
	}
	
	// 서비스 소개
	@GetMapping("/serviceIntroduce")
	public String serviceIntroduce() {
		return "serviceIntroduce";
	}
	
	// 의사 진료내용 조회
	@GetMapping("/docDiagnosis")
	public String docDiagnosis() {
		return "docDiagnosis";
	}

	// 환자 추가
	@GetMapping("/proPatientAdd")
	public String proPatientAdd() {
		return "proPatientAdd";
	}

	@GetMapping("/hospitalMap")
	public String hospitalMap() {
		return "hospitalMap";
	}

	@GetMapping("/hospitalAdd")
	public String hospitalAdd() {
		return "hospitalAdd";
	}


	// 약국 위치 조회
	@GetMapping("/pharmacyMap")
	public String pharmacyMap() {
		return "pharmacyMap";
	}

	// 약국 위치 추가
	@GetMapping("/pharmacyInfoAdd")
	public String pharmacyInfoAdd() {
		return "pharmacyInfoAdd";
	}
	
   // 의사스케줄 등록
   @GetMapping("/scheduleDoctorAdd")
   public String scheduleDoctorAdd() {
      return "scheduleDoctorAdd";
   }

   // 의사 스케줄 수정 및 삭제
   @GetMapping("/scheduleDoctorEdit")
   public String scheduleDoctorEdit() {
	   return "scheduleDoctorEdit";
   }

   // 보호자 스케줄(예약) 등록
   @GetMapping("/scheduleProtectorAdd")
   public String scheduleProtectorAdd() {
	   return "scheduleProtectorAdd";
   }
   
   // 보호자 스케줄(예약 수정 및 삭제
   @GetMapping("/scheduleProtectorEdit")
   public String scheduleProtectorEdit() {
	   return "scheduleProtectorEdit";
   }
}
