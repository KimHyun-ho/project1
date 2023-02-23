package kr.human.anihospital.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.human.anihospital.vo.ProPostscriptVO;

@Mapper
public interface ProPostscriptMapper {
	//----------------------------------------------------------------------------------------------------
	// 후기 추가 메서드, 
	//----------------------------------------------------------------------------------------------------
	void insertPostscript(ProPostscriptVO proPostscriptVO ) throws Exception;
	//----------------------------------------------------------------------------------------------------
	// 후기 수정 메서드
	//----------------------------------------------------------------------------------------------------
	void updatePostscript(ProPostscriptVO proPostscriptVO) throws Exception;
}