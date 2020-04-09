package com.lh.news.domain;

import java.io.Serializable;

/**
 * 
 * @ClassName: Categories 
 * @Description: 栏目子分类
 * @author: Administrator
 * @date: 2020年3月31日 下午6:19:30
 */
public class Categories implements Serializable{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;

	//编号
	private Integer id;
	//子分类名称，就像体育下边还分了NBA，足球这种
	private String name;
	//所属栏目Id
	private Integer channelId;
	//多对一，一个栏目下有多个分类
	private Channels channel;

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

	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	public Channels getChannel() {
		return channel;
	}

	public void setChannel(Channels channel) {
		this.channel = channel;
	}

	public Categories(Integer id, String name, Integer channelId, Channels channel) {
		super();
		this.id = id;
		this.name = name;
		this.channelId = channelId;
		this.channel = channel;
	}

	public Categories() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Categories [id=" + id + ", name=" + name + ", channelId=" + channelId + ", channel=" + channel + "]";
	}
	
	
}
