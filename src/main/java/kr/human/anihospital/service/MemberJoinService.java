package kr.human.anihospital.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.human.anihospital.mapper.MemberJoinMapper;
import kr.human.anihospital.vo.DoctorVO;
import kr.human.anihospital.vo.ProtectorVO;

@Service
public class MemberJoinService {

	@Autowired
	MemberJoinMapper memberJoinMapper;

	public void protectorMemberJoin(Map<String, Object> map) {
		ProtectorVO protectorVO = new ProtectorVO();
		try {
			protectorVO.setProtectorId(String.valueOf(map.get("email")));
			protectorVO.setProtectorPassword(String.valueOf(map.get("password")));
			protectorVO.setProtectorName(String.valueOf(map.get("protector_name")));
			protectorVO.setProtectorGender(
					Integer.parseInt(String.valueOf(map.get("protector_gender"))) == 1 ? true : false);
			String dateString = map.get("protector_year") + "-" + map.get("protector_month") + "-"
					+ map.get("protector_date");
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = simpleDateFormat.parse(dateString);
			protectorVO.setProtectorAge(date);
			protectorVO.setProtectorAddress(String.valueOf(map.get("protector_address")));
			protectorVO.setProtectorPhoneNo(String.valueOf(map.get("protector_phoneNumber")));
			memberJoinMapper.protectorMemberJoin(protectorVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doctorMemberJoin(Map<String, Object> map, MultipartFile file) {
		DoctorVO doctorVO = new DoctorVO();
		try {
			doctorVO.setDoctorId(String.valueOf(map.get("email")));
			doctorVO.setDoctorPassword(String.valueOf(map.get("password")));
			doctorVO.setDoctorName(String.valueOf(map.get("doctor_id")));
			doctorVO.setDoctorGender(Integer.parseInt(String.valueOf(map.get("doctor_gender"))) == 1 ? true : false);
			String dateString = map.get("doctor_year") + "-" + map.get("doctor_month") + "-" + map.get("doctor_date");
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date birthDate = simpleDateFormat.parse(dateString);
			doctorVO.setDoctorAge(birthDate);
			String licenseString = map.get("doctor_license_year") + "-" + map.get("doctor_license_month") + "-"
					+ map.get("doctor_license_date");
			Date licenseDate = simpleDateFormat.parse(licenseString);
			doctorVO.setDoctorWorkYear(licenseDate);
			doctorVO.setDoctorRegistrationNumber(Integer.parseInt(String.valueOf(map.get("doctor_license_seq"))));
			doctorVO.setDoctorAddress(map.get("doctor_address") + " " + map.get("address2"));
			doctorVO.setDoctorPhoneNo(String.valueOf(map.get("doctor_phoneNumber")));
			doctorVO.setDoctorSay(String.valueOf(map.get("greetingMessage")));
			doctorVO.setDoctorWorkSpace(String.valueOf(map.get("careerMessage")));
			doctorVO.setDoctorEducation(String.valueOf(map.get("educationMessage")));
			
			// 저장할 경로를 지정
			  String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
		        // UUID(식별자)를 사용해 사용해 랜덤으로 이름 만들어줌
		        UUID uuid = UUID.randomUUID();

		        // 랜덤식별자_원래 파일 이름 = 저장될 파일이름 지정
		        String fileName = uuid + "_" + file.getOriginalFilename();

		        // File이 생성되며, 이름은 "name", projectPath 라는 경로에 담긴다
		        File saveFile = new File(projectPath, fileName);
		        file.transferTo(saveFile);
		        //DB에 파일 넣기
		        doctorVO.setDoctorPicture(fileName);
		        //저장되는 경로 설정 
		        doctorVO.setFilePath("/files/" + fileName);

			memberJoinMapper.doctorMemberJoin(doctorVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}