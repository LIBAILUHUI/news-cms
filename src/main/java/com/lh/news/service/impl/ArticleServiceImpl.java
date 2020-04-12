package com.lh.news.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lh.news.dao.ArticleDao;
import com.lh.news.domain.Article;
import com.lh.news.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleDao articleDao;
	
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

	@Override
	public PageInfo<Article> selectArticles(Article article,Integer pageNum,Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Article> articleList = articleDao.selectArticles(article);
		PageInfo<Article> info = new PageInfo<Article>(articleList);
		return info;
	}

	@Override
	public Article select(Integer id) {
		return articleDao.select(id);
	}

	@Override
	public boolean updateArticle(Article article) {
		
		try {
			articleDao.updateArticle(article);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("更新失败");
		}
	}

	@Override
	public Article selectByTitle(String title) {
		return articleDao.selectByTitle(title);
	}

	
}
