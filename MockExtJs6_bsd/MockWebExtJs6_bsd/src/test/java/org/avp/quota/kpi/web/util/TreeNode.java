package org.avp.quota.kpi.web.util;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
	private String task;
	private String user;
	private String iconCls;
	private List<TreeNode> children;

	public TreeNode() {}
	public TreeNode(String task) {
		this.task = task;
	}
	public String getTask() {return task;}
	public void setTask(String task) {this.task = task;}
	public String getUser() {return user;}
	public void setUser(String user) {this.user = user;}
	public String getIconCls() {return iconCls;}
	public void setIconCls(String iconCls) {this.iconCls = iconCls;}
	public List<TreeNode> getChildren() {
		if(children == null)
			children = new ArrayList<TreeNode>();
		return children;
	}
	public void setChildren(List<TreeNode> children) {this.children = children;}
	
}
