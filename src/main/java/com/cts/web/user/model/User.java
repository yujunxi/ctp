package com.cts.web.user.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

@Entity(name = "User")
public class User implements Serializable {

	private static final long serialVersionUID = 167908325591693579L;
	
	//�û�ע��ID
	private String id;
	//��¼�˺�
	private String account;
	//��¼����
	private String password;
	//�û���
	private String username;
	//ע������
	private String email;
	//����ʱ��
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createTime;
	//�˺�״̬:0 ����  1 ��ʹ��
	private Integer status;
	//�û�������Ϣ
	private UserInfo userInfo;

	public User() {
		super();
	}

	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")
	@GeneratedValue(generator="systemUUID")
	@Column(name = "ID", insertable = true, updatable = true, nullable = false)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "ACCOUNT", nullable = false ,length = 32)
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Column(name = "PASSWORD", nullable = false ,length = 32)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "USERNAME", length = 32)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "EMAIL", length = 32)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME", length = 19)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "STATUS",nullable = false)
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "USERINFO_CODE", referencedColumnName = "USERINFO_CODE", unique = true)
	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	

}