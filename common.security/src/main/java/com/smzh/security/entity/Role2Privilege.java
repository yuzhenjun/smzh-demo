package com.smzh.security.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 角色与权限关系
 * @author zhenjun
 *
 */
@Entity
@Table(name="security_role_to_privilege")
public class Role2Privilege implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5210295491803470863L;

	/**
	 * 主键ID
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private long id;
	
	/**
	 * 角色ID
	 */
	@Column(name="role_id")
	private long roleId;
	
	/**
	 * 权限ID
	 */
	@Column(name="privilege_id")
	private long privilegeId;
	
	/**
	 * 创建者
	 */
	@Column
	private long creator;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time")
	private Date createTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public long getPrivilegeId() {
		return privilegeId;
	}

	public void setPrivilegeId(long privilegeId) {
		this.privilegeId = privilegeId;
	}

	public long getCreator() {
		return creator;
	}

	public void setCreator(long creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
	
	
	
}
