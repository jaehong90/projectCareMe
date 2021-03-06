package com.careme.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.tagext.TryCatchFinally;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.careme.model.command.LoginCommand;
import com.careme.model.dto.MemberDto;
import com.careme.model.dto.DoctorDto;
import com.careme.model.dto.DoctorMajor;

public class MemberDao extends SqlSessionDaoSupport {

	public List<MemberDto> selectAll() {
		return getSqlSession().selectList("member.selectAll");
	}

	// 로그인
	public List<MemberDto> login(LoginCommand lc) {
		return getSqlSession().selectList("member.login", lc);
	}

	// 회원정보
	public MemberDto selectMember(String id) {
		return getSqlSession().selectOne("member.selectMember", id);
	}

	// 아이디 중복체크
	public int idChk(LoginCommand lc) {
		return getSqlSession().selectOne("member.idchk", lc);
	}

	// 닉네임 중복체크
	public int nickChk(LoginCommand lc) {
		return getSqlSession().selectOne("member.nickchk", lc);
	}

	// 이메일 중복체크
	public int mailChk(LoginCommand lc) {
		return getSqlSession().selectOne("member.mailchk", lc);
	}

	// 회원가입
	public int insert(MemberDto mdto) {
		return getSqlSession().insert("member.insert", mdto);
	}

	// 정보수정 비밀번호 변경
	public List<MemberDto> update(MemberDto mdto) {
		return getSqlSession().selectList("member.update", mdto);
	}

	// 비밀번호 찾기(랜덤생성 저장)
	public List<MemberDto> updatePw(MemberDto mdto) {
		return getSqlSession().selectList("member.updatePw", mdto);
	}

	// 의사등록
	public int dinsert(DoctorDto ddto) {
		return getSqlSession().insert("doctors.dinsert", ddto);
	}

	// 의사등록에 펫 담는거
	public List<DoctorMajor> selectPet() {
		return getSqlSession().selectList("doctors.selectPet");
	}

	// 의사정보
	public DoctorDto selectDoctor(int member_idx) {
		return getSqlSession().selectOne("doctors.selectDoctor", member_idx);
	}

	// 정보수정 의사
	public List<DoctorDto> dupdate(DoctorDto ddto) {
		return getSqlSession().selectList("doctors.dupdate", ddto);
	}

	// 정보수정에 펫 버리는거
	public List<DoctorMajor> deletePet() {
		return getSqlSession().selectList("doctors.deletePet");
	}

	// 회원탈퇴
	public int delete(MemberDto mdto) {
		return getSqlSession().update("member.delete", mdto);
	}
}
