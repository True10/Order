package com.sbw.atrue.Order.Activity;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sbw.atrue.Order.Entity.Food;
import com.sbw.atrue.Order.Util.FoodAdapter;
import com.sbw.atrue.Order.R;
import com.sbw.atrue.Order.Util.FoodFctory;
import com.sbw.atrue.Order.Util.ShareUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: OrderActivity <br>
 * description: 用于展示菜单的活动 <br>
 * author: 沈滨伟-13042299081 <br>
 * Date: 2019/2/18 21:03
 */
public class OrderActivity extends AppCompatActivity {
    //从生产类中加载系统存储的菜单列表
    private Food[] foods = FoodFctory.Beef;
    private List<Food> foodList=new ArrayList<>();
    private DrawerLayout mDrawerLayout;
    private FoodAdapter adapter;
    private SwipeRefreshLayout swipeRefresh;
    private int first=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        //导入Toolbar的自定义布局形式
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //导入滑动菜单
        mDrawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView=(NavigationView) findViewById(R.id.nav_view);
        //获得ActionBar的实例，虽然此ActionBar具体实现是由ToolBar来完成的
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            //将ActionBar的导航按钮HomeAsUp显示出来（Toolbar标题栏最左侧的按钮图标默认为箭头，含义为返回上一级活动）
            actionBar.setDisplayHomeAsUpEnabled(true);
            //更改导航按钮HomeAsUp的默认图标（箭头改为导航键）
            //actionBar.setHomeAsUpIndicator(R.drawable.ic_comment);
        }
        //设置滑动菜单的按钮点击事件
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                //点击任意菜单选项，则将滑动菜单关闭
                // mDrawerLayout.closeDrawers();
                menuItem.setChecked(true);
                switch (menuItem.getItemId()) {
                    case R.id.nav_Order:
                        //跳转到订单界面
                        startActivity(new Intent(OrderActivity.this, ReadOrderActivity.class));break;
                    case R.id.nav_money:
                        //跳转到钱包界面
                        startActivity(new Intent(OrderActivity.this, MyMoneyActivity.class));break;
                    case R.id.nav_setting:
                        //跳转到钱包界面
                        startActivity(new Intent(OrderActivity.this, SettingActivity.class));break;
                    case R.id.nav_logout:
                        //跳转到登陆界面
                        startActivity(new Intent(OrderActivity.this, LoginActivity.class));
                        //结束当前界面
                        OrderActivity.this.finish(); break;
                    default:
                        break;
                }
                return true;
            }
        });
        //悬浮按键的设置
        FloatingActionButton fab=(FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到登陆界面
                startActivity(new Intent(OrderActivity.this, ShoppingActivity.class));
            }
        });
        //显示菜单的控件逻辑
        initFruits();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FoodAdapter(foodList);
        recyclerView.setAdapter(adapter);
        //刷新菜单的控件逻辑
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshfoods();
            }
        });
    }

    /**
     * 刷新菜单
     */
    private void refreshfoods() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //为了避免程序刷新太快，先让线程睡眠2秒
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //从子线程切回到主线程，从而可以更改UI
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //向菜单中增加新菜
                        if(first==0){
                            Food WanZi=new Food("牛肉丸",R.drawable.wanzi,"牛肉丸是潮汕美食啊！！！");
                            foodList.add(WanZi);
                            first=1;
                        }
                        //通知适配器数据发生了变化，用于RecycleView自动更新显示项
                        adapter.notifyDataSetChanged();
                        //刷新结束，将刷新进度条隐藏
                        swipeRefresh.setRefreshing(false);
                        //显示更新结果的弹框通知，告知用户更新情况
                        AlertDialog.Builder builder = new AlertDialog.Builder(OrderActivity.this);
                        builder.setMessage("菜单更新成功啦~")
                                .setCancelable(false)
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                });
            }
        }).start();
    }

    /**
     * 将Food数组转换为数组列表
     */
    private void initFruits() {
        foodList.clear();
        for (int i = 0; i < 9; i++) {
            foodList.add(foods[i]);
        }
    }

    /**
     * 加载toolbar.xml菜单文件
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    /**
     * Toolbat导航栏按键的响应事件
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            //android.R.id.home是标题栏最左侧按键HomeAsUp的Id
            case android.R.id.home:
                //将滑动菜单展示出来，GravityCompat.START参数与XML文件定义的保持一致
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.backup:
                Toast.makeText(this, "You click Backup", Toast.LENGTH_SHORT).show();
                break;
            case  R.id.delete:
                Toast.makeText(this, "You click Delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting:
                Toast.makeText(this, "You click Setting", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }
}
