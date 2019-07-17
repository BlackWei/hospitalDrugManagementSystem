package cn.ggvc.drugadmin.dao;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库访问类，封装对药品入库表和药品出库表的基本操作
 *
 * @author Chan
 * @version 1.0
 */
public class DrugDAO {
    /**
     * 创建Connection对象
     */
    Connection conn;
    /**
     * 创建PreparedStatement对象
     */
    PreparedStatement ps;
    /**
     * 创建ResultSet对象
     */
    ResultSet rs;
    /**
     * 创建Statement对象
     */
    Statement st;

    /**
     * 获取入库单中的信息,存入DrugInfo中
     *
     * @return 入库单的信息
     */
    public List<DrugInfo> getInStorageList() {
        List<DrugInfo> drugInfos = new ArrayList<DrugInfo>();
        conn = DriverDrugManager.getCoon();
        try {
            String sql = "select * from InBoundOrder";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                DrugInfo drugInfo = new DrugInfo();
                drugInfo.setInStorageOrder(rs.getString(1));
                drugInfo.setDrugId(rs.getString(2));
                drugInfo.setDrugName(rs.getString(3));
                drugInfo.setDrugType(rs.getString(4));
                drugInfo.setAddress(rs.getString(5));
                drugInfo.setAmount(rs.getString(6));
                drugInfo.setPrice(rs.getString(7));
                drugInfo.setStorageDate(rs.getString(8));
                drugInfos.add(drugInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drugInfos;
    }

    /**
     * 获取出库单的信息,存入DrugInfo中
     *
     * @return 出库单的信息
     */
    public List<DrugInfo> getOurBoundList(){
        List<DrugInfo> drugInfos = new ArrayList<DrugInfo>();
        conn = DriverDrugManager.getCoon();
        try {
            String sql = "select * from OutBoundOrder";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                DrugInfo drugInfo = new DrugInfo();
                drugInfo.setOutBoundOrder(rs.getString(1));
                drugInfo.setDrugId(rs.getString(2));
                drugInfo.setDrugName(rs.getString(3));
                drugInfo.setAmount(rs.getString(4));
                drugInfo.setOutBoundDate(rs.getString(5));
                drugInfos.add(drugInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drugInfos;
    }

    /**
     * 获取仓库的信息，存入DrugInfo中
     *
     * @return 仓库中的信息
     */
    public List<DrugInfo> getWarehouseList(){
        List<DrugInfo> drugInfos = new ArrayList<DrugInfo>();
        conn = DriverDrugManager.getCoon();
        try {
            String sql = "select * from Warehouse";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                DrugInfo drugInfo = new DrugInfo();
                drugInfo.setDrugId(rs.getString(1));
                drugInfo.setDrugName(rs.getString(2));
                drugInfo.setAmount(rs.getString(3));
                drugInfos.add(drugInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drugInfos;
    }

    /**
     * 药品入库方法，将输入信息存入入库表中
     *
     * @param drugId      药品ID
     * @param drugName    药品名称
     * @param drugType    药品类型
     * @param drugAddress 生产地址
     * @param amount      药品数量
     * @param price       药品价格
     * @return flag
     */
    public boolean addDrug(String drugId, String drugName, String drugType, String drugAddress, String amount, String price) throws SQLException {
        boolean flag = false;
        //连接数据库引擎
        conn = DriverDrugManager.getCoon();
        //添加药品信息到入库表中
        try {
            //若仓库中已存在该药品编号,则直接将药品数量增加即可
            st = conn.createStatement();
            //查询仓库中药品的数量
            String selectSql = "select amount from Warehouse where drugID = " + drugId + " and drugName = '" + drugName + "'";
            rs = st.executeQuery(selectSql);
            if (rs.next()) {
                //获得仓库的药品数量
                int allAmount = rs.getInt(1);
                //将入库的药品数量转换为int型
                int inAmount = Integer.parseInt(amount);
                //将要入库的药品数量与仓库的药品数量相加
                int upAmount = allAmount + inAmount;
                //更新仓库表
                String updateSql = "update Warehouse set amount = " + upAmount + " where drugID = " + drugId + " and drugName = '" + drugName + "'";
                ps = conn.prepareStatement(updateSql);
                ps.executeUpdate();

                getInsertSql(drugId, drugName, drugType, drugAddress, amount, price);
                flag = ps.executeUpdate() != 0 ? true : false;
            } else {
                String sql = "insert into Warehouse values (?,?,?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1, drugId);
                ps.setString(2, drugName);
                ps.setString(3, amount);
                ps.executeUpdate();

                getInsertSql(drugId, drugName, drugType, drugAddress, amount, price);
                flag = ps.executeUpdate() != 0 ? true : false;
            }
        } catch (SQLException e) {
            //抛出异常
            JOptionPane.showMessageDialog(null,"药品编号必须为数字!");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 药品出库方法，将输入信息存入出库表中
     *
     * @param drugId   药品编号
     * @param drugName 药品名称
     * @param amount   药品数量
     * @return flag
     */
    public boolean outDrug(String drugId, String drugName, String amount) {
        boolean flag = false;
        conn = DriverDrugManager.getCoon();
        try {
            st = conn.createStatement();
            String selectSql = "select amount from Warehouse where drugID = " + drugId + " and drugName = '" + drugName + "'";
            rs = st.executeQuery(selectSql);
            if (rs.next()) {
                //获得仓库的药品数量
                int allAmount = rs.getInt(1);
                //将出库药品数量转换为int型
                int outAmount = Integer.parseInt(amount);
                //比较仓库数量和出库数量,若仓库数量大于出库数量,才可出库，返回true
                if (allAmount >= outAmount) {
                    //定义数据update,用于保存出库后还剩下的药品数量
                    int upAmount = allAmount - outAmount;
                    //SQL更新语句,更新仓库表的信息
                    String updateSql = "update Warehouse set amount = " + upAmount + " where drugID = " + drugId + " and drugName = '" + drugName + "'";
                    //执行SQL语句
                    ps = conn.prepareStatement(updateSql);
                    ps.executeUpdate();

                    //SQL插入语句，将信息插入到出库表中
                    String insertSql = "insert into OutBoundOrder(drugID, drugName, amount) values (" + drugId + ",'" + drugName + "' ," + amount + ")";
                    ps = conn.prepareStatement(insertSql);
                    //判断是否更新成功,成功返回true,失败返回false
                    flag = ps.executeUpdate() != 0 ? true : false;
                }
                //若仓库数量小于出库数量,出库失败,返回false
                else {
                    JOptionPane.showMessageDialog(null, "库存不足");
                    flag = false;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "药品编号必须为数字!");
            e.printStackTrace();
        }
        return flag;
    }

    public void getInsertSql(String drugId, String drugName, String drugType, String drugAddress, String amount, String price) {

        //创建数据库语法
        String insertSql = "insert into InBoundOrder (drugID, drugName, drugType, drugAddress, amount, price) values (?,?,?,?,?,?)";
        try {
            //执行数据库语句
            ps = conn.prepareStatement(insertSql);
            //将得到的值插入到入库表中
            ps.setString(1, drugId);
            ps.setString(2, drugName);
            ps.setString(3, drugType);
            ps.setString(4, drugAddress);
            ps.setString(5, amount);
            ps.setString(6, price);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
