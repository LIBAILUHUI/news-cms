package com.lh.news.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.lh.news.domain.Article;
import com.lh.news.domain.Users;
import com.lh.news.service.ArticleService;
import com.lh.news.service.UserService;

/**
 * 
 * @ClassName: AdminController 
 * @Description: 管理员中心
 * @author: Administrator
 * @date: 2020年4月3日 上午10:58:13
 */
@Controller
@RequestMapping("admin")
public class AdminController {

	@Autowired
	private ArticleService articleService;
	
	@Resource
	private UserService userService;
	
	/**
	 * 
	 * @Title: index 
	 * @Description: 管理员的首页
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = {"","/","index"})
	public String index() {
		return "admin/index";
	}
	
	@RequestMapping("articles")
	public String articles(Model model,Article article,@RequestParam(defaultValue = "1") Integer pageNum,@RequestParam(defaultValue = "8") Integer pageSize) {
		if(null==article.getStatus()) {
			article.setStatus(0);
		}
		
		PageInfo<Article> info = articleService.selectArticles(article, pageNum, pageSize);
		model.addAttribute("info", info);
		model.addAttribute("alist", info.getList());
		model.addAttribute("article", article);
		return "admin/articles";
	}
	
	@ResponseBody
	@PostMapping("article/update")
	public boolean updateArticle(Article article) {
		return articleService.updateArticle(article);
	}
	
	@RequestMapping("users")
	public String users(Model model,Users user,@RequestParam(defaultValue = "1") Integer pageNum,@RequestParam(defaultValue = "3") Integer pageSize) {
		if(null==user.getLocked()) {
			user.setLocked(0);
		}
		PageInfo<Users> info = userService.selectUsers(user, pageNum, pageSize);
		model.addAttribute("info", info);
		model.addAttribute("ulist", info.getList());
		model.addAttribute("user", user);
		return "admin/users";
	}
	
	@ResponseBody
	@PostMapping("user/update")
	public boolean updateUser(Users user) {
		return userService.updateUser(user);
	}
}
