package com.careme.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.careme.dao.QuestionBoardDao;
import com.careme.model.command.FileUploadCommand;
import com.careme.model.command.SearchBoardCommand;
import com.careme.model.dto.BoardCommentDto;
import com.careme.model.dto.BoardFileDto;
import com.careme.model.dto.HeartDto;
import com.careme.model.dto.QuestionBoardDto;
import com.careme.model.dto.TagDto;

@Service("QuestionBoardService")
public class QuestionBoardServiceImpl implements QuestionBoardService {
	@Autowired
	QuestionBoardDao dao = new QuestionBoardDao();

	public void setDao(QuestionBoardDao dao) {
		this.dao = dao;
	}
	
	@Autowired
	QuestionBoardService bs;
	
	public void setQuestionBoardService(QuestionBoardService bs) {
		this.bs = bs;
	}
	
	@Autowired
	FileUploadService fus;
	
	public void setFileUploadService(FileUploadService fus) {
		this.fus = fus;
	}
	
	@Autowired
	HeartService hts;
	
	public void setHeartService(HeartService hts) {
		this.hts = hts;
	}
	
	// 공통
	
	public void updateCheckHeart(BoardCommentDto cdto) {
		dao.updateCheckHeart(cdto);
	}
	
	public BoardCommentDto getHeartInfo(int idx) {
		return dao.getHeartInfo(idx);
	}
	
	public int addHeartForDoctor(int question_board_comment_idx) {
		return dao.addHeartForDoctor(question_board_comment_idx);
	}
	
	public int subHeartForDoctor(int question_board_comment_idx) {
		return dao.subHeartForDoctor(question_board_comment_idx);
	}
	
	public int addHeartForCasual(int question_board_comment_idx) {
		return dao.addHeartForCasual(question_board_comment_idx);
	}
	
	public int subHeartForCasual(int question_board_comment_idx) {
		return dao.subHeartForCasual(question_board_comment_idx);
	}
	
	// 전문 상담 게시판 구현
	public List<QuestionBoardDto> getDoctorBoard() {
		return dao.getDoctorBoard();
	}

	public List<QuestionBoardDto> getDoctorBoardPage(Map<String,Integer>param){
		return dao.getDoctorBoardList(param);
	}

	public int getTotalDoctor() {
		return dao.getTotalDoctor();
	}
	
	public QuestionBoardDto getDoctorBoardContents(int question_table_idx) {
		return dao.getDoctorBoardContents(question_table_idx);
	}

	public List<BoardFileDto> getDoctorBoardFiles (int question_table_idx){
		return dao.getDoctorBoardFiles(question_table_idx);
	}
	
	public void getDoctorBoardViews(int question_table_idx) {
		dao.getDoctorBoardViews(question_table_idx);
	}
	
	// 검색 기능
	@Override
	public List<QuestionBoardDto> getDoctorBoardSearch(SearchBoardCommand sbc) {
		return dao.getDoctorBoardSearch(sbc);
	}

	@Override
	public List<BoardCommentDto> getDoctorBoardComments(int question_table_idx) {
		return dao.getDoctorBoardComments(question_table_idx);
	}
	
	@Override
	public BoardCommentDto getDoctorComment(int question_board_comment_idx) {
		return dao.getDoctorComment(question_board_comment_idx);
	}

// 전문 상담 게시판 작성, 수정, 삭제

	// 게시글 작성

