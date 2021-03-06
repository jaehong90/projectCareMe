package com.careme.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.careme.model.dto.PetDto;
import com.careme.model.dto.PetSpeciesDto;

public class PetDao extends SqlSessionDaoSupport {
	public List<PetSpeciesDto> selectPetSpeciesLevel1 () {
		return getSqlSession().selectList("pet.selectSpeciesLevel1"); 
	}
	
	public List<PetSpeciesDto> selectPetSpeciesLevel2 (int level1) {
		return getSqlSession().selectList("pet.selectSpeciesLevel2", level1); 
	}
	
	public List<PetSpeciesDto> selectSpeciesLevel2BySelfIdx (int level2) {
		return getSqlSession().selectList("pet.selectSpeciesLevel2BySelfIdx", level2); 
	}
	
	public int insertPet(PetDto dto) {
		return getSqlSession().insert("pet.insertPet", dto);
	}
	
	public PetDto selectPet(int petIdx) {
		return getSqlSession().selectOne("pet.selectPet", petIdx);
	}
	
	
	public List<PetDto> selectPetList(int memberIdx) {
		return getSqlSession().selectList("pet.selectPetList", memberIdx);
	}
	
	public int updatePet(PetDto dto) {
		return getSqlSession().update("pet.updatePet", dto);
	}
	
	public int deletePet(int petIdx) {
		return getSqlSession().update("pet.deletePet", petIdx);
	}
	
	public int findSelectedPet(int memberIdx) {
		return getSqlSession().selectOne("pet.findSelectedPet", memberIdx);
	}
	
	public int updateToselectedPet(int petIdx) {
		return getSqlSession().update("pet.updateToselectedPet", petIdx);
	}
	
	public int updateToselectedPetTop1(int member_idx) {
		return getSqlSession().update("pet.updateToselectedPetTop1", member_idx);
	}
	
	public void deSelectPet(int memberIdx) {
		getSqlSession().update("pet.deSelectPet", memberIdx);
	}
	
	public HashMap<String, Object> getPetSpecName(int pet_idx) {
		return getSqlSession().selectOne("pet.selectPetSpecName", pet_idx);
	}

}
