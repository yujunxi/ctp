package com.cts.web.sys.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name = "sys_user_role")
public class SysUserRole implements java.io.Serializable {

	private SysUserRoleId id;

	public SysUserRole() {
	}

	public SysUserRole(SysUserRoleId id) {
		this.id = id;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "userId", column = @Column(name = "USER_ID", nullable = false, length = 50)),
			@AttributeOverride(name = "rolecode", column = @Column(name = "ROLECODE", nullable = false, length = 50)) })
	public SysUserRoleId getId() {
		return this.id;
	}

	public void setId(SysUserRoleId id) {
		this.id = id;
	}

}
