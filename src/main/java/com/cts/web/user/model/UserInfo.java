package com.cts.web.user.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "UserInfo")
public class UserInfo implements Serializable {

	private static final Long serialVersionUID = 4394286525597767995L;

	private Integer userInfoCode;
	private String username;
	private String realname;
	private String address;
	private String email;
	private String tel;
	private User user;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USERINFO_CODE", nullable = false)
	public Integer getUserInfoCode() {
		return userInfoCode;
	}

	public void setUserInfoCode(Integer userInfoCode) {
		this.userInfoCode = userInfoCode;
	}

	@Column(name = "REALNAME", nullable = true, length = 20)
	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}
	
	@Column(name = "USERNAME", nullable = true, length = 20)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "TEL", nullable = true, length = 20)
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "ADDRESS", nullable = true, length = 200)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "EMAIL", nullable = true, length = 50)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@OneToOne(mappedBy = "userInfo")
	@JsonIgnore
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}