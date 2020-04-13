package com.lh.news.vo;

/**
 * 
 * @ClassName: ArticleVO 
 * @Description: 最新图片集
 * @author: Administrator
 * @date: 2020年4月13日 上午10:01:24
 */
public class ArticleVO{

	private String url;//图片集的单个图片地址
	private String descr;//图片描述
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	
}
