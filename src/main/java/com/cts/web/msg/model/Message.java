package com.cts.web.msg.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity(name = "message")
public class Message {
	
	//留言ID
	private Integer id;
	//留言内容
	private String content;
	//发布时间
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createTime;
	//发布人
	private String author;
	//商品编号
	private Integer goodsCode;
	//商品销售者ID
	private String uid;
	private String reAuthor;
	
	public Message(){
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "CONTENT", nullable = false)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME", length = 19)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "AUTHOR", nullable = false)
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	@Column(name = "GOODS_CODE", nullable = false)
	public Integer getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(Integer goodsCode) {
		this.goodsCode = goodsCode;
	}

	@Column(name = "U_ID", nullable = true)
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	
}
