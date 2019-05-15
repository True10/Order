package com.sbw.atrue.Order.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import com.sbw.atrue.Order.R;
import com.sbw.atrue.Order.Entity.Product;
import com.sbw.atrue.Order.Util.ShareUtils;

public class InputActivity extends Activity {
    private EditText etname; //姓名输入框
    private EditText etPhone; //手机号输入框
    private EditText ettable; //桌号输入框
    private Button btnInputCompleted; //输入完成按钮
    private ArrayList<Product> selectedProducts = new ArrayList<Product>(); //被选择的的商品数据


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        initViews(); //初始化页面控件
        initEvents(); //初始化控件事件
    }

    //初始化控件事件
    private void initEvents() {
        Intent otherIntent = getIntent(); //获取信息输入界面传来的意图
        selectedProducts = otherIntent.getParcelableArrayListExtra("selectedProducts"); //从意图中获取被选择的的商品数据
        etPhone.setSelection(etPhone.getText().length()); //将光标移动到电话输入框最后面
        etname.setText(ShareUtils.getString(InputActivity.this,"user_true_name",""));//从本地读取客户姓名
        etPhone.setText(ShareUtils.getString(InputActivity.this,"user_true_phone",""));//从本地读取客户电话
        //给输入完成按钮设置点击事件
        btnInputCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = ettable.getText().toString(); //获取输入框中的桌号
                String name = etname.getText().toString(); //获取输入框中的客户姓名
                String phone = etPhone.getText().toString(); //获取输入框中的手机号
                if (!TextUtils.isEmpty(phone)
                        && !TextUtils.isEmpty(address)
                        && phone.length() >= 7) { //手机号和地址不为空且手机号大于等于7位
                    Intent intent = new Intent(getApplicationContext(), SuccessActivity.class); //新建意图
                    intent.putParcelableArrayListExtra("selectedProducts", selectedProducts); //给意图添加被选择的的商品数据
                    intent.putExtra("name",name); //给意图设置客户姓名
                    intent.putExtra("phone", phone); //给意图设置联系电话
                    intent.putExtra("table", address); //给意图设置用餐桌号
                    startActivity(intent); //跳转到购买成功页面
                    ShoppingActivity.mInstance.finish(); //结束购物界面
                    InputActivity.this.finish(); //结束信息输入界面
                } else { //输入手机号或地址不正确
                    //Toast.makeText(InputActivity.this, "手机号或地址输入不正确，手机号至少7位", Toast.LENGTH_SHORT).show();
                    showDialog("手机号或地址输入不正确，手机号至少7位!");
                }
            }
        });
    }

    //初始化页面控件
    private void initViews() {
        etname = (EditText) findViewById(R.id.et_name);
        etPhone = (EditText) findViewById(R.id.et_phone);
        ettable = (EditText) findViewById(R.id.et_table);
        btnInputCompleted = (Button) findViewById(R.id.btn_input_completed);
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
