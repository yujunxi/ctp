package com.cts.web.goods.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

@Entity(name="Orders")
public class Orders implements Serializable{
	
	private static final long serialVersionUID = 1920573222750990816L;
	
	//������
	private String orderId;
	//�����û�
	private String owner;
	//��������
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createTime;
	//����״̬
	private Integer status;
	//����״̬
	private Integer sellStatus;
	//�������ʱ��
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date finishTime;
	
	private Integer goodsCode;
	//��Ʒ����
	private String goodsName;
	//��Ʒ����
	private Integer goodsNum;
	//��Ʒ�۸�
	private Double goodsPrice;
	//����������
	private String seller;
	
	private String goodsImg;
	
	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")
	@GeneratedValue(generator="systemUUID")
	@Column(name = "ID", insertable = true, updatable = true, nullable = false)
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	@Column(name ="OWNER" , nullable=false , length= 200)
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME", length = 19)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	//1������ 0�ѹر� 2�����
	@Column(name = "STATUS")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FINISH_TIME", length = 19)
	public Date getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	
	@Column(name = "GOODS_CODE")
	public Integer getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(Integer goodsCode) {
		this.goodsCode = goodsCode;
	}
	
	@Column(name = "GOODS_NAME",length=200)
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	@Column(name = "GOODS_NUM")
	public Integer getGoodsNum() {
		return goodsNum;
	}
	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}
	@Column(name = "GOODS_PRICE")
	public Double getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(Double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	@Column(name = "SELLER" , length = 200)
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	@Column(name = "GOODS_IMG" , length = 200)
	public String getGoodsImg() {
		return goodsImg;
	}
	public void setGoodsImg(String goodsImg) {
		this.goodsImg = goodsImg;
	}
	
	@Column(name = "SELL_STATUS")
	public Integer getSellStatus() {
		return sellStatus;
	}
	public void setSellStatus(Integer sellStatus) {
		this.sellStatus = sellStatus;
	}
	
	
	
}
