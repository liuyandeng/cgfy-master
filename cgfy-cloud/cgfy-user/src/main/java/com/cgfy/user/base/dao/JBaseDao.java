package com.cgfy.user.base.dao;

import com.cgfy.user.base.dao.jdbc.CustomColumnMapRowMapper;
import com.cgfy.user.base.dao.jdbc.CustomRowMapperResultSetExtractor;
import com.cgfy.user.base.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 该类是基类dao实现类(for JdbcTemplate)
 * Repository注解将数据访问层 (DAO 层 ) 的类标识为 Spring Bean
 * Scope注解是springIoc容器中的一个作用域，在 Spring IoC 容器中具有以下几种作用域：基本作用域singleton（单例）、prototype(多例)，Web 作用域（reqeust、session、globalsession），自定义作用域
 * a.singleton单例模式 -- 全局有且仅有一个实例
 * b.prototype原型模式 -- 每次获取Bean的时候会有一个新的实例
 * c.request -- request表示该针对每一次HTTP请求都会产生一个新的bean，同时该bean仅在当前HTTP request内有效
 * d.session -- session作用域表示该针对每一次HTTP请求都会产生一个新的bean，同时该bean仅在当前HTTP session内有效
 * e.globalsession -- global session作用域类似于标准的HTTP Session作用域，不过它仅仅在基于portlet的web应用中才有意义
 * Scope注解默认的singleton实例，singleton实例的意思不管你使用多少次在springIOC容器中只会存在一个实例,
 * 几乎90%以上的业务使用singleton单实例就可以，所以spring默认的类型也是singleton，singleton虽然保证了全局是一个实例，对性能有所提高，但是如果实例中有非静态变量时，会导致线程安全问题，共享资源的竞争
 * 当设置为prototype时：每次连接请求，都会生成一个bean实例，也会导致一个问题，当请求数越多，性能会降低，因为创建的实例，导致GC频繁，gc时长增加
 */
@Repository("jBaseDao")
@Scope("prototype")
@Slf4j
public class JBaseDao {

    //进行数据库查询时，最大查询记录数，为0时，不限制
    private int maxRowsQuery=0;

    private JdbcTemplate jdbcTemplate;

