package kr.human.anihospital.service;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.human.anihospital.mapper.PharmacyInfoMapper;
import kr.human.anihospital.vo.PharmacyLocationVO;

@Service
@Transactional
public class PharmacyInfoService {

	// selectAll 매퍼
	@Autowired
	PharmacyInfoMapper pharmacyInfoMapper;

	//-----------------------------------------------------------
	// 약국을 선택하는 Service
	//-----------------------------------------------------------
	public PharmacyLocationVO selectPharmacy(int seq) {
		// 해당 하는 약국 정보를 Mapper에 넘겨주기
		PharmacyLocationVO pharmacyLocationVO = new PharmacyLocationVO();
		try {
			// Mapper에 SQL실행 시 필요한 데이터 넘겨주기 및 실행할 메서드 부르기
			pharmacyLocationVO = pharmacyInfoMapper.selectPharmacy(seq);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pharmacyLocationVO;
	}

	//-----------------------------------------------------------
	// 약국을 신규 등록하는 Service.
	//-----------------------------------------------------------
	public void insertPharmacy(Map<String, String> map) {
		// 해당 약국정보의 내용을 Mapper에 넘겨주기
		PharmacyLocationVO pharmacyLocationVO = new PharmacyLocationVO();
		
		
		pharmacyLocationVO.setAnimalPharmacyName(map.get("pharmacy_name"));
		pharmacyLocationVO
				.setAnimalPharmacyAddress(map.get("input_pharmacy_address") + " " + map.get("pharmacy_address_detail"));
		pharmacyLocationVO.setAnimalPharmacyPhoneNo(map.get("pharmacy_phone"));
		pharmacyLocationVO.setPharmacyLocationLatitude(map.get("pharmacy_location_latitude"));
		pharmacyLocationVO.setPharmacyLocationLongitude(map.get("pharmacy_location_longitude"));
		try {
			// Mapper에 SQL실행 시 필요한 데이터 넘겨주기 및 실행할 메서드 부르기
			pharmacyInfoMapper.insertPharmacy(pharmacyLocationVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//-----------------------------------------------------------
	// 약국을 수정하는 Service.
	//-----------------------------------------------------------
	public void updatePharmacy(Map<String, String> map, int seq) {
		// 해당 약국정보의 내용을 Mapper에 넘겨주기
		PharmacyLocationVO pharmacyLocationVO = new PharmacyLocationVO();
		pharmacyLocationVO.setSeqPharmacyLocation(seq);
		pharmacyLocationVO.setAnimalPharmacyName(map.get("pharmacy_name"));
		pharmacyLocationVO
				.setAnimalPharmacyAddress(map.get("input_pharmacy_address") + " " + map.get("pharmacy_address_detail"));
		pharmacyLocationVO.setAnimalPharmacyPhoneNo(map.get("pharmacy_phone"));
		pharmacyLocationVO.setPharmacyLocationLatitude(map.get("pharmacy_location_latitude"));
		pharmacyLocationVO.setPharmacyLocationLongitude(map.get("pharmacy_location_longitude"));
		try {
			// Mapper에 SQL실행 시 필요한 데이터 넘겨주기 및 실행할 메서드 부르기
			pharmacyInfoMapper.updatePharmacy(pharmacyLocationVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	};
}
