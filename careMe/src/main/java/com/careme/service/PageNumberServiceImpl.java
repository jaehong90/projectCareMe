package com.careme.service;

import org.springframework.stereotype.Service;

import com.careme.model.command.PageNumberCommand;

@Service
public class PageNumberServiceImpl implements PageNumberService {

	public PageNumberCommand paging (int totalCount, int contentPerPage, int currentPage, String path) {
		PageNumberCommand pnc = new PageNumberCommand();
		
		int pageBlock = 10;
		int totalPage = totalCount / contentPerPage;
		int startPage = (currentPage / pageBlock) + 1;
		int endPage = (totalPage / pageBlock) * pageBlock + (totalPage % pageBlock);
		
		pnc.setContentPerPage(contentPerPage);
		pnc.setCurrentPage(currentPage);
		pnc.setEndPage(endPage);
		pnc.setStartPage(startPage);
		pnc.setTotalCount(totalCount);
		pnc.setTotalPage(totalPage);
		
		pnc.setPath(path);
		
		return pnc;
	}
	
	
	
}