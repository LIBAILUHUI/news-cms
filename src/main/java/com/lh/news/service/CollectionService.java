package com.lh.news.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.lh.news.domain.Collections;

public interface CollectionService {

	/**
	 * 
	 * @Title: insertCollection 
	 * @Description: 增加
	 * @param collection
	 * @return
	 * @return: boolean
	 */
	boolean insertCollection(Collections collection);

	/**
	 * 
	 * @Title: selectCollections 
	 * @Description: 查詢 
	 * @param collection
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @return: PageInfo<Collection>
	 */
	PageInfo<Collections> selectCollections(Collections collection,Integer pageNum,Integer pageSize);



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
