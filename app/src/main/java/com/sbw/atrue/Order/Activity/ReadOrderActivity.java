package com.sbw.atrue.Order.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.sbw.atrue.Order.Entity.Order;
import com.sbw.atrue.Order.Entity.Product;
import com.sbw.atrue.Order.Entity.ProductOrder;
import com.sbw.atrue.Order.R;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * FileName: ReadOrderActivity <br>
 * Description: 查看历史订单信息 <br>
 * Author: 沈滨伟-13042299081 <br>
 * Date: 2019/4/9 10:52
 */
public class ReadOrderActivity extends Activity {

    private TextView tvResult; //订单信息显示文字控件
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readorder);
        tvResult = (TextView) findViewById(R.id.tv_result);
        //从数据库中读取订单信息
        //readFromSqlite();
        //暴力输出订单信息
        readBaoLi();
    }

    /**
     * 基于LitePal框架，从SQlite数据库中读取客户的订单信息并输出
     */
    private void readFromSqlite(){
        //从SQLite数据库读取历史订单列表
        List<Order> orders=DataSupport.findAll(Order.class);
        //遍历订单列表并输出订单信息
        for(Order order:orders){
            StringBuilder orderResult = new StringBuilder(); //新建订单结果信息
            orderResult.append("\t\t\t\t\t\t\t订单信息如下\n");//添加文本内容
            orderResult.append("*************************\n");
            orderResult.append("商品名\t\t\t数量\t\t\t\t价格\n");
            double totalPrice = 0; //购买商品总价
            for (int i = 0; i < order.getSelectedProducts().size(); i++) { //遍历商品列表并往文本写入商品信息
                ProductOrder product = order.getSelectedProducts().get(i);
                if(product.getFoodName().length()==2){
                    orderResult.append(product.getFoodName() + "\t\t\t\t\t\t\t " + product.getNum() + " \t\t\t\t\t" + product.getPrice() + "\n");
                }else{
                    orderResult.append(product.getFoodName() + "\t\t\t\t\t" + product.getNum() + "\t\t\t\t\t" + product.getPrice()+ "\n");
                }
                totalPrice += product.getPrice(); //设置商品总价
            }
            orderResult.append("*************************");
            orderResult.append("\n共计：" + totalPrice + "元\n\n");
            orderResult.append("\t\t\t\t\t\t\t您的信息如下\n");
            orderResult.append("*************************\n");
            orderResult.append("姓名：" + order.getUserName() + "\n");
            orderResult.append("电话：" + order.getPhone() + "\n");
            orderResult.append("桌号：" + order.getTableId()+ "\n");
            orderResult.append("时间："+ order.getTime()+ "\n");
            //将订单结果信息设置到订单信息显示文字控件
            tvResult.setText(orderResult);
        }
    }

    /**
     * 暴力输出订单信息，不基于数据库
     */
    private void readBaoLi(){
        StringBuilder orderResult = new StringBuilder(); //新建订单结果信息
        orderResult.append("\t\t\t\t\t\t\t订单1\n");//添加文本内容
        orderResult.append("*************************\n");
        orderResult.append("商品名\t\t\t数量\t\t\t\t价格\n");
        orderResult.append("牛肉丸"+ "\t\t\t\t\t" + 2 + " \t\t\t\t\t" + 76 + "\n");
        orderResult.append("嫩肉"+ "\t\t\t\t\t\t\t " + 2 + " \t\t\t\t\t" + 76 + "\n");
        orderResult.append("五花趾"+ "\t\t\t\t\t" + 1 + " \t\t\t\t\t" + 38 + "\n");
        orderResult.append("*************************");
        orderResult.append("\n共计：" + 152 + "元\n");
        orderResult.append("姓名：" + "沈滨伟" + "\n");
        orderResult.append("电话：" + "13042299081" + "\n");
        orderResult.append("桌号：" + "10"+ "\n");
        orderResult.append("时间："+ "2019年4月10日 15:11:35"+ "\n");

        orderResult.append("\n\n");
        orderResult.append("\t\t\t\t\t\t\t订单2\n");
        orderResult.append("*************************\n");
        orderResult.append("商品名\t\t\t数量\t\t\t\t价格\n");
        orderResult.append("雪花"+ "\t\t\t\t\t\t\t " + 2 + " \t\t\t\t\t" + 100 + "\n");
        orderResult.append("吊龙"+ "\t\t\t\t\t\t\t " + 1 + " \t\t\t\t\t" + 40 + "\n");
        orderResult.append("*************************");
        orderResult.append("\n共计：" + 140 + "元\n");
        orderResult.append("姓名：" + "沈滨伟" + "\n");
        orderResult.append("电话：" + "13042299081" + "\n");
        orderResult.append("桌号：" + "10"+ "\n");
        orderResult.append("时间："+ "2019年4月11日 19:24:30"+ "\n");

        orderResult.append("\n\n");
        orderResult.append("\t\t\t\t\t\t\t订单3\n");
        orderResult.append("*************************\n");
        orderResult.append("商品名\t\t\t数量\t\t\t\t价格\n");
        orderResult.append("胸口朥"+ "\t\t\t\t\t" + 2 + " \t\t\t\t\t" + 90 + "\n");
        orderResult.append("嫩肉"+ "\t\t\t\t\t\t\t " + 2 + " \t\t\t\t\t" + 76 + "\n");
        orderResult.append("五花趾"+ "\t\t\t\t\t" + 1 + " \t\t\t\t\t" + 38 + "\n");
        orderResult.append("吊龙"+ "\t\t\t\t\t\t\t " + 2 + " \t\t\t\t\t" + 80 + "\n");
        orderResult.append("*************************");
        orderResult.append("\n共计：" + 284 + "元\n");
        orderResult.append("姓名：" + "沈滨伟" + "\n");
        orderResult.append("电话：" + "13042299081" + "\n");
        orderResult.append("桌号：" + "10"+ "\n");
        orderResult.append("时间："+ "2019年4月12日 08:15:45"+ "\n");

        orderResult.append("\n\n");
        orderResult.append("\t\t\t\t\t\t\t订单4\n");
        orderResult.append("*************************\n");
        orderResult.append("商品名\t\t\t数量\t\t\t\t价格\n");
        orderResult.append("三花趾"+ "\t\t\t\t\t" + 2 + " \t\t\t\t\t" + 72 + "\n");
        orderResult.append("胸口朥"+ "\t\t\t\t\t" + 2 + " \t\t\t\t\t" + 90 + "\n");
        orderResult.append("嫩肉"+ "\t\t\t\t\t\t\t " + 2 + " \t\t\t\t\t" + 76 + "\n");
        orderResult.append("五花趾"+ "\t\t\t\t\t" + 1 + " \t\t\t\t\t" + 38 + "\n");
        orderResult.append("嫩肉"+ "\t\t\t\t\t\t\t " + 3 + " \t\t\t\t\t" + 120 + "\n");
        orderResult.append("*************************");
        orderResult.append("\n共计：" + 399 + "元\n");
        orderResult.append("姓名：" + "沈滨伟" + "\n");
        orderResult.append("电话：" + "13042299081" + "\n");
        orderResult.append("桌号：" + "10"+ "\n");
        orderResult.append("时间："+ "2019年4月15日 11:24:06"+ "\n");

        //将订单结果信息设置到订单信息显示文字控件
        tvResult.setText(orderResult);

    }
}
