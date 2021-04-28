package com.cgfy.mybatis.base.dao.jdbc;

import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.support.JdbcUtils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;

public class CustomColumnMapRowMapper extends ColumnMapRowMapper {
	
	/** 是否需要格式化日期时间数据类型 **/
	private boolean isFormatDate = true;
	
	public Map<String, Object> mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();
		Map<String, Object> mapOfColValues = createColumnMap(columnCount);
		for (int i = 1; i <= columnCount; i++) {
			String key = getColumnKey(JdbcUtils.lookupColumnName(rsmd, i)).toLowerCase();
			Object obj = getColumnValue(rs, i);
			if(isFormatDate){
				int colType = rsmd.getColumnType(i);
				if(colType==Types.DATE || colType==Types.TIMESTAMP || colType==Types.TIME){
					/*if(obj instanceof Date){
						Date sqlDate = (Date)obj;
						if(colType == Types.TIMESTAMP || colType == Types.TIME){
							obj = DateUtil.TimeStampFormat(sqlDate);
						}else{
							obj = DateUtil.dataFormatWhole(sqlDate);
						}
					}*/
					obj = JdbcUtils.getResultSetValue(rs, i, String.class);
				}
			}
			mapOfColValues.put(key, obj);
		}
		return mapOfColValues;
	}

	public CustomColumnMapRowMapper() {
		super();
	}
	
	public CustomColumnMapRowMapper(boolean isFormatDate) {
		super();
		this.isFormatDate = isFormatDate;
	}
}