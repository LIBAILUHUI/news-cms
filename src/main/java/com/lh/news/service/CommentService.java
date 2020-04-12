package com.lh.news.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.lh.news.domain.Comment;

public interface CommentService {

	/**
	 * 
	 * @Title: insertComment 
	 * @Description: 发布评论
	 * @param comment
	 * @return
	 * @return: int
	 */
	int insertComment(Comment comment);
	
	
	/**
	 * 
	 * @Title: selectComments 
	 * @Description:查询评论
	 * @param articleId
	 * @return
	 * @return: PageInfo<Comment>
	 */
	PageInfo<Comment> selectComments(Integer articleId,Integer pageNum,Integer pageSize);
}
