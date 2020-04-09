package com.lh.news.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.lh.news.domain.Article;
import com.lh.news.service.ArticleService;

/**
 * 
 * @ClassName: MyController 
 * @Description: 个人中心控制器
 * @author: Administrator
 * @date: 2020年4月1日 上午11:22:02
 */
@Controller
@RequestMapping("my")
public class MyController {

	@Autowired
	private ArticleService articleService;
	
	
	//进入个人中心首页，支持三种访问方式
	@RequestMapping(value = {"","/","index"})
	public String index() {
		return "my/index";
	}
	
	/**
	 * 
	 * @Title: articles 
	 * @Description: 显示登录人我的文章
	 * @return
	 * @return: String
	 */
	@RequestMapping("articles")
	public String articles(Model model,Article article,@RequestParam(defaultValue = "1") Integer pageNum,@RequestParam(defaultValue = "3") Integer pageSize) {
		article.setUserId(6); //6是1712d  7是lily
		PageInfo<Article> info = articleService.selectArticles(article, pageNum, pageSize);
		model.addAttribute("info", info);
		model.addAttribute("alist", info.getList());
		return "my/articles";
	}
	
	/**
	 * 
	 * @Title: publish 
	 * @Description: 去发布文章页面
	 * @return
	 * @return: String
	 */
	@GetMapping("publish")
	public String publish() {
		return "my/publish";
	}
	
	/**
	 * 
	 * @Title: publish 
	 * @Description: 执行发布
	 * @param article
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@PostMapping("publish")
	public boolean publish(Article article,MultipartFile file) {
		//判断是否上传了文件
		if(file!=null && !file.isEmpty()) {
			//执行文件上传
			String path = "d:/pic/"; //文件上传路径
			//防止文件重名使用UUID,先获取原始名称
			String oldFileName = file.getOriginalFilename();
			String fileName = UUID.randomUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
			File f = new File(path, fileName);
			try {
				file.transferTo(f);
				article.setPicture(fileName);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//需要初始化数据，作者后期应从session获取
		article.setUserId(6);
		article.setStatus(0);
		article.setCreated(new Date());
		article.setHits(0);
		article.setHot(0);
		article.setDeleted(0);//未删除
		article.setContentType(0);//普通html文本
		
		;
		return articleService.insertArticle(article);
	}
	
	//文章详情
	@ResponseBody
	@RequestMapping("article")
	public Article article(Integer id) {
		return articleService.select(id);
	}
	
	
	
}
