package com.sbw.atrue.Order.Entity;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * FileName: Order <br>
 * Description: 订单类 <br>
 * Author: 沈滨伟-13042299081 <br>
 * Date: 2019/4/8 15:25
 */
public class Order extends DataSupport {
    private  int id; //订单ID
    private  List<ProductOrder> selectedProducts; //被选择的的商品数据
    private  double totalPrice;//该订单的总价
    private  String userName;//客户姓名
    private  String phone;//客户电话
    private  String tableId;//用餐桌号
    private  String time;//下单时间
    private  boolean ispay;//是否已结账

    public int getId() {
        return id;
    }

    public List<ProductOrder> getSelectedProducts() {
        return selectedProducts;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getUserName() {
        return userName;
    }

    public String getPhone() {
        return phone;
    }

    public String getTableId() {
        return tableId;
    }

    public String getTime() {
        return time;
    }

    public boolean isIspay() {
        return ispay;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSelectedProducts(List<ProductOrder> selectedProducts) {
        this.selectedProducts = selectedProducts;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setIspay(boolean ispay) {
        this.ispay = ispay;
    }
}
