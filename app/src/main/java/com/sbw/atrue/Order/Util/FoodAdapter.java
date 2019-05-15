package com.sbw.atrue.Order.Util;

import android.content.Context;
import android.content.Intent;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sbw.atrue.Order.Activity.FoodActivity;
import com.sbw.atrue.Order.Entity.Food;
import com.sbw.atrue.Order.R;

import java.util.List;

/**
 * ClassName: FoodAdapter <br>
 * description: 菜品适配器，用于与RecycleView控件结合 <br>
 * author: 沈滨伟-13042299081 <br>
 * Date: 2019/2/18 21:03
 */
public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder>{

    private static final String TAG = "FoodAdapter";
    private Context mContext;
    private List<Food> mfoodList;

    /**
     * foodAdapter的内部类，用于加载布局各控件的实例
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView foodImage;
        TextView foodName;
        //构造函数
        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            foodImage = (ImageView) view.findViewById(R.id.food_image);
            foodName = (TextView) view.findViewById(R.id.food_name);
        }
    }

    /**
     * foodAdapter类的构造函数，接收并指定菜单列表
     * @param foodList 菜单列表
     */
    public FoodAdapter(List<Food> foodList) {
        mfoodList = foodList;
    }

    /**
     * 用于创建ViewHolder实例，并设置菜品卡片的点击监听事件
     * @param parent 父布局
     * @param viewType
     * @return ViewHolder实例，用于onBindViewHolder函数加载本地展示布局
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        //动态加载布局文件（菜品的简单展示布局，即图片+菜名），并并把加载出来的布局传入到构造函数中，从而创建ViewHolder实例
        View view = LayoutInflater.from(mContext).inflate(R.layout.food_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        //如果用户点击菜品卡片，则跳转到显示相应的菜品详情的活动中去
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Food food = mfoodList.get(position);
                Intent intent = new Intent(mContext, FoodActivity.class);
                //将用户点击的菜品的对象传递到下一个活动（菜品详情展示FoodActivity ）
                intent.putExtra("Food_Choose",food);
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    /**
     * 用于对RecycleView子项的数据（图片+菜名）进行赋值，会在每个子项被滚动到屏幕内的时候自动执行
     * @param holder 从onCreateViewHolder函数返回的值获取ViewHolder实例
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Food food = mfoodList.get(position);
        holder.foodName.setText(food.getName());
        //使用Glide加载图片，不用担心像素太高导致内存溢出
        Glide.with(mContext).load(food.getImageId()).into(holder.foodImage);
    }

    /**
     * 返回菜品总数
     * @return
     */
    @Override
    public int getItemCount() {
        return mfoodList.size();
    }

}
