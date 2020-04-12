package com.lh.news.dao;

import java.util.List;

import com.lh.news.domain.Article;

public interface ArticleDao {

	/**
	 * 
	 * @Title: insertArticle 
	 * @Description: 增加文章
	 * @param article
	 * @return
	 * @return: int
	 */
	int insertArticle(Article article);
	
	/**
	 * 
	 * @Title: selectArticles 
	 * @Description: 查询文章,条件查询
	 * @param article
	 * @return
	 * @return: List<Article>
	 */
	List<Article> selectArticles(Article article);
	
	
	Article select(Integer id);
	
	/**
	 * 
	 * @Title: updateArticle 
	 * @Description: 改热门和状态
	 * @param article
	 * @return
	 * @return: int
	 */
	int updateArticle(Article article);
	
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
