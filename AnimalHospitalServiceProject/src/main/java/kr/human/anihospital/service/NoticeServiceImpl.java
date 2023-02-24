package kr.human.anihospital.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.human.anihospital.mapper.NoticeMapper;
import kr.human.anihospital.vo.AllNoticeListVO;
import kr.human.anihospital.vo.InsertWriterInfoVO;
import lombok.extern.slf4j.Slf4j;

@Service("NoticeService")
@Slf4j
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	NoticeMapper noticeMapper;
	
	//----------------------------------------------------------------------------------------------------
	// 모든 공지를 화면에 표시해줄 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public List<AllNoticeListVO> selectAllNoticeListVO() {
		// 모든 공지 리스트를 담아줄 그릇 준비
		List<AllNoticeListVO> allNoticeListVO = null;
		try {
			// 공지 리스트를 받아오기
			allNoticeListVO = noticeMapper.selectAllNoticeListVO();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 공지 리스트가 잘 넘어오고 있는지 찍어보기
		log.info("selectAllNoticeList 실행 한 값 : {}", allNoticeListVO);
		// controller에 담아온 리스트 돌려주기
		return allNoticeListVO;
	}

	//----------------------------------------------------------------------------------------------------
	// 수정화면으로 이동했을 때 수정할 내용을 화면에 표시해줄 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public AllNoticeListVO selectOneDetailNoticeVO(int seqNotice){
		log.info("selectOneDetailNotice 화면에서 넘어온 값(서비스) : {}", seqNotice);
		// 하나의 공지 데이터를 담을 그릇 준비
		AllNoticeListVO allNoticeListVO = null;
		try {
			// 하나의 공지 데이터를 담아오기
			allNoticeListVO = noticeMapper.selectOneDetailNoticeVO(seqNotice);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 공지 리스트가 잘 넘어오고 있는지 찍어보기
		log.info("selectOneDetailNotice 실행 한 값 : {}", allNoticeListVO);
		// controller에 담아온 데이터 돌려주기
		return allNoticeListVO;
	}
	
	//----------------------------------------------------------------------------------------------------
	// insert화면에 로그인 한 의사 정보 뿌려줄 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public Map<String, Object> selectInsertNoticeWriterInfo(int seqDoctor, int seqAnimalHospital) throws Exception {
		// 화면에서 넘어온 값 찍어보기
		log.info("selectInsertNoticeWriterInfo 화면에서 넘어온 값 : {} {}", seqDoctor, seqAnimalHospital);
		// 데이터 담을 그릇 준비하기
		Map<String, Object> insertWriterInfoMap = new HashMap<>();
		// 그릇에 데이터 담기
		InsertWriterInfoVO insertWriterInfoVO = noticeMapper.selectInsertNoticeWriterInfo(seqDoctor, seqAnimalHospital);
		insertWriterInfoMap.put("seqDoctor", String.valueOf(seqDoctor));
		insertWriterInfoMap.put("seqAnimalHospital", String.valueOf(seqAnimalHospital));
		insertWriterInfoMap.put("doctorName", insertWriterInfoVO.getDoctorName());
		insertWriterInfoMap.put("animalHospitalName", insertWriterInfoVO.getAnimalHospitalName());
		insertWriterInfoMap.put("today", insertWriterInfoVO.getToday());
		// 담은 데이터 돌려주기
		return insertWriterInfoMap;
	}

	//----------------------------------------------------------------------------------------------------
	// 공지를 추가해줄 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public void insertOneNotice(Map<String, String> insertNoticeMap) {
		try {
			// insert하려는 데이터가 잘 넘어오고 있는지 로그로 확인해보기
			log.info("insertOneNotice 화면에서 넘어온 값(서비스) : {}", insertNoticeMap);
			// insert를 실행할 mapper부르기
			noticeMapper.insertOneNotice(insertNoticeMap);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	//----------------------------------------------------------------------------------------------------
	// 공지 수정 및 수정 내용을 화면에 돌려줄 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public void updateOneNotice(Map<String, String> updateNoticeMap) {
		// update하려는 seq가 잘 넘어오고 있는지 찍어보기
		log.info("updateOneNotice 화면에서 넘어온 값(서비스) : {}", updateNoticeMap);
		try {
			// update를 실행할 mapper부르기
			noticeMapper.updateOneNotice(updateNoticeMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteOneNotice(int seqNotice) {
		// delete하려는 seq가 잘 넘어오고 있는지 찍어보기
		log.info("insertOneNotice 화면에서 넘어온 값(서비스) : {}", seqNotice);
		try {
			// delete를 실행할 mapper부르기
			noticeMapper.deleteOneNotice(seqNotice);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
