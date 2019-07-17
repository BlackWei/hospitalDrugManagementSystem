package cn.ggvc.drugadmin.window;

import cn.ggvc.drugadmin.dao.LoginDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 修改密码窗体
 *
 * @author Chen
 * @version 1.0
 */
public class ModifyUser extends JFrame implements ActionListener {
    /**
     * 主面板
     */
    JPanel jPanel;
    /**
     * "用户名"标签
     */
    JLabel jL1Name;
    /**
     * "原密码"标签
     */
    JLabel jlOldPwd;
    /**
     * "新密码"标签
     */
    JLabel jlNewPwd;
    /**
     * "用户名"输入框
     */
    JTextField jTfName;
    /**
     * "原密码"输入框
     */
    JPasswordField passwordField;
    /**
     * "新密码"输入框
     */
    JPasswordField newPasswordField;
    /**
     * "确认修改"按钮
     */
    JButton btnPassword;
    /**
     * "退出修改"按钮
     */
    JButton btnExitModify;

    public ModifyUser() {
        //设置默认字体
        Font font = new Font("宋体", 1, 16);
        //创建新窗体
        setTitle("修改密码");
        //设置窗体大小
        setBounds(500, 200, 400, 350);

        //创建新面板
        jPanel = new JPanel();
        //设置面板属性
        jPanel.setBackground(Color.WHITE);
        jPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(jPanel);
        jPanel.setLayout(null);

        //创建用户名标签
        jL1Name = new JLabel("用户名:");
        //设置用户名标签属性
        jL1Name.setBounds(100, 85, 64, 18);
        jL1Name.setFont(font);
        jPanel.add(jL1Name);
        //创建用户名文本框
        jTfName = new JTextField();
        //设置用户名文本框属性
        jTfName.setBounds(180, 85, 156, 21);
        jPanel.add(jTfName);
        jTfName.setColumns(10);

        //创建原密码标签
        jlOldPwd = new JLabel("原密码:");
        //设置原密码标签属性
        jlOldPwd.setFont(font);
        jlOldPwd.setBounds(100, 130, 64, 18);
        jPanel.add(jlOldPwd);
        //创建原密码文本框
        passwordField = new JPasswordField();
        //设置原密码文本框属性
        passwordField.setBounds(180, 130, 156, 21);
        jPanel.add(passwordField);
        passwordField.setColumns(10);

        //创建"新密码"标签
        jlNewPwd = new JLabel("新密码:");
        //设置"新密码"标签属性
        jlNewPwd.setBounds(100, 175, 64, 18);
        jlNewPwd.setFont(font);
        jPanel.add(jlNewPwd);
        //创建"新密码"文本框
        newPasswordField = new JPasswordField();
        //设置"新密码"文本框属性
        newPasswordField.setBounds(180, 175, 156, 21);
        //创建键盘事件
        newPasswordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char enter = '\n';
                //当按下回车时，触发事件
                if (e.getKeyChar() == enter) {
                    btnPassword();
                }
            }
        });
        jPanel.add(newPasswordField);
        newPasswordField.setColumns(10);

        //"确认修改"按钮
        btnPassword = new JButton("确认修改");
        //设置"确认修改"按钮属性
        btnPassword.setFont(font);
        btnPassword.setBounds(90, 240, 110, 23);
        jPanel.add(btnPassword);

        //"退出修改"按钮
        btnExitModify = new JButton("退出修改");
        //设置"退出修改"按钮属性
        btnExitModify.setFont(font);
        btnExitModify.setBounds(220, 240, 110, 23);
        jPanel.add(btnExitModify);

        btnPassword.addActionListener(this);
        btnExitModify.addActionListener(this);

        //将面板添加到窗体中
        setContentPane(jPanel);
        //设置窗体大小不可改变
        setResizable(false);
    }

    /**
     * 创建按钮触发事件
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //按下"确认修改"按钮触发
        if (e.getSource() == btnPassword) {
            btnPassword();
        }
        //按下"退出修改"按钮触发
        else if (e.getSource() == btnExitModify) {
            btnExitModify();
        }
    }

    /**
     * "确认修改"按钮事件
     */
    public void btnPassword() {
        //创建loginDAO对象
        LoginDAO loginDAO = new LoginDAO();
        //调用loginDAO类中的update方法
        if (loginDAO.update(jTfName.getText(), String.valueOf(passwordField.getPassword()), String.valueOf(newPasswordField.getPassword()))) {
            //关闭当前窗口
            dispose();
            //设置login窗口可见
            Login login = new Login();
            login.setVisible(true);
        }
    }

    /**
     * "退出修改"按钮事件
     */
    public void btnExitModify() {
        Login login = new Login();
        //关闭当前窗口
        dispose();
        //设置login窗口可见
        login.setVisible(true);
    }
}

