package com.smzh.security.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 角色信息
 * @author zhenjun
 *
 */
@Entity
@Table(name="security_role")
public class Role implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2921473946493719453L;

	
	/**
	 * 主键ID
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private long id;
	
	/**
	 * 角色名称
	 */
	@Column
	private String name;
	
	/**
	 * 角色标识
	 */
	@Column
	private String code;
	
	/**
	 * 角色描述
	 */
	@Column
	private String decription;
	
	/**
	 * 角色状态
	 */
	@Column
	private int status;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time")
	private Date createTime;
	
	/**
	 * 最后修改时间
	 */
	@Column(name="last_modify_time")
	private Date lastModifyTime;
	
	/**
	 * 角色拥有的权限
	 */
	@Transient
	private Set<DefaultPrivilege> privileges;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDecription() {
		return decription;
	}

	public void setDecription(String decription) {
		this.decription = decription;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public Set<DefaultPrivilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set<DefaultPrivilege> privileges) {
		this.privileges = privileges;
	}
	
	
	
}
