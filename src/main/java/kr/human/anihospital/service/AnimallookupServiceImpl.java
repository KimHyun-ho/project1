package kr.human.anihospital.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.human.anihospital.mapper.AnimallookupMapper;
import kr.human.anihospital.vo.AnimallookupVO;
import lombok.extern.slf4j.Slf4j;

@Service("AnimallookupService")
@Slf4j
public class AnimallookupServiceImpl implements AnimallookupService{
	
	@Autowired
	AnimallookupMapper animallookupMapper;

	@Override
	//----------------------------------------------------------------------------------------------------
	// 한 명의 보호자에 따른 환자의 진료내역 리스트를 화면에 표시해줄 메서드
	//----------------------------------------------------------------------------------------------------
	public List<AnimallookupVO> selectAnimallookup(int seqProtector) {
		// 환자 진료내역 리스트를 Mapper에 넘겨주기
		List<AnimallookupVO> animallookup = null;
		try {
			// Mapper에 SQL 실행 시 필요한 데이터 넘겨주기 및 실행할 메서드 부르기
			animallookup = animallookupMapper.selectAnimallookup(seqProtector);
		}catch (Exception e) {
			e.printStackTrace();
		}
		// Mapper가 실행되고 나서 가져온 데이터 확인하기
		log.info("animallookupMapper에서 넘어온 값(서비스) : {}", animallookup);
		return animallookup;
	}
}
