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
 * 登陆窗口
 *
 * @author Chan
 * @version 1.0
 */
public class Login extends JFrame implements ActionListener {

    /**
     * 主面板
     */
    JPanel jPanel;

    /**
     * 用户名标签
     */
    JLabel jL1Name;

    /**
     * 密码标签
     */
    JLabel lblPwd;

    /**
     * 用户名文本框框
     */
    JTextField jTfName;

    /**
     * 密码文本框框
     */
    JPasswordField passwordField;

    /**
     * 登陆按钮
     */
    JButton btnLogin;

    /**
     * 注册按钮
     */
    JButton btnRegister;

    /**
     * 修改密码按钮
     */
    JButton btnUpdate;

    /**
     * 删除用户按钮
     */
    JButton btnDelete;

    public Login() {
        //设置窗体大小不可改变
        setResizable(false);
        //设置窗体标题
        setTitle("登陆窗口");
        //设置窗体关闭方式
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //设置窗体大小
        setBounds(100, 100, 400, 500);

        //创建主面板
        jPanel = new JPanel();

        //设置主面板属性
        jPanel.setBackground(Color.WHITE);
        jPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(jPanel);
        jPanel.setLayout(null);

        //设置用户名标签属性
        jL1Name = new JLabel("用户名：");
        jL1Name.setFont(new Font("宋体", Font.PLAIN, 16));
        jL1Name.setBounds(115, 144, 64, 18);
        jPanel.add(jL1Name);


        //设置用户名文本框框属性
        jTfName = new JTextField();
        jTfName.setBounds(180, 143, 156, 21);
        jPanel.add(jTfName);

        jTfName.setColumns(10);

        //设置密码标签属性
        lblPwd = new JLabel("密  码：");
        lblPwd.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPwd.setFont(new Font("幼圆", Font.PLAIN, 16));
        lblPwd.setBounds(115, 175, 64, 15);
        jPanel.add(lblPwd);

        //设置密码文本框框属性
        passwordField = new JPasswordField();
        passwordField.setBounds(180, 172, 156, 21);
        passwordField.addKeyListener(new KeyAdapter() {
            // 为“密码”文本框添加键盘事件的监听
            @Override
            public void keyTyped(KeyEvent e) {
                //当按下回车键时
                char enter = '\n';
                if (e.getKeyChar() == enter) {
                    //触发登录事件
                    btnLogin.doClick();
                }
            }
        });
        jPanel.add(passwordField);

        //设置登录按钮属性
        btnLogin = new JButton("登  陆");
        btnLogin.setFont(new Font("宋体", Font.PLAIN, 16));
        btnLogin.setBounds(100, 216, 100, 20);
        jPanel.add(btnLogin);

        //设置注册按钮属性
        btnRegister = new JButton("注  册");
        btnRegister.setFont(new Font("宋体", Font.PLAIN, 16));
        btnRegister.setBounds(225, 216, 100, 20);
        jPanel.add(btnRegister);

        //设置删除用户按钮属性
        btnDelete = new JButton("删除用户");
        btnDelete.setFont(new Font("宋体", Font.PLAIN, 16));
        btnDelete.setBounds(100, 246, 100, 23);
        jPanel.add(btnDelete);

        //设置修改密码按钮属性
        btnUpdate = new JButton("修改密码");
        btnUpdate.setFont(new Font("宋体", Font.PLAIN, 16));
        btnUpdate.setBounds(225, 246, 100, 23);
        jPanel.add(btnUpdate);

        //添加按钮监听事件
        btnLogin.addActionListener(this);
        btnRegister.addActionListener(this);
        btnDelete.addActionListener(this);
        btnUpdate.addActionListener(this);
    }

    public static void main(String[] args) {
        //创建login对象
        Login login = new Login();
        //设置窗体可见
        login.setVisible(true);

    }

    /**
     * 监听按钮事件
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //如果用户按登陆按钮 触发事件
        if (e.getSource() == btnLogin) {
            //调用btnLogin();方法
            btnLogin();
        }
        //如果用户按注册按钮 触发事件
        else if (e.getSource() == btnRegister) {
            //调用btnRegister();方法
            btnRegister();
        }
        //如果用户按删除用户按钮 触发事件
        else if (e.getSource() == btnDelete) {
            //调用btnDelete()方法
            btnDelete();
        }
        //如果用户按更新密码按钮 触发事件
        else if (e.getSource() == btnUpdate) {
            //关闭当前窗口
            dispose();
            //调用 btuUpdate()方法
            btuUpdate();

        }
    }

    /**
     * 登陆按钮触发事件
     */
    public void btnLogin() {
        //创建loginDAO对象
        LoginDAO loginDAO = new LoginDAO();
        //调用login方法,将用户名框的值和密码框的值传到login方法中,若登陆成功,关闭当前窗口,打开主窗体
        if (loginDAO.login(jTfName.getText(), String.valueOf(passwordField.getPassword()))) {
            //创建mainWindow对象
            MainWindow mainWindow = new MainWindow(jTfName.getText());
            //设置mainWindow为可见
            mainWindow.setVisible(true);
            //关闭当前窗口
            dispose();
        }
    }

    /**
     * 注册按钮触发事件
     */
    public void btnRegister() {
        //创建loginDAO对象
        LoginDAO loginDAO = new LoginDAO();
        //调用register方法
        loginDAO.register(jTfName.getText(), String.valueOf(passwordField.getPassword()));
    }

    /**
     * 删除按钮触发事件
     */
    public void btnDelete() {
        //创建loginDAO对象
        LoginDAO loginDAO = new LoginDAO();
        //调用delete方法
        loginDAO.delete(jTfName.getText(), String.valueOf(passwordField.getPassword()));
    }

    /**
     * 修改密码按钮触发事件
     */
    public void btuUpdate() {
        ModifyUser modifyUser = new ModifyUser();
        modifyUser.setVisible(true);
        dispose();
    }

}
