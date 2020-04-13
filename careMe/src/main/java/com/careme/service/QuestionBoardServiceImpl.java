package com.careme.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careme.dao.QuestionBoardDao;
import com.careme.model.command.SearchBoardCommand;
import com.careme.model.dto.BoardCommentDto;
import com.careme.model.dto.QuestionBoardDto;

@Service
public class QuestionBoardServiceImpl implements QuestionBoardService{
	
	@Autowired
	QuestionBoardDao dao = new QuestionBoardDao();

// Doctor Board 게시글 뿌리기
	public List<QuestionBoardDto> getDoctorBoard(){
		return dao.getDoctorBoard();
	}
	
	public QuestionBoardDto getDoctorBoardContents(int question_table_idx){
		return dao.getDoctorBoardContents(question_table_idx);
	}

	public void getDoctorBoardViews (int question_table_idx) {
		dao.getDoctorBoardViews(question_table_idx);
	}
	
	public List<QuestionBoardDto>getDoctorBoardSearch(SearchBoardCommand sbc){
		return dao.getDoctorBoardSearch(sbc);
	}
	
	public List<BoardCommentDto> getDoctorBoardComments(int question_table_idx){
		return dao.getDoctorBoardComments(question_table_idx);
	}


// Doctor Board 작성, 수정, 삭제
	
	// 게시글 작성
		
		public int addDoctorArticles(QuestionBoardDto boardDto) {
			boardDto.setReg_date(LocalDateTime.now());
			return dao.insertArticleForDoctor(boardDto);
		}
	
	// 게시글 수정
	
		public int updateDoctorArticle(QuestionBoardDto boardDto) {
			boardDto.setUpdate_date(LocalDateTime.now());
			return dao.updateArticlesForDoctor(boardDto);
		}
		
	// 게시글 삭제
		public int deleteDoctorArticle(int idx) {
			return dao.deleteArticlesForDoctor(idx);
		}

// Doctor Board Comments 작성, 수정, 삭제
		
	// comment 작성
		public int addDoctorComment(BoardCommentDto commentDto) {
			commentDto.setReg_date(LocalDateTime.now());
			return dao.insertCommentForDoctor(commentDto);
		}
						
	// comment 수정
		public int updateDoctorComment(BoardCommentDto commentDto) {
			commentDto.setReg_date(LocalDateTime.now());
			return dao.updateCommentForDoctor(commentDto);
		}
							
	// comment 삭제	
		public int deleteDoctorComment(int idx) {
			return dao.deleteCommentForDoctor(idx);
		}
	
	
	
// Casual Board 내용 구현
	public List<QuestionBoardDto> getCasualBoard(){
		return dao.getCasualBoard();
	}
	
	public QuestionBoardDto getCasualBoardContents(int question_table_idx){
		return dao.getCasualBoardContents(question_table_idx);
	}

	public void getCasualBoardViews (int question_table_idx) {
		dao.getCasualBoardViews(question_table_idx);
	}
	
	public List<QuestionBoardDto>getCasualBoardSearch(SearchBoardCommand sbc){
		return dao.getCasualBoardSearch(sbc);
	}

	public List<BoardCommentDto> getCasualBoardComments(int question_table_idx){
		return dao.getCasualBoardComments(question_table_idx);
	}
	
	
// Casual Board 작성, 수정, 삭제
	
	// 게시글 작성
		
		public int addCasualArticles(QuestionBoardDto boardDto) {
			boardDto.setReg_date(LocalDateTime.now());
			return dao.insertArticleForCasual(boardDto);
		}
		
	// 게시글 수정
		
		public int updateCasualArticle(QuestionBoardDto boardDto) {
			boardDto.setUpdate_date(LocalDateTime.now());
			return dao.updateArticlesForCasual(boardDto);
		}
			
	// 게시글 삭제
		public int deleteCasualArticle(int idx) {
			return dao.deleteArticlesForCasual(idx);
		}

// Casual Board Comments 작성, 수정, 삭제	
			
	// comment 작성
		public int addCasualComment(BoardCommentDto commentDto) {
			commentDto.setReg_date(LocalDateTime.now());
			return dao.insertCommentForCasual(commentDto);
		}
					
	// comment 수정
		public int updateCasualComment(BoardCommentDto commentDto) {
			commentDto.setReg_date(LocalDateTime.now());
			return dao.updateCommentForCasual(commentDto);
		}
						
	// comment 삭제	
		public int deleteCasualComment(int idx) {
			return dao.deleteCommentForCasual(idx);
		}	
}