package com.lh.news.service;

import java.util.List;

import com.lh.news.domain.Categories;
import com.lh.news.domain.Channels;

public interface ChannelService {


	/**
	 * 
	 * @Title: selectChannels 
	 * @Description: 查询所有的栏目
	 * @return
	 * @return: List<Channels>
	 */
	List<Channels> selectChannels();
	
	
	/**
	 * 
	 * @Title: selectCategories 
	 * @Description: 根据栏目id查询所有的分类
	 * @return
	 * @return: List<Categories>
	 */
	List<Categories> selectCategories(Integer channelId);
}
