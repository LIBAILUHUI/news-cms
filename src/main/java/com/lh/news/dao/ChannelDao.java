package com.lh.news.dao;

import java.util.List;

import com.lh.news.domain.Categories;
import com.lh.news.domain.Channels;

/**
 * 
 * @ClassName: ChannelDao 
 * @Description: 栏目和分类的dao
 * @author: Administrator
 * @date: 2020年4月2日 上午10:44:04
 */
public interface ChannelDao {

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
