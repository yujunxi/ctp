package com.cts.web.sys.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cts.common.dao.impl.GenericDaoImpl;
import com.cts.common.model.TreeNode;
import com.cts.web.sys.dao.RoleDao;
import com.cts.web.sys.model.Function;
import com.cts.web.sys.model.Role;

@Repository(value = "roleDao")
public class RoleDaoImpl extends GenericDaoImpl<Role, Integer> implements RoleDao{
	
	public RoleDaoImpl(){
		super();
		
		setClazz(Role.class);
	}
	
	public List<TreeNode> getRole() {
		// TODO Auto-generated method stub

		List<TreeNode> tree = new ArrayList<TreeNode>();
		List<Role> role = this.findAll();
		for(Role r : role){
			TreeNode node = new TreeNode();
			node.setId(r.getId().toString());
			node.setText(r.getRoleName());
			tree.add(node);
		}
		return tree;
	}
	
}
