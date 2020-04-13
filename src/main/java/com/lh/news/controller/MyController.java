package com.lh.news.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.lh.news.domain.Article;
import com.lh.news.domain.Collections;
import com.lh.news.domain.Users;
import com.lh.news.service.ArticleService;
import com.lh.news.service.CollectionService;
import com.lh.news.util.ArticleEnum;
import com.lh.news.vo.ArticleVO;

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
	
	@Autowired
	private CollectionService collectionService;
	
	
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
	public String articles(Model model,Article article,@RequestParam(defaultValue = "1") Integer pageNum,@RequestParam(defaultValue = "3") Integer pageSize,HttpSession session) {
		Users user = (Users) session.getAttribute("user");
		article.setUserId(user.getId()); //6是1712d  7是lily
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
	
	//获取cms.properties里key为filePath的值
	@Value(value = "${filePath}")
	private String filePath;
	
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
	public boolean publish(Article article,MultipartFile file,HttpSession session) {
		//判断是否上传了文件
		if(file!=null && !file.isEmpty()) {
			//执行文件上传
			String path = filePath; //文件上传路径,具体值在cms.properties里边
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
		Users user = (Users) session.getAttribute("user");
		
		article.setUserId(user.getId());
		article.setStatus(0);
		article.setCreated(new Date());
		article.setHits(0);
		article.setHot(0);
		article.setDeleted(0);//未删除
		article.setContentType(0);//普通html文本
		
		return articleService.insertArticle(article);
	}
	
	//文章详情
	@ResponseBody
	@RequestMapping("article")
	public Article article(Integer id) {
		return articleService.select(id);
	}
	
	//展示
	@RequestMapping("collections")
	public String collections(Collections collection,@RequestParam(defaultValue = "1") Integer pageNum,@RequestParam(defaultValue = "3") Integer pageSize,Model model,HttpSession session ) {
		Users user = (Users) session.getAttribute("user");
		collection.setUserId(user.getId());
		PageInfo<Collections> info = collectionService.selectCollections(collection, pageNum, pageSize);
		model.addAttribute("info", info);
		return "my/collections";
	}
	
	/**
	 * 
	 * @Title: unCollect 
	 * @Description: 取消收藏
	 * @param id
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@RequestMapping("unCollect")
	public boolean unCollect(Integer id) {
		return collectionService.deleteCollection(id)>0;
	}
	
	@RequestMapping("articleByTitle")
	public String articleByTitle(String title,Model model) {
		model.addAttribute("article", articleService.selectByTitle(title));
		return "/my/collectionArticle";
	}
	
	/**
	 * 
	 * @Title: publishpic 
	 * @Description: 去发布图片集页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("publishpic")
	public String publishpic() {
		return "my/publishpic";
	}
	
	
	/**
	 * 
	 * @Title: publish
	 * @Description: 发布图片集
	 * @param article
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@PostMapping("publishpic")
	public boolean publishpic(HttpServletRequest request, Article article, MultipartFile[] files,
			String[] descr) {

		String newFilename = null;
		List<ArticleVO> list = new ArrayList<ArticleVO>();// 用来存放图片的地址和描述
		int i = 0;
		for (MultipartFile file : files) {
			ArticleVO vo = new ArticleVO();
			if (!file.isEmpty()) {
				// 文件上传路径.把文件放入项目的 /resource/pic 下
				String path =filePath;
				// 为了防止文件重名.使用UUID 的方式重命名上传的文件
				String oldFilename = file.getOriginalFilename();
				// a.jpg
				newFilename = UUID.randomUUID() + oldFilename.substring(oldFilename.lastIndexOf("."));
				File f = new File(path, newFilename);
			
				vo.setUrl(newFilename);//封装的文件的名称
				vo.setDescr(descr[i]);//封装文件描述
				i++;
				list.add(vo);

				// 写入硬盘
				try {
					file.transferTo(f);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
		
		article.setPicture(newFilename);// 图片集封面
		Gson gson = new Gson();
		// 使用gson,把java对象转为json
		article.setContent(gson.toJson(list));
		// 初始化设置
		article.setStatus(1);// 
		HttpSession session = request.getSession(false);
		if (session == null) {
			return false;
		}
		Users user = (Users) session.getAttribute("user");
		article.setUserId(user.getId());// 发布人
		article.setHits(0);
		article.setHot(0);
		article.setDeleted(0);
		article.setCreated(new Date());
		article.setUpdated(new Date());
		// 图片集标识
		article.setContentType(ArticleEnum.IMAGE.getCode());

		return articleService.insertArticle(article);

	}
}
