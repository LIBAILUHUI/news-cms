package com.lh.news.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.lh.news.dao.ChannelDao;
import com.lh.news.domain.Categories;
import com.lh.news.domain.Channels;
import com.lh.news.service.ChannelService;

@Service
public class ChannelServiceImpl implements ChannelService {

	@Autowired
	private ChannelDao channelDao;
	
	@Resource
	private RedisTemplate<String, Channels> redisTemplate;
	
	@Override
	public List<Channels> selectChannels() {
		
		ListOperations<String, Channels> opsForList = redisTemplate.opsForList();
		
		//从redis获取所有栏目
		List<Channels> channels = opsForList.range("channels", 0, -1);
		
		if(null==channels || channels.size()==0) {
			channels = channelDao.selectChannels();
			opsForList.rightPushAll("channels", channels);
		}
		
		return channels;
	}

	@Override
	public List<Categories> selectCategories(Integer channelId) {
		return channelDao.selectCategories(channelId);
	}

}
