package com.cts.common.model;

import java.util.Collection;

public class TreeNode {
	
	private String id;
	private String text;
	private String url;
	private Collection<TreeNode> nodes;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Collection<TreeNode> getNodes() {
		return nodes;
	}

	public void setNode(Collection<TreeNode> nodes) {
		this.nodes = nodes;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "TreeNode [id=" + id + ", text=" + text + ", url=" + url
				+ ", nodes=" + nodes + "]";
	}
	
	
}
