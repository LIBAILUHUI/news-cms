package com.lh.news.domain;

import java.io.Serializable;

/**
 * 
 * @ClassName: Channels 
 * @Description: 栏目实体类
 * @author: Administrator
 * @date: 2020年3月31日 下午6:17:38
 */
public class Channels implements Serializable{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;

	//编号
	private Integer id;
	//栏目名称
	private String name;
	//描述
	private String description;
	//栏目图标
	private String icon;
	//排序
	private Integer sorted;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getSorted() {
		return sorted;
	}
	public void setSorted(Integer sorted) {
		this.sorted = sorted;
	}
	public Channels(Integer id, String name, String description, String icon, Integer sorted) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.icon = icon;
		this.sorted = sorted;
	}
	public Channels() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Channels [id=" + id + ", name=" + name + ", description=" + description + ", icon=" + icon + ", sorted="
				+ sorted + "]";
	}
	
	
}
