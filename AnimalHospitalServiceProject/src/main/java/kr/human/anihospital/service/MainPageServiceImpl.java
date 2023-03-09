package kr.human.anihospital.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.human.anihospital.mapper.MainpageMapper;
import kr.human.anihospital.vo.DoctorInfoVO;

@Service("MainPageService")
public class MainPageServiceImpl implements MainPageService{

	@Autowired
	MainpageMapper mainpageMapper;

	//----------------------------------------------------------------------------------------------------
	// 의사 인원을 조회하는 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public int selectCountDoctorCount(){
		int doctorCount = 0;
		try {
			doctorCount = mainpageMapper.selectCountDoctorCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doctorCount;
	}
	
	//----------------------------------------------------------------------------------------------------
	// 보호자 인원을 조회하는 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public int selectCountProCount() {
		int ProtectorCount = 0;
		try {
			ProtectorCount = mainpageMapper.selectCountProCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ProtectorCount;
	}

	//----------------------------------------------------------------------------------------------------
	// 병원 수를 조회하는 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public int selectCountHospitalCount() {
		int hospitalCount = 0;
		try {
			hospitalCount = mainpageMapper.selectCountHospitalCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hospitalCount;
	}

	//----------------------------------------------------------------------------------------------------
	// 약국 수를 조회하는 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public int selectCountPharmacyCount() {
		int pharmacyCount = 0;
		try {
			pharmacyCount = mainpageMapper.selectCountPharmacyCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pharmacyCount;
	}

	//----------------------------------------------------------------------------------------------------
	// 환자 수가 많은 상위 4명의 의사를 조회하는 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public List<DoctorInfoVO> selectBestDoctor(){
		List<DoctorInfoVO> bestDoctorList = null;
		try {
			bestDoctorList = mainpageMapper.selectBestDoctor();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bestDoctorList;
	}

	//----------------------------------------------------------------------------------------------------
	// 최근 후기 2개를 조회하는 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public List<Map<String, Object>> selectRecentPostscript() throws Exception {
		List<Map<String, Object>> recentPostscriptList = null;
		try {
			recentPostscriptList = mainpageMapper.selectRecentPostscript();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return recentPostscriptList;
	}
	
	//----------------------------------------------------------------------------------------------------
	// 추천 사료를 랜덤으로 가져오는 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public Map<String, Object> selectOneFeed() throws Exception {
		Map<String, Object> selectFeedMap = null;
		try {
			selectFeedMap = mainpageMapper.selectOneFeed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return selectFeedMap;
	}

	//----------------------------------------------------------------------------------------------------
	// 추천 영양제를 랜덤으로 가져오는 메서드
	//----------------------------------------------------------------------------------------------------
	@Override
	public Map<String, Object> selectOneSupplements() throws Exception {
		Map<String, Object> selectSupplementsMap = null;
		try {
			selectSupplementsMap = mainpageMapper.selectOneSupplements();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return selectSupplementsMap;
	}

}
