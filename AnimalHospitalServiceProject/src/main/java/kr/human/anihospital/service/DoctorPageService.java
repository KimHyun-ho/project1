package kr.human.anihospital.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.human.anihospital.mapper.DoctorPageMapper;
import kr.human.anihospital.vo.DoctorInfoVO;
import kr.human.anihospital.vo.PatientInfoVO;
import kr.human.anihospital.vo.PatientInfoVO.PatientDiaRecord;
import lombok.extern.slf4j.Slf4j;

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
	public void updateOneDoctorInfoVO(DoctorInfoVO doctorInfoVO) {
		try {
			doctorPageMapper.updateOneDoctorInfoVO(doctorInfoVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<PatientInfoVO> selectAllPatientInfoVO(int seqDoctor) {
		List<PatientInfoVO> patientInfoVOs = new ArrayList<>();
		try {
			patientInfoVOs = doctorPageMapper.selectAllPatientInfoVO(seqDoctor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return patientInfoVOs;
	}

	public PatientInfoVO selectOnePatientInfoVO(int seqAnimal) {
		PatientInfoVO patientInfoVO = new PatientInfoVO();
		try {
			patientInfoVO = doctorPageMapper.selectOnePatientInfoVO(seqAnimal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return patientInfoVO;
	}

	public List<PatientDiaRecord> selectAllPatientDiaRecord(int seqAnimal) {
		List<PatientDiaRecord> patientDiaRecords = new ArrayList<>();
		try {
			patientDiaRecords = doctorPageMapper.selectAllPatientDiaRecord(seqAnimal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return patientDiaRecords;

	}
}
