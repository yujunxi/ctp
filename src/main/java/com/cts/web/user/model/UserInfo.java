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
	private User user;
	
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
	
	@OneToOne(mappedBy = "userInfo")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "UserInfo [userInfoId=" + userInfoId + ", realName=" + realName
				+ ", tel=" + tel + ", user=" + user + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((realName == null) ? 0 : realName.hashCode());
		result = prime * result + ((tel == null) ? 0 : tel.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result
				+ ((userInfoId == null) ? 0 : userInfoId.hashCode());
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
		UserInfo other = (UserInfo) obj;
		if (realName == null) {
			if (other.realName != null)
				return false;
		} else if (!realName.equals(other.realName))
			return false;
		if (tel == null) {
			if (other.tel != null)
				return false;
		} else if (!tel.equals(other.tel))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (userInfoId == null) {
			if (other.userInfoId != null)
				return false;
		} else if (!userInfoId.equals(other.userInfoId))
			return false;
		return true;
	}
	
	

}