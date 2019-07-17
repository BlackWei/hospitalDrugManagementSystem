package cn.ggvc.drugadmin.window;

import cn.ggvc.drugadmin.dao.DrugDAO;
import cn.ggvc.drugadmin.dao.DrugInfo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.List;

/**
 * 主界面
 *
 * @author Chan
 * @version 1.0
 */
public class MainWindow extends JFrame implements ActionListener {
    /**
     * 主面板
     */
    JPanel panelHead;

    /**
     * 入库按钮
     */
    JButton btnAcceptance;

    /**
     * 出库按钮
     */
    JButton btnOutLibrary;

    /**
     * 入库订单查询按钮
     */
    JButton btnInStorageQuery;

    /**
     * 出库订单查询按钮
     */
    JButton btnOutBoundQuery;

    /**
     * 仓库查询按钮
     */
    JButton btnWarehouseQuery;

    /**
     * 操作人面板
     */
    JPanel panelUser;

    /**
     * 验收面板
     */
    JPanel panelAcceptance;

    /**
     * 出库面板
     */
    JPanel panelOutLibrary;

    /**
     * 入库单查询面板
     */
    JPanel panelInStorageQuery;

    /**
     * 出库单查询面板
     */
    JPanel panelOutBoundQuery;

    /**
     * 出库查询面板
     */
    JPanel panelWarehouseQuery;

    /**
     * 确认入库按钮
     */
    JButton btnConfirmAcceptance;

    /**
     * 操作人标签
     */
    JLabel user;
    /**
     * 药品编号
     */
    JLabel jLlDrugId;

    /**
     * 药品名称
     */
    JLabel jLlDrugName;

    /**
     * 药品类型
     */
    JLabel jLlDrugType;

    /**
     * 生产地址
     */
    JLabel jLlAddress;

    /**
     * 入库单价
     */
    JLabel jLlPrice;

    /**
     * 数量
     */
    JLabel jLlAmount;

    /**
     * 药品编号文本框
     */
    JTextField jTfDrugId;

    /**
     * 药品名称文本框
     */
    JTextField jTfDrugName;

    /**
     * 药品类型下拉框
     */
    JComboBox jComboBoxDrugType;

    /**
     * 生产地址文本框
     */
    JTextField jTfAddress;

    /**
     * 单价文本框
     */
    JTextField jTfPrice;

    /**
     * 药品数量文本框
     */
    JTextField jTfAmount;

    /**
     * 确认出库按钮
     */
    JButton btnOutBound;

    /**
     * 出库药品编号标签
     */
    JLabel jLlOutDrugId;

    /**
     * 药出库品名称标签
     */
    JLabel jLlOutDrugName;

    /**
     * 出库数量标签
     */
    JLabel jLlOutAmount;


    /**
     * 出库药品编号文本框
     */
    JTextField jTfOutDrugId;

    /**
     * 出库药品名称文本框
     */
    JTextField jTfOutDrugName;

    /**
     * 出库药品数量文本框
     */
    JTextField jTfOutAmount;

    /**
     * 默认字体
     */
    Font font = new Font("宋体", 1, 16);

