package com.smzh.manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * 报表查询
 *
 */
@Repository
public class JdbcManager extends JdbcTemplate {



	public Connection getConnection() {
		try {
			return getDataSource().getConnection();
		} catch (SQLException e) {
			logger.error("获取连接异常", e);
			return null;
		}
	}
	
	/**
	 * 
	 * @param sql
	 * @param timeName 时间的列名
	 * @return
	 */
	public List<Map<String, String>> queryMap(String sql, String timeName) {
		List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
		Connection conn = getConnection();

		if (conn != null) {
			Statement stmt = null;
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				ResultSetMetaData rsmd = rs.getMetaData();

				while (rs.next()) {
					Map<String, String> hashMap = new HashMap<String, String>();
					for (int i = 1; i <= rsmd.getColumnCount(); i++) {
						String column = rsmd.getColumnName(i).toLowerCase();
						if(column.equalsIgnoreCase(timeName)) {
							hashMap.put(column, String.valueOf(rs.getTimestamp(i).getTime()));
						} else {
							hashMap.put(rsmd.getColumnName(i).toLowerCase(), rs.getString(i));
						}
					}
					dataList.add(hashMap);
				}

				rs.close();
			} catch (SQLException e) {
				logger.error(String.format("sql:%s执行失败!", sql), e);
			} finally {
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) {
						logger.error("Statement关闭异常", e);
					}
				}
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error("Connection关闭异常", e);
				}
			}
		}

		return dataList;
	}

	public List<Map<String, String>> queryMap(String sql) {
		List<Map<String, String>> dataList = new ArrayList<>();
		Connection conn = getConnection();

		if (conn != null) {
			Statement stmt = null;
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				ResultSetMetaData rsmd = rs.getMetaData();

				while (rs.next()) {
					Map<String, String> hashMap = new HashMap<String, String>();
					for (int i = 1; i <= rsmd.getColumnCount(); i++) {
						hashMap.put(rsmd.getColumnName(i).toLowerCase(),
								rs.getString(i));
					}
					dataList.add(hashMap);
				}

				rs.close();
			} catch (SQLException e) {
				logger.error(String.format("sql:%s执行失败!", sql), e);
			} finally {
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) {
						logger.error("Statement关闭异常", e);
					}
				}
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error("Connection关闭异常", e);
				}
			}
		}

		return dataList;
	}

	public List<List<String>> queryList(String sql) {
		List<List<String>> dataList = new ArrayList<List<String>>();
		Connection conn = getConnection();
		if (conn != null) {
			Statement stmt = null;
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				ResultSetMetaData rsmd = rs.getMetaData();
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					for (int i = 1; i <= rsmd.getColumnCount(); i++) {
						list.add(rs.getString(i));
					}
					dataList.add(list);
				}
				rs.close();
			} catch (SQLException e) {
				logger.error(String.format("sql:%s执行失败!", sql), e);
				return null;
			} finally {
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) {
						logger.error("Statement关闭异常", e);
					}
				}
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error("Connection关闭异常", e);
				}
			}
		}
		return dataList;
	}

	/**
	 * 查询一列数据
	 * 
	 * @param sql
	 * @return
	 */
	public List<String> queryOneColumnList(String sql) {
		List<String> dataList = new ArrayList<String>();
		Connection conn = getConnection();
		if (conn != null) {
			Statement stmt = null;
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);

				while (rs.next()) {

					dataList.add(rs.getString(1));
				}
				rs.close();
			} catch (SQLException e) {
				logger.error(String.format("sql:%s执行失败!", sql), e);
				return null;
			} finally {
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) {
						logger.error("Statement关闭异常", e);
					}
				}
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error("Connection关闭异常", e);
				}
			}
		}
		return dataList;
	}

	/**
	 * 将查询出的数据转换成Map，只支持查询两列的sql
	 * 
	 * @param sql
	 * @return
	 */
	public Map<String, String> queryValueMap(String sql) {
		Map<String, String> dataMap = new HashMap<String, String>();
		Connection conn = getConnection();
		if (conn != null) {
			Statement stmt = null;
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				ResultSetMetaData rsmd = rs.getMetaData();
				if (rsmd.getColumnCount() == 2) {
					while (rs.next()) {
						String key = rs.getString(1);
						String value = rs.getString(2);
						dataMap.put(key, value);
					}
				} else {
					logger.error("sql查询的列数不是两列，无法转换成MAP");
				}
				rs.close();
			} catch (SQLException e) {
				logger.error(String.format("sql:%s执行失败!", sql), e);
				return null;
			} finally {
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) {
						logger.error("Statement关闭异常", e);
					}
				}
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error("Connection关闭异常", e);
				}
			}
		}
		return dataMap;
	}

	/**
	 * 查询一对多数据只支持两列(第一列是key，第二列是值)
	 * 
	 * @param sql
	 * @return
	 */
	public Map<String, List<String>> queryOneToMany(String sql) {
		Map<String, List<String>> dataMap = new HashMap<String, List<String>>();
		Connection conn = getConnection();
		if (conn != null) {
			Statement stmt = null;
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				ResultSetMetaData rsmd = rs.getMetaData();
				if (rsmd.getColumnCount() == 2) {
					while (rs.next()) {
						String key = rs.getString(1);
						List<String> value = dataMap.get(key);
						if (null == value) {
							value = new ArrayList<String>();
							dataMap.put(key, value);
						}
						value.add(rs.getString(2));
					}
				} else {
					logger.error("sql查询的列数不是两列，无法转换成MAP");
				}
				rs.close();
			} catch (SQLException e) {
				logger.error(String.format("sql:%s执行失败!", sql), e);
				return null;
			} finally {
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) {
						logger.error("Statement关闭异常", e);
					}
				}
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error("Connection关闭异常", e);
				}
			}
		}
		return dataMap;
	}

	/**
	 * sql查询返回唯一一个数据
	 * 
	 * @param sql
	 * @return
	 */
	public String searchOnlyOne(String sql) {
		String result = "";
		Connection conn = getConnection();
		if (conn != null) {
			Statement stmt = null;
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					result = rs.getString(1);
				}
				rs.close();
			} catch (SQLException e) {
				logger.error(String.format("sql:%s执行失败!", sql), e);
				return null;
			} finally {
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) {
						logger.error("Statement关闭异常", e);
					}
				}
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error("Connection关闭异常", e);
				}
			}
		}

		return result;
	}

	@Resource
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

}