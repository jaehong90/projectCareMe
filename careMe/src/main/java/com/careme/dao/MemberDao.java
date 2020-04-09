package com.careme.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.careme.model.command.LoginCommand;
import com.careme.model.dto.MemberDto;

public class MemberDao extends SqlSessionDaoSupport {
	
	
	
	public List<MemberDto> selectAll() {
		return getSqlSession().selectList("member.selectAll");		
	}
	
	public List<MemberDto> login(LoginCommand lc){
		return getSqlSession().selectList("member.login", lc);
	}
	
}
