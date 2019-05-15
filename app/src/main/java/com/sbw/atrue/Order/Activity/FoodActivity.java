package com.sbw.atrue.Order.Activity;

import android.content.Intent;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sbw.atrue.Order.Entity.Food;
import com.sbw.atrue.Order.R;

/**
 * ClassName: FoodActivity <br>
 * description: 用于加载展示菜品的详细信息的活动 <br>
 * author: 沈滨伟-13042299081 <br>
 * Date: 2019/2/18 20:59
 */
public class FoodActivity extends AppCompatActivity {

    public static final String food_NAME = "food_name";
    public static final String food_IMAGE_ID = "food_image_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        //获取各布局控件的实例
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        ImageView foodImageView = (ImageView) findViewById(R.id.food_image_view);
        TextView foodContentText = (TextView) findViewById(R.id.food_content_text);

        //从上一级活动获取用户点击的菜品的对象
        Intent intent = getIntent();
        Food food=(Food)intent.getSerializableExtra("Food_Choose");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //显示标题栏最左侧的系统默认自带的返回导航键，用于返回上一级活动
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //设置菜品的名称、图片
        collapsingToolbar.setTitle(food.getName());
        Glide.with(this).load(food.getImageId()).into(foodImageView);
        //设置菜品详情介绍
        foodContentText.setText(food.getDescription());
    }

    /**
     * 设置当前布局里的菜单子布局的控件监听事件，此处实现返回导航键的注销本活动功能
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //R.id.home是系统默认的ActionBar标题栏最左侧按键HomeAsUp键的Id
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
