package com.cts.web.user.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity(name = "UserInfo")
public class UserInfo implements Serializable {

	private static final long serialVersionUID = 4394286525597767995L;

	private Long userInfoId;
	private String realName;
	private String address;
	private String email;
	private String tel;
	private User user;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userInfoId", nullable = false)
	public Long getUserInfoId() {
		return userInfoId;
	}

	public void setUserInfoId(Long id) {
		this.userInfoId = id;
	}

	@Column(name = "realname", nullable = true, length = 20)
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Column(name = "tel", nullable = true, length = 20)
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "address", nullable = true, length = 200)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "email", nullable = true, length = 50)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@OneToOne(mappedBy = "userInfo")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}