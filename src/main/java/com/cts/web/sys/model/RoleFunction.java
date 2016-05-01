package com.cts.web.sys.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "role_function")
public class RoleFunction implements java.io.Serializable {

	private RoleFunctionId id;

	public RoleFunction() {
	}

	public RoleFunction(RoleFunctionId id) {
		this.id = id;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "roleId", column = @Column(name = "ROLE_ID", nullable = false, length = 50)),
			@AttributeOverride(name = "funcId", column = @Column(name = "FUNC_ID", nullable = false, length = 50)) })
	public RoleFunctionId getId() {
		return this.id;
	}

	public void setId(RoleFunctionId id) {
		this.id = id;
	}

}