    public MainWindow(String userName) {
        //设置窗体标题
        setTitle("主窗体");
        //设置窗体关闭方式
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //设置窗体大小
        setBounds(600, 150, 600, 700);
        //设置窗体布局方式
        getContentPane().setLayout(null);
        //设置窗体大小不可改变
        setResizable(false);

        //顶层面板放置"入库","出库","入库单查询","出库单查询","仓库查询"按钮
        panelHead = new JPanel();
        //设置面板大小
        panelHead.setBounds(2, 2, 590, 40);
        //设置面板颜色
        panelHead.setBackground(Color.lightGray);
        //设置面板可见
        panelHead.setVisible(true);
        //设置面板布局方式
        panelHead.setLayout(new GridLayout(1, 5, 2, 0));

        //顶层"入库"按钮
        btnAcceptance = new JButton("入库");
        //设置字体属性
        btnAcceptance.setFont(new Font("宋体", 1, 12));
        //将"入库"按钮添加到顶层面板中
        btnAcceptance.addActionListener(this);
        panelHead.add(btnAcceptance);

        //顶层"出库"按钮
        btnOutLibrary = new JButton("出库");
        //设置字体属性
        btnOutLibrary.setFont(new Font("宋体", 1, 12));
        btnOutLibrary.addActionListener(this);
        //将"出库"按钮添加到顶层面板中
        panelHead.add(btnOutLibrary);

        //顶层"入库订单查询"按钮
        btnInStorageQuery = new JButton("入库订单查询");
        //设置字体属性
        btnInStorageQuery.setFont(new Font("宋体", 1, 12));
        btnInStorageQuery.addActionListener(this);
        //将"入库订单查询"按钮添加到顶层面板中
        panelHead.add(btnInStorageQuery);

        //顶层"出库订单查询"按钮
        btnOutBoundQuery = new JButton("出库订单查询");
        btnOutBoundQuery.setFont(new Font("宋体", 1, 12));
        btnOutBoundQuery.addActionListener(this);
        panelHead.add(btnOutBoundQuery);

        //顶层"仓库查询"按钮
        btnWarehouseQuery = new JButton("仓库查询");
        btnWarehouseQuery.setFont(new Font("宋体", 1, 12));
        btnWarehouseQuery.addActionListener(this);
        panelHead.add(btnWarehouseQuery);


        //将顶层面板添加到窗体中
        getContentPane().add(panelHead);

        //创建"操作人"面板
        panelUser = new JPanel();
        panelUser.setBounds(5, 50, 580, 40);
        panelUser.setLayout(null);

        //创建"操作人"标签
        user = new JLabel();
        //获取"操作人"名称
        user.setText("操作人:" + userName);
        //设置字体
        user.setFont(font);
        //设置属性
        user.setBounds(10, 5, 200, 25);
        //将"操作人"标签添加到操作人面板中
        panelUser.add(user);

        //将"操作人"面板添加到窗体中
        getContentPane().add(panelUser);

        //调用panelAcceptance方法，将"入库"面板添加到主面板中
        getContentPane().add(panelAcceptance());

        //调用panelOutLibrary方法,将"出库"面板添加到主面板中
        getContentPane().add(panelOutLibrary());

        getContentPane().add(panelInStorageQuery());

        getContentPane().add(panelOutBoundQuery());

        getContentPane().add(panelWarehouseQuery());

    }

