package com.lh.news.domain;

import java.io.Serializable;

/**
 * 
 * @ClassName: Settings 
 * @Description: 配置
 * @author: Administrator
 * @date: 2020年3月31日 下午6:34:22
 */
public class Settings implements Serializable{

	
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;
	//编号
	private Integer id;
	//网站域名
	private String siteDomain;
	//网站名称
	private String siteName;
	//文章列表页显示数量
	private Integer articleListSize;
	//首页幻灯片数量
	private Integer slideSize;
	//管理员账号
	private String adminUsername;
	//管理员密码
	private String adminPassword;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSiteDomain() {
		return siteDomain;
	}
	public void setSiteDomain(String siteDomain) {
		this.siteDomain = siteDomain;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public Integer getArticleListSize() {
		return articleListSize;
	}
	public void setArticleListSize(Integer articleListSize) {
		this.articleListSize = articleListSize;
	}
	public Integer getSlideSize() {
		return slideSize;
	}
	public void setSlideSize(Integer slideSize) {
		this.slideSize = slideSize;
	}
	public String getAdminUsername() {
		return adminUsername;
	}
	public void setAdminUsername(String adminUsername) {
		this.adminUsername = adminUsername;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	public Settings(Integer id, String siteDomain, String siteName, Integer articleListSize, Integer slideSize,
			String adminUsername, String adminPassword) {
		super();
		this.id = id;
		this.siteDomain = siteDomain;
		this.siteName = siteName;
		this.articleListSize = articleListSize;
		this.slideSize = slideSize;
		this.adminUsername = adminUsername;
		this.adminPassword = adminPassword;
	}
	public Settings() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Settings [id=" + id + ", siteDomain=" + siteDomain + ", siteName=" + siteName + ", articleListSize="
				+ articleListSize + ", slideSize=" + slideSize + ", adminUsername=" + adminUsername + ", adminPassword="
				+ adminPassword + "]";
	}
	
	
	
}
