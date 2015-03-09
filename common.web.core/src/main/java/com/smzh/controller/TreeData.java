package com.smzh.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smzh.beans.NodeState;
import com.smzh.beans.TreeNode;

@Controller
@RequestMapping("tree")
public class TreeData {

	 @RequestMapping(method = RequestMethod.GET)  
	public @ResponseBody List<TreeNode> getTreeNode(){
		List<TreeNode> tree=new ArrayList<TreeNode>();
		int id=0;
		NodeState os=new NodeState();
		os.setSelected(NodeState.STATE_SELECTED);
		os.setOpened(NodeState.STATE_OPEN);
		NodeState uc=new NodeState();
		uc.setSelected(NodeState.STATE_UNSELECTED);
		uc.setOpened(NodeState.STATE_CLOSED);
		for(int i=0;i<10;i++){
			TreeNode parent=new TreeNode();
			parent.setId(id++);
			parent.setText("父节点"+i);
			if(i%2==0){
				parent.setState(uc);	
			}else{
				parent.setState(os);	
			}
			tree.add(parent);
		}
		for(TreeNode parent:tree){
			List<TreeNode> children=new ArrayList<TreeNode>();
			parent.setChildren(children);
			for(int i=0;i<4;i++){
				TreeNode node=new TreeNode();
				node.setId(id++);
				node.setText("子节点"+i);
				children.add(node);
			}
		}
		return tree;
	}
}
