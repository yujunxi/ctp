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

	@Override
	public String toString() {
		return "User [id=" + id + ", user_name=" + name + ", age=" + age
				+ ", nice_name=" + nice_name + "]";
	}

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

}