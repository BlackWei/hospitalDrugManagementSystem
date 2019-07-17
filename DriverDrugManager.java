package cn.ggvc.drugadmin.dao;


import java.sql.*;

/**
 * 数据库连接和关闭
 *
 * @author Chan
 * @version 1.0
 */

public class DriverDrugManager {
    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String URL = "jdbc:sqlserver://localhost:1433;DatabaseName=DrugManagement";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "081628";

    //加载数据库驱动
    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("数据库驱动加载失败！");
        }
    }

    /**
     * 连接数据库
     *
     * @return
     */
    public static Connection getCoon() {
        Connection coon = null;
        try {
            coon = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库连接失败！");
        }
        return coon;
    }

    /**
     * 关闭数据库
     *
     * @param conn
     * @param rs
     * @param st
     */
    public static void close(Connection conn, ResultSet rs, Statement st) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