    /**
     * 入库面板
     *
     * @return 入库面板
     */
    public JPanel panelAcceptance() {
        panelAcceptance = new JPanel();
        //设置面板大小
        panelAcceptance.setBounds(5, 90, 580, 500);
        //设置面板布局方式
        panelAcceptance.setLayout(null);

        //创建"药品编号"标签
        jLlDrugId = new JLabel("药品编号:");
        jLlDrugId.setFont(font);
        jLlDrugId.setBounds(170, 50, 100, 21);
        panelAcceptance.add(jLlDrugId);

        //创建"药品编号"输入框
        jTfDrugId = new JTextField();
        jTfDrugId.setFont(font);
        jTfDrugId.setBounds(270, 50, 156, 21);
        panelAcceptance.add(jTfDrugId);
        jTfDrugId.setColumns(10);

        //创建"药品名"标签
        jLlDrugName = new JLabel("药 品 名:");
        jLlDrugName.setFont(font);
        jLlDrugName.setBounds(170, 100, 100, 21);
        panelAcceptance.add(jLlDrugName);

        //创建"药品名"输入框
        jTfDrugName = new JTextField();
        jTfDrugName.setFont(font);
        jTfDrugName.setBounds(270, 100, 156, 21);
        panelAcceptance.add(jTfDrugName);

        //创建"药品类型"标签
        jLlDrugType = new JLabel("药品类型：");
        jLlDrugType.setFont(font);
        jLlDrugType.setBounds(170, 150, 100, 21);
        panelAcceptance.add(jLlDrugType);

        //设置"药品类型"下拉框键
        jComboBoxDrugType = new JComboBox();
        jComboBoxDrugType.addItem("OTC");
        jComboBoxDrugType.addItem("非OTC");
        jComboBoxDrugType.setBounds(270, 150, 100, 21);
        panelAcceptance.add(jComboBoxDrugType);

        //创建"生产地址"标签
        jLlAddress = new JLabel("生产地址：");
        jLlAddress.setFont(font);
        jLlAddress.setBounds(170, 200, 100, 21);
        panelAcceptance.add(jLlAddress);

        //创建"生产地址"输入框
        jTfAddress = new JTextField();
        jTfAddress.setFont(font);
        jTfAddress.setBounds(270, 200, 156, 21);
        panelAcceptance.add(jTfAddress);

        jLlAmount = new JLabel("数    量：");
        jLlAmount.setFont(font);
        jLlAmount.setBounds(170, 250, 100, 21);
        panelAcceptance.add(jLlAmount);

        jTfAmount = new JTextField();
        jTfAmount.setFont(font);
        jTfAmount.setBounds(270, 250, 156, 21);
        panelAcceptance.add(jTfAmount);

        jLlPrice = new JLabel("单    价:");
        jLlPrice.setFont(font);
        jLlPrice.setBounds(170, 300, 100, 21);
        panelAcceptance.add(jLlPrice);

        jTfPrice = new JTextField();
        jTfPrice.setFont(font);
        jTfPrice.setBounds(270, 300, 156, 21);
        //为"单价"输入框添加键盘监听事件
        jTfPrice.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char enter = '\n';
                //当在"单价"输入框按下回车键时
                if (e.getKeyChar() == enter) {
                    //调用btnConfirmAcceptance()方法
                    try {
                        btnConfirmAcceptance();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        panelAcceptance.add(jTfPrice);

        btnConfirmAcceptance = new JButton("确认入库!");
        //设置字体
        btnConfirmAcceptance.setFont(font);
        //设置"确认入库"按钮属性
        btnConfirmAcceptance.setBounds(230, 400, 130, 30);
        btnConfirmAcceptance.addActionListener(this);
        //将"确认入库"按钮添加到验收面板
        panelAcceptance.add(btnConfirmAcceptance);

        //设置"入库"面板可见
        panelAcceptance.setVisible(true);
        return panelAcceptance;
    }

    /**
     * 出库面板
     *
     * @return 出库面板
     */
    public JPanel panelOutLibrary() {
        //创建出库面板
        panelOutLibrary = new JPanel();
        //设置面板属性
        panelOutLibrary.setBounds(5, 90, 580, 500);
        //设置布局方式
        panelOutLibrary.setLayout(null);

        //出库"药品编号"标签
        jLlOutDrugId = new JLabel("药品编号：");
        jLlOutDrugId.setFont(font);
        jLlOutDrugId.setBounds(170, 100, 100, 21);
        panelOutLibrary.add(jLlOutDrugId);

        //出库"药品编号"文本框
        jTfOutDrugId = new JTextField();
        jTfOutDrugId.setFont(font);
        jTfOutDrugId.setBounds(270, 100, 156, 21);
        panelOutLibrary.add(jTfOutDrugId);

        //出库"药品名称"标签
        jLlOutDrugName = new JLabel("药品名称：");
        jLlOutDrugName.setFont(font);
        jLlOutDrugName.setBounds(170, 150, 100, 21);
        panelOutLibrary.add(jLlOutDrugName);

        //出库"药品名称"文本框
        jTfOutDrugName = new JTextField();
        jTfOutDrugName.setFont(font);
        jTfOutDrugName.setBounds(270, 150, 156, 21);
        panelOutLibrary.add(jTfOutDrugName);

        //出库"药品数量"标签
        jLlOutAmount = new JLabel("药品数量：");
        jLlOutAmount.setFont(font);
        jLlOutAmount.setBounds(170, 200, 100, 21);
        panelOutLibrary.add(jLlOutAmount);

        //出库"药品数量"文本框
        jTfOutAmount = new JTextField();
        jTfOutAmount.setFont(font);
        jTfOutAmount.setBounds(270, 200, 156, 21);
        //如果在药品数量文本框按下回车,则触发btnOutBound方法
        jTfOutAmount.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char enter = '\n';
                if (e.getKeyChar() == enter) {
                    btnOutBound();
                }
            }
        });
        panelOutLibrary.add(jTfOutAmount);

        //"确认出库"按钮
        btnOutBound = new JButton("确认出库");
        btnOutBound.setFont(font);
        btnOutBound.setBounds(240, 250, 130, 25);
        btnOutBound.addActionListener(this);
        panelOutLibrary.add(btnOutBound, BorderLayout.CENTER);

