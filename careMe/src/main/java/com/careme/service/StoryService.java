package com.careme.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.careme.model.dto.StoryBoardDto;
import com.careme.model.dto.StoryCommentDto;
import com.careme.model.dto.StoryFileDto;
import com.careme.model.dto.TagDto;

public interface StoryService {
	// 글목록
	public List<StoryBoardDto> list();
	public List<StoryBoardDto> totalListing(Map<String, Integer> map);
	public int getTotal();
	public List<StoryFileDto> fileList();
	
	// 글 상세보기
	public StoryBoardDto read(int story_board_idx);
	public StoryFileDto readFile(int story_board_idx);
	public List<StoryCommentDto> readCom(int story_board_idx);
	// 조회수
	public int counting(int story_board_idx);
	// 좋아요
	public int heart(int story_board_idx);
	public int comHeart(int story_comment_idx);
	// 인기글
	public List<StoryBoardDto> hitList();
	
	// 작성
	public int insert(MultipartHttpServletRequest request);
	public void insertFile(StoryFileDto fileDto, MultipartHttpServletRequest request);
	public int insertCom(StoryCommentDto comDto);
	public int insertTag(TagDto tagDto);
	
	// 수정
	public int update(StoryBoardDto dto);
	public int updateFile(StoryFileDto fileDto);
	public int updateCom(StoryCommentDto comDto);
	public int updateTag(TagDto tagDto);
	
	// 삭제
	public int delete(int story_board_idx);
	public int deleteFile(int story_file_idx);
	public int deleteCom(int story_comment_idx);
	public int deleteTag(int tag_idx);
	
}
