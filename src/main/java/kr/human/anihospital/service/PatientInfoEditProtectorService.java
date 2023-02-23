package kr.human.anihospital.service;

import java.io.File;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.human.anihospital.mapper.PatientInfoEditProtectorMapper;
import kr.human.anihospital.vo.AnimalVO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PatientInfoEditProtectorService {

	@Autowired
	PatientInfoEditProtectorMapper patientInfoEditProtectorMapper;
	//----------------------------------------------------------------------------------------------------
	// 환자 정보 수정 페이지에서 사용할 환자 정보
	//----------------------------------------------------------------------------------------------------	
	public AnimalVO selectPatient() {
		AnimalVO animalVO = new AnimalVO();
		try {
			animalVO = patientInfoEditProtectorMapper.selectPatient();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return animalVO;
	}
	
	//----------------------------------------------------------------------------------------------------
	// 보호자의 환자 정보 수정
	//----------------------------------------------------------------------------------------------------
	public void updatePatient(Map<String, Object> map) {
		try {
			patientInfoEditProtectorMapper.updatePatient(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//----------------------------------------------------------------------------------------------------
	// 환자 추가 
	//----------------------------------------------------------------------------------------------------
	public void insertPatient(AnimalVO animalVO, MultipartFile file, MultipartFile vidfile) throws Exception{
		  // 저장할 경로를 지정
		  String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
	        // UUID(식별자)를 사용해 사용해 랜덤으로 이름 만들어줌
	        UUID uuid = UUID.randomUUID();

	        // 랜덤식별자_원래 파일 이름 = 저장될 파일이름 지정
	        String fileName = uuid + "_" + file.getOriginalFilename();
	        String vidfileName = uuid + "_" + vidfile.getOriginalFilename();

	        // File이 생성되며, 이름은 "name", projectPath 라는 경로에 담긴다
	        File saveFile = new File(projectPath, fileName);
	        File savevidFile = new File(projectPath, vidfileName);
	        file.transferTo(saveFile);
	        vidfile.transferTo(savevidFile);
	        //DB에 파일 넣기
	        animalVO.setAnimalPicture(fileName);
	        animalVO.setAnimalVideo(vidfileName);
	        //저장되는 경로 설정 
	        animalVO.setFilePath("/files/" + fileName);
	        animalVO.setVidfilePath("/files/" + vidfileName);
		try {
			log.info("서비스 받은 값 : {}", animalVO);
			patientInfoEditProtectorMapper.insertPatient(animalVO);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}