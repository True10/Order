package com.sbw.atrue.Order.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.sbw.atrue.Order.Entity.Product;
import com.sbw.atrue.Order.R;
import com.sbw.atrue.Order.Util.FoodFctory;
import com.sbw.atrue.Order.Util.ProductListAdapter;

public class ShoppingActivity extends Activity {
    public static ShoppingActivity mInstance = null; //当前对象的实例本身

    private ListView lvProduct; //商品展示列表控件
    private TextView tvSelected; //已选择商品数量提示文字
    private TextView tvTotal; //已选择商品总价提示文字
    private Button btnTakeOrder; //下单按钮
    private Button btnSortByNew; //根据新品排序
    private Button btnSortBySale; //根据销量排序
    private Button btnSortByPrice; //根据价格排序
    private Button btnSortByAll; //根据综合情况排序
    private List<Product> productsData = new ArrayList<Product>(); //所有的商品数据
    private ArrayList<Product> selectedProducts = new ArrayList<Product>(); //被选择的的商品数据
    private ProductListAdapter productListAdapter; //商品列表适配器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        initViews(); //初始化页面控件
        productsData=FoodFctory.initDatas(); //初始化数据
        initEvents(); //初始化控件事件
    }

    //初始化页面控件
    private void initViews() {
        lvProduct = (ListView) findViewById(R.id.lv_product);
        tvSelected = (TextView) findViewById(R.id.tv_selected);
        tvTotal = (TextView) findViewById(R.id.tv_total);
        btnTakeOrder = (Button) findViewById(R.id.btn_take_order);
        btnSortByNew = (Button) findViewById(R.id.btn_sort_by_new);
        btnSortByPrice = (Button) findViewById(R.id.btn_sort_by_price);
        btnSortBySale = (Button) findViewById(R.id.btn_sort_by_sale);
        btnSortByAll = (Button) findViewById(R.id.btn_sort_by_all);
    }

    //初始化控件事件
    private void initEvents() {
        mInstance = this; //设置实例等于当前对象本身
        //将数据源放入适配器中
        productListAdapter = new ProductListAdapter(this, productsData);
        //给商品展示列表控件设置顶部分隔线
        lvProduct.addHeaderView(new View(this));
        //给商品展示列表控件设置适配器
        lvProduct.setAdapter(productListAdapter);

        //给下单按钮添加点击事件
        btnTakeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedProducts.size() != 0) { //已选择商品数据不为空
                    Intent intent = new Intent(getApplicationContext(), InputActivity.class); //新建意图
                    intent.putParcelableArrayListExtra("selectedProducts", selectedProducts); //设置已选择的商品数据
                    startActivity(intent); //启动信息输入界面
                } else { //已选择商品数据为空
                    showDialog("购物车为空，请选择商品！");
                }
            }
        });

        //给按新品排序按钮设置点击事件
        btnSortByNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //点击事件
                Collections.sort(productsData, new Comparator<Product>() { //对商品数据进行排序
                    @Override
                    public int compare(Product product1, Product product2) { //比较商品
                        //如果本商品被选择次数大于第二个商品
                        if (product1.getSelectedCount() > product2.getSelectedCount()) {
                            return 1; //返回正数代表本商品大于第二个商品
                        }
                        return -1; //返回负数代表本商品小于第二个商品
                    }
                });
                productListAdapter.notifyDataSetChanged(); //通知系统数据改变
            }
        });

        //给按销量排序按钮设置点击事件
        btnSortBySale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //点击事件
                Collections.sort(productsData, new Comparator<Product>() { //对商品数据进行排序
                    @Override
                    public int compare(Product product1, Product product2) { //比较商品
                        //如果本商品的销量小于第二个商品
                        if (product1.getSale() < product2.getSale()) {
                            return 1; //返回正数代表本商品大于第二个商品
                        }
                        return -1; //返回负数代表本商品小于第二个商品
                    }
                });
                productListAdapter.notifyDataSetChanged(); //通知系统数据改变
            }
        });

        //给按价格排序按钮设置点击事件
        btnSortByPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //点击事件
                Collections.sort(productsData, new Comparator<Product>() { //对商品数据进行排序
                    @Override
                    public int compare(Product product1, Product product2) { //比较商品
                        //如果本商品的价格大于第二个商品
                        if (product1.getPrice() > product2.getPrice()) {
                            return 1; //返回正数代表本商品大于第二个商品
                        }
                        return -1; //返回负数代表本商品小于第二个商品
                    }
                });
                productListAdapter.notifyDataSetChanged(); //通知系统数据改变
            }
        });

        //给按综合排序按钮设置点击事件
        btnSortByAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //点击事件
                Collections.sort(productsData, new Comparator<Product>() { //对商品数据进行排序
                    @Override
                    public int compare(Product product1, Product product2) { //比较商品
                        //如果本商品的价格大于第二个商品
                        if (product1.getId()> product2.getId()) {
                            return 1; //返回正数代表本商品大于第二个商品
                        }
                        return -1; //返回负数代表本商品小于第二个商品
                    }
                });
                productListAdapter.notifyDataSetChanged(); //通知系统数据改变
            }
        });
    }

    //设置被选择商品数量与价格
    public void setSelectedCountAndPrice(int selectedCount, int totalPrice) {
        tvSelected.setText(String.valueOf(selectedCount)); //在界面上设置已选择商品数量的信息
        tvTotal.setText(String.valueOf(totalPrice)); //在界面上设置已选择商品总价的信息
        if (selectedCount == 0 && totalPrice == 0) { //当被选择商品数量和总价都为0时
            for (Product product : productsData) { //遍历商品列表
                product.clearZero(); //将每一项商品的选择次数归零与不显示减去按钮
            }
            selectedProducts.clear(); //清空已选择商品列表
            productListAdapter.notifyDataSetChanged(); //通知系统数据已改变
        }
    }

    //设置被选择的商品数据
    public void setSelectedProducts(ArrayList<Product> data) {
        this.selectedProducts = data; //获取被选择商品数据
        productListAdapter.setSelectedProducts(data); //设置被选择商品数据到商品适配器
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
}
