package kr.human.anihospital.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.human.anihospital.mapper.ProtectorPageMapper;
import kr.human.anihospital.vo.ProAnimalListVO;
import kr.human.anihospital.vo.ProDiaMedicineVO;
import kr.human.anihospital.vo.ProDiagnosisVO;
import kr.human.anihospital.vo.ProMyPageDetailVO;
import lombok.extern.slf4j.Slf4j;

@Service("ProtectorPageService")
@Slf4j
public class ProtectorPageServiceImpl implements ProtectorPageService{
	
	@Autowired
	ProtectorPageMapper protectorPageMapper;

	//----------------------------------------------------------------------------------------------------
	// 보호자 정보 페이지에서 보호자,환자 정보를 화면에 표시해줄 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public List<ProMyPageDetailVO> selectAllProMypageVOList(int seqMember) {
		// 보호자 정보 페이지의 보호자,환자 정보를 Mapper에 넘겨주기
		List<ProMyPageDetailVO> proMyPageDetailVOList = null;
		try {
			// Mapper에 SQL 실행 시 필요한 데이터 넘겨주기 및 실행할 메서드 부르기
			proMyPageDetailVOList = protectorPageMapper.selectAllProMypageVOList(seqMember);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Mapper가 실행되고 나서 가져온 데이터 확인하기
		log.info("selectAllProMypageVOList Mapper에서 넘어온 값(서비스) : {}", proMyPageDetailVOList);
		return proMyPageDetailVOList;
	}
	
	//----------------------------------------------------------------------------------------------------
	// 보호자 정보 페이지에서 환자의 진료리스트를 화면에 표시해줄 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public List<ProMyPageDetailVO> selectAllProDiagnosisList(int seqMember){
		// 보호자 정보 페이지의 환자 진료 리스트를 Mapper에 넘겨주기
		List<ProMyPageDetailVO> proMyPageDianosisList = null;
		try {
			// Mapper에 SQL 실행 시 필요한 데이터 넘겨주기 및 실행할 메서드 부르기
			proMyPageDianosisList = protectorPageMapper.selectAllProDiagnosisList(seqMember);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Mapper가 실행되고 나서 가져온 데이터 확인하기
		log.info("selectAllProDiagnosisList Mapper에서 넘어온 값(서비스) : {}", proMyPageDianosisList);
		return proMyPageDianosisList;
	}
	
	//----------------------------------------------------------------------------------------------------
	// 보호자 정보 페이지에서 후기를 화면에 표시해줄 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public List<ProDiagnosisVO> selectAllPostscript(int seqMember) {
		// 보호자 정보 페이지의 후기 리스트를 Mapper에 넘겨주기
		List<ProDiagnosisVO> postscriptList = null;
		try {
			postscriptList = protectorPageMapper.selectAllPostscript(seqMember);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Mapper가 실행되고 나서 가져온 데이터 확인하기
		log.info("selectAllPostscript Mapper에서 넘어온 값(서비스) : {}", postscriptList);
		return postscriptList;
	}

	
	
