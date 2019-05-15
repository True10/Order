package com.sbw.atrue.Order.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sbw.atrue.Order.R;
import com.sbw.atrue.Order.Util.ShareUtils;

/**
 * FileName: SettingActivity <br>
 * Description:  <br>
 * Author: 沈滨伟-13042299081 <br>
 * Date: 2019/4/12 20:55
 */
public class SettingActivity extends Activity {
    private EditText etname; //姓名输入框
    private EditText etPhone; //手机号输入框
    private EditText etMail; //邮箱输入框
    private Button btnInputCompleted; //输入完成按钮
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_setting);
        etname = (EditText) findViewById(R.id.et_name);
        etPhone = (EditText) findViewById(R.id.et_phone);
        etMail = (EditText) findViewById(R.id.et_mail);
        btnInputCompleted = (Button) findViewById(R.id.btn_input_completed);
        //登录时默认自动在本地保存了一份客户信息，将其读取出来并显示
        etname.setText(ShareUtils.getString(this,"user_true_name",""));
        etPhone.setText(ShareUtils.getString(this,"user_true_phone",""));
        etMail.setText(ShareUtils.getString(this,"user_true_mail",""));
        btnInputCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //保存用户新设置的客户个人信息
                ShareUtils.putString(SettingActivity.this, "user_true_name", etname.getText().toString());
                ShareUtils.putString(SettingActivity.this, "user_true_phone", etPhone.getText().toString());
                ShareUtils.putString(SettingActivity.this, "user_true_mail",  etMail.getText().toString());
                finish();
            }
        });
    }
}
