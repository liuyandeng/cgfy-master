package com.cgfy.oauth.base.dao;

import com.cgfy.oauth.base.dao.jdbc.CustomColumnMapRowMapper;
import com.cgfy.oauth.base.dao.jdbc.CustomRowMapperResultSetExtractor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 
 * 该类是基类dao实现类(for JdbcTemplate)
 */
@Repository("jBaseDao")
@Scope("prototype")
public class JBaseDao{
	
	private Logger log = LogManager.getLogger(JBaseDao.class);
	
	@Value("${custom.maxRowsQuery:0}")
	private int maxRowsQuery;
	
	private JdbcTemplate jdbcTemplate;

	@Resource
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}

	public JdbcTemplate getJdbcTemplate(){
		return jdbcTemplate;
	}

	/**
	 * 查询Map
	 * 
	 * @param sql
	 *            预编译带？的sql
	 * @return Map
	 */
	public Map<String, Object> queryForMap(String sql) throws DataAccessException{
		return this.queryForMap(sql, true);
	}

	/**
	 * 查询Map
	 * 
	 * @param sql
	 *            预编译带？的sql
	 * @param isFormatDate
	 *            是否格式化日期
	 * @return Map
	 */
	public Map<String, Object> queryForMap(String sql, boolean isFormatDate) throws DataAccessException{
		Map<String, Object> map = null;
		try{
			map = this.jdbcTemplate.queryForObject(sql, new CustomColumnMapRowMapper(isFormatDate));
		}catch (EmptyResultDataAccessException emptyException){
			map = null;
		}finally{
			log.info("操作的sql[" + sql + "]");
		}
		
		return map;
	}

	/**
	 * 查询Map
	 * 
	 * @param sql
	 *            预编译带？的sql
	 * @param args
	 *            参数数组
	 * @return Map
	 */
	public Map<String, Object> queryForMap(String sql, Object[] args) throws DataAccessException{
		return this.queryForMap(sql, args, true);
	}
	
	/**
	 * 查询Map
	 * 
	 * @param sql
	 *            预编译带？的sql
	 * @param args
	 *            参数数组
	 * @param isFormatDate
	 *            是否格式化日期
	 * @return Map
	 */
	public Map<String, Object> queryForMap(String sql, Object[] args, boolean isFormatDate) throws DataAccessException{
		Map<String, Object> backVal = null;
		String argsInfo = (args != null ? "args - " + Arrays.toString(args) : "");
		try{
			backVal = this.jdbcTemplate.queryForMap(sql, args);
		}catch (EmptyResultDataAccessException emptyException){
			backVal = null;
		}finally{
			log.info("操作的sql[" + sql + "];参数["+argsInfo+"]");
		}
		
		return backVal;
	}

	/**
	 * 查询Map
	 * 
	 * @param sql
	 *            预编译带？的sql
	 * @param args
	 *            参数数组
	 * @param argTypes
	 *            参数类型数组(使用java.sql.Types常数)
	 * @return Map
	 */
	public Map<String, Object> queryForMap(String sql, Object[] args, int[] argTypes) throws DataAccessException{
		return this.queryForMap(sql, args, argTypes, true);
	}
	
	/**
	 * 查询Map
	 * 
	 * @param sql
	 *            预编译带？的sql
	 * @param args
	 *            参数数组
	 * @param argTypes
	 *            参数类型数组(使用java.sql.Types常数)
	 * @param isFormatDate
	 *            是否格式化日期
	 * @return Map
	 */
	public Map<String, Object> queryForMap(String sql, Object[] args, int[] argTypes, boolean isFormatDate) throws DataAccessException{
		Map<String, Object> backVal = null;
		String argsInfo = (args != null ? "args - " + Arrays.toString(args) : "");
		
		try{
			backVal = this.jdbcTemplate.queryForMap(sql, args, argTypes);
		}catch (EmptyResultDataAccessException emptyException){
			backVal = null;
		}finally{
			log.info("操作的sql[" + sql + "];参数["+argsInfo+"]");
		}
		
		return backVal;
	}
	

	/**
	 * 查询List
	 * 
	 * @param sql
	 *            预编译带？的sql
	 * @return List
	 */
	public List<Map<String, Object>> queryForList(String sql) throws DataAccessException{
		return this.queryForList(sql, true);
	}
	
	/**
	 * 查询List
	 * 
	 * @param sql
	 *            预编译带？的sql
	 * @param isFormatDate
	 *            是否需要格式化日期、时间
	 * @return List
	 */
	public List<Map<String, Object>> queryForList(String sql, boolean isFormatDate) throws DataAccessException{
		List<Map<String, Object>> backVal = null;
		
		try{
			backVal = (List<Map<String, Object>>) this.jdbcTemplate.query(sql, new CustomRowMapperResultSetExtractor<Map<String, Object>>(new CustomColumnMapRowMapper(isFormatDate), 0, maxRowsQuery));
		}catch (DataAccessException dataAccessException){
			throw dataAccessException;
		}finally{
			log.info("操作的sql[" + sql + "]");
		}
		
		return backVal;
	}

	/**
	 * 查询List
	 * 
	 * @param sql
	 *            预编译带？的sql
	 * @param args
	 *            参数数组
	 * @return List
	 */
	public List<Map<String, Object>> queryForList(String sql, Object[] args) throws DataAccessException{
		return this.queryForList(sql, args, true);
	}
	
	/**
	 * 查询List
	 * 
	 * @param sql
	 *            预编译带？的sql
	 * @param args
	 *            参数数组
	 * @param isFormatDate
	 *            是否需要格式化日期、时间
	 * @return List
	 */
	public List<Map<String, Object>> queryForList(String sql, Object[] args, boolean isFormatDate) throws DataAccessException{
		List<Map<String, Object>> backVal = null;
		String argsInfo = (args != null ? "args - " + Arrays.toString(args) : "");
		
		try{
			backVal = (List<Map<String, Object>>) this.jdbcTemplate.query(sql, args, 
					new CustomRowMapperResultSetExtractor<Map<String, Object>>(new CustomColumnMapRowMapper(isFormatDate), 0, maxRowsQuery));
		}catch (DataAccessException dataAccessException){
			throw dataAccessException;
		}finally{
			log.info("操作的sql[" + sql + "];参数["+argsInfo+"]");
		}
		
		return backVal;
	}

	/**
	 * 查询List
	 * 
	 * @param sql
	 *            预编译带？的sql
	 * @param args
	 *            参数数组
	 * @param argTypes
	 *            参数类型数组(使用java.sql.Types常数)
	 * @return
	 */
	public List<Map<String, Object>> queryForList(String sql, Object[] args, int[] argTypes) throws DataAccessException{
		return this.queryForList(sql, args, argTypes, true);
	}
	
	/**
	 * 查询List
	 * 
	 * @param sql
	 *            预编译带？的sql
	 * @param args
	 *            参数数组
	 * @param argTypes
	 *            参数类型数组(使用java.sql.Types常数)
	 * @param isFormatDate
	 *            是否需要格式化日期、时间
	 * @return
	 */
	public List<Map<String, Object>> queryForList(String sql, Object[] args, int[] argTypes, boolean isFormatDate) throws DataAccessException{
		List<Map<String, Object>> backVal = null;
		String argsInfo = (args != null ? "args - " + Arrays.toString(args) : "");
		
		try{
			backVal = (List<Map<String, Object>>) this.jdbcTemplate.query(sql, args, argTypes, new CustomRowMapperResultSetExtractor<Map<String, Object>>(new CustomColumnMapRowMapper(isFormatDate), 0, maxRowsQuery));
		}catch (DataAccessException dataAccessException){
			throw dataAccessException;
		}finally{
			log.info("操作的sql[" + sql + "];参数["+argsInfo+"]");
		}
		
		return backVal;
	}
	
	/**
	 * 查询List
	 * 
	 * @param sql
	 *            预编译带？的sql
	 * @param isFormatDate
	 *            是否需要格式化日期、时间
	 * @return List
	 */
	public <T> List<T> queryForBeanList(String sql, Class<T> clazz) throws DataAccessException{
		List<T> backVal = null;
		
		try{
			BeanPropertyRowMapper<T> beanPropertyRowMapper = new BeanPropertyRowMapper<T>(clazz);
			backVal = this.jdbcTemplate.query(sql, new CustomRowMapperResultSetExtractor<T>(beanPropertyRowMapper, 0, maxRowsQuery));
		}catch (DataAccessException dataAccessException){
			throw dataAccessException;
		}finally{
			log.info("操作的sql[" + sql + "]");
		}
		
		return backVal;
	}
	
	/**
	 * 查询List
	 * 
	 * @param sql
	 *            预编译带？的sql
	 * @param args
	 *            参数数组
	 * @param isFormatDate
	 *            是否需要格式化日期、时间
	 * @return List
	 */
	public <T> List<T> queryForBeanList(String sql, Object[] args, Class<T> clazz) throws DataAccessException{
		List<T> backVal = null;
		String argsInfo = (args != null ? "args - " + Arrays.toString(args) : "");
		
		try{
			BeanPropertyRowMapper<T> beanPropertyRowMapper = new BeanPropertyRowMapper<T>(clazz);
			backVal = this.jdbcTemplate.query(sql, args, new CustomRowMapperResultSetExtractor<T>(beanPropertyRowMapper, 0, maxRowsQuery));
		}catch (DataAccessException dataAccessException){
			throw dataAccessException;
		}finally{
			log.info("操作的sql[" + sql + "];参数["+argsInfo+"]");
		}
		
		return backVal;
	}
	
	/**
	 * 查询List
	 * 
	 * @param sql
	 *            预编译带？的sql
	 * @param args
	 *            参数数组
	 * @param argTypes
	 *            参数类型数组(使用java.sql.Types常数)
	 * @param isFormatDate
	 *            是否需要格式化日期、时间
	 * @return
	 */
	public <T> List<T> queryForBeanList(String sql, Object[] args, int[] argTypes, Class<T> clazz) throws DataAccessException{
		List<T> backVal = null;
		String argsInfo = (args != null ? "args - " + Arrays.toString(args) : "");
		try{
			BeanPropertyRowMapper<T> beanPropertyRowMapper = new BeanPropertyRowMapper<T>(clazz);
			backVal = this.jdbcTemplate.query(sql, args, argTypes, new CustomRowMapperResultSetExtractor<T>(beanPropertyRowMapper, 0, maxRowsQuery));
		}catch (DataAccessException dataAccessException){
			throw dataAccessException;
		}finally{
			log.info("操作的sql[" + sql + "];参数["+argsInfo+"]");
		}
		
		return backVal;
	}
}
