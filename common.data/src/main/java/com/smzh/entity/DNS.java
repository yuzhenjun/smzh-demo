package com.smzh.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "r_gsd_dns")
public class DNS {

	/**
	 * 主键ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;

	@Column(name = "record_time")
	private Date recordTime;

	/**
	 * 设备ID
	 */
	@Column(name = "host_name")
	private String host_name;

	@Column
	private int use_num;

	@Column
	private int suss_num;

	@Column
	private String all_content;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	public String getHost_name() {
		return host_name;
	}

	public void setHost_name(String host_name) {
		this.host_name = host_name;
	}

	public int getUse_num() {
		return use_num;
	}

	public void setUse_num(int use_num) {
		this.use_num = use_num;
	}

	public int getSuss_num() {
		return suss_num;
	}

	public void setSuss_num(int suss_num) {
		this.suss_num = suss_num;
	}

	public String getAll_content() {
		return all_content;
	}

	public void setAll_content(String all_content) {
		this.all_content = all_content;
	}
}
