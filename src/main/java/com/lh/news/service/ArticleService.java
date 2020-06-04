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
	 * @Description: 根据条件查询文章，动态sql
	 * @param article
	 * @return
	 * @return: PageInfo<Article>
	 */
	PageInfo<Article> selectArticles(Article article,Integer pageNum,Integer pageSize);
	
	/**
	 * 
	 * @Title: selectArticlesRedis 
	 * @Description: redis优化，根据条件查询最新文章，动态sql
	 * @param article
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @return: PageInfo<Article>
	 */
	PageInfo<Article> selectLastArticlesRedis(Article article,Integer pageNum,Integer pageSize);
	
	
	
	/**
	 * 
	 * @Title: selectHotArticleRedis 
	 * @Description: 查询热门文章，redis
	 * @param article
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @return: PageInfo<Article>
	 */
	PageInfo<Article> selectHotArticleRedis(Article article,Integer pageNum,Integer pageSize);
	
	
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

	
	/**
	 * 
	 * @Title: selectALlArticleByCondition 
	 * @Description: 根据某些条件查询全部文章，返回List
	 * @param article
	 * @return
	 * @return: List<Article>
	 */
	List<Article> selectALlArticleByCondition(Article article);
	
	/**
	 * 
	 * @Title: selectFromES 
	 * @Description: 从es中搜索
	 * @param key
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @return: PageInfo<Article>
	 */
	PageInfo<Article> selectFromES(String key,Integer pageNum,Integer pageSize);
	
}