    @Resource
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }



    /**
     * 查询Map
     *
     * @param sql 预编译带？的sql
     * @return Map
     */
    public Map<String, Object> queryForMap(String sql) throws DataAccessException {
        return this.queryForMap(sql, true);
    }

    /**
     * 查询Map
     *
     * @param sql          预编译带？的sql
     * @param isFormatDate 是否格式化日期
     * @return Map
     */
    public Map<String, Object> queryForMap(String sql, boolean isFormatDate) throws DataAccessException {
        Map<String, Object> map = null;
        try {
            map = this.jdbcTemplate.queryForObject(sql, new CustomColumnMapRowMapper(isFormatDate));
        } catch (EmptyResultDataAccessException emptyException) {
            map = null;
        } finally {
            log.info("操作的sql[" + sql + "]");
        }

        return map;
    }

    /**
     * 查询Map
     *
     * @param sql  预编译带？的sql
     * @param args 参数数组
     * @return Map
     */
    public Map<String, Object> queryForMap(String sql, Object[] args) throws DataAccessException {
        return this.queryForMap(sql, args, true);
    }

    /**
     * 查询Map
     *
     * @param sql          预编译带？的sql
     * @param args         参数数组
     * @param isFormatDate 是否格式化日期
     * @return Map
     */
    public Map<String, Object> queryForMap(String sql, Object[] args, boolean isFormatDate) throws DataAccessException {
        Map<String, Object> backVal = null;
        String argsInfo = (args != null ? "args - " + Arrays.toString(args) : "");
        try {
            backVal = this.jdbcTemplate.queryForMap(sql, args);
        } catch (EmptyResultDataAccessException emptyException) {
            backVal = null;
        } finally {
            log.info("操作的sql[" + sql + "];参数[" + argsInfo + "]");
        }

        return backVal;
    }

    /**
     * 查询Map
     *
     * @param sql      预编译带？的sql
     * @param args     参数数组
     * @param argTypes 参数类型数组(使用java.sql.Types常数)
     * @return Map
     */
    public Map<String, Object> queryForMap(String sql, Object[] args, int[] argTypes) throws DataAccessException {
        return this.queryForMap(sql, args, argTypes, true);
    }

    /**
     * 查询Map
     *
     * @param sql          预编译带？的sql
     * @param args         参数数组
     * @param argTypes     参数类型数组(使用java.sql.Types常数)
     * @param isFormatDate 是否格式化日期
     * @return Map
     */
    public Map<String, Object> queryForMap(String sql, Object[] args, int[] argTypes, boolean isFormatDate) throws DataAccessException {
        Map<String, Object> backVal = null;
        String argsInfo = (args != null ? "args - " + Arrays.toString(args) : "");

        try {
            backVal = this.jdbcTemplate.queryForMap(sql, args, argTypes);
        } catch (EmptyResultDataAccessException emptyException) {
            backVal = null;
        } finally {
            log.info("操作的sql[" + sql + "];参数[" + argsInfo + "]");
        }

        return backVal;
    }


    /**
     * 查询List
     *
     * @param sql 预编译带？的sql
     * @return List
     */
    public List<Map<String, Object>> queryForList(String sql) throws DataAccessException {
        return this.queryForList(sql, true);
    }

    /**
     * 查询List
     *
     * @param sql          预编译带？的sql
     * @param isFormatDate 是否需要格式化日期、时间
     * @return List
     */
    public List<Map<String, Object>> queryForList(String sql, boolean isFormatDate) throws DataAccessException {
        List<Map<String, Object>> backVal = null;

        try {
            backVal = (List<Map<String, Object>>) this.jdbcTemplate.query(sql, new CustomRowMapperResultSetExtractor<Map<String, Object>>(new CustomColumnMapRowMapper(isFormatDate), 0, maxRowsQuery));
        } catch (DataAccessException dataAccessException) {
            throw dataAccessException;
        } finally {
            log.info("操作的sql[" + sql + "]");
        }

        return backVal;
    }

    /**
     * 查询List
     *
     * @param sql  预编译带？的sql
     * @param args 参数数组
     * @return List
     */
    public List<Map<String, Object>> queryForList(String sql, Object[] args) throws DataAccessException {
        return this.queryForList(sql, args, true);
    }

    /**
     * 查询List
     *
     * @param sql          预编译带？的sql
     * @param args         参数数组
     * @param isFormatDate 是否需要格式化日期、时间
     * @return List
     */
    public List<Map<String, Object>> queryForList(String sql, Object[] args, boolean isFormatDate) throws DataAccessException {
        List<Map<String, Object>> backVal = null;
        String argsInfo = (args != null ? "args - " + Arrays.toString(args) : "");

        try {
            backVal = (List<Map<String, Object>>) this.jdbcTemplate.query(sql, args,
                    new CustomRowMapperResultSetExtractor<Map<String, Object>>(new CustomColumnMapRowMapper(isFormatDate), 0, maxRowsQuery));
        } catch (DataAccessException dataAccessException) {
            throw dataAccessException;
        } finally {
            log.info("操作的sql[" + sql + "];参数[" + argsInfo + "]");
        }

        return backVal;
    }

    /**
     * 查询List
     *
     * @param sql      预编译带？的sql
     * @param args     参数数组
     * @param argTypes 参数类型数组(使用java.sql.Types常数)
     * @return
     */
    public List<Map<String, Object>> queryForList(String sql, Object[] args, int[] argTypes) throws DataAccessException {
        return this.queryForList(sql, args, argTypes, true);
    }

    /**
     * 查询List
     *
     * @param sql          预编译带？的sql
     * @param args         参数数组
     * @param argTypes     参数类型数组(使用java.sql.Types常数)
     * @param isFormatDate 是否需要格式化日期、时间
     * @return
     */
    public List<Map<String, Object>> queryForList(String sql, Object[] args, int[] argTypes, boolean isFormatDate) throws DataAccessException {
        List<Map<String, Object>> backVal = null;
        String argsInfo = (args != null ? "args - " + Arrays.toString(args) : "");

        try {
            backVal = (List<Map<String, Object>>) this.jdbcTemplate.query(sql, args, argTypes, new CustomRowMapperResultSetExtractor<Map<String, Object>>(new CustomColumnMapRowMapper(isFormatDate), 0, maxRowsQuery));
        } catch (DataAccessException dataAccessException) {
            throw dataAccessException;
        } finally {
            log.info("操作的sql[" + sql + "];参数[" + argsInfo + "]");
        }

        return backVal;
    }

    /**
     * 查询List
     *
     * @param sql
     * @param clazz
     * @param <T>
     * @return
     * @throws DataAccessException
     */
    public <T> List<T> queryForBeanList(String sql, Class<T> clazz) throws DataAccessException {
        List<T> backVal = null;

        try {
            BeanPropertyRowMapper<T> beanPropertyRowMapper = new BeanPropertyRowMapper<T>(clazz);
            backVal = this.jdbcTemplate.query(sql, new CustomRowMapperResultSetExtractor<T>(beanPropertyRowMapper, 0, maxRowsQuery));
        } catch (DataAccessException dataAccessException) {
            throw dataAccessException;
        } finally {
            log.info("操作的sql[" + sql + "]");
        }

        return backVal;
    }

    /**
     * 查询List
     *
     * @param sql
     * @param args
     * @param clazz
     * @param <T>
     * @return
     * @throws DataAccessException
     */
    public <T> List<T> queryForBeanList(String sql, Object[] args, Class<T> clazz) throws DataAccessException {
        List<T> backVal = null;
        String argsInfo = (args != null ? "args - " + Arrays.toString(args) : "");

        try {
            BeanPropertyRowMapper<T> beanPropertyRowMapper = new BeanPropertyRowMapper<T>(clazz);
            backVal = this.jdbcTemplate.query(sql, args, new CustomRowMapperResultSetExtractor<T>(beanPropertyRowMapper, 0, maxRowsQuery));
        } catch (DataAccessException dataAccessException) {
            throw dataAccessException;
        } finally {
            log.info("操作的sql[" + sql + "];参数[" + argsInfo + "]");
        }

        return backVal;
    }

    /**
     * 查询List
     *
     * @param sql
     * @param args
     * @param argTypes
     * @param clazz
     * @param <T>
     * @return
     * @throws DataAccessException
     */
    public <T> List<T> queryForBeanList(String sql, Object[] args, int[] argTypes, Class<T> clazz) throws DataAccessException {
        List<T> backVal = null;
        String argsInfo = (args != null ? "args - " + Arrays.toString(args) : "");
        try {
            BeanPropertyRowMapper<T> beanPropertyRowMapper = new BeanPropertyRowMapper<T>(clazz);
            backVal = this.jdbcTemplate.query(sql, args, argTypes, new CustomRowMapperResultSetExtractor<T>(beanPropertyRowMapper, 0, maxRowsQuery));
        } catch (DataAccessException dataAccessException) {
            throw dataAccessException;
        } finally {
            log.info("操作的sql[" + sql + "];参数[" + argsInfo + "]");
        }

        return backVal;
    }

    /**
     * 返回泛型为String的list
     *
     * @param sql
     * @return
     * @throws Exception
     * @author liuyd
     */
    public List<String> queryForStringList(String sql) throws DataAccessException {
        List<String> data = null;
        try {
            data = this.jdbcTemplate.query(sql, new RowMapper<String>() {
                public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return rs.getString(1);
                }
            }, null);
        } catch (Exception e) {
            throw e;
        } finally {
            log.info("操作的sql[" + sql + "]");
        }
        return data;
    }
    /**
     * 查询Map,特殊list集合转map
     * 适用于sql查询列仅为两列的情况,第一列作为key,第二列作为value,第一列的值不能重复且不能为空和null
     * @author liuyd
     * @return Map
     */
    public Map<String, Object> queryForListToMap(String sql) throws DataAccessException {
        Map <String, Object>resultMap=new HashMap();
        List<Map<String, Object>> list = this.queryForList(sql, true);
        if(list!=null&&!list.isEmpty()){
            for(Map<String, Object> map:list){
                if(map.size()==2){
                    Object mapKey="";
                    Object mapValue="";
                    int i=0;
                    for (String key :map.keySet()) {
                        if(i==0){
                            mapKey=map.get(key);
                        }
                        if(i==1){
                            mapValue=map.get(key);
                        }
                        i++;
                    }
                    if(mapKey!=""&&mapKey!=null){
                        if(!resultMap.containsKey(mapKey)){
                            resultMap.put(mapKey.toString(),mapValue);
                        }else{
                            throw new BusinessException("查询字段第一列有重复值:"+mapKey);
                        }

                    }else{
                        throw new BusinessException("查询字段第一列不能有空值或者null");
                    }

                }else{
                    throw new BusinessException("查询字段个数必须为两列");
                }

            }
        }else{
            return null;
        }
        return resultMap;
    }

    /**
     * 返回String字符串
     *
     * @param sql
     * @return
     * @throws Exception
     * @author liuyd
     */
    public String queryForString(String sql) throws DataAccessException {
        String data = "";
        try {
            data = this.jdbcTemplate.queryForObject(sql, new RowMapper<String>() {
                public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return rs.getString(1);
                }
            }, null);
        } catch (Exception e) {
            throw e;
        } finally {
            log.info("操作的sql[" + sql + "]");
        }
        return data;
    }
}
