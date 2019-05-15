package com.sbw.atrue.Order.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sbw.atrue.Order.R;
import com.sbw.atrue.Order.Util.ShareUtils;

/**
 * ClassName: LoginActivity <br>
 * description: 系统登录活动 <br>
 * author: 沈滨伟-13042299081 <br>
 * Date: 2019/2/18 14:54
 */
public class LoginActivity extends AppCompatActivity {
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button btnlogin;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews(); //初始化页面控件
        initEvents(); //初始化控件事件
    }

    //初始化页面控件
    private void initViews() {
        accountEdit = (EditText) findViewById(R.id.account);
        passwordEdit = (EditText) findViewById(R.id.password);
        btnlogin = (Button) findViewById(R.id.login);
        btnRegister=(Button) findViewById(R.id.register);
    }

    //初始化控件事件
    private void initEvents() {
        //给登录按钮设置点击事件
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                //判断用户名和密码是否正确
                if (!TextUtils.isEmpty(account) && !TextUtils.isEmpty(password)) { //用户名和密码都不为空
                    //尝试登陆
                    tryToLogin(account, password);
                }else{
                    showDialog("用户名或密码不能为空！");
                }

            }
        });

        //给注册按钮设置点击事件
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到注册界面
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                LoginActivity.this.finish(); //结束当前界面
            }
        });
    }

    /**
     * 进行登录请求的数据库查询
     * @param username 用户名
     * @param password 密码
     */
    private void tryToLogin(String username, String password) {
        //获取数据库中该用户名对应的密码
        String realPassword = ShareUtils.getString(this, username, "");
        //对数据库查询结果进行判断和处理
        if (password.equals(realPassword)) {
            //给用户的钱包余额设定金额
            ShareUtils.putInt(this, "money", 300);
            ShareUtils.putString(this, "user_true_name", "沈滨伟");
            ShareUtils.putString(this, "user_true_phone", "13042299081");
            ShareUtils.putString(this, "user_true_mail",  "1425553230@qq.com");
            //登录成功，跳转到菜单展示的主页面
            Intent intent = new Intent(LoginActivity.this, OrderActivity.class);
            startActivity(intent);
            finish();
        } else {
            showDialog("用户名称或者密码错误，请重新输入！");
        }
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
