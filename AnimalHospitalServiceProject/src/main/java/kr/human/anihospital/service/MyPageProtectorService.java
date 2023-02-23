package kr.human.anihospital.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.human.anihospital.mapper.MyPageProtectorMapper;
import kr.human.anihospital.vo.OneProtectorPatientListVO;
import kr.human.anihospital.vo.PatientDiagnosisListVO;
import kr.human.anihospital.vo.OneProtectorPostscriptListVO;
import kr.human.anihospital.vo.ProtectorVO;
import lombok.extern.slf4j.Slf4j;

@Service("MyPageProtectorService")
@Slf4j
public class MyPageProtectorService {
	
	// selectAll 매퍼
	@Autowired
	MyPageProtectorMapper myPageProtectorMapper;
	
	//----------------------------------------------------------------------------------------------------
	// 보호자 정보 조회할 메서드
	//----------------------------------------------------------------------------------------------------
	public ProtectorVO selectProtector(int seqProtector) {
		// 보호자 정보를 담을 그릇 준비
		ProtectorVO vo = new ProtectorVO();
		try {
			// 데이터를 가져올 mapper부르기
			vo = myPageProtectorMapper.selectProtector(seqProtector);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 가져온 데이터 로그로 찍어보기
		log.info("selectProtector 실행 mapper에서 돌아온 값(서비스): {}", vo);
		return vo;
	}
	
	//----------------------------------------------------------------------------------------------------
	// 보호자 정보 수정할 메서드
	//----------------------------------------------------------------------------------------------------
	public void updateProtector(Map<String, Object> map) {
		
		try {
			// 수정할 내용을 mapper에 넘겨주기
			myPageProtectorMapper.updateProtector(map);
			// mapper가 실행되고 나서 가져온 데이터 확인하기
			log.info("수정할 보호자 정보 내용을 서비스에서 받은 값(서비스): {}",map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//----------------------------------------------------------------------------------------------------
	// 보호자의 모든 환자 이전 진료 내역 조회할 메서드
	//----------------------------------------------------------------------------------------------------
	public List<PatientDiagnosisListVO> selectPatientDiagnosisList(int seqProtector) {
		List<PatientDiagnosisListVO> patientDiagnosisList = null;
		// 모든 환자의 이전 진료 내역을 담을 그릇 준비하기
		try {
			// 데이터를 가져올 mapper부르기
			patientDiagnosisList = myPageProtectorMapper.selectPatientDiagnosisList(seqProtector);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 가져온 데이터 로그로 찍어보기
		log.info("selectPatientDiagnosisList 실행 mapper에서 돌아온 값(서비스): {}", patientDiagnosisList);
		return patientDiagnosisList;
	}
	
	//----------------------------------------------------------------------------------------------------
	//보호자의 환자 리스트 조회할 메서드
	//----------------------------------------------------------------------------------------------------
	public List<OneProtectorPatientListVO> selectOneProtectorPatientList (int seqProtector) {
		// 보호자의 환자 리스트를 담을 그릇 준비하기
		List<OneProtectorPatientListVO> oneProtectorPatientListVO = null;
		try {
			// 데이터를 가져올 mapper부르기
			oneProtectorPatientListVO = myPageProtectorMapper.selectOneProtectorPatientList(seqProtector);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 가져온 데이터 로그로 찍어보기
		log.info("selectOneProtectorPatientList 실행 mapper에서 돌아온 값(서비스): {}", oneProtectorPatientListVO);
		return oneProtectorPatientListVO;
	}
	
	//----------------------------------------------------------------------------------------------------
	// 후기 리스트 조회할 메서드
	//----------------------------------------------------------------------------------------------------
	public List<OneProtectorPostscriptListVO> selectOneProtectorPostcriptList (int seqProtector){
		// 후기 리스트를 담을 그릇 준비하기
		List<OneProtectorPostscriptListVO> oneProtectorPostscriptListVO = null;
		try {
			// 데이터를 가져올 mapper부르기
			oneProtectorPostscriptListVO = myPageProtectorMapper.selectOneProtectorPostcriptList(seqProtector);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 가져온 데이터 로그로 찍어보기
		log.info("selectOneProtectorPostcriptList 실행 mapper에서 돌아온 값(서비스): {}", oneProtectorPostscriptListVO);
		return oneProtectorPostscriptListVO;
		
	}
	
}
