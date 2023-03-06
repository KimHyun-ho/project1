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
import kr.human.anihospital.vo.FeedVO;
import kr.human.anihospital.vo.MedicineInfoVO;
import kr.human.anihospital.vo.PagingVO;
import kr.human.anihospital.vo.SupplementsVO;
import lombok.extern.slf4j.Slf4j;
import kr.human.anihospital.vo.DiagnosisInfoVO;
import kr.human.anihospital.vo.DocPatientInfoVO;

//####################################################################
//##수의사 전용 페이지의 모든 CRUD를 담은 Service 클래스 파일입니다.##
//####################################################################
@Service
@Slf4j
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
		// DB에 파일 넣기
		doctorInfoVO.setDoctorPicture(fileName);
		// 저장되는 경로 설정
		doctorInfoVO.setDoctorPicturePath("/files/" + fileName);
		try {
			doctorPageMapper.updateOneDoctorInfoVO(doctorInfoVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 세션으로부터 seqDoctor값을 받아 /patientInfoList 페이지에서 진료내역을 리스트로 조회하는 서비스 메소드
	public PagingVO<DocPatientInfoVO> selectAllPatientInfoVO(int seqDoctor,int currentPage, int pageSize, int blockSize) {
		PagingVO<DocPatientInfoVO> pagingVO = null;
		try {
			int totalCount = doctorPageMapper.selectCountPatientInfoVOList(seqDoctor);
			pagingVO = new PagingVO<>(totalCount, currentPage, pageSize, blockSize); 
			pagingVO.setList(doctorPageMapper.selectAllPatientInfoVO(seqDoctor,pagingVO.getStartNo(),pagingVO.getPageSize()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pagingVO;
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

	// diagnosisAdd 페이지에서 추천사료 엑셀 파일을 추가해 넘어온 JSON파일을 insert하는 메퍼 메소드
	public void insertFeedExcelUpload(Map<String, String> feedJSONMap) {
		// 값이 제대로 넘어오는지 로그 찍어보기
		log.info("insertFeedExcelUpload 실행, 화면에서 넘어온 값(서비스) : {}", feedJSONMap);
		// 데이터 담을 그릇 준비
		FeedVO feedVO = new FeedVO();
		// Json에서 데이터 뽑아다 vo에 담기
		for (int i = 0; i < feedJSONMap.size() / 5; i++) {
			feedVO.setFeedName(feedJSONMap.get("data[" + i + "][사료 이름]"));
			feedVO.setFeedCompany(feedJSONMap.get("data[" + i + "][제조회사]"));
			feedVO.setFeedAllergySymptom(feedJSONMap.get("data[" + i + "][성분]"));
			feedVO.setFeedSideEffect(feedJSONMap.get("data[" + i + "][알레르기 증상]"));
			feedVO.setFeedUpdateDate(feedJSONMap.get("data[" + i + "][DB추가일]"));
			try {
				// vo에 담은 데이터 DB에 집어 넣기
				doctorPageMapper.insertFeedExcelUpload(feedVO);
				// vo에 데이터가 잘 담겨 있는지 로그 찍어보기
				log.info("insertFeedExcelUpload 실행, 메퍼에서 넘어온 값(서비스) : {}", feedVO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	// diagnosisAdd 페이지에서 추천사료 엑셀 파일을 추가해 넘어온 JSON파일을 insert하는 메퍼 메소드
	public void insertSupplementsExcelUpload(Map<String, String> SupplementsJSONMap) {
		// 값이 제대로 넘어오는지 로그 찍어보기
		log.info("insertFeedExcelUpload 실행, 화면에서 넘어온 값(서비스) : {}", SupplementsJSONMap);
		// 데이터 담을 그릇 준비
		SupplementsVO supplementsVO = new SupplementsVO();
		// Json에서 데이터 뽑아다 vo에 담기
		for (int i = 0; i < SupplementsJSONMap.size() / 4; i++) {
			supplementsVO.setSupplementsName(SupplementsJSONMap.get("data[" + i + "][영양제 이름]"));
			supplementsVO.setSupplementsCompany(SupplementsJSONMap.get("data[" + i + "][제조회사]"));
			supplementsVO.setSupplementsSideEffect(SupplementsJSONMap.get("data[" + i + "][알레르기 증상]"));
			supplementsVO.setSupplementsUpdateDate(SupplementsJSONMap.get("data[" + i + "][DB추가일]"));
			try {
				// vo에 담은 데이터 DB에 집어 넣기
				doctorPageMapper.insertSupplementsExcelUpload(supplementsVO);
				// vo에 데이터가 잘 담겨 있는지 로그 찍어보기
				log.info("insertSupplementsExcelUpload 실행, 메퍼에서 넘어온 값(서비스) : {}", supplementsVO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	// 검색창에 입력된 환자 이름에 해당하는 환자 정보를 조회하는 서비스 메소드
	public PagingVO<DocPatientInfoVO> selectOneAnimalPatientInfoListVO(int seqDoctor, 
																	   int currentPage, 
																	   int pageSize, 
																	   int blockSize,
																	   String animalName) {
		log.info("seqDoctor, currentPage, pageSize, blockSize, animalName : {} {} {} {} {}", seqDoctor, currentPage, pageSize, blockSize, animalName);
		// 페이징에 필요한 그릇들 준비
		PagingVO<DocPatientInfoVO> pagingVO = null;
		// 페이징 count에 필요한 값 담을 그릇
		Map<String, Object> countPagingOnePatient = new HashMap<>();
		// 데이터 담기
		countPagingOnePatient.put("seqDoctor", seqDoctor);
		countPagingOnePatient.put("animalName", animalName);
		Map<String, Object> pagingOnePatient = new HashMap<>();
		pagingOnePatient.put("seqDoctor", seqDoctor);
		pagingOnePatient.put("animalName", animalName);
		try {
			// 현재 페이지에 표시되는 내용 count
			int totalCount = doctorPageMapper.selectOneAnimalPatientInfoListVOList(countPagingOnePatient);
			log.info("selectOneAnimalPatientInfoListVO totalCount : {}", totalCount);
			pagingVO = new PagingVO<>(totalCount, currentPage, pageSize, blockSize);
			// 페이징에 필요한 데이터를 메퍼에서 받아와 계산하기
			pagingOnePatient.put("startNo", pagingVO.getStartNo());
			pagingOnePatient.put("pageSize", pagingVO.getPageSize());
			pagingVO.setList(doctorPageMapper.selectOneAnimalPatientInfoListVO(pagingOnePatient));
			log.info("selectOneAnimalPatientInfoListVO pagingVO : {}", pagingVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pagingVO;
	}
}
