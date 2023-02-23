package kr.human.anihospital.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.human.anihospital.vo.ProAllAnimalListVO;

@Mapper
public interface ProAllAnimalListMapper {
	//----------------------------------------------------------------------------------------------------
	// 한 명의 보호자에 따른 환자의 진료내역 리스트를 화면에 표시해줄 메서드
	//----------------------------------------------------------------------------------------------------
	List<ProAllAnimalListVO> selectAnimalList(int seqMember) throws Exception;
	
}
