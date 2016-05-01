package com.cts.web.goods.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity(name = "Goods")
public class Goods implements Serializable{
	
	private static final long serialVersionUID = -6926651442934977680L;
	
	private Integer goodsCode;
	private String goodsName;
	private String introduce;
	private int	num;
	private double price;
	private String imgName;
	private String seller;
	private String goodsType;
	private String address;
	private String tel;
	private String changenType;
	private int status;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createTime;
	private int audit;
	private int readNum;
	
	public Goods() {
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "GOODS_CODE", nullable = false)
	public Integer getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(Integer goodsCode) {
		this.goodsCode = goodsCode;
	}
	
	@Column(name = "GOODS_NAME", nullable = false)
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	@Column(name = "INTRODUCE", nullable = true)
	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
	@Column(name = "NUM", nullable = false)
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
	@Column(name = "PRICE", nullable = false)
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Column(name = "IMG_NAME", nullable = true,length = 250)
	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	@Column(name = "SELLER", nullable = true)
	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	@Column(name = "STATUS", nullable = true)
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	@Column(name = "GOODS_TYPE", nullable = true)
	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME", length = 19)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "ADDRESS", nullable = true)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name = "TEL", nullable = true)
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "AUDIT", nullable = false)
	public int getAudit() {
		return audit;
	}

	public void setAudit(int audit) {
		this.audit = audit;
	}
	
	@Column(name = "CHANGETYPE", nullable = false)
	public String getChangenType() {
		return changenType;
	}

	public void setChangenType(String changenType) {
		this.changenType = changenType;
	}
	
	@Column(name = "READNUM", nullable = true)
	public int getReadNum() {
		return readNum;
	}

	public void setReadNum(int readNum) {
		this.readNum = readNum;
	}

	
}
