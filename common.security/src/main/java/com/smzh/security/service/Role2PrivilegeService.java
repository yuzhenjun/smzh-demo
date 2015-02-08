package com.smzh.security.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smzh.security.dao.IPrivilegeDao;
import com.smzh.security.dao.IRole2PrivilegeDao;
import com.smzh.security.entity.DefaultPrivilege;
import com.smzh.security.entity.Role2Privilege;

@Service("role2PrivilegeService")
public class Role2PrivilegeService implements IRole2PrivilegeService{

	@Resource
	private IRole2PrivilegeDao role2PrivilegeDao;
	
	@Resource
	private IPrivilegeDao  privilegeDao;
	public void bind(long roleId, List<Long> privilegeIds) {
		if(privilegeIds!=null&&privilegeIds.size()>0){
			List<DefaultPrivilege> list=privilegeDao.findByIds(privilegeIds);
			Set<Long> privilegeToCreate = new HashSet<Long>(privilegeIds);
			if(list!=null&&list.size()>0){
				for(DefaultPrivilege dp:list){
					if(dp.getParentId()!=-1)
						privilegeToCreate.add(dp.getParentId());
				}
			}
			List<Long> pids=new ArrayList<Long>(privilegeToCreate);
			role2PrivilegeDao.bind(roleId,pids);
		}
	   
		
		
	}

	public void deleteByRoleId(long roleId) {
		role2PrivilegeDao.deleteByRoleId(roleId);
		
	}

	public void deleteByRoleIds(List<Long> roleIds) {
		role2PrivilegeDao.deleteByRoleIds(roleIds);
		
	}

	public List<Role2Privilege> findPrivilegesByRoleId(long roleId) {
		return role2PrivilegeDao.findPrivilegesByRoleId(roleId);
	}

}
