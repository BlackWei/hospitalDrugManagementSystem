package cn.ggvc.drugadmin.dao;

import javax.swing.*;
import java.sql.*;

/**
 * 数据库访问类，封装对用户数据库表的基本操作
 *
 * @author Chan
 * @version 1.0
 */
public class LoginDAO {
    private Connection conn;
    private ResultSet rs;
    private Statement st;

    /**
     * 对比用户名和密码是否匹配
     *
     * @param username 用户名
     * @param password  密码
     * @return  flag
     */
    public boolean login(String username, String password) {
        boolean flag = false;
        if (username == null || username.trim().length() <= 0 || password == null || password.trim().length() <= 0) {
            JOptionPane.showMessageDialog(null, "用户名或密码框不能为空"
                    ,"消息", JOptionPane.ERROR_MESSAGE);

        } else {
            try {
                conn = DriverDrugManager.getCoon();
                st = conn.createStatement();
                String sql = "select userPassword from UserTable where userName = '" + username + "' and userPassword = '" + password + "'";
                rs = st.executeQuery(sql);
                if (rs.next()) {
                    String psd = rs.getString(1);
                    if (psd.equals(password)) {
                        flag = true;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "用户名或密码错误"
                            , "消息", JOptionPane.ERROR_MESSAGE);
                }
                rs.close();
                conn.close();
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return flag;
    }

    /**
     * 用户注册，添加数据
     *
     * @param username 用户名
     * @param password  密码
     */
    public void register(String username, String password) {
        if (username == null || username.trim().length() <= 0) {
            JOptionPane.showMessageDialog(null, "注册账号为空,请重新输入!"
                    ,"消息", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            conn = DriverDrugManager.getCoon();
            st = conn.createStatement();
            String sql = "insert into UserTable(userName, userPassword) values ('" + username + "','" + password + "')";
            int a = st.executeUpdate(sql);
            conn.close();
            st.close();
            if (a == 1) {
                JOptionPane.showMessageDialog(null, "注册成功");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "该用户名已被占用!"
                    ,"消息", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * 删除用户
     *
     * @param username 用户名
     * @param password  密码
     */
    public void delete(String username, String password) {
        if (login(username, password)) {
            JOptionPane.showMessageDialog(null, "删除成功!");
        } else {
            return;
        }
        try {
            conn = DriverDrugManager.getCoon();
            st = conn.createStatement();
            String sql = "delete from UserTable where userName = '" + username + "' ";
            int a = st.executeUpdate(sql);
            conn.close();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "该用户不存在!"
                    ,"消息", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * 修改用户密码
     *
     * @param username 用户名
     * @param password 旧密码
     * @param newPassword 新密码
     * @return flag
     */
    public boolean update(String username, String password, String newPassword) {
        boolean flag = false;
        boolean update = login(username, password);
        if (update) {
            try {
                conn = DriverDrugManager.getCoon();
                st = conn.createStatement();
                String sql = "update UserTable set userPassword = '" + newPassword + "' where userName = '" + username + "'";
                int a = st.executeUpdate(sql);
                if (a == 1) {
                    JOptionPane.showMessageDialog(null, "修改成功");
                    flag = true;
                }
                conn.close();
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null,"修改失败!",
                    "消息", JOptionPane.ERROR_MESSAGE);
        }
        return flag;
    }
}