        //设置窗体默认不可见，将窗体添加到主面板中
        panelOutLibrary.setVisible(false);
        return panelOutLibrary;
    }

    /**
     * 入库单查询面
     *
     * @return 入库单面板
     */
    public JPanel panelInStorageQuery() {
        panelInStorageQuery = new JPanel();
        panelInStorageQuery.setBounds(5, 90, 580, 500);
        panelInStorageQuery.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 20, 580, 250);
        DrugDAO drugDAO = new DrugDAO();
        String[] columnName = {"入库编号", "药品编号", "药品名称", "药品类型"
                , "生产地址", "数    量", "价    格", "入库日期"};
        List<DrugInfo> drugInfos = drugDAO.getInStorageList();
        String[][] tableValues = new String[drugInfos.size()][columnName.length];
        for (int i = 0; i < drugInfos.size(); i++) {
            DrugInfo drugInfo = drugInfos.get(i);
            tableValues[i][0] = drugInfo.getInStorageOrder();
            tableValues[i][1] = drugInfo.getDrugId();
            tableValues[i][2] = drugInfo.getDrugName();
            tableValues[i][3] = drugInfo.getDrugType();
            tableValues[i][4] = drugInfo.getAddress();
            tableValues[i][5] = drugInfo.getAmount();
            tableValues[i][6] = drugInfo.getPrice();
            tableValues[i][7] = drugInfo.getStorageDate();
        }
