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
	
	/**
	 * 
	 * @Title: select 
	 * @Description: 根据id查文章详情
	 * @param id
	 * @return
	 * @return: Article
	 */
	Article select(Integer id);
	
	
	/**
	 * 
	 * @Title: updateArticle 
	 * @Description: 改文章热门，状态
	 * @param article
	 * @return
	 * @return: boolean
	 */
	boolean updateArticle(Article article);
	
	
	/**
	 * 
	 * @Title: selectByTitle 
	 * @Description:根据文章标题查文章详情
	 * @param title
	 * @return
	 * @return: Article
	 */
	Article selectByTitle(String title);
}
