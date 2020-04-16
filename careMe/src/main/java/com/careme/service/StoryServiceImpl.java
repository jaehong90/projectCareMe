package com.careme.service;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.careme.dao.StoryDao;
import com.careme.model.command.FileUploadCommand;
import com.careme.model.command.StoryCommand;
import com.careme.model.dto.StoryBoardDto;
import com.careme.model.dto.StoryCommentDto;
import com.careme.model.dto.StoryFileDto;
import com.careme.service.FileUploadService;

@Service("StoryService")
public class StoryServiceImpl implements StoryService {
	@Autowired
	StoryDao dao;
	
	public void setDao(StoryDao dao) {
		this.dao = dao;
	}
	
	StoryBoardDto dto;

	public void setDto(StoryBoardDto dto) {
		this.dto = dto;
	}
	
	@Autowired
	FileUploadService service;
	
	public void setService(FileUploadService service) {
		this.service = service;
	}

	@Override
	public List<StoryBoardDto> list() {
		return dao.listing();
	}
	public List<StoryFileDto> fileList() {
		return dao.fileListing();
	}
	
	@Override
	public StoryBoardDto read(int story_board_idx) {
		return dao.read(story_board_idx);
	}
	@Override
	public StoryFileDto readFile(int story_board_idx) {
		return dao.readFile(story_board_idx);
	}

	@Override
	public int counting(int story_board_idx) {
		return dao.counting(story_board_idx);
	}

	@Override
	public List<StoryCommentDto> readCom(int story_board_idx) {
		return dao.readCom(story_board_idx);
	}

	@Override
	public int insert(MultipartHttpServletRequest request) {
		dto = requesting(request);
		return dao.insert(dto);
	}

	public StoryBoardDto requesting(MultipartHttpServletRequest request) {
		dto = new StoryBoardDto();
		
		int member_idx = 1;
		if (request.getParameter("story_board_idx") != null && request.getParameter("story_board_idx") != "") {
			dto.setStory_board_idx(Integer.parseInt(request.getParameter("story_board_idx")));
		}
		Integer story_board_idx = (Integer) request.getSession().getAttribute("story_board_idx");
		if(story_board_idx != null) {
			dto.setStory_board_idx(story_board_idx);
		}
		dto.setMember_idx(member_idx);
		dto.setContent(request.getParameter("content"));
		dto.setTitle(request.getParameter("title"));
		
		int tag_idx = 1;
		dto.setTag_idx(tag_idx);
		dto.setReg_date(LocalDateTime.now());
		return dto;
	}
	
	@Override
	public void insertFile(StoryFileDto dto, MultipartHttpServletRequest request) {
		int story_board_idx = dto.getStory_board_idx();
		if(story_board_idx > 0) fileRequesting(story_board_idx, request);
		
	}
	
	public void fileRequesting(int story_board_idx, MultipartHttpServletRequest request) {
		List<FileUploadCommand> files = service.upload(request, "/img/story/");
		
		for(FileUploadCommand file : files) {
			StoryFileDto dto = new StoryFileDto();
			dto.setStory_board_idx(story_board_idx);
			dto.setFile_name(file.getFileOriginName());
			dto.setFile_path(file.getFilePath());
			dto.setFile_size(file.getFileSize());
			
			dao.insertFile(dto);
		}
	}

	@Override
	public int insertCom(StoryCommentDto comDto) {
		comDto.setReg_date(LocalDateTime.now());
		return dao.insertCom(comDto);
	}

	@Override
	public int update(StoryBoardDto dto) {
		dto.setReg_date(LocalDateTime.now());
		return dao.update(dto);
	}

	@Override
	public int updateCom(StoryCommentDto comDto) {
		comDto.setReg_date(LocalDateTime.now());
		return dao.updateCom(comDto);
	}

	@Override
	public int delete(int story_board_idx) {
		return dao.delete(story_board_idx);
	}

	@Override
	public int deleteCom(int story_comment_idx) {
		return dao.deleteCom(story_comment_idx);
	}

	@Override
	public List<StoryBoardDto> hitList() {
		return dao.hitList();
	}

}