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

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 用户信息实体类
 * @author zhenjun
 *
 */
@Entity
@Table(name="security_default_user")
public class DefaultUser implements User, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 532507579662662119L;
	
	

	/**
	 * 锁定状态
	 */
	private static final int STATUS_LOCKED = 1;

	/**
	 * 主键ID
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private long id;
	
	/**
	 * 用户名
	 */
	@Column
	private String name;
	
	/**
	 * 登录名
	 */
	@Column(name="login_name")
	private String loginName;
	
	/**
	 * 邮件
	 */
	@Column
	private String email;
	
	/**
	 * 电话号码
	 */
	@Column
	private String phone;
	
	/**
	 * 状态 0：正常 1：锁定
	 */
	@Column
	private int status;
	
	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name="create_time")
	private Date createTime;
	
	/**
	 * 最后修改时间
	 */
	@Column(name="last_modify_time")
	private Date lastModifyTime;
	
	/**
	 * 过期时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name="expiration_time")
	private Date expirationTime;
	
	/**
	 * 最后登录IP
	 */
	@Column(name="last_login_host")
	private String lastLoginHost;
	
	/**
	 * spCode暂时
	 */
	@Column(name="sp_code")
	private String spCode;
	
	/**
	 * 密码盐
	 */
	@Column
	private String salt;
	
	/**
	 * 密码(加密后)
	 */
	@Column(name="password")
	private String passwordEncrypted;
	
	
	/**
	 * 未加密的密码
	 */
	@Transient
	private String password;
	
	
	
	/**
	 * 用户类型:0普通注册用户 1：BSS同步用户
	 */
	@Column
	private int type;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name="last_login_time")
	private Date lastLoginTime;
	
	/**
	 * 用户拥有的角色
	 */
	@Transient
	private Set<Role> roles;

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

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public Date getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Date expirationTime) {
		this.expirationTime = expirationTime;
	}

	public String getLastLoginHost() {
		return lastLoginHost;
	}

	public void setLastLoginHost(String lastLoginHost) {
		this.lastLoginHost = lastLoginHost;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordEncrypted() {
		return passwordEncrypted;
	}

	public void setPasswordEncrypted(String passwordEncrypted) {
		this.passwordEncrypted = passwordEncrypted;
	}
	
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public boolean hasExpirtation() {
		if(null != this.expirationTime){
			return this.expirationTime.getTime() < (new Date().getTime());
		}
		return false;
	}

	public boolean hasLocked() {
		return this.status == STATUS_LOCKED;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getSpCode() {
		return spCode;
	}

	public void setSpCode(String spCode) {
		this.spCode = spCode;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	
}
