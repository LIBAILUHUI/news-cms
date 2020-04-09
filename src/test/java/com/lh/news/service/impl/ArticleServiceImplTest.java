package com.lh.news.service.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageInfo;
import com.lh.news.domain.Article;
import com.lh.news.service.ArticleService;

public class ArticleServiceImplTest extends SpringJunit{

	@Autowired
	private ArticleService articleService;
	
	/**
	 * 
	 * @Title: testInsertArticle 
	 * @Description: 测试增加文章
	 * @return: void
	 */
	@Test
	public void testInsertArticle() {
		Article article = new Article();
		article.setTitle("如何看待2020年高考延期至7月7日和7月8日?会产生哪些影响?");
		boolean result = articleService.insertArticle(article);
		System.out.println(result);
	}

	
	/**
	 * 
	 * @Title: testSelectArticles 
	 * @Description: 查询文章
	 * @return: void
	 */
	@Test
	public void testSelectArticles() {
		PageInfo<Article> info = articleService.selectArticles(null, 1, 100);
		for (Article article : info.getList()) {
			System.out.println(article);
		}
	}

}
