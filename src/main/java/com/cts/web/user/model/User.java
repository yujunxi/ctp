package com.cts.web.user.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "User")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	public User() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "name", length = 32)
	private String name;

	@Column(name = "age")
	private Integer age;

	@Column(name = "nice_name", length = 32)
	private String nice_name;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_info_id",referencedColumnName = "userInfoId", unique = true)
	private UserInfo userInfo;

	public final Long getId() {
		return id;
	}

	public final void setId(Long id) {
		this.id = id;
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final Integer getAge() {
		return age;
	}

	public final void setAge(Integer age) {
		this.age = age;
	}

	public final String getNice_name() {
		return nice_name;
	}

	public final void setNice_name(String nice_name) {
		this.nice_name = nice_name;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age
				+ ", nice_name=" + nice_name + ", userInfo=" + userInfo + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((nice_name == null) ? 0 : nice_name.hashCode());
		result = prime * result
				+ ((userInfo == null) ? 0 : userInfo.hashCode());
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
		User other = (User) obj;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (nice_name == null) {
			if (other.nice_name != null)
				return false;
		} else if (!nice_name.equals(other.nice_name))
			return false;
		if (userInfo == null) {
			if (other.userInfo != null)
				return false;
		} else if (!userInfo.equals(other.userInfo))
			return false;
		return true;
	}
	
	

}