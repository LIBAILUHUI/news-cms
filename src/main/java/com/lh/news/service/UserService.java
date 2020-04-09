package com.lh.news.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.lh.news.domain.Users;

public interface UserService {

	PageInfo<Users> selectUsers(Users user,Integer pageNum,Integer pageSize);
	
	boolean updateUser(Users user);
	
	boolean insertUser(Users user);
	
	Users selectByName(String name);
	
	Users login(Users user);
}
