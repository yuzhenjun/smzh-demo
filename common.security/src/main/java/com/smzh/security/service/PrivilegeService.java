package com.smzh.security.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.smzh.security.bean.NodeState;
import com.smzh.security.bean.TreeNode;
import com.smzh.security.dao.IPrivilegeDao;
import com.smzh.security.entity.DefaultPrivilege;


@Service("privilegeService")
public class PrivilegeService implements IPrivilegeService{

	@Resource
	private IPrivilegeDao privilegeDao;
	
	public DefaultPrivilege findByCode(String code) {
		if(StringUtils.isNotBlank(code)){
			return privilegeDao.findByCode(code);
		}
		return null;
		
	}

	public List<DefaultPrivilege> findByUserId(long userId) {
		return privilegeDao.findByUserId(userId);
	}

	public List<DefaultPrivilege> findByRoleId(long roleId) {
		return privilegeDao.findByRoleId(roleId);
	}

	public List<DefaultPrivilege> findByParentId(long parentId) {
		return privilegeDao.findByParentId(parentId);
	}

	public Set<String> findPermissionsByRoleId(long roleId) {
		List<DefaultPrivilege> dps=findByRoleId(roleId);
		Set<String> permissions=new LinkedHashSet<String>();
		if(dps!=null&&dps.size()>0){
			for(DefaultPrivilege dp:dps)
				permissions.add(dp.getCode());
		}
		return permissions;
	}

	public Set<String> findPermissionsByUserId(long userId) {
		List<DefaultPrivilege> dps=findByUserId(userId);
		Set<String> permissions=new LinkedHashSet<String>();
		if(dps!=null&&dps.size()>0){
			for(DefaultPrivilege dp:dps)
				permissions.add(dp.getCode());
		}
		return permissions;
	}

	public Map<String, NodeState> findTreeByRoleId(long roleId,long parentId) {
		Map<String, NodeState> map=new HashMap<String, NodeState>();
		List<DefaultPrivilege> dps=privilegeDao.findByRoleIdAndPid(roleId, parentId);
		if(dps!=null&&dps.size()>0){
			for(DefaultPrivilege dp:dps){
				String id=String.valueOf(dp.getId());
				List<DefaultPrivilege>childList=privilegeDao.findByRoleIdAndPid(roleId, dp.getId());
				//如果不为父节点且该节点无子节点则选中
				if(dp.getParentId()!=-1&&!(childList!=null&&childList.size()>0)){
					NodeState state=new NodeState();
					state.setSelected(NodeState.STATE_SELECTED);
					map.put(id,state);
				}
				Map<String, NodeState> temp=findTreeByRoleId(roleId,dp.getId());
				if(temp!=null&&temp.size()>0){
					map.putAll(temp);
				}
			}
			return map;
		}
		return null;
	}

	public List<TreeNode> findAllTree(long parentId,Map<String, NodeState> treeMap) {
		List<TreeNode> tree=new ArrayList<TreeNode>();
		List<DefaultPrivilege> dps=privilegeDao.findByParentId(parentId);
		if(dps!=null&&dps.size()>0){
			for(DefaultPrivilege dp:dps){
				TreeNode parent=new TreeNode();
				parent.setId(String.valueOf(dp.getId()));
				parent.setText(dp.getName());
				if(treeMap!=null)
					parent.setState(treeMap.get(String.valueOf(dp.getId())));
				parent.setChildren(findAllTree(dp.getId(),treeMap));
				tree.add(parent);
			}
			return tree;
		}
		return null;
	}

	public List<TreeNode> findTree(long roleId, long parentId) {
		Map<String, NodeState> treeMap=findTreeByRoleId(roleId,parentId);
		return findAllTree(parentId,treeMap);
	}
}
