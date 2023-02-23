package kr.human.anihospital.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.human.anihospital.mapper.NoticeMapper;
import kr.human.anihospital.vo.NoticeListVO;
import kr.human.anihospital.vo.NoticeOneDetailVO;
import kr.human.anihospital.vo.NoticeVO;
import lombok.extern.slf4j.Slf4j;

@Service("NoticeService")
@Slf4j
public class NoticeServiceImpl implements NoticeService {

	// selectAll 매퍼
	@Autowired
	NoticeMapper noticeMapper;
	
	//----------------------------------------------------------------------------------------------------
	// 모든 공지를 화면에 표시해줄 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public List<NoticeListVO> selectNoticeList() {
		// 모든 공지를 담을 그릇 준비하기
		List<NoticeListVO> noticeList = null;
		try {
			// 데이터를 가져올 mapper부르기
			noticeList = noticeMapper.selectNoticeList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 가져온 데이터 로그로 찍어보기
		log.info("selectNoticeList실행 mapper에서 돌아온 값(서비스) : {}", noticeList);
		return noticeList;
	}

	//----------------------------------------------------------------------------------------------------
	// 공지를 추가해줄 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public void insertNotice(NoticeVO noticeVO) {
		try {
			// insert하려는 데이터가 잘 넘어오고 있는지 로그로 확인해보기
			log.info("서비스 받은 값 : {}", noticeVO);
			// insert를 실행할 mapper부르기
			noticeMapper.insertNotice(noticeVO);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//----------------------------------------------------------------------------------------------------
	// 공지를 삭제해줄 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public void deleteNotice(int seqNotice) {
		// 공지 상세 화면 - 화면에서 삭제할 데이터의 seq값을 받아 delete처리 시작------------------------------
		try {
			// 삭제처리시 필요한 데이터 seq번호 로그로 찍어보기
			log.info("deleteNotice실행 공지삭제(서비스) : {}", seqNotice);
			// 삭제처리를 위해 mapper부르기
			noticeMapper.deleteNotice(seqNotice);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 공지 상세 화면 - 화면에서 삭제할 데이터의 seq값을 받아 delete처리 시작------------------------------
	}
	
	//----------------------------------------------------------------------------------------------------
	// 공지 상세 내용을 가져올 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public NoticeListVO selectNotice(int seqNotice) {
		// 수정한 내용을 공지상세 화면에 표시할 select 시작--------------------------------------------------
		
		// 데이터를 담을 그릇 준비하기
		NoticeListVO noticeListVO = null;
		
		// select에 필요한 seq번호 넘겨주기
		log.info("selectNotice실행 공지상세조회(서비스) : {}", seqNotice);
		try {
			// 데이터를 가져올 mapper부르기
			noticeListVO = noticeMapper.selectNotice(seqNotice);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 수정한 내용을 공지상세 화면에 표시할 select 종료--------------------------------------------------
		return noticeListVO;
	}
	
	//----------------------------------------------------------------------------------------------------
	// 공지 수정 및 수정 내용을 화면에 돌려줄 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public void updateNotice(NoticeListVO noticeListVO) {
		// 수정할 내용을 mapper에 넘겨주기
		try {
			noticeMapper.updateNotice(noticeListVO);
			// mapper가 실행되고 나서 가져온 데이터 확인하기
			log.info("수정할 공지 내용을 서비스에서 받은 값(서비스) : {}", noticeListVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//----------------------------------------------------------------------------------------------------
	// 수정화면으로 이동했을 때 수정할 내용을 화면에 표시해줄 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public List<NoticeOneDetailVO> selectOneDetailNotice(int seqNotice) {
		// mapper에서 실행한 결과값 담을 그릇 준비
		List<NoticeOneDetailVO> noticeOneDatailVO = null;
		try {
			// mapper에 SQL실행시 필요한 데이터 넘겨주기 및 실행할 메서드 부르기
			noticeOneDatailVO = noticeMapper.selectOneDetailNotice(seqNotice);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// mapper가 실행되고 나서 가져온 데이터 확인하기
		log.info("공지 상세 내용을 가지고 mapper에서 넘어온 값(서비스) : {}", noticeOneDatailVO);
		return noticeOneDatailVO;
	}
}
