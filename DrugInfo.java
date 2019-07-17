package cn.ggvc.drugadmin.dao;

import java.io.Serializable;

/**
 * 药品信息类，实体类，存放药品基本信息及基本操作
 *
 * @author Chan
 * @version 1.0
 */
public class DrugInfo implements Serializable {
    /**
     * 入库单编号
     */
    private String inStorageOrder;

    /**
     * 出库单编号
     */
    private String outBoundOrder;

    /**
     * 药品编号
     **/
    private String drugId;

    /**
     * 药品名称
     **/
    private String drugName;

    /**
     * 药品类型
     */
    private String drugType;

    /**
     * 生产地址
     */
    private String address;

    /**
     * 数量
     */
    private String amount;

    /**
     * 入库单价
     */
    private String price;

    /**
     * 入库日期
     */
    private String storageDate;

    /**
     * 出库日期
     */
    private String outBoundDate;

    public DrugInfo() {

    }

    /**
     * 入库单构造方法
     *
     * @param drugId   药品编号
     * @param drugName 药品名称
     * @param drugType 药品类型
     * @param address  生产地址
     * @param amount   数量
     * @param price    价格
     */
    public DrugInfo(String drugId, String drugName, String drugType, String address, String amount, String price) {
        this.drugId = drugId;
        this.drugName = drugName;
        this.drugType = drugType;
        this.address = address;
        this.amount = amount;
        this.price = price;
    }

    /**
     * 出库单构造方法
     *
     * @param drugId   药品编号
     * @param drugName 药品名称
     * @param amount   数量
     */
    public DrugInfo(String drugId, String drugName, String amount) {
        this.drugId = drugId;
        this.drugName = drugName;
        this.amount = amount;
    }

    public String getInStorageOrder() {
        return inStorageOrder;
    }

    public void setInStorageOrder(String inStorageOrder) {
        this.inStorageOrder = inStorageOrder;
    }

    public String getOutBoundOrder() {
        return outBoundOrder;
    }

    public void setOutBoundOrder(String outBoundOrder) {
        this.outBoundOrder = outBoundOrder;
    }

    public String getDrugId() {
        return drugId;
    }

    public void setDrugId(String drugId) {
        this.drugId = drugId;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getDrugType() {
        return drugType;
    }

    public void setDrugType(String drugType) {
        this.drugType = drugType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStorageDate() {
        return storageDate;
    }

    public void setStorageDate(String storageDate) {
        this.storageDate = storageDate;
    }

    public String getOutBoundDate() {
        return outBoundDate;
    }

    public void setOutBoundDate(String outBoundDate) {
        this.outBoundDate = outBoundDate;
    }
}
