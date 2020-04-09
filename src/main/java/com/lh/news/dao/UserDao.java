package com.lh.news.dao;

import java.util.List;

import com.lh.news.domain.Users;

public interface UserDao {

	List<Users> selectUsers(Users user);
	
	int updateUser(Users user);
	
	/**
	 * 
	 * @Title: insertUser 
	 * @Description: 注册用户
	 * @param user
	 * @return
	 * @return: int
	 */
	int insertUser(Users user);
	
	/**
	 * 
	 * @Title: selectByName 
	 * @Description: 根据姓名查询用户
	 * @param name
	 * @return
	 * @return: Users
	 */
	Users selectByName(String name);
}
