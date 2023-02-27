package kr.human.anihospital.service;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public void updateOneDoctorInfoVO(DoctorInfoVO doctorInfoVO) {
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
	public List<MedicineInfoVO> selectAllMedicineInfo(){
		List<MedicineInfoVO> medicineInfoVOs = new ArrayList<>();
		try {
			medicineInfoVOs = doctorPageMapper.selectAllMedicineInfo();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return medicineInfoVOs;
	}
}
