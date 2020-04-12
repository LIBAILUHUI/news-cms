package com.lh.news.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lh.common.utils.StringUtil;
import com.lh.news.dao.CollectionDao;
import com.lh.news.domain.Collections;
import com.lh.news.service.CollectionService;
import com.lh.news.util.CMSException;

@Service
public class CollectionServiceImpl implements CollectionService {

	@Resource
	private CollectionDao collectionDao;
	
	/**
	 * 增加收藏
	 */
	@Override
	public boolean insertCollection(Collections collection) {
		if(!StringUtil.isHttpUrl(collection.getUrl())) {
			throw new CMSException("url不合法");
		}
		return collectionDao.insertCollection(collection)>0;
	}
	
	
	/**
	 * 查询收藏
	 */
	@Override
	public PageInfo<Collections> selectCollections(Collections collection, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Collections> clist = collectionDao.selectCollections(collection);
		return new PageInfo<Collections>(clist);
	}


	@Override
	public int selectCount(String text, Integer userId) {
		return collectionDao.selectCount(text, userId);
	}


	@Override
	public int deleteCollection(Integer id) {
		return collectionDao.deleteCollection(id);
	}

}
