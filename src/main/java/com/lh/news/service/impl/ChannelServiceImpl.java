package com.lh.news.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lh.news.dao.ChannelDao;
import com.lh.news.domain.Categories;
import com.lh.news.domain.Channels;
import com.lh.news.service.ChannelService;

@Service
public class ChannelServiceImpl implements ChannelService {

	@Autowired
	private ChannelDao channelDao;
	
	@Override
	public List<Channels> selectChannels() {
		return channelDao.selectChannels();
	}

	@Override
	public List<Categories> selectCategories(Integer channelId) {
		return channelDao.selectCategories(channelId);
	}

}
