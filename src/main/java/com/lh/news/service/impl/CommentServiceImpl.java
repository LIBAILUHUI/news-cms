package com.lh.news.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lh.news.dao.CommentDao;
import com.lh.news.domain.Comment;
import com.lh.news.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Resource
	private CommentDao commentDao;
	
	
	@Override
	public int insertComment(Comment comment) {
		try {
			//增加评论
			commentDao.insertComment(comment);
			//让评论数量增加1
			commentDao.updateArticle(comment.getArticleId());
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("评论失败");
		}
	}

	@Override
	public PageInfo<Comment> selectComments(Integer articleId,Integer pageNum,Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Comment> clist = commentDao.selectComments(articleId);
		return new PageInfo<Comment>(clist);
	}

}
