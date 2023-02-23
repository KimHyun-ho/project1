package kr.human.anihospital.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.human.anihospital.mapper.DoctorInfoMapper;
import kr.human.anihospital.vo.DoctorVO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DoctorInfoService {

	@Autowired
	DoctorInfoMapper doctorInfoMapper;

	//----------------------------------------------------------------------------------------------------
	// 의사 정보를 화면에 표시해줄 메서드
	//----------------------------------------------------------------------------------------------------
	public Map<String, Object> viewMyInfoDoctor(int seq) {
		Map<String, Object> viewMyInfoDoctorMap = new HashMap<>();
		// 의사 정보를 Mapper에 넘겨주기
		DoctorVO doctorVO = new DoctorVO();
			try {
				// Mapper에 SQL 실행 시 필요한 데이터 넘겨주기 및 실행할 메서드 부르기   
				doctorVO = doctorInfoMapper.viewMyInfoDoctor(seq);
				
				viewMyInfoDoctorMap.put("seqDoctor", doctorVO.getSeqDoctor());
				viewMyInfoDoctorMap.put("doctorId", doctorVO.getDoctorId());
				viewMyInfoDoctorMap.put("doctorGender", doctorVO.isDoctorGender());
				viewMyInfoDoctorMap.put("doctorName", doctorVO.getDoctorName());
				viewMyInfoDoctorMap.put("doctorAge", doctorVO.getDoctorAge());
				viewMyInfoDoctorMap.put("doctorWorkYear", doctorVO.getDoctorWorkYear());
				viewMyInfoDoctorMap.put("doctorRegNo", doctorVO.getDoctorRegistrationNumber());
				viewMyInfoDoctorMap.put("doctorPhoneNo", doctorVO.getDoctorPhoneNo());
				viewMyInfoDoctorMap.put("doctorAddress", doctorVO.getDoctorAddress());
				viewMyInfoDoctorMap.put("doctorEducation", doctorVO.getDoctorEducation());
				viewMyInfoDoctorMap.put("doctorWorkSpace", doctorVO.getDoctorWorkSpace());
				viewMyInfoDoctorMap.put("doctorSay", doctorVO.getDoctorSay());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// Mapper가 실행되고 나서 가져온 데이터 확인하기
			log.info("viewMyInfoDoctor Mapper에서 넘어온 값(서비스) : {}", doctorVO);
			return viewMyInfoDoctorMap;
	}

	//----------------------------------------------------------------------------------------------------
	// 수정한 의사 정보를 화면에 표시해줄 메서드
	//----------------------------------------------------------------------------------------------------
	public void editMyInfoDoctor(Map<String, Object> map) {
		
		// 수정할 값 받기  
		map.put("doctorAddress", (String.valueOf(map.get("inputKakao"))+ " " + String.valueOf(map.get("address"))));
		// 가져온 데이터 확인하기
		log.info("Edit Map : {}", map);
		
		try {
			// Mapper에 SQL 실행 시 필요한 데이터 넘겨주기 및 실행할 메서드 부르기
			doctorInfoMapper.editMyInfoDoctor(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("정보 수정 성공");
	}
}
