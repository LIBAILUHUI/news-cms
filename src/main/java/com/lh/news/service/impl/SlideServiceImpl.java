package com.lh.news.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lh.news.dao.SlideDao;
import com.lh.news.domain.Slide;
import com.lh.news.service.SlideService;

@Service
public class SlideServiceImpl implements SlideService {

	@Resource
	private SlideDao slideDao;
	
	@Override
	public List<Slide> selectSlides() {
		return slideDao.selectSlides();
	}

}
