package com.sbw.atrue.Order.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sbw.atrue.Order.R;
import com.sbw.atrue.Order.Util.ShareUtils;

/**
 * FileName: MyMoneyActivity <br>
 * Description:  <br>
 * Author: 沈滨伟-13042299081 <br>
 * Date: 2019/4/12 16:51
 */
public class MyMoneyActivity extends Activity {
    //账户余额显示文字控件
    private TextView myMoney;
    private Button btnSure;
    private Button btnsaveMoney;
    private int nowMoney;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_money);
        myMoney = (TextView) findViewById(R.id.my_money);
        btnSure = (Button) findViewById(R.id.btn_Sure);
        btnsaveMoney = (Button) findViewById(R.id.btn_SaveMoney);
        //读取本地文件中的账户余额
        nowMoney=ShareUtils.getInt(MyMoneyActivity.this, "money",1);
        myMoney.setText(String.valueOf(nowMoney));

        //给确定按钮设置点击事件
        btnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //结束当前界面，将自动返回系统上一级未被销毁的活动！比如结账活动或者主页面活动
                MyMoneyActivity.this.finish();
            }
        });

        //给充值按钮设置点击事件
        btnsaveMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //给账户余额加200元
                nowMoney = nowMoney + 200;
                ShareUtils.putInt(MyMoneyActivity.this, "money", nowMoney);
                myMoney.setText(String.valueOf(nowMoney));
            }
        });
    }
}
