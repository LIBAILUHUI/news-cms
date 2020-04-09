package com.lh.news.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.lh.common.utils.DateUtil;
import com.lh.common.utils.RandomUtil;
import com.lh.news.domain.Article;
import com.lh.news.domain.Categories;
import com.lh.news.domain.Channels;
import com.lh.news.domain.Slide;
import com.lh.news.service.ArticleService;
import com.lh.news.service.ChannelService;
import com.lh.news.service.SlideService;

/**
 * 
 * @ClassName: IndexController 
 * @Description: 首页主要给用户用，所以类上不写RequestMapping
 * @author: Administrator
 * @date: 2020年4月7日 上午10:39:19
 */
@Controller
public class IndexController {

	@Resource
	private ChannelService channelService;
	
	@Resource
	private ArticleService articleService;
	
	@Resource
	private SlideService slideService;
	
	//进入首页
	@RequestMapping(value = {"","/","index"})
	public String index(Model model,Article article,@RequestParam(defaultValue = "1") Integer pageNum,@RequestParam(defaultValue = "5") Integer pageSize) {
		model.addAttribute("article", article);
		//查询所有的栏目
		List<Channels> channels = channelService.selectChannels();
		model.addAttribute("channels", channels);
		
		/*
		 * article.setStatus(1); article.setDeleted(0); article.setContentType(0);
		 */
		
		//如果首页点击栏目不为空  则查询栏目下的分类
		if(null!=article.getChannelId()) {
			//根据栏目id查询下属分类
			List<Categories> categories = channelService.selectCategories(article.getChannelId());
			model.addAttribute("categories", categories);
			//查询栏目下分类文章或者全部文章
			PageInfo<Article> info = articleService.selectArticles(article, pageNum, pageSize);
			model.addAttribute("info", info);
		}
		
		
		//栏目为空查询广告和热点文章
		if(null==article.getChannelId()) {
			//查询广告
			List<Slide> slist = slideService.selectSlides();
			model.addAttribute("slist", slist);
			//查询热点文章
			Article hotArticle = new Article();
			hotArticle.setHot(1);//1:热点文章
			PageInfo<Article> info = articleService.selectArticles(hotArticle, pageNum, pageSize);
			model.addAttribute("info", info);
		}
		
		
		//右侧边栏显示最新五篇文章
		Article lastArticles = new Article();
		PageInfo<Article> info = articleService.selectArticles(lastArticles, 1, 5);
		model.addAttribute("lastArticles", info.getList());
		
		
		//右侧边栏显示24小时热文
		Article article24 = new Article();
		article24.setStatus(1);
		article24.setDeleted(0);
		article24.setHot(1);
		article24.setCreated(DateUtil.getDateOneDayBefore());
		PageInfo<Article> info24 = articleService.selectArticles(article24, pageNum, 30);
		int[] index = RandomUtil.subRandom(0, info24.getList().size()-1, 0);
		List<Article> list2 = info24.getList();
		List<Article> randomArticles = new ArrayList<Article>();
		for (int i = 0; i < index.length; i++) {
			
				randomArticles.add(list2.get(index[i]));
			
		}
		
		model.addAttribute("randomArticles", randomArticles);
		return "index/index";
	}
	
	
	/**
	 * 
	 * @Title: detail 
	 * @Description: 首页文章详情
	 * @param id
	 * @return
	 * @return: String
	 */
	@RequestMapping("detail")
	public String detail(Integer id,Model model) {
		Article article = articleService.select(id);
		model.addAttribute("article", article);
		return "index/article";
	}
	
	
	
	
	
}
