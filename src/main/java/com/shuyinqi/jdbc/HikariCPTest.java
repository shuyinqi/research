package com.shuyinqi.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jiayusun on 2016/4/28.
 */
public class HikariCPTest {

    private static HikariDataSource ds;

    static{
        HikariConfig config = new HikariConfig();
        config.setMaximumPoolSize(100);
        config.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        config.addDataSourceProperty("serverName", "10.xx.xx.xx");
        config.addDataSourceProperty("port", "3306");
        config.addDataSourceProperty("databaseName", "points");
        config.addDataSourceProperty("user", "points");
        config.addDataSourceProperty("password", "xx");
        ds = new HikariDataSource(config);
    }
    @PostConstruct
    public static void init(){
        ds.setAutoCommit(false);
        ds.setIdleTimeout(10000);
        ds.setMinimumIdle(1);
        ds.setMaximumPoolSize(20);
    }



    public static void main(String[] args) {
        init();
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
