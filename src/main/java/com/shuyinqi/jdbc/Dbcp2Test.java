package com.shuyinqi.jdbc;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jiayusun on 2016/4/28.
 */
public class Dbcp2Test {

    /** 数据源，static */
    private static DataSource DS;

    /** 从数据源获得一个连接 */
    public Connection getConn() {
        Connection con = null;
        if (DS != null) {
            try {
                con = DS.getConnection();
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
            return con;
        }
        return con;
    }

    /** 构造函数，初始化了 DS ，指定 数据库 */
    public Dbcp2Test(String connectURI) {
        initDS(connectURI);
    }

    public static void initDS(String connectURI) {
        initDS(connectURI, "points", "xx", "com.mysql.jdbc.Driver", 20, 20,
                5, 10000, 1);
    }

    public static void initDS(String connectURI, String username, String pswd,
                              String driverClass, int initialSize, int maxtotal, int maxIdle,
                              int maxWaitMillis , int minIdle) {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(driverClass);
        ds.setUsername(username);
        ds.setPassword(pswd);
        ds.setUrl(connectURI);
        ds.setInitialSize(initialSize); // 初始的连接数；
        ds.setMaxTotal(maxtotal);
        ds.setMaxIdle(maxIdle);
        ds.setMaxWaitMillis(maxWaitMillis);
        ds.setMinIdle(minIdle);
        ds.setDefaultAutoCommit(false);
        DS = ds;
    }

    /** 获得数据源连接状态 */
    public static Map<String, Integer> getDataSourceStats() throws SQLException {
        BasicDataSource bds = (BasicDataSource) DS;
        Map<String, Integer> map = new HashMap<String, Integer>(2);
        map.put("active_number", bds.getNumActive());
        map.put("idle_number", bds.getNumIdle());
        return map;
    }

    public static void main(String[] args) {
        Dbcp2Test db = new Dbcp2Test("jdbc:mysql://10.xx.xx.xx:3306/points");
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        long start = System.currentTimeMillis();
        for(int loop=0;loop<100;loop++){
            try {
                conn = db.getConn();
                stmt = conn.createStatement();
                rs = stmt.executeQuery("select * from PRIZE_INFO limit 1 ");
                System.out.println("Results:");
                int numcols = rs.getMetaData().getColumnCount();
                while (rs.next()) {
                    for (int i = 1; i <= numcols; i++) {
                        System.out.print("\t" + rs.getString(i) + "\t");
                    }
                    System.out.println("");
                }
//                System.out.println(getDataSourceStats());
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (rs != null)
                        rs.close();
                    if (stmt != null)
                        stmt.close();
                    if (conn != null)
                        conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