	//----------------------------------------------------------------------------------------------------
	// 보호자 정보를 수정하는 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public void updateProMypage(Map<String, Object> updateMap){
		try {
			protectorPageMapper.updateProMypage(updateMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//----------------------------------------------------------------------------------------------------
	// 보호자가 환자를 추가하는 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public void insertProPatient(Map<String, Object> insertPatientMap, MultipartFile animalPicture, MultipartFile animalVideo){
		// 저장할 경로를 지정
		  String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
	        // UUID(식별자)를 사용해 사용해 랜덤으로 이름 만들어줌
	        UUID uuid = UUID.randomUUID();

	        // 랜덤식별자_원래 파일 이름 = 저장될 파일이름 지정
	        String fileName = uuid + "_" + animalPicture.getOriginalFilename();
	        String vidfileName = uuid + "_" + animalVideo.getOriginalFilename();

	        // File이 생성되며, 이름은 "name", projectPath 라는 경로에 담긴다
	        File saveFile = new File(projectPath, fileName);
	        File savevidFile = new File(projectPath, vidfileName);
	        try {
				animalPicture.transferTo(saveFile);
				animalVideo.transferTo(savevidFile);
			} catch (IllegalStateException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	        //DB에 파일 넣기
	        insertPatientMap.put("animalPicture", fileName);
	        insertPatientMap.put("animalVideo", vidfileName);
	        //저장되는 경로 설정 
	        insertPatientMap.put("animalPicturePath", "/files/" + fileName);
	        insertPatientMap.put("animalVideoPath", "/files/" + vidfileName);
		try {
			protectorPageMapper.insertProPatient(insertPatientMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	//----------------------------------------------------------------------------------------------------
	// 보호자가 환자 정보를 수정하는 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public void updateProPatient(Map<String, Object> updatePatientMap, MultipartFile animalPicture, MultipartFile animalVideo){
		// 저장할 경로를 지정
		  String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
	        // UUID(식별자)를 사용해 사용해 랜덤으로 이름 만들어줌
	        UUID uuid = UUID.randomUUID();

	        // 랜덤식별자_원래 파일 이름 = 저장될 파일이름 지정
	        String fileName = uuid + "_" + animalPicture.getOriginalFilename();
	        String vidfileName = uuid + "_" + animalVideo.getOriginalFilename();

	        // File이 생성되며, 이름은 "name", projectPath 라는 경로에 담긴다
	        File saveFile = new File(projectPath, fileName);
	        File savevidFile = new File(projectPath, vidfileName);
	        try {
	        	animalPicture.transferTo(saveFile);
	        	animalVideo.transferTo(savevidFile);
			} catch (IllegalStateException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	        //DB에 파일 넣기
	        updatePatientMap.put("animalPicture", fileName);
	        updatePatientMap.put("animalVideo", vidfileName);
	        //저장되는 경로 설정 
	        updatePatientMap.put("animalPicturePath", "/files/" + fileName);
	        updatePatientMap.put("animalVideoPath", "/files/" + vidfileName);
		try {
			protectorPageMapper.updateProPatient(updatePatientMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//----------------------------------------------------------------------------------------------------
	// 한 명의 보호자에 따른 환자의 진료내역 리스트를 화면에 표시해줄 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public List<ProAnimalListVO> selectAllProAnimalListVO(int seqMember) {
		// 환자 진료내역 리스트를 Mapper에 넘겨주기
		List<ProAnimalListVO> proAnimalList = null;
		try {
			// Mapper에 SQL 실행 시 필요한 데이터 넘겨주기 및 실행할 메서드 부르기
			proAnimalList = protectorPageMapper.selectAllProAnimalListVO(seqMember);
		}catch (Exception e) {
			e.printStackTrace();
		}
		// Mapper가 실행되고 나서 가져온 데이터 확인하기
		log.info("selectAllProAnimalListVO Mapper에서 넘어온 값(서비스) : {}", proAnimalList);
		return proAnimalList;
	}
	
	//----------------------------------------------------------------------------------------------------
	// 한 명의 보호자에 따른 환자의 진료내역 리스트를 화면에 표시해줄 메서드
	//----------------------------------------------------------------------------------------------------	
	@Override
	public ProDiagnosisVO selectOneProDiagnosisVO(Map<String, Object> diagnosisMap) {
		// 환자의 상세 진료내역 리스트를 Mapper에 넘겨주기
		ProDiagnosisVO proDiagnosisVO = null;
		try {
			// Mapper에 SQL 실행 시 필요한 데이터 넘겨주기 및 실행할 메서드 부르기
			proDiagnosisVO = protectorPageMapper.selectOneProDiagnosisVO(diagnosisMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Mapper가 실행되고 나서 가져온 데이터 확인하기
		log.info("selectOneProDiagnosisVO Mapper에서 넘어온 값(서비스) : {}", proDiagnosisVO);
		return proDiagnosisVO;
	}
	
	//----------------------------------------------------------------------------------------------------
	// 한 명의 보호자에 따른 환자의 1개의 상세진료 내역에서 후기를 화면에 표시해줄 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public ProDiagnosisVO selectOnePostScript(Map<String, Object> diagnosisMap) {
		ProDiagnosisVO proDiagnosisVOPostscript = new ProDiagnosisVO();
		try {
			proDiagnosisVOPostscript = protectorPageMapper.selectOnePostScript(diagnosisMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return proDiagnosisVOPostscript;
	}
	
	
	//----------------------------------------------------------------------------------------------------
	// 한 명의 보호자에 따른 환자의 상세진료 내역의 처방목록을 화면에 표시해줄 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public List<ProDiaMedicineVO> selectListProDiaMedicineVO(Map<String, Object> diagnosisMap) {
		// 환자의 상세 진료내역 리스트를 Mapper에 넘겨주기
		List<ProDiaMedicineVO> proDiaMedicineVOList = null;
		try {
			// Mapper에 SQL 실행 시 필요한 데이터 넘겨주기 및 실행할 메서드 부르기
			proDiaMedicineVOList = protectorPageMapper.selectListProDiaMedicineVO(diagnosisMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Mapper가 실행되고 나서 가져온 데이터 확인하기
		log.info("selectListProDiaMedicineVO Mapper에서 넘어온 값(서비스) : {}", proDiaMedicineVOList);
		return proDiaMedicineVOList;
	}

	//----------------------------------------------------------------------------------------------------
	// 진료내역페이지에서 후기작성하는 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public void insertProPostScript(Map<String, Object> postScriptMap) {
		try {
			protectorPageMapper.insertProPostScript(postScriptMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Mapper가 실행되고 나서 가져온 데이터 확인하기
		log.info("insertProPostScript에 넘어온 값(서비스) : {}", postScriptMap);
	}

	//----------------------------------------------------------------------------------------------------
	// 진료내역페이지에서 후기 수정하는 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public void updatePostscript(Map<String, Object> updatePostScriptMap) {
		try {
			protectorPageMapper.updatePostscript(updatePostScriptMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Mapper가 실행되고 나서 가져온 데이터 확인하기
		log.info("updatePostscript에 넘어온 값(서비스) : {}", updatePostScriptMap);
	}

	//----------------------------------------------------------------------------------------------------
	// 진료내역페이지에서 후기 삭제하는 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public void deletePostscript(int seqPostscript) {
		log.info("deletePostscript에 넘어온 seqPostscript : {}",seqPostscript);
		try {
			protectorPageMapper.deletePostscript(seqPostscript);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

