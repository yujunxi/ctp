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
	
	private Integer userInfoId;
	private String realName;
	private String tel;
//	private User user;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="userInfoId")
	public Integer getUserInfoId() {
		return userInfoId;
	}

	public void setUserInfoId(Integer id) {
		this.userInfoId = id;
	}
	
	@Column(name="realName")
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	@Column(name="tel")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
//	@OneToOne(mappedBy = "userInfo")
//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}


}