package com.lh.news.dao;

import java.util.List;

import com.lh.news.domain.Comment;

/**
 * 
 * @ClassName: CommentDao 
 * @Description: 评论
 * @author: Administrator
 * @date: 2020年4月12日 下午4:07:43
 */
public interface CommentDao {

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
	 * @Description: 根据文章id展示评论
	 * @param articleId
	 * @return
	 * @return: List<Comment>
	 */
	List<Comment> selectComments(Integer articleId);

	
	/**
	 * 
	 * @Title: updateArticle 
	 * @Description: 增加文章的评论数量
	 * @param articleId
	 * @return
	 * @return: int
	 */
	int updateArticle(Integer articleId);

}
