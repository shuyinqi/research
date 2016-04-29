package com.shuyinqi.jdbc;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by jiayusun on 2016/4/29.
 */
public class DruidTest {

    private static DataSource ds;

    static{
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("points");
        dataSource.setPassword("xx");
        dataSource.setUrl("jdbc:mysql://10.xx.xx.xx:3306/points");
        dataSource.setInitialSize(20);
        dataSource.setMinIdle(1);
        dataSource.setMaxActive(20);
        dataSource.setPoolPreparedStatements(false);
        dataSource.setDefaultAutoCommit(false);
        ds=dataSource;
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
