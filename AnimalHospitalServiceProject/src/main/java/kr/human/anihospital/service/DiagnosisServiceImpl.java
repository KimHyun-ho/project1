package kr.human.anihospital.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.human.anihospital.mapper.DiagnosisMapper;
import kr.human.anihospital.vo.DiagnosisAnimalVO;
import kr.human.anihospital.vo.DiagnosisDetailInMedicineVO;
import kr.human.anihospital.vo.postscriptOneDiagnosisVO;
import lombok.extern.slf4j.Slf4j;

@Service("DiagnosisService")
@Slf4j
public class DiagnosisServiceImpl implements DiagnosisService{
	
	@Autowired
	DiagnosisMapper diagnosisMapper;
	
	// ----------------------------------------------------------------------------------------------------
	// 해당 진료내역의 환자 정보를 화면에 표시해줄 메서드
	// ----------------------------------------------------------------------------------------------------
	@Override
	public DiagnosisAnimalVO selectDiagnosisAnimalInfo(int seqDiagnosis) {
		// 해당 진료내역의 환자 정보를 Mapper에 넘겨주기
		DiagnosisAnimalVO diagnosisAnimalVO = null;
		try {
			// Mapper에 SQL 실행 시 필요한 데이터 넘겨주기 및 실행할 메서드 부르기
			diagnosisAnimalVO = diagnosisMapper.selectDiagnosisAnimalInfo(seqDiagnosis);		
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Mapper가 실행되고 나서 가져온 데이터 확인하기
		log.info("selectDiagnosisAnimalInfo Mapper에서 넘어온 값(서비스) : {}", diagnosisAnimalVO);
		return diagnosisAnimalVO;
	}

	// ----------------------------------------------------------------------------------------------------
	// 해당 진료내역 정보를 리스트로 화면에 표시해줄 메서드
	// ----------------------------------------------------------------------------------------------------
	@Override
	public List<DiagnosisDetailInMedicineVO> selectDiagnosisDetailInMedicine(int seqDiagnosis) {
		// 해당 진료내역 정보를 리스트로 Mapper에 넘겨주기
		List<DiagnosisDetailInMedicineVO> diadetailImMedicineList = null;
		try {
			// Mapper에 SQL 실행 시 필요한 데이터 넘겨주기 및 실행할 메서드 부르기
			diadetailImMedicineList = diagnosisMapper.selectDiagnosisDetailInMedicine(seqDiagnosis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Mapper가 실행되고 나서 가져온 데이터 확인하기
		log.info("selectDiagnosisDetailInMedicine Mapper에서 넘어온 값(서비스) : {}", diadetailImMedicineList);
		return diadetailImMedicineList;
	}
	
	// ----------------------------------------------------------------------------------------------------
	// 해당 진료내역의 후기 내용을 화면에 표시해줄 메서드
	// ----------------------------------------------------------------------------------------------------
	@Override
	public postscriptOneDiagnosisVO selectPostscriptOneDiagnosis(int seqDiagnosis) {
		// 해당 진료내역의 후기 내용을 Mapper에 넘겨주기
		postscriptOneDiagnosisVO postOneDiagnosisVO = new postscriptOneDiagnosisVO();
		try {
			// Mapper에 SQL 실행 시 실행할 메서드 부르기
			postOneDiagnosisVO = diagnosisMapper.selectPostscriptOneDiagnosis(seqDiagnosis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Mapper가 실행되고 나서 가져온 데이터 확인하기
		log.info("selectPostscriptOneDiagnosis Mapper에서 넘어온 값(서비스) : {}", postOneDiagnosisVO);
		
		return postOneDiagnosisVO;
	}

	// ----------------------------------------------------------------------------------------------------
	// 해당 진료내역의 후기 내용을 삭제할 메서드
	// ----------------------------------------------------------------------------------------------------
	@Override
	public void deletePostscript(int seqPostscript){
		try {
			// 해당 진료내역의 후기 내용을 삭제
			diagnosisMapper.deletePostscript(seqPostscript);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Mapper가 실행되고 나서 가져온 데이터 확인하기
		log.info("deletePostscript2 서비스 : {}", diagnosisMapper);
	}
}
