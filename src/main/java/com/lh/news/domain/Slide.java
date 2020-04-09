package com.lh.news.domain;

import java.io.Serializable;

/**
 * @ClassName: Slide 
 * @Description: 广告
 * @author: Administrator
 * @date: 2020年3月31日 下午6:25:16
 */
public class Slide implements Serializable{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;
	
	//编号
	private Integer id;
	//广告文字说明
	private String title;
	//广告的图片地址
	private String picture;
	//点击广告进入的广告详情,跳转地址
	private String url;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Slide(Integer id, String title, String picture, String url) {
		super();
		this.id = id;
		this.title = title;
		this.picture = picture;
		this.url = url;
	}
	public Slide() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Slide [id=" + id + ", title=" + title + ", picture=" + picture + ", url=" + url + "]";
	}
	
}
