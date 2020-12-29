package com.cgfy.lock.base.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 数据库操作都是在   jdbcTemplate  中进行的
 * jdbcTemplate 是springboot的核心文件
 * jdbcTemplate 用来简化数据库操作,内部定义了很多避免错误的机制
 * springboot默认提供了数据源com.zaxxer.hikari.HikariDataSource
 * jdbcTemplate 自动注入数据源,使用它不用管理数据源,也不用管理关闭问题
 *
 * 数据库连接帮助类
 * @author liguolong
 * @version 1.0
 */
@Configuration
public class SQLHelper implements Serializable {
    private final static Log log = LogFactory.getLog(SQLHelper.class);
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    DataSource dataSource;//数据源
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
//    public Connection creatConnection() {
//        try {
//            String jdbc_driver = "com.ibm.db2.jcc.DB2Driver";//PropertyUtil.getJdbc_driver();
//            String jdbc_url = "jdbc:db2://10.202.5.136:50000/HJZF";//PropertyUtil.getJdbc_url();
//            String jdbc_username = "HJZF";//PropertyUtil.getJdbc_username();
//            String jdbc_password = "Windows2016";//PropertyUtil.getJdbc_password();
//            Class.forName(jdbc_driver);
//            conn = DriverManager.getConnection(jdbc_url, jdbc_username, jdbc_password);
//            conn.setAutoCommit(true);
//        } catch (Exception e) {
//            close();
//            System.out.println("creatConnectionError:" + e.getMessage());
//        }
//        return conn;
//    }
    public Connection creatConnection() {
        try {
            conn =dataSource.getConnection();
            conn.setAutoCommit(true);
        } catch (Exception e) {
            System.out.println("creatConnectionError:"+e.getMessage());
        }
        return conn;
    }

    //关闭资源
    public void close() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            log.error("close database error!", e);
        }
    }


    /**
     * 数据查询  MySQL
     *
     * @param sql
     * @return 返回结果集JSONArray
     * @throws SQLException
     */
    public JSONArray query(String sql) {
        if (sql.equals("") || sql == null) {
            return null;
        }
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject2 = null;
        try {
            conn = creatConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            // 可以得到有多少列
            int columnNum = rsmd.getColumnCount();
            // 将数据封装到list中
            while (rs.next()) {
                jsonObject2 = new JSONObject();
                for (int i = 0; i < columnNum; i++) {
                    jsonObject2.put(rsmd.getColumnLabel(i + 1), rs.getObject(i + 1));
                }
                jsonArray.add(jsonObject2);
            }
        } catch (Exception e) {
            log.error("error select:" + sql, e);
        } finally {
            close();
        }
        return jsonArray;
    }

    /**
     * 插入、修改数据操作   MySQL
     *
     * @param sql
     * @return boolean 成功返回true，失败返回false
     * @throws SQLException
     */
    public int executeSql(String sql) {
//		boolean b = false;
        int i = -1;
        if (sql.equals("") || sql == null) {
            return i;
        }
        try {
            conn = creatConnection();
            ps = conn.prepareStatement(sql);
            i = ps.executeUpdate();
//			if (i == 1) {
//				b = true;
//			}
        } catch (Exception e) {
            i = -1;
            log.error("error insert:" + sql, e);
        } finally {
            close();
        }
        return i;
    }

    /**
     * 对数据库的增加、修改和删除的操作
     *
     * @param sqls
     * @return
     */
    public boolean executeTransUpdate(List sqls) {
        if (sqls == null || sqls.size() == 0) return false;
        boolean flag = true;
        try {
            conn = creatConnection();
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            String sql = "";
            for (int i = 0, n = sqls.size(); i < n; i++) {
                sql = sqls.get(i).toString();
                stmt.executeUpdate(sql);
            }
            conn.commit();
            conn.setAutoCommit(true);
            return true;
        } catch (Exception e) {
            flag = false;
            log.error("error insert:", e);
        } finally {
            close();
        }
        return flag;
    }


    public String queryForString(String sql) {
        if (sql.equals("") || sql == null) {
            return null;
        }
        String s = "";
        try {
            conn = creatConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            // 将数据封装到list中
            while (rs.next()) {

                s = rs.getString(1);
            }
        } catch (Exception e) {
            log.error("error select:" + sql, e);
        } finally {
            close();
        }
        return s;
    }


    /**
     * 数据查询  SQLServer
     *
     * @param sql
     * @return 返回结果集JSONArray
     * @throws SQLException
     */
    public JSONArray queryOfSQLServer(String sql) {
        if (sql.equals("") || sql == null) {
            return null;
        }
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject2 = null;
        try {
            conn = creatConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            // 可以得到有多少列
            int columnNum = rsmd.getColumnCount();
            // 将数据封装到list中
            while (rs.next()) {
                jsonObject2 = new JSONObject();
                for (int i = 0; i < columnNum; i++) {
                    jsonObject2.put(rsmd.getColumnLabel(i + 1), rs.getObject(i + 1));
                }
                jsonArray.add(jsonObject2);
            }
        } catch (Exception e) {
            log.error("error select:" + sql, e);
        } finally {
            close();
        }
        return jsonArray;
    }

    /**
     * 查询目标表是否存在   MySQL
     *
     * @param sql
     * @return
     */
    public boolean selectTable(String sql) {
        boolean b = false;
        if (sql.equals("") || sql == null) {
            return b;
        }
        try {
            conn = creatConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            // 将数据封装到list中
            b = rs.last();
        } catch (Exception e) {
            log.error("error select:" + sql, e);
        } finally {
            close();
        }
        return b;
    }

    public int select(String sql) {
        int i = 0;
        if (sql.equals("") || sql == null) {
            return i;
        }
        try {
            conn = creatConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            // 将数据封装到list中
            while (rs.next()) {
                i = rs.getInt(1);
            }
        } catch (Exception e) {
            log.error("error select:" + sql, e);
        } finally {
            close();
        }
        return i;
    }


    public List<Map<String, Object>> query_ex(String sql) {
        if (sql.equals("") || sql == null) {
            return null;
        }
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = null;
        try {
            conn = creatConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            // 可以得到有多少列
            int columnNum = rsmd.getColumnCount();
            // 将数据封装到list中
            while (rs.next()) {
                map = new HashMap<String, Object>();
                for (int i = 0; i < columnNum; i++) {
                    map.put(rsmd.getColumnLabel(i + 1), rs.getObject(i + 1));
                }
                list.add(map);
            }
        } catch (Exception e) {
            log.error("error select:" + sql, e);
        } finally {
            close();
        }
        return list;
    }
}
