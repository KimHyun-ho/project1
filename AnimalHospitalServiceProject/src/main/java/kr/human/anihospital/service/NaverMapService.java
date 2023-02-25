package kr.human.anihospital.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.human.anihospital.mapper.NaverMapMapper;
import kr.human.anihospital.vo.AnimalHospitalVO;
import kr.human.anihospital.vo.PharmacyLocationVO;

@Service
public class NaverMapService {

	@Autowired
	NaverMapMapper naverMapMapper;

	public List<AnimalHospitalVO> selectHospitalMap() {
		List<AnimalHospitalVO> list = null;
		try {
			list = naverMapMapper.selectHospitalMap();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<PharmacyLocationVO> selectPharmacyMap() {
		List<PharmacyLocationVO> list = null;
		try {
			list = naverMapMapper.selectPharmacyMap();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
