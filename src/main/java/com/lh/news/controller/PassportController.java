package com.lh.news.controller;

import javax.annotation.Resource;
import javax.naming.spi.DirStateFactory.Result;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lh.news.domain.Users;
import com.lh.news.service.UserService;
import com.lh.news.util.CMSException;
import com.lh.news.util.CMSResult;

@Controller
@RequestMapping("passport")
public class PassportController {
	
	@Resource
	private UserService userService;
	
	/**
	 * 
	 * @Title: reg 
	 * @Description: 去注册
	 * @return
	 * @return: String
	 */
	@GetMapping("reg")
	public String reg() {
		return "passport/reg";
	}
	
	/**
	 * 
	 * @Title: reg 
	 * @Description:执行注册
	 * @param user
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@PostMapping("reg")
	public CMSResult<Users> reg(Users user,Model model) {
		CMSResult<Users> result = new CMSResult<>();
		try {
			userService.insertUser(user);
			result.setCode(200);//状态码
			result.setMsg("恭喜注册成功，请登录");//错误消息
		}catch (CMSException e) {//捕获自定义异常
			e.printStackTrace();
			result.setCode(300);//错误码
			result.setMsg(e.message);//错误消息
		}
		catch (Exception e) {
			e.printStackTrace();
			result.setCode(400);//错误码
			result.setMsg("未知错误，请联系管理员");//错误消息
		}
		return result;
	}
	
	/**
	 * 
	 * @Title: login 
	 * @Description: 去登录页面
	 * @return
	 * @return: String
	 */
	@GetMapping("login")
	public String login() {
		return "passport/login";
	}
	
	/**
	 * 
	 * @Title: login 
	 * @Description: 执行登陆
	 * @param user
	 * @return
	 * @return: CMSResult<Users>
	 */
	@ResponseBody
	@PostMapping("login")
	public CMSResult<Users> login(Users user,HttpSession session){
		CMSResult<Users> result = new CMSResult<Users>();
		try {
			Users u = userService.login(user);
			result.setCode(200);
			result.setMsg("登陆成功");
			//result.setData(u);
			//登陆成功用户信息存session
			session.setAttribute("user", u);
		} catch (CMSException e) {//捕获自定义异常
			e.printStackTrace();
			result.setCode(300);//错误码
			result.setMsg(e.message);//错误消息
		}
		catch (Exception e) {
			e.printStackTrace();
			result.setCode(400);//错误码
			result.setMsg("未知错误，请联系管理员");//错误消息
		}
		return result;
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		//注销后刷新首页
		return "redirect:/";
	}
}