	public int addDoctorArticles(QuestionBoardDto dto, MultipartHttpServletRequest request) {
		dto.setReg_date(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
	
		int result = dao.insertArticleForDoctor(dto);
		int idx = dto.getQuestion_table_idx();
		if (idx>0) {
			bs.addFileForDoctor(idx, request);
		}
		return result;
	}

	public void addFileForDoctor(int question_table_idx, MultipartHttpServletRequest request) {
		List<FileUploadCommand> addfiles;
		addfiles = fus.upload(request, "/img/boardUpload");
		for (FileUploadCommand file : addfiles) {
			BoardFileDto bdto = new BoardFileDto();
			bdto.setQuestion_table_idx(question_table_idx);
			bdto.setFile_name(file.getFileOriginName());
			bdto.setFile_path(file.getFilePath());
			bdto.setFile_size(file.getFileSize());
			bdto.setReg_date(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
			dao.insertFileForDoctor(bdto);
		}
	}

	// 게시글 수정

	public int updateDoctorArticle(QuestionBoardDto dto) {
		dto.setReg_date(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		return dao.updateArticlesForDoctor(dto);
	}

	// 게시글 삭제
	@Override
	public int deleteDoctorArticle(int idx) {
		return dao.deleteArticlesForDoctor(idx);
	}

// 전문 상담 게시판 Comments 작성, 수정, 삭제, 추천

	// comment 작성
	@Override
	public int addDoctorComment(BoardCommentDto commentDto) {
		commentDto.setReg_date(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		return dao.insertCommentForDoctor(commentDto);
	}

	// comment 수정
	@Override
	public int updateDoctorComment(BoardCommentDto commentDto) {
		commentDto.setReg_date(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		return dao.updateCommentForDoctor(commentDto);
	}

	// comment 삭제
	@Override
	public int deleteDoctorComment(int idx) {
		return dao.deleteCommentForDoctor(idx);
	}
	
	//heart 추천 여부 확인 후 갯수 정리
	@Override
	public void heartProcessDoctor(HeartDto hdto, int question_board_comment_idx) {
	
	String hcheck = hdto.getHeartCheck();
	if(hcheck.equals("n")) {
		addHeartForDoctor(question_board_comment_idx);
		hdto.setHeartCheck("y");
		hts.updateHeartCheck(hdto);
	
	}else if(hcheck.equals("y")) {
		subHeartForDoctor(question_board_comment_idx);
		hdto.setHeartCheck("n");
		hts.updateHeartCheck(hdto);
		}
	}
	
	
// Casual Board 내용 구현
	public List<QuestionBoardDto> getCasualBoard() {
		return dao.getCasualBoard();
	}

	public List<QuestionBoardDto> getCasualBoardPage(Map<String,Integer>param){
		return dao.getCasualBoardList(param);
	}
	
	public int getTotal() {
		return dao.getTotal();
	}
	
	public QuestionBoardDto getCasualBoardContents(int question_table_idx) {
		return dao.getCasualBoardContents(question_table_idx);
	}

	public List<BoardFileDto> getCasualBoardFiles (int question_table_idx){
		return dao.getCasualBoardFiles(question_table_idx);
	}
	
	public void getCasualBoardViews(int question_table_idx) {
		dao.getCasualBoardViews(question_table_idx);
	}
	

	
	@Override
	public List<QuestionBoardDto> getCasualBoardSearch(SearchBoardCommand sbc) {
		return dao.getCasualBoardSearch(sbc);
	}

	@Override
	public List<BoardCommentDto> getCasualBoardComments(int question_table_idx) {
		return dao.getCasualBoardComments(question_table_idx);
	}
	
	@Override
	public BoardCommentDto getCasualComment(int question_board_comment_idx) {
		return dao.getCasualComment(question_board_comment_idx);
	}

	
	
// Casual Board 작성, 수정, 삭제

	// 게시글 작성

	@Override
	public int addCasualArticles(QuestionBoardDto dto, MultipartHttpServletRequest request) {
		dto.setReg_date(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		int result = dao.insertArticleForCasual(dto);
		int idx = dto.getQuestion_table_idx();
		if (idx>0) {
			bs.addFileForCasual(idx, request);
		}
		return result;
	}

	@Override
	public void addFileForCasual(int question_table_idx, MultipartHttpServletRequest request) {
		List<FileUploadCommand> addfiles;
		addfiles = fus.upload(request, "/img/boardUpload");
		for (FileUploadCommand file : addfiles) {
			BoardFileDto bdto = new BoardFileDto();
			bdto.setQuestion_table_idx(question_table_idx);
			bdto.setFile_name(file.getFileOriginName());
			bdto.setFile_path(file.getFilePath());
			bdto.setFile_size(file.getFileSize());
			bdto.setReg_date(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
			dao.insertFileForCasual(bdto);
		}
	}
		

	// 게시글 수정

	public int updateCasualArticle(QuestionBoardDto dto) {

		dto.setUpdate_date(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		return dao.updateArticlesForCasual(dto);
	}

	// 게시글 삭제
	public int deleteCasualArticle(int idx) {
		return dao.deleteArticlesForCasual(idx);
	}

// Casual Board Comments 작성, 수정, 삭제	

	// comment 작성
	public int addCasualComment(BoardCommentDto commentDto) {
		commentDto.setReg_date(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		return dao.insertCommentForCasual(commentDto);
	}

	// comment 수정
	public int updateCasualComment(BoardCommentDto commentDto) {
		commentDto.setReg_date(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		return dao.updateCommentForCasual(commentDto);
	}

	// comment 삭제
	public int deleteCasualComment(int idx) {
		return dao.deleteCommentForCasual(idx);
	}
	
	//heart 추천 여부 확인 후 갯수 정리
		@Override
		public void heartProcess(HeartDto hdto, int question_board_comment_idx) {
		String hcheck = hdto.getHeartCheck();
		if(hcheck.equals("n")) {
			addHeartForCasual(question_board_comment_idx);
			hdto.setHeartCheck("y");
			hts.updateHeartCheck(hdto);
			
		}else if(hcheck.equals("y")) {
			subHeartForCasual(question_board_comment_idx);
			hdto.setHeartCheck("n");
			hts.updateHeartCheck(hdto);
			}
		}
	
	// 공통
	@Override
	public SearchBoardCommand listSearchInfo (int searchn, String searchKeyword) {
		SearchBoardCommand sbc = new SearchBoardCommand();
			if (searchn == 0) {
				sbc.setSearch_option("member_id");
			} else if (searchn == 1) {
				sbc.setSearch_option("title");
			} else if (searchn == 2) {
				sbc.setSearch_option("content");
			}
		sbc.setSearchKeyword(searchKeyword);
		return sbc;
	}
	
<<<<<<< HEAD
	@Override
=======
	public List<TagDto> getTagContent(int question_table_idx){
		return dao.getTagContent(question_table_idx);
	}
	
	// 회원이 쓴 글 가져오기
	@Override

>>>>>>> master
	public List<QuestionBoardDto> getMemberDoctorBoard (int member_idx, Map<String,Integer>param){
		int contentPerPage= 5;
		param.put("member_idx", member_idx);
		param.put("contentPerPage", contentPerPage);
		return dao.getMemberDoctorBoard(param);
	}
	
	
	public List<QuestionBoardDto> getMemberCasualBoard (int member_idx, Map<String,Integer>param){
		int contentPerPage= 5;
		param.put("member_idx", member_idx);
		param.put("contentPerPage", contentPerPage);
		return dao.getMemberCasualBoard(param);
	}
<<<<<<< HEAD
		
=======
	
	
	
	
>>>>>>> master
		
}
