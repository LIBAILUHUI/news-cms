package com.lh.news.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lh.common.utils.StringUtil;
import com.lh.news.dao.UserDao;
import com.lh.news.domain.Users;
import com.lh.news.service.UserService;
import com.lh.news.util.CMSException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	
	@Override
	public PageInfo<Users> selectUsers(Users user,Integer pageNum,Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Users> ulist = userDao.selectUsers(user);
		PageInfo<Users> info = new PageInfo<Users>(ulist);
		return info;
	}

	@Override
	public boolean updateUser(Users user) {
		try {
			userDao.updateUser(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("更新用户状态失败");
		}
	}

	@Override
	public boolean insertUser(Users user) {
		//使用工具类注册业务逻辑
		//用户名不能为空
		if(!StringUtil.hasText(user.getUsername())) {
			throw new CMSException("用户名不能为空");
		}
		//用户名的长度
		if(!(user.getUsername().length()>=2 && user.getUsername().length()<=6)) {
			throw new CMSException("用户名长度2-6之间");
		}
		//密码
		if(!StringUtil.hasText(user.getPassword())) {
			throw new CMSException("密码不能为空");
		}
		//密码长度
		if(!(user.getPassword().length()>=6 && user.getPassword().length()<=10)) {
			throw new CMSException("用户名密码6-10之间");
		}
		//确认密码不能为空
		if(!StringUtil.hasText(user.getRepassword())) {
			throw new CMSException("确认密码不能为空");
		}
		//两次密码是否一致
		if(!user.getRepassword().equals(user.getPassword())) {
			throw new CMSException("两次密码不一致");
		}
		
		try {
			userDao.insertUser(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("添加用户失败");
		}
	}

	@Override
	public Users selectByName(String name) {
		return userDao.selectByName(name);
	}

	@Override
	public Users login(Users user) {
		//用户名不能为空
		if(!StringUtil.hasText(user.getUsername())) {
			throw new CMSException("用户名不能为空");
		}
		//密码
		if(!StringUtil.hasText(user.getPassword())) {
			throw new CMSException("密码不能为空");
		}
		//比较密码
		//user是从前台传过来的user信息，根据user的姓名查出数据库中的u，如果u为空数据库中没有这个用户名
		Users u = this.selectByName(user.getUsername());
		if(null==u) {
			throw new CMSException("用户名不正确");
		}
		
		if(!u.getPassword().equals(user.getPassword())) {
			throw new CMSException("密码不正确");
		}
		return u;
	}

}
