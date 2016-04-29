package com.shuyinqi.jdbc;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by jiayusun on 2016/4/29.
 */
public class TomcatJdbcTest {

    private static DataSource ds;
    static{
        PoolProperties p = new PoolProperties();
        p.setUrl("jdbc:mysql://10.xx.xx.xx:3306/points");
        p.setDriverClassName("com.mysql.jdbc.Driver");
        p.setUsername("points");
        p.setPassword("xx");
        p.setJmxEnabled(true);
        p.setTestWhileIdle(false);
        p.setTestOnBorrow(true);
        p.setValidationQuery("SELECT 1");
        p.setTestOnReturn(false);
        p.setValidationInterval(30000);
        p.setTimeBetweenEvictionRunsMillis(30000);
        p.setMaxActive(20);
        p.setInitialSize(20);
        p.setMaxWait(10000);
        p.setRemoveAbandonedTimeout(60);
        p.setMinEvictableIdleTimeMillis(30000);
        p.setMinIdle(1);
        p.setLogAbandoned(true);
        p.setRemoveAbandoned(true);
        p.setDefaultAutoCommit(false);
        p.setJdbcInterceptors("org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"+
                "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
        DataSource datasource = new DataSource();
        datasource.setPoolProperties(p);
        ds = datasource;
    }

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        long start = System.currentTimeMillis();
        for(int loop=0;loop<100;loop++){
            try {
                conn = ds.getConnection();
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
