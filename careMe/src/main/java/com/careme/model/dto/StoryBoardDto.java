package com.careme.model.dto;

import java.time.LocalDateTime;

public class StoryBoardDto {
	private int story_board_idx;
	private int member_idx;
	private String title;
	private String content;
	private int heart;
	private int view_count;
	private String reg_date;
	private String update_date;
	private String del_yn;

	private String member_id;
	
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public int getHeart() {
		return heart;
	}
	public void setHeart(int heart) {
		this.heart = heart;
	}
	public int getStory_board_idx() {
		return story_board_idx;
	}
	public void setStory_board_idx(int story_board_idx) {
		this.story_board_idx = story_board_idx;
	}
	public int getMember_idx() {
		return member_idx;
	}
	public void setMember_idx(int member_idx) {
		this.member_idx = member_idx;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getView_count() {
		return view_count;
	}
	public void setView_count(int view_count) {
		this.view_count = view_count;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}
	public String getDel_yn() {
		return del_yn;
	}
	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}
	@Override
	public String toString() {
		return "StoryBoardDto [story_board_idx=" + story_board_idx + ", member_idx=" + member_idx + ", title=" + title
				+ ", content=" + content + ", heart=" + heart + ", view_count=" + view_count + ", reg_date=" + reg_date
				+ ", update_date=" + update_date + ", del_yn=" + del_yn + "]";
	}
	
	
	
}