//        getTable(tableValues,columnName);
        scrollPane.setViewportView(getTable(tableValues,columnName));
        panelInStorageQuery.add(scrollPane);

        return panelInStorageQuery;
    }


    /**
     * 出库单查询面板
     *
     * @return 出库面板
     */
    public JPanel panelOutBoundQuery() {
        panelOutBoundQuery = new JPanel();
        panelOutBoundQuery.setLayout(null);
        panelOutBoundQuery.setBounds(5, 90, 580, 500);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 20, 580, 250);
        DrugDAO drugDAO = new DrugDAO();
        String[] columnName = {"出库单编号", "药品编号", "药品名称", "药品数量", "出库日期"};
        List<DrugInfo> drugInfos = drugDAO.getOurBoundList();
        String[][] tableValues = new String[drugInfos.size()][columnName.length];
        for (int i = 0; i < drugInfos.size(); i++) {
            DrugInfo drugInfo = drugInfos.get(i);
            tableValues[i][0] = drugInfo.getOutBoundOrder();
            tableValues[i][1] = drugInfo.getDrugId();
            tableValues[i][2] = drugInfo.getDrugName();
            tableValues[i][3] = drugInfo.getAmount();
            tableValues[i][4] = drugInfo.getOutBoundDate();
        }
        scrollPane.setViewportView(getTable(tableValues,columnName));
        panelOutBoundQuery.add(scrollPane);

        return panelOutBoundQuery;
    }

    /**
     * 仓库查询面板
     *
     * @return 仓库查询面板
     */
    public JPanel panelWarehouseQuery() {
        panelWarehouseQuery = new JPanel();
        panelWarehouseQuery.setBounds(5, 90, 580, 500);
        panelWarehouseQuery.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 20, 580, 250);
        DrugDAO drugDAO = new DrugDAO();
        String[] columnName = {"药品编号", "药品名称", "药品数量"};
        List<DrugInfo> drugInfos = drugDAO.getWarehouseList();
        String[][] tableValues = new String[drugInfos.size()][columnName.length];
        for (int i = 0; i < drugInfos.size(); i++) {
            DrugInfo drugInfo = drugInfos.get(i);
            tableValues[i][0] = drugInfo.getDrugId();
            tableValues[i][1] = drugInfo.getDrugName();
            tableValues[i][2] = drugInfo.getAmount();
        }

        scrollPane.setViewportView(getTable(tableValues,columnName));
        panelWarehouseQuery.add(scrollPane);

        return panelWarehouseQuery;
    }

    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow("");
        mainWindow.setVisible(true);
    }

    /**
     * 按钮触发事件
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //如果用户按'入库'按钮，触发事件
        if (e.getSource() == btnAcceptance) {
            //调用btnAcceptance方法
            btnAcceptance();
        }
        //如果用户按'出库'按钮，触发事件
        else if (e.getSource() == btnOutLibrary) {
            //调用btnOutLibrary方法
            btnOutLibrary();
        }
        //如果用户按'入库订单查'按钮，触发事件
        else if (e.getSource() == btnInStorageQuery) {
            //调用btnQuery方法
            btnInStorageQuery();
        }
        //如果用户按"出库订单查询"按钮,触发事件
        else if (e.getSource() == btnOutBoundQuery) {
            //调用btnOutBoundQuery方法
            btnOutBoundQuery();
        } else if (e.getSource() == btnWarehouseQuery) {
            btnWarehouseQuery();

        }
        //如果用按‘确认验收’按钮，触发事件
        else if (e.getSource() == btnConfirmAcceptance) {
            //调用btnConfirmAcceptance方法
            try {
                btnConfirmAcceptance();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        //如果用户按‘确认出库’按钮，触发事件
        else if (e.getSource() == btnOutBound) {
            //调用btnOutBound方法
            btnOutBound();
        }
        //如果用户按‘按编号查询’按钮，触发事件
    }

    /**
     * 按"入库"按钮触发事件
     */
    public void btnAcceptance() {
        //设置"验收"面板可见，其他面板不可见
        panelAcceptance.setVisible(true);
        panelOutLibrary.setVisible(false);
        panelInStorageQuery.setVisible(false);
        panelOutBoundQuery.setVisible(false);
        panelWarehouseQuery.setVisible(false);
    }

    /**
     * 按"出库"按钮触发事件
     */
    public void btnOutLibrary() {
        //设置"出库"面板可见，其他面板不可见
        panelAcceptance.setVisible(false);
        panelOutLibrary.setVisible(true);
        panelInStorageQuery.setVisible(false);
        panelOutBoundQuery.setVisible(false);
        panelWarehouseQuery.setVisible(false);
    }

    /**
     * 按"入库单查询"按钮触发事件
     */
    public void btnInStorageQuery() {
        //设置"入库单查询"面板可见，其他面板不可见
        panelAcceptance.setVisible(false);
        panelOutLibrary.setVisible(false);
        panelInStorageQuery.setVisible(true);
        panelOutBoundQuery.setVisible(false);
        panelWarehouseQuery.setVisible(false);
    }

    /**
     * 按"出库单查询"按钮触发事件
     */
    public void btnOutBoundQuery() {
        panelAcceptance.setVisible(false);
        panelOutLibrary.setVisible(false);
        panelInStorageQuery.setVisible(false);
        panelOutBoundQuery.setVisible(true);
        panelWarehouseQuery.setVisible(false);
    }

    /**
     * 按"仓库查询"按钮触发事件
     */
    public void btnWarehouseQuery() {
        panelAcceptance.setVisible(false);
        panelOutLibrary.setVisible(false);
        panelInStorageQuery.setVisible(false);
        panelOutBoundQuery.setVisible(false);
        panelWarehouseQuery.setVisible(true);
    }

    /**
     * 按"确认入库"按钮触发事件
     */
    public void btnConfirmAcceptance() throws SQLException {
        DrugDAO dDAO = new DrugDAO();
        if (dDAO.addDrug(jTfDrugId.getText(), jTfDrugName.getText(), jComboBoxDrugType.getSelectedItem().toString()
                , jTfAddress.getText(), jTfAmount.getText(), jTfPrice.getText())) {
            //弹出消息提示用户入库成功
            JOptionPane.showMessageDialog(null, "入库成功!");
            //将输入框清空，方便用户再次输入
            jTfDrugId.setText("");
            jTfDrugName.setText("");
            jTfAddress.setText("");
            jTfPrice.setText("");
            jTfAmount.setText("");
        } else {
            //弹出消息提示用户入库失败
            JOptionPane.showMessageDialog(null, "入库失败!");
        }
    }

    /**
     * 按"确认出库"按钮触发事件
     */
    public void btnOutBound() {
        //创建DrugDAO对象
        DrugDAO drugDAO = new DrugDAO();
        if (drugDAO.outDrug(jTfOutDrugId.getText(), jTfOutDrugName.getText(), jTfOutAmount.getText())) {
            //提示用户"出库成功"
            JOptionPane.showMessageDialog(null, "出库成功!");
            //清空输入框
            jTfOutDrugId.setText("");
            jTfOutDrugName.setText("");
            jTfOutAmount.setText("");
        } else {
            //提示用户"出库失败"
            JOptionPane.showMessageDialog(null, "出库失败!");
        }
    }

    /**
     * 创建表格
     *
     * @param tableValues 表格列
     * @param columnName 列名
     * @return 表格
     */
    public JTable getTable(String[][] tableValues,String[] columnName){
        DefaultTableModel tableModel = new DefaultTableModel(tableValues, columnName);
        JTable table = new JTable(tableModel);
        table.setRowSorter(new TableRowSorter<DefaultTableModel>(tableModel));
        table.setEnabled(false);
        return table;
    }

}
