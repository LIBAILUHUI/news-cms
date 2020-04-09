package com.lh.news.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @ClassName: Users 
 * @Description: 用户
 * @author: Administrator
 * @date: 2020年3月31日 上午10:43:03
 */
public class Users implements Serializable{
	
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;
	//编号
	private Integer id;
	//用户名
	private String username;
	//密码
	private String password;
	//昵称
	private String nickname;
	//性别:0女1男
	private Integer gender;
	//生日
	private Date birthday;
	//是否禁用:0正常1禁用
	private Integer locked;
	//注册时间
	private Date created;
	//最后修改时间
	private Date updated;
	//角色0普通用户1管理员
	private String role;
	
	private String repassword;
	
	
	
	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Integer getLocked() {
		return locked;
	}
	public void setLocked(Integer locked) {
		this.locked = locked;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Users(Integer id, String username, String password, String nickname, Integer gender, Date birthday,
			Integer locked, Date created, Date updated, String role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.gender = gender;
		this.birthday = birthday;
		this.locked = locked;
		this.created = created;
		this.updated = updated;
		this.role = role;
	}
	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", password=" + password + ", nickname=" + nickname
				+ ", gender=" + gender + ", birthday=" + birthday + ", locked=" + locked + ", created=" + created
				+ ", updated=" + updated + ", role=" + role + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Users other = (Users) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	
	
}
