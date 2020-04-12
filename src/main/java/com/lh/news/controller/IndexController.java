package com.lh.news.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.lh.common.utils.DateUtil;
import com.lh.common.utils.RandomUtil;
import com.lh.news.domain.Article;
import com.lh.news.domain.Categories;
import com.lh.news.domain.Channels;
import com.lh.news.domain.Collections;
import com.lh.news.domain.Comment;
import com.lh.news.domain.Slide;
import com.lh.news.domain.Users;
import com.lh.news.service.ArticleService;
import com.lh.news.service.ChannelService;
import com.lh.news.service.CollectionService;
import com.lh.news.service.CommentService;
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
	
	@Resource
	private CollectionService collectionService;
	
	@Resource
	private CommentService commentService;
	
	
	//进入首页
	@RequestMapping(value = {"","/","index"})
	public String index(Model model,Article article,@RequestParam(defaultValue = "1") Integer pageNum,@RequestParam(defaultValue = "5") Integer pageSize) {
		
		article.setStatus(1); //显示审核通过的,0刚发布,1审核通过,-1驳回
		article.setDeleted(0);  //显示未删除的,0未删除,1删除
		article.setContentType(0);//文章类型,0html类型,1json
		model.addAttribute("article", article);
		
		Thread t1;
		Thread t2;
		Thread t3;
		Thread t4;
		Thread t5;
		
		t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				//1.查询所有的栏目
				List<Channels> channels = channelService.selectChannels();
				model.addAttribute("channels", channels);
			}
		});
		
		
		t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {

				//2.如果首页点击栏目不为空  则查询栏目下的分类
				if(null!=article.getChannelId()) {
					//根据栏目id查询下属分类
					List<Categories> categories = channelService.selectCategories(article.getChannelId());
					model.addAttribute("categories", categories);
					//查询栏目下分类文章或者全部文章
					PageInfo<Article> info = articleService.selectArticles(article, pageNum, pageSize);
					model.addAttribute("info", info);
				}				
			}
		});
		
		
		 t3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				//3.栏目为空查询广告和热点文章
				if(null==article.getChannelId()) {
					//查询广告
					List<Slide> slist = slideService.selectSlides();
					model.addAttribute("slist", slist);
					//查询热点文章
					Article hotArticle = new Article();
					hotArticle.setStatus(1);
					hotArticle.setDeleted(0);
					hotArticle.setContentType(0);
					hotArticle.setHot(1);//1:热点文章
					PageInfo<Article> info = articleService.selectArticles(hotArticle, pageNum, pageSize);
					model.addAttribute("info", info);
				}
			}
		});
		
		t4 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				//4.右侧边栏显示最新五篇文章
				Article lastArticles = new Article();
				lastArticles.setStatus(1);
				lastArticles.setDeleted(0);
				lastArticles.setContentType(0);
				PageInfo<Article> info = articleService.selectArticles(lastArticles, 1, 5);
				model.addAttribute("lastArticles", info.getList());
			}
		});
		
		
		t5 = new Thread(new Runnable() {
			
			@Override
			public void run() {

				//5.右侧边栏显示24小时热文
				Article article24 = new Article();
				article24.setStatus(1);
				article24.setDeleted(0);
				article24.setContentType(0);
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
			}
		});
		
		//启动线程
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		//t5.start();
		
		//等所有子线程执行完了再执行最外边的主线程
		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
			//t5.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
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
	public String detail(Integer id,Model model,HttpSession session,@RequestParam(defaultValue = "1") Integer pageNum,@RequestParam(defaultValue = "5") Integer pageSize) {
		Article article = articleService.select(id);
		model.addAttribute("article", article);
		
		
		//查询该文章是否收藏过
		Users user = (Users) session.getAttribute("user");
		if(null!=user) {  //如果用户已经登录查询是否收藏
			int isCollect = collectionService.selectCount(article.getTitle(), user.getId());
			model.addAttribute("isCollect", isCollect);
		}
		
		PageInfo<Comment> info = commentService.selectComments(id, pageNum, pageSize);
		model.addAttribute("info", info);
		
		return "index/article";
	}
	
	
	
	/**
	 * 
	 * @Title: collect 
	 * @Description: 收藏文章
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@RequestMapping("collect")
	public boolean collect(Collections collection,HttpSession session) {
		Users user = (Users)session.getAttribute("user");
		if(null!=user) { //如果已经登录则执行收藏
			collection.setUserId(user.getId());//收藏人
			collection.setCreated(new Date());//收藏时间
			return collectionService.insertCollection(collection);
		}
		return false;//没有登陆不执行收藏
	}
	
	@ResponseBody
	@RequestMapping("addComment")
	public boolean addComment(Comment comment,HttpSession session) {
		//登陆才能评论
		Users user = (Users) session.getAttribute("user");
		if(null!=user) {
			//评论人
			comment.setUserId(user.getId());
			//评论时间
			comment.setCreated(new Date());
			return commentService.insertComment(comment)>0;
		}
		//没有登陆不能评论
		return false;
	}
}
