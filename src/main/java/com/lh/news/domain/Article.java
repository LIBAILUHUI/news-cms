package com.lh.news.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * 
 * @ClassName: Article 
 * @Description: 文章实体类
 * @author: Administrator
 * @date: 2020年3月31日 下午5:48:09
 */
@Document(indexName = "cms",type = "article")
public class Article implements Serializable{
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;
	//编号
	@Id
	private Integer id;
	//标题
	private String title;
	//文章内容
	private String content;
	//标题图片
	private String picture;
	//所属栏目
	private Integer channelId;
	//所属分类
	private Integer categoryId;
	//文章发布人
	private Integer userId;
	//点击量
	private Integer hits;
	//是否热门文章 1:热门 0:一般文章
	private Integer hot;
	//文章审核状态 0:待审 1:审核通过 -1:审核未通过
	private Integer status;
	//删除状态 0:正常 1:逻辑删除
	private Integer deleted;
	//文章发布时间
	private Date created;
	//文章修改时间
	private Date updated;
	//文章摘要
	private String summary;
	//文章内容类型 0:html 1:json
	private Integer contentType;
	//文章关键词
	private String keywords;
	//文章来源
	private String original;
	//评论数量
	private Integer commentNum;
	//多对一，栏目
	private Channels channel;
	//多对一，分类
	private Categories category;
	//多对一，发布人
	private Users user;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	
	public Integer getChannelId() {
		return channelId;
	}
	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getHits() {
		return hits;
	}
	public void setHits(Integer hits) {
		this.hits = hits;
	}
	public Integer getHot() {
		return hot;
	}
	public void setHot(Integer hot) {
		this.hot = hot;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getDeleted() {
		return deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
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
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Integer getContentType() {
		return contentType;
	}
	public void setContentType(Integer contentType) {
		this.contentType = contentType;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getOriginal() {
		return original;
	}
	public void setOriginal(String original) {
		this.original = original;
	}
	public Integer getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}
	public Channels getChannel() {
		return channel;
	}
	public void setChannel(Channels channel) {
		this.channel = channel;
	}
	public Categories getCategory() {
		return category;
	}
	public void setCategory(Categories category) {
		this.category = category;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	
	public Article(Integer id, String title, String content, String picture, Integer channelId, Integer categoryId,
			Integer userId, Integer hits, Integer hot, Integer status, Integer deleted, Date created, Date updated,
			String summary, Integer contentType, String keywords, String original, Integer commentNum, Channels channel,
			Categories category, Users user) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.picture = picture;
		this.channelId = channelId;
		this.categoryId = categoryId;
		this.userId = userId;
		this.hits = hits;
		this.hot = hot;
		this.status = status;
		this.deleted = deleted;
		this.created = created;
		this.updated = updated;
		this.summary = summary;
		this.contentType = contentType;
		this.keywords = keywords;
		this.original = original;
		this.commentNum = commentNum;
		this.channel = channel;
		this.category = category;
		this.user = user;
	}
	public Article() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", content=" + content + ", picture=" + picture
				+ ", channelId=" + channelId + ", categoryId=" + categoryId + ", userId=" + userId + ", hits=" + hits
				+ ", hot=" + hot + ", status=" + status + ", deleted=" + deleted + ", created=" + created + ", updated="
				+ updated + ", summary=" + summary + ", contentType=" + contentType + ", keywords=" + keywords
				+ ", original=" + original + ", commentNum=" + commentNum + ", channel=" + channel + ", category="
				+ category + ", user=" + user + "]";
	}
	
	
}
