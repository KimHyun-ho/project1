package kr.human.anihospital.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.human.anihospital.vo.DiagnosisAnimalVO;
import kr.human.anihospital.vo.DiagnosisDetailInMedicineVO;
import kr.human.anihospital.vo.postscriptOneDiagnosisVO;

@Mapper
public interface DiagnosisMapper {
	//----------------------------------------------------------------------------------------------------
	// 해당 진료내역의 환자 정보를 화면에 표시해줄 메서드
	//----------------------------------------------------------------------------------------------------
	DiagnosisAnimalVO selectDiagnosisAnimalInfo(int seqDignosis) throws Exception;
	//----------------------------------------------------------------------------------------------------
	// 해당 진료내역 정보를 리스트로 화면에 표시해줄 메서드
	//----------------------------------------------------------------------------------------------------
	List<DiagnosisDetailInMedicineVO> selectDiagnosisDetailInMedicine(int seqDiagnosis) throws Exception;
	//----------------------------------------------------------------------------------------------------
	// 해당 진료내역의 후기 내용을 화면에 표시해줄 메서드
	//----------------------------------------------------------------------------------------------------
	postscriptOneDiagnosisVO selectPostscriptOneDiagnosis(int seqDiagnosis) throws Exception;
	//----------------------------------------------------------------------------------------------------
	// 해당 진료내역의 후기 내용을 삭제할 메서드
	//----------------------------------------------------------------------------------------------------
	void deletePostscript(int seqPostscript) throws Exception;
}
