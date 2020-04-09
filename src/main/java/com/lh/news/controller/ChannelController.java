package com.lh.news.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lh.news.domain.Categories;
import com.lh.news.domain.Channels;
import com.lh.news.service.ChannelService;

/**
 * 
 * @ClassName: ChannelController 
 * @Description: 栏目和分类控制器
 * @author: Administrator
 * @date: 2020年4月2日 上午10:54:08
 */
@Controller
@RequestMapping("channel")
public class ChannelController {

	@Autowired
	private ChannelService channelService;
	
	@ResponseBody
	@RequestMapping("channels")
	public List<Channels> channels(){
		return channelService.selectChannels();
	}
	
	@ResponseBody
	@RequestMapping("categories")
	public List<Categories> categories(Integer channelId){
		return channelService.selectCategories(channelId);
	}
	
	
	
	
	
	
	
	
}
