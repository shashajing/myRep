package com.shashajing.benison.entity;

import java.util.Date;


/**
 * 
 * 
 * @author yanghanjing
 */
public class News {
	
	private int newsId;
	private int type;
	private String title;
	private String content;
	private String url;
	private Date createTime;
	private Date modifyTime;
	private Date lastViewTime;
	private int viewCount;
	
	public int getNewsId() {
		return newsId;
	}
	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public Date getLastViewTime() {
		return lastViewTime;
	}
	public void setLastViewTime(Date lastViewTime) {
		this.lastViewTime = lastViewTime;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
}