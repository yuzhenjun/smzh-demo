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
 * 权限信息实体类
 * @author zhenjun
 *
 */
@Entity
@Table(name="security_default_privilege")
public class DefaultPrivilege implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3841555315564605333L;

	/**
	 * 主键ID
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private long id;
	
	/**
	 * 权限名称
	 */
	@Column
	private String name;
	
	/**
	 * 权限标识
	 */
	@Column
	private String code;
	
	/**
	 * 权限创建时间
	 */
	@Column(name="create_time")
	private Date createTime;
	
	/**
	 * 权限最后修改时间
	 */
	@Column(name="last_modify_time")
	private Date lastModifyTime;
	
	/**
	 * 父权限ID
	 */
	@Column(name="parent_id")
	private long parentId;

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

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	
	
}
