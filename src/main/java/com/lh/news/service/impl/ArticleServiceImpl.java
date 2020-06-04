package com.lh.news.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.builder.StandardToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lh.news.dao.ArticleDao;
import com.lh.news.domain.Article;
import com.lh.news.service.ArticleService;
import com.lh.news.util.HLUtils;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleDao articleDao;
	
	@Resource
	private RedisTemplate<String, Article> redisTemplate;
	
	@Resource
	private ElasticsearchTemplate elasticsearchTemplate;
	
	/**
	 * 增加文章
	 */
	@Override
	public boolean insertArticle(Article article) {
		try {
			articleDao.insertArticle(article);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("-------------增加文章失败-----------------");
		}
	}
	
	/**
	 * 根据条件查询文章，动态sql
	 */
	@Override
	public PageInfo<Article> selectArticles(Article article,Integer pageNum,Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Article> articleList = articleDao.selectArticles(article);
		PageInfo<Article> info = new PageInfo<Article>(articleList);
		return info;
	}
	
	/**
	 * redis优化，取最新文章
	 */
	@Override
	public PageInfo<Article> selectLastArticlesRedis(Article article, Integer pageNum, Integer pageSize) {
		//获得redis操作对象
		ListOperations<String, Article> opsForList = redisTemplate.opsForList();
		//从redis中查询最新文章
		List<Article> last_article = opsForList.range("last_article", (pageNum-1)*pageSize, pageNum*pageSize-1);
		PageInfo<Article> info = null;
		
		//用于redis分页
		Page<Article> page = new Page<Article>(pageNum, pageSize);
		//把当前页加进去
		page.addAll(last_article);
		//查询总条数
		long total = opsForList.size("last_article");
		//设置总条数
		page.setTotal(total);
		
		System.out.println(null == last_article || last_article.size()==0);
		//如果从redis中查出来空或者没有内容
		if(null == last_article || last_article.size()==0) {
			//设置分页参数
			PageHelper.startPage(pageNum, pageSize);
			//redis中没有从mysql数据库中取
			last_article = articleDao.selectArticles(article);
			//存入redis中
			opsForList.rightPushAll("last_article", last_article);
			//从mysql中取出来的分页就用PageHelper
			info = new PageInfo<Article>(last_article);
		}else {
			System.out.println("在这里");
			//从redis中取出来的分页封装Page对象
			info = new PageInfo<Article>(page);
			System.out.println(info.getList());
		}
		return info;
	}

	/**
	 * 查询热门文章，redis
	 */
	@Override
	public PageInfo<Article> selectHotArticleRedis(Article article, Integer pageNum, Integer pageSize) {
		
		ListOperations<String, Article> opsForList = redisTemplate.opsForList();
		
		List<Article> hot_article = opsForList.range("hot_article", 0, -1);
		
		if(null==hot_article || hot_article.size()==0) {
			hot_article = articleDao.selectArticles(article);
			opsForList.rightPushAll("hot_article", hot_article);
		}
		
		List<Article> hotList = opsForList.range("hot_article", (pageNum-1)*pageSize, pageNum*pageSize-1);
		
		Page<Article> page = new Page<>(pageNum,pageSize);
		//加入当前页
		page.addAll(hotList);
		
		long total = opsForList.size("hot_article");
		
		page.setTotal(total);
		
		return new PageInfo<Article>(page);
	}
	

	/**
	 * 根据id查文章详情
	 */
	@Override
	public Article select(Integer id) {
		return articleDao.select(id);
	}

	/**
	 * 改文章热门，状态
	 */
	@Override
	public boolean updateArticle(Article article) {
		
		boolean flag = articleDao.updateArticle(article)>0;
		
		//判断文章是否审核通过，并且已经修改成功，更新之后删除redis原有的最新文章
		if(null!=article.getStatus() && article.getStatus()==1 && flag) {
			redisTemplate.delete("last_article");
		}
		
		//判断文章是否被设置为热门，并且已经修改成功，更新之后删除redis原有的热门文章
		if(null!=article.getHot() && article.getHot()==1 && flag) {
			redisTemplate.delete("hot_article");
		}
		//通过id查询审核过后的文章详情
		//Article select = articleDao.select(article.getId());
		
		
		return flag;
		
		//以前的
		/*
		 * try { articleDao.updateArticle(article); return true; } catch (Exception e) {
		 * e.printStackTrace(); throw new RuntimeException("更新失败"); }
		 */
	}

	/**
	 * 根据文章标题查文章详情
	 */
	@Override
	public Article selectByTitle(String title) {
		return articleDao.selectByTitle(title);
	}

	

	/**
	 * 根据某些条件查询全部文章，返回List
	 */
	@Override
	public List<Article> selectALlArticleByCondition(Article article) {
		return articleDao.selectArticles(article);
	}

	
	/**
	 * 从ES中高亮搜索
	 */
	@Override
	public PageInfo<Article> selectFromES(String key, Integer pageNum, Integer pageSize) {
		PageInfo<Article> info = HLUtils.findByHighLight(elasticsearchTemplate, Article.class, pageNum, pageSize, new String[] {"title","keywords"}, "id", key);
		return info;
	}

	

	
}
