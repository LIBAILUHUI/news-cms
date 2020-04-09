package com.lh.news.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.lh.news.domain.Article;

public interface ArticleService {

	/**
	 * 
	 * @Title: insertArticle 
	 * @Description: 增加文章
	 * @param article
	 * @return
	 * @return: boolean
	 */
	boolean insertArticle(Article article);
		
	
	/**
	 * 
	 * @Title: selectArticles 
	 * @Description: TODO
	 * @param article
	 * @return
	 * @return: PageInfo<Article>
	 */
	PageInfo<Article> selectArticles(Article article,Integer pageNum,Integer pageSize);
	
	Article select(Integer id);
	
	boolean updateArticle(Article article);
}
