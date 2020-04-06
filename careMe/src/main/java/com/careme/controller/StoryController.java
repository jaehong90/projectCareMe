package com.careme.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.careme.model.dto.StoryBoardDto;
import com.careme.service.StoryService;

@Controller
public class StoryController {
	@Autowired
	StoryService service;

	public void setService(StoryService service) {
		this.service = service;
	}
	
	@RequestMapping("/view/story/storyMain")
	public ModelAndView selectAll() {
		ModelAndView mav = new ModelAndView();
		List<StoryBoardDto> list = service.selectAll();
		mav.setViewName("/story/storyMain");
		mav.addObject("story", list);
		return mav;
	}
}
