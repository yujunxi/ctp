package com.cts.web.sys.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cts.common.dao.impl.GenericDaoImpl;
import com.cts.common.model.TreeNode;
import com.cts.web.sys.dao.FunctionDao;
import com.cts.web.sys.model.Function;

@Repository("functionDao")
public class FunctionDaoImpl extends GenericDaoImpl<Function, String> implements FunctionDao{
	
	public FunctionDaoImpl() {
		  super();
	      setClazz(Function.class);
	}

	@SuppressWarnings("unchecked")
	public List<TreeNode> getFunction() {
		// TODO Auto-generated method stub
		String hql = "from com.cts.web.sys.model.Function where parentCode=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setString(0, "");
		List<Function> func = query.list();
		List<TreeNode> tree = new ArrayList<TreeNode>();
		
		for(Function f : func){
			TreeNode node = new TreeNode();
			node.setId(f.getFuncCode());
			node.setText(f.getFuncName());
			
			String hql2 = "from com.cts.web.sys.model.Function where parentCode=?";
			Query query2 = getCurrentSession().createQuery(hql2);
			query2.setString(0, f.getFuncCode());
			List<Function> func2 = query2.list();
			List<TreeNode> nodeList = new ArrayList<TreeNode>();
			for(Function f2 : func2){
				TreeNode node2 = new TreeNode();
				node2.setId(f2.getFuncCode());
				node2.setText(f2.getFuncName());
				nodeList.add(node2);
			}
			node.setNode(nodeList);
			tree.add(node);
		}
		return tree;
	}
	
}
