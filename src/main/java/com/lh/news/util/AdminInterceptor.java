package com.lh.news.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 
 * @ClassName: UserInterceptor 
 * @Description: 个人中心拦截器
 * @author: Administrator
 * @date: 2020年4月10日 上午9:56:26
 */
public class AdminInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//不拦截规则，如果用户已登录不拦截
		//参数false:如果有session返回session,没有session返回null
		HttpSession session = request.getSession(false);
		if(null!=session) {  //如果存在session，则从session获取登录的user对象
			Object user = session.getAttribute("admin");
			if(null!=user) {   //如果对象不为空
				return true;  //放行
			}
		}
		//没有登陆，跳转到登录页面
		request.setAttribute("msg","请登录后再试");
		request.getRequestDispatcher("/WEB-INF/view/passport/login_admin.jsp").forward(request, response);
		//没登录拦截
		return false;
	}
}
