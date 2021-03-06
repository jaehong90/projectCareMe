package com.careme.model.command;

public class LoginCommand {
	
	private String member_id;
	private String member_pass;
	private String member_nick;
	private String member_email;
	private int member_idx;
	
	
	
	public int getMember_idx() {
		return member_idx;
	}
	public void setMember_idx(int member_idx) {
		this.member_idx = member_idx;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public String getMember_nick() {
		return member_nick;
	}
	public void setMember_nick(String member_nick) {
		this.member_nick = member_nick;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMember_pass() {
		return member_pass;
	}
	public void setMember_pass(String member_pass) {
		this.member_pass = member_pass;
	}
	@Override
	public String toString() {
		return "LoginCommand [member_id=" + member_id + ", member_pass=" + member_pass + ", member_nick=" + member_nick
				+ ", member_email=" + member_email + ", member_idx=" + member_idx + "]";
	}

	
	

}
