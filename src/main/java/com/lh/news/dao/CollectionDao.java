package com.lh.news.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lh.news.domain.Collections;

public interface CollectionDao {

	/**
	 * 
	 * @Title: insertCollection 
	 * @Description: 收藏
	 * @param collection
	 * @return
	 * @return: int
	 */
	int insertCollection(Collections collection);
	
	/**
	 * 
	 * @Title: selectCollections 
	 * @Description: 查询
	 * @param collection
	 * @return
	 * @return: List<Collection>
	 */
	List<Collections> selectCollections(Collections collection);

	/**
	 * 
	 * @Title: selectCount 
	 * @Description: 查询注册用户是否收藏过text的文章
	 * @param text
	 * @param userId
	 * @return
	 * @return: int  1已收藏  0未收藏
	 */
	int selectCount(@Param("text") String text,@Param("userId") Integer userId);

	
	/**
	 * 
	 * @Title: deleteCollection 
	 * @Description: 根据id删除收藏
	 * @param id
	 * @return
	 * @return: int
	 */
	int deleteCollection(Integer id);
}
