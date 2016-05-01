package com.cts.web.sys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "function")
public class Function {

	private String funcCode;
	private String funcName;
	private String url;
	private String parentCode;

	@Id
	@Column(name = "FUNC_CODE",length=200,nullable=false)
	public String getFuncCode() {
		return funcCode;
	}

	public void setFuncCode(String funcCode) {
		this.funcCode = funcCode;
	}

	@Column(name = "FUNC_NAME",length=200,nullable=true)
	public String getFuncName() {
		return funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	@Column(name = "URL",length=200,nullable=true)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "PARENT_CODE",length=200,nullable=true)
	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	@Override
	public String toString() {
		return "Function [funcCode=" + funcCode + ", funcName=" + funcName
				+ ", url=" + url + ", parentCode=" + parentCode + "]";
	}

	
}
