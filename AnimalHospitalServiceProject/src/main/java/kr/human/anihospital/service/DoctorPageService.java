package kr.human.anihospital.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.human.anihospital.mapper.DoctorPageMapper;
import kr.human.anihospital.vo.DoctorInfoVO;
import kr.human.anihospital.vo.MedicineInfoVO;
import kr.human.anihospital.vo.DiagnosisInfoVO;
import kr.human.anihospital.vo.DocPatientInfoVO;

//####################################################################
//##수의사 전용 페이지의 모든 CRUD를 담은 Service 클래스 파일입니다.##
//####################################################################
@Service
public class DoctorPageService {

	@Autowired
	DoctorPageMapper doctorPageMapper;

	// 세션으로부터 seqMember값을 받아 내 정보 조회(의사)하는 서비스 메소드
	public DoctorInfoVO selectOneDoctorInfoVO(int seqMember) {
		DoctorInfoVO doctorInfoVO = new DoctorInfoVO();
		try {
			doctorInfoVO = doctorPageMapper.selectOneDoctorInfoVO(seqMember);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doctorInfoVO;
	}

	// /doctorInfoEdit 페이지에서 수정한 값으로 DB를 업데이트 하는 서비스 메소드
	public void updateOneDoctorInfoVO(DoctorInfoVO doctorInfoVO, MultipartFile file) {
		// 저장할 경로를 지정
		  String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
	        // UUID(식별자)를 사용해 사용해 랜덤으로 이름 만들어줌
	        UUID uuid = UUID.randomUUID();

	        // 랜덤식별자_원래 파일 이름 = 저장될 파일이름 지정
	        String fileName = uuid + "_" + file.getOriginalFilename();

	        // File이 생성되며, 이름은 "name", projectPath 라는 경로에 담긴다
	        File saveFile = new File(projectPath, fileName);
	        	try {
					file.transferTo(saveFile);
				} catch (IllegalStateException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
	        //DB에 파일 넣기
	        doctorInfoVO.setDoctorPicture(fileName);
	        //저장되는 경로 설정 
	        doctorInfoVO.setDoctorPicturePath("/files/" + fileName);
		try {
			doctorPageMapper.updateOneDoctorInfoVO(doctorInfoVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 세션으로부터 seqDoctor값을 받아 /patientInfoList 페이지에서 진료내역을 리스트로 조회하는 서비스 메소드
	public List<DocPatientInfoVO> selectAllPatientInfoVO(int seqDoctor) {
		List<DocPatientInfoVO> patientInfoVOs = new ArrayList<>();
		try {
			patientInfoVOs = doctorPageMapper.selectAllPatientInfoVO(seqDoctor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return patientInfoVOs;
	}

	// /patientInfoList 페이지에서 진료내역을 클릭하여 /patientInfo 페이지로 이동할 때 seqAnimal 값을 받아
	// 환자 정보를 조회하는 서비스 메소드
	public DocPatientInfoVO selectOnePatientInfoVO(int seqAnimal) {
		DocPatientInfoVO patientInfoVO = new DocPatientInfoVO();
		try {
			patientInfoVO = doctorPageMapper.selectOnePatientInfoVO(seqAnimal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return patientInfoVO;
	}

	// /patientInfoList 페이지에서 진료내역을 클릭하여 /patientInfo 페이지로 이동할 때 seqAnimal 값을 받아
	// 환자의 이전 진료내역을 조회하는 서비스 메소드
	public List<DiagnosisInfoVO> selectAllPatientDiaRecord(int seqAnimal) {
		List<DiagnosisInfoVO> patientDiaRecords = new ArrayList<>();
		try {
			patientDiaRecords = doctorPageMapper.selectAllPatientDiaRecord(seqAnimal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return patientDiaRecords;

	}

	// /patientInfo 페이지에서 '수정'버튼을 눌러 환자의 개인정보를 수정하는 서비스 메소드
	public void updateOnePatientInfo(DocPatientInfoVO docPatientInfoVO) {
		try {
			doctorPageMapper.updateOnePatientInfo(docPatientInfoVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// /diagnosisAdd 페이지에 의약품 정보를 출력하기 위한 서비스 메소드
	public List<MedicineInfoVO> selectAllMedicineInfo() {
		List<MedicineInfoVO> medicineInfoVOs = new ArrayList<>();
		try {
			medicineInfoVOs = doctorPageMapper.selectAllMedicineInfo();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return medicineInfoVOs;
	}

	public MedicineInfoVO selectOneMedicineInfo(String medicineName) {
		MedicineInfoVO medicineInfoVO = new MedicineInfoVO();
		try {
			medicineInfoVO = doctorPageMapper.selectOneMedicineInfo(medicineName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return medicineInfoVO;
	}

	public void insertOneDiagnosisInfoAndDiagnosisMedicine(Map<String, Object> map, List<Integer> seqMedicineList,
			List<String> medicineNameList, List<String> medicationGuideList) {
		HashMap<String, Integer> hashMap = new HashMap<>();
		DiagnosisInfoVO diagnosisInfoVO = new DiagnosisInfoVO();
		diagnosisInfoVO.setSeqDoctor(Integer.parseInt(String.valueOf(map.get("seqDoctor"))));
		diagnosisInfoVO.setSeqAnimal(Integer.parseInt(String.valueOf(map.get("seqAnimal"))));
		diagnosisInfoVO.setSeqAnimalHospital(Integer.parseInt(String.valueOf(map.get("seqAnimalHospital"))));
		diagnosisInfoVO.setDiagnosisSymptom(String.valueOf(map.get("diagnosisSymptom")));
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String diagnosisMedicationWay = "";
		for (int i = 0; i < seqMedicineList.size(); i++) {
			diagnosisMedicationWay += medicineNameList.get(i) + " : " + medicationGuideList.get(i);
			diagnosisMedicationWay += "\n";
		}
		diagnosisInfoVO.setDiagnosisMedicationWay(diagnosisMedicationWay);
		try {
			diagnosisInfoVO.setDiagnosisDate(dateFormat.parse(String.valueOf(map.get("diagnosisDate"))));
			doctorPageMapper.insertOneDiagnosisInfo(diagnosisInfoVO);
			int seqDiagnosis = doctorPageMapper.selectLastInsertIndex();
			for (int i = 0; i < seqMedicineList.size(); i++) {
				hashMap.put("seqMedicine", seqMedicineList.get(i));
				hashMap.put("seqDiagnosis", seqDiagnosis);
				doctorPageMapper.insertOneDiagnosisMedicine(hashMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
