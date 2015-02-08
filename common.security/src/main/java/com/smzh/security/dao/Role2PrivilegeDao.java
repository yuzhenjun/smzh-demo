package com.smzh.security.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Repository;

import com.smzh.common.BaseDao;
import com.smzh.security.entity.DefaultUser;
import com.smzh.security.entity.Role2Privilege;

/**
 * 角色与权限关系
 * @author zhenjun
 *
 */

@Repository
public class Role2PrivilegeDao extends BaseDao<Role2Privilege> implements IRole2PrivilegeDao {

	/**
	 * 删除角色上原有的权限，重新绑定权限
	 */
	public void bind(long roleId, List<Long> privilegeIds) {
		List<Role2Privilege> deletedRole2Privileges = new ArrayList<Role2Privilege>();
		
		Set<Long> privilegeToCreate = new HashSet<Long>(privilegeIds);
		List<Role2Privilege> old = this.findPrivilegesByRoleId(roleId);
		if(null != old && old.size() > 0){
			for(Role2Privilege r2p : old){
				if(privilegeToCreate.contains(r2p.getPrivilegeId())){
					privilegeToCreate.remove(r2p.getPrivilegeId());
				}else{
					deletedRole2Privileges.add(r2p);
				}
			}
		}
		this.getHibernateTemplate().deleteAll(deletedRole2Privileges);
		DefaultUser user = (DefaultUser) SecurityUtils.getSubject().getSession().getAttribute("user");
		for(Long privilegeId : privilegeToCreate){
			Role2Privilege r2p = new Role2Privilege();
			r2p.setPrivilegeId(privilegeId);
			r2p.setRoleId(roleId);
			r2p.setCreateTime(new Date());
			r2p.setCreator(user.getId());
			this.save(r2p);
		}
	}

	public void deleteByRoleId(long roleId) {
		String hql = "delete from Role2Privilege r2p where r2p.roleId = ?";
		this.execute(hql, roleId);
	}

	public void deleteByRoleIds(List<Long> roleIds) {
		String hql = "delete from Role2Privilege r2p where r2p.roleId in (:ids)";
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("ids", roleIds);
		this.executeByParams(hql, params);
	}

	public List<Role2Privilege> findPrivilegesByRoleId(long roleId) {
		String hql = "from Role2Privilege r2p where r2p.roleId = ?";
		return this.find(hql, roleId);
	}

	

	

}
