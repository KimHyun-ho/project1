package kr.human.anihospital.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import kr.human.anihospital.mapper.ProtectorPageMapper;
import kr.human.anihospital.vo.AnimalHospitalVO;
import kr.human.anihospital.vo.DoctorInfoVO;
import kr.human.anihospital.vo.PagingVO;
import kr.human.anihospital.vo.ProAnimalListVO;
import kr.human.anihospital.vo.ProDiaMedicineVO;
import kr.human.anihospital.vo.ProDiagnosisVO;
import kr.human.anihospital.vo.ProMyPageDetailVO;
import lombok.extern.slf4j.Slf4j;

@Service("ProtectorPageService")
@Slf4j
public class ProtectorPageServiceImpl implements ProtectorPageService {

	@Autowired
	ProtectorPageMapper protectorPageMapper;

	// ----------------------------------------------------------------------------------------------------
	// 보호자 정보 페이지에서 보호자,환자 정보를 화면에 표시해줄 메서드
	// ----------------------------------------------------------------------------------------------------
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

	// ----------------------------------------------------------------------------------------------------
	// 보호자 정보 페이지에서 환자의 진료리스트를 화면에 표시해줄 메서드
	// ----------------------------------------------------------------------------------------------------
	@Override
	public List<ProMyPageDetailVO> selectAllProDiagnosisList(int seqMember) {
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

	// ----------------------------------------------------------------------------------------------------
	// 보호자 정보 페이지에서 후기를 화면에 표시해줄 메서드
	// ----------------------------------------------------------------------------------------------------
	@Override
	public PagingVO<ProDiagnosisVO> selectAllPostscript(int seqMember, int currentPage, int pageSize, int blockSize) {
		// 보호자 정보 페이지의 후기 리스트를 Mapper에 넘겨주기
		PagingVO<ProDiagnosisVO> pagingVO = null;
		try {
			int totalCount = protectorPageMapper.selectCountPostscript(seqMember);
			pagingVO = new PagingVO<>(totalCount, currentPage, pageSize, blockSize);
			pagingVO.setList(protectorPageMapper.selectAllPostscript(seqMember, pagingVO.getStartNo(), pagingVO.getPageSize()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Mapper가 실행되고 나서 가져온 데이터 확인하기
		log.info("selectAllPostscript Mapper에서 넘어온 값(서비스) : {}", pagingVO.getList());
		return pagingVO;
	}

	// ----------------------------------------------------------------------------------------------------
	// 보호자 정보를 수정하는 메서드
	// ----------------------------------------------------------------------------------------------------
	@Override
	public void updateProMypage(Map<String, Object> updateMap) {
		try {
			protectorPageMapper.updateProMypage(updateMap);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// ----------------------------------------------------------------------------------------------------
	// 보호자가 환자를 추가하는 메서드
	// ----------------------------------------------------------------------------------------------------
	@Override
	public void insertProPatient(Map<String, Object> insertPatientMap, MultipartFile animalPicture,
			MultipartFile animalVideo) {
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
		// DB에 파일 넣기
		insertPatientMap.put("animalPicture", fileName);
		insertPatientMap.put("animalVideo", vidfileName);
		// 저장되는 경로 설정
		insertPatientMap.put("animalPicturePath", "/files/" + fileName);
		insertPatientMap.put("animalVideoPath", "/files/" + vidfileName);
		try {
			protectorPageMapper.insertProPatient(insertPatientMap);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// ----------------------------------------------------------------------------------------------------
	// 보호자가 환자 정보를 수정하는 메서드
	// ----------------------------------------------------------------------------------------------------
	@Override
	public void updateProPatient(Map<String, Object> updatePatientMap, MultipartFile animalPicture,
			MultipartFile animalVideo) {
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
		// DB에 파일 넣기
		updatePatientMap.put("animalPicture", fileName);
		updatePatientMap.put("animalVideo", vidfileName);
		// 저장되는 경로 설정
		updatePatientMap.put("animalPicturePath", "/files/" + fileName);
		updatePatientMap.put("animalVideoPath", "/files/" + vidfileName);
		try {
			protectorPageMapper.updateProPatient(updatePatientMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ----------------------------------------------------------------------------------------------------
	// 한 명의 보호자에 따른 환자의 진료내역 리스트를 화면에 표시해줄 메서드
	// ----------------------------------------------------------------------------------------------------
	@Override
	public PagingVO<ProAnimalListVO> selectAllProAnimalListVO(int seqMember, int currentPage, int pageSize, int blockSize) {
		// 환자 진료내역 리스트를 Mapper에 넘겨주기
		PagingVO<ProAnimalListVO> pagingVO = null;
		try {
			int totalCount = protectorPageMapper.selectCountProAnimalListVO(seqMember);
			pagingVO = new PagingVO<>(totalCount, currentPage, pageSize, blockSize);
			// Mapper에 SQL 실행 시 필요한 데이터 넘겨주기 및 실행할 메서드 부르기
			pagingVO.setList(protectorPageMapper.selectAllProAnimalListVO(seqMember, pagingVO.getStartNo(), pagingVO.getPageSize()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Mapper가 실행되고 나서 가져온 데이터 확인하기
		log.info("selectAllProAnimalListVO Mapper에서 넘어온 값(서비스) : {}", pagingVO.getList());
		return pagingVO;
	}

	// ----------------------------------------------------------------------------------------------------
	// 한 명의 보호자에 따른 환자의 진료내역 리스트를 화면에 표시해줄 메서드
	// ----------------------------------------------------------------------------------------------------
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

	// ----------------------------------------------------------------------------------------------------
	// 한 명의 보호자에 따른 환자의 1개의 상세진료 내역에서 후기를 화면에 표시해줄 메서드
	// ----------------------------------------------------------------------------------------------------
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

	// ----------------------------------------------------------------------------------------------------
	// 한 명의 보호자에 따른 환자의 상세진료 내역의 처방목록을 화면에 표시해줄 메서드
	// ----------------------------------------------------------------------------------------------------
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

	// ----------------------------------------------------------------------------------------------------
	// 진료내역페이지에서 후기작성하는 메서드
	// ----------------------------------------------------------------------------------------------------
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

	// ----------------------------------------------------------------------------------------------------
	// 진료내역페이지에서 후기 수정하는 메서드
	// ----------------------------------------------------------------------------------------------------
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

	// ----------------------------------------------------------------------------------------------------
	// 진료내역페이지에서 후기 삭제하는 메서드
	// ----------------------------------------------------------------------------------------------------
	@Override
	public void deletePostscript(int seqPostscript) {
		log.info("deletePostscript에 넘어온 seqPostscript : {}", seqPostscript);
		try {
			protectorPageMapper.deletePostscript(seqPostscript);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	//----------------------------------------------------------------------------------------------------
	// 보호자 스케줄 페이지에서 보여줄 병원 정보 표시 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public List<AnimalHospitalVO> selectAllAnimalHospitalVO()  {
		List<AnimalHospitalVO> animalHospitalList = null;
		try {
			animalHospitalList = protectorPageMapper.selectAllAnimalHospitalVO();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return animalHospitalList;
	}


	//----------------------------------------------------------------------------------------------------
	// 보호자 스케줄 페이지에서 보여줄 의사 정보 표시 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public List<DoctorInfoVO> selectAllDoctorName(String animalHospitalName) {
		List<DoctorInfoVO> doctorList = null;
		try {
			doctorList = protectorPageMapper.selectAllDoctorName(animalHospitalName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doctorList;
	}

	// ----------------------------------------------------------------------------------------------------
	// PDF파일을 생성하는 메소드
	// ----------------------------------------------------------------------------------------------------
	public String createPdf(List<String> medicineName, List<String> medicationGuide, List<String> sideEffect,
			List<String> diagnosisInfo) {

		String fileURL = "src/main/resources/static/pdf/";
		String fileName = diagnosisInfo.get(1).replace("진료일 : ", "").replaceAll("-", "")
				+ diagnosisInfo.get(3).replace("환자명 : ", "") + "_처방전.pdf";
		try {
			Document document = new Document();
			PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(fileURL + fileName));
			document.open();
			BaseFont titleBaseFont = BaseFont.createFont("c:/windows/fonts/H2HDRM.TTF", BaseFont.IDENTITY_H,
					BaseFont.EMBEDDED);
			Font titleFont = new Font(titleBaseFont, 18, 1);
			Font miniTitleFont = new Font(titleBaseFont, 13, 0);
			Font contentFont = new Font(titleBaseFont, 9, 0);
			PdfPTable table = new PdfPTable(3);
			Chunk chunkTitle = new Chunk("처방전", titleFont);
			Chunk chunkSeqDiagnosis = new Chunk(diagnosisInfo.get(0), miniTitleFont);
			Chunk chunkDiagnosisDate = new Chunk(diagnosisInfo.get(1), miniTitleFont);
			Chunk chunkDoctorName = new Chunk(diagnosisInfo.get(2), miniTitleFont);
			Chunk chunkAnimalHospitalName = new Chunk(diagnosisInfo.get(4), miniTitleFont);
			Chunk chunkAnimalName = new Chunk(diagnosisInfo.get(3), miniTitleFont);
			Paragraph paragraphTitle = new Paragraph(chunkTitle);
			Paragraph paragraphSeqDiagnosis = new Paragraph(chunkSeqDiagnosis);
			Paragraph paragraphDiagnosisDate = new Paragraph(chunkDiagnosisDate);
			Paragraph paragraphDoctorName = new Paragraph(chunkDoctorName);
			Paragraph paragraphAnimalHospitalName = new Paragraph(chunkAnimalHospitalName);
			Paragraph paragraphAnimalName = new Paragraph(chunkAnimalName);
			paragraphTitle.setAlignment(Element.ALIGN_CENTER);
			paragraphSeqDiagnosis.setAlignment(Element.ALIGN_LEFT);
			paragraphDiagnosisDate.setAlignment(Element.ALIGN_LEFT);
			paragraphDoctorName.setAlignment(Element.ALIGN_LEFT);
			paragraphAnimalHospitalName.setAlignment(Element.ALIGN_LEFT);
			paragraphAnimalName.setAlignment(Element.ALIGN_RIGHT);
			document.add(paragraphTitle);
			document.add(Chunk.NEWLINE);
			document.add(paragraphSeqDiagnosis);
			document.add(paragraphDiagnosisDate);
			document.add(paragraphDoctorName);
			document.add(paragraphAnimalHospitalName);
			document.add(paragraphAnimalName);
			document.add(Chunk.NEWLINE);
			PdfPCell cell1 = new PdfPCell(new Phrase("약 이름", miniTitleFont));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setVerticalAlignment(Element.ALIGN_CENTER);
			PdfPCell cell2 = new PdfPCell(new Phrase("복약안내", miniTitleFont));
			cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell cell3 = new PdfPCell(new Phrase("부작용", miniTitleFont));
			cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell1);
			table.addCell(cell2);
			table.addCell(cell3);
			for (int i = 0; i < medicineName.size(); i++) {
				PdfPCell cellMedicineName = new PdfPCell(new Phrase(medicineName.get(i), contentFont));
				PdfPCell cellMedicationGuide = new PdfPCell(new Phrase(medicationGuide.get(i), contentFont));
				PdfPCell cellSideEffect = new PdfPCell(new Phrase(sideEffect.get(i), contentFont));
				table.addCell(cellMedicineName);
				table.addCell(cellMedicationGuide);
				table.addCell(cellSideEffect);
			}
			document.add(table);
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return fileName;
	}
  
	// ----------------------------------------------------------------------------------------------------
	// PDF파일을 삭제하는 메소드
	// ----------------------------------------------------------------------------------------------------
	public String deletePDF(String pdfName) {
		String fileURL = "src/main/resources/static/pdf/";
		File deletePDF = new File(fileURL + pdfName);
		if (deletePDF.exists()) {
			deletePDF.delete();
			return "삭제 성공";
		} else {
			return "파일이 존재 하지 않습니다.";
		}
	}

}
