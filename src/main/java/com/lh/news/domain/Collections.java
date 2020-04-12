package com.lh.news.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @ClassName: Collection 
 * @Description: 收藏
 * @author: Administrator
 * @date: 2020年4月12日 下午12:18:31
 */
public class Collections implements Serializable{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String text;//文章标题
	
	private String url;//文章的url
	
	private Integer userId;
	
	private Date created;//收藏时间
	
	private Users user;//收藏人

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Collections(Integer id, String text, String url, Integer userId, Date created, Users user) {
		super();
		this.id = id;
		this.text = text;
		this.url = url;
		this.userId = userId;
		this.created = created;
		this.user = user;
	}

	public Collections() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Collection [id=" + id + ", text=" + text + ", url=" + url + ", userId=" + userId + ", created="
				+ created + ", user=" + user + "]";
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Collections other = (Collections) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

}
