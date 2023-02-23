package kr.human.anihospital.mapper;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.human.anihospital.vo.AnimalVO;

@Mapper
public interface PatientInfoEditDoctorMapper {
	
	AnimalVO patientInfoDoctor(int seqAnimal) throws SQLException;
	
	void updateAnimalDoctor(Map<String, Object> map) throws SQLException;
}
