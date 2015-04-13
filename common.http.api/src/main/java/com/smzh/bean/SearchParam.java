/**
 * @title SearchParam.java
 * @package com.shatacloud.oauth2.bean
 * @projectName portal-openapi
 * @author yzj
 * @date 2015年4月2日 下午4:33:10
 */
package com.smzh.bean;



/**
 * 报表查询参数<br>
 */
public class SearchParam {
	/**
	 * 开始时间（字符型）
	 */
	private String startTime;
	
	/**
	 * 结束时间（字符型）
	 */
	private String endTime;
	
	/**
	 * 频道
	 */
	private String channel;
	
	/**
	 * 域名
	 */
	private String domain;
	
	/**
	 * sp
	 */
	private String sp;
	
	/**
	 * ISP
	 */
	private String isp;
	
	/**
	 * 省份
	 */
	private String district;
	
	/**
	 * 查询类型
	 */
	private int searchType;
	
	/**
	 * 环比结束时间
	 */
	private String endRingTime;


	
	private String access_token;
	
	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getSp() {
		return sp;
	}

	public void setSp(String sp) {
		this.sp = sp;
	}

	public String getIsp() {
		return isp;
	}

	public void setIsp(String isp) {
		this.isp = isp;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}
	
	public int getSearchType() {
		return searchType;
	}

	public void setSearchType(int searchType) {
		this.searchType = searchType;
	}

	public String getEndRingTime() {
		return endRingTime;
	}

	public void setEndRingTime(String endRingTime) {
		this.endRingTime = endRingTime;
	}

}
