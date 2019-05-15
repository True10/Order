package com.sbw.atrue.Order.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sbw.atrue.Order.Entity.Order;
import com.sbw.atrue.Order.Entity.ProductOrder;
import com.sbw.atrue.Order.R;
import com.sbw.atrue.Order.Entity.Product;
import com.sbw.atrue.Order.Util.ShareUtils;

import org.litepal.tablemanager.Connector;

public class SuccessActivity extends Activity{
    private TextView tvResult; //订单信息显示文字控件
    private Button btnContinueBuy; //加菜按钮
    private Button btnPay; //结账按钮
    private ArrayList<Product> selectedProducts = new ArrayList<Product>(); //被选择的的商品数据
    private String nowTime;//订单提交时间
    private double totalPrice = 0; //购买商品总价
    private boolean ispay = false; //判断订单是否已被支付

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        initViews(); //初始化页面控件
        initDatas(); //初始化页面数据
        initEvents(); //初始化控件事件
    }

    //初始化页面控件
    private void initViews() {
        tvResult = (TextView) findViewById(R.id.tv_result);
        btnContinueBuy = (Button) findViewById(R.id.btn_continue_buy);
        btnPay = (Button) findViewById(R.id.btn_pay);
    }

    /**
     * 初始化控件事件
     */
    private void initEvents() {
        showDialog("您的订单提交成功！请您及时支付！");
        //给加菜按钮设置点击事件
        btnContinueBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ispay){
                    //跳转到购物界面
                    startActivity(new Intent(getApplicationContext(), ShoppingActivity.class));
                    //结束当前界面
                    SuccessActivity.this.finish();
                }else{
                    showDialog("您尚未支付当前订单！请结账后再加菜！");
                }
            }
        });

        //给结账按钮设置点击事件
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //保存订单信息至SQLite数据库中，可惜Litepal框架使用失败。
                //transformData();
                if(ispay){
                    showDialog("该订单您已支付过啦！");
                }else{
                    int myMoney= ShareUtils.getInt(SuccessActivity.this,"money",1);
                    if(totalPrice>myMoney){
                        //当账户余额的钱不足以支付当前订单时
                        showDialogNoMoney();
                    }else{
                        myMoney-=totalPrice;
                        //更新账户余额
                        ShareUtils.putInt(SuccessActivity.this,"money",myMoney);
                        //将订单标记为“已结账”,并将结账按钮设置为不可见状态
                        ispay=true;
                        btnPay.setVisibility(View.INVISIBLE);
                        //结账成功后，客户选择返回主界面，还是继续加菜
                        showDialogFinish();
                    }
                }
            }
        });
    }

    /**
     * 初始化页面数据
     */
    private void initDatas() {
        Intent otherIntent = getIntent(); //获取信息输入界面传来的意图
        selectedProducts = otherIntent.getParcelableArrayListExtra("selectedProducts"); //从意图中获取被选择的的商品数据
        String name = otherIntent.getStringExtra("name"); //从意图获取客户姓名
        String phone = otherIntent.getStringExtra("phone"); //从意图获取手机号
        String tableId = otherIntent.getStringExtra("table"); //从意图获取用餐桌号
        StringBuilder orderResult = new StringBuilder(); //新建订单结果信息
        orderResult.append("\t\t\t\t\t\t\t订单信息如下\n");//添加文本内容
        orderResult.append("*************************\n");
        orderResult.append("商品名\t\t\t数量\t\t\t\t价格\n");
        for (int i = 0; i < selectedProducts.size(); i++) { //遍历商品列表并往文本写入商品信息
            Product product = selectedProducts.get(i);
            if(product.getName().length()==2){
                orderResult.append(product.getName() + "\t\t\t\t\t\t\t " + product.getSelectedCount() + " \t\t\t\t\t" + product.getPrice() * product.getSelectedCount() + "\n");
            }else{
                orderResult.append(product.getName() + "\t\t\t\t\t" + product.getSelectedCount() + "\t\t\t\t\t" + product.getPrice() * product.getSelectedCount() + "\n");
            }
            totalPrice += product.getPrice() * product.getSelectedCount(); //设置商品总价
        }
        orderResult.append("*************************");
        orderResult.append("\n共计：" + totalPrice + "元\n\n");
        orderResult.append("\t\t\t\t\t\t\t您的信息如下\n");
        orderResult.append("*************************\n");
        orderResult.append("姓名：" + name + "\n");
        orderResult.append("电话：" + phone + "\n");
        orderResult.append("桌号：" + tableId + "\n");
        //获取当前系统时间(记录下单时间)
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        nowTime=simpleDateFormat.format(date);
        orderResult.append("时间："+nowTime + "\n");
        //将订单结果信息设置到订单信息显示文字控件
        tvResult.setText(orderResult);
    }

    /**
     * 将该订单信息保存到数据库中，便于客户查询
     */
    private void transformData(){
        Connector.getDatabase();//创建数据库
        Intent otherIntent = getIntent(); //获取信息输入界面传来的意图
        selectedProducts = otherIntent.getParcelableArrayListExtra("selectedProducts"); //从意图中获取被选择的的商品数据
        String name = otherIntent.getStringExtra("name"); //从意图获取客户姓名
        String phone = otherIntent.getStringExtra("phone"); //从意图获取手机号
        String tableId = otherIntent.getStringExtra("table"); //从意图获取用餐桌号
        List<ProductOrder> productOrders=new ArrayList<>();
        ProductOrder productOrder=new ProductOrder();
        for (int i = 0; i < selectedProducts.size(); i++) { //遍历商品列表并存入到productOrders列表中
            Product product = selectedProducts.get(i);
            productOrder.setFoodName(product.getName());
            productOrder.setNum(product.getSelectedCount());
            productOrder.setPrice(product.getPrice()* product.getSelectedCount());
            productOrders.add(productOrder);
        }
        Order newOrder=new Order();
        newOrder.setSelectedProducts(productOrders);
        newOrder.setUserName(name);
        newOrder.setPhone(phone);
        newOrder.setTableId(tableId);
        newOrder.setTime(nowTime);
        newOrder.save();
    }


    /**
     * 为了方便，定义一个弹框控件的函数
     * @param msg 要显示的提示信息
     */
    private void showDialog(String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    /**
     * 当结账时钱不够的弹框提示
     */
    private void showDialogNoMoney(){
        AlertDialog.Builder dialog=new AlertDialog.Builder(SuccessActivity.this);
        dialog.setTitle("账户余额不足\n\n");
        dialog.setMessage("请您到充值页面进行充值后再结账！");
        dialog.setCancelable(false);
        dialog.setPositiveButton("好的", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(getApplicationContext(), MyMoneyActivity.class));
            }
        });
        dialog.setNegativeButton("不吃了", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(SuccessActivity.this, "您选择不吃了！", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }

    /**
     * 成功结账后的弹框提示
     */
    private void showDialogFinish(){
        AlertDialog.Builder dialog=new AlertDialog.Builder(SuccessActivity.this);
        dialog.setTitle("订单支付成功！\n\n");
        dialog.setMessage("您是否想继续加菜？");
        dialog.setCancelable(false);
        dialog.setPositiveButton("是的", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(SuccessActivity.this, "请继续点餐~", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setNegativeButton("不用了", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(SuccessActivity.this, "感谢您的光临！祝您用餐愉快！", Toast.LENGTH_SHORT).show();
                //跳转到购物界面
                startActivity(new Intent(getApplicationContext(), OrderActivity.class));
                SuccessActivity.this.finish(); //结束当前界面
            }
        });
        dialog.show();
    }
}
