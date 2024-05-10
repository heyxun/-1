package com.app.home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity{
    EditText etUname,etPwd;
    CheckBox cbRem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.setTitle("登录 210003111李嘉洋");
        SharedPreferences sp = getSharedPreferences("SPData_File",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        if(!sp.contains("name")){
            Intent toReg = new Intent(login.this,reg.class);
            startActivity(toReg);
        }

        etUname = findViewById(R.id.uname);
        etPwd = findViewById(R.id.pwd);

        findViewById(R.id.btnReg).setOnClickListener(view -> {
            if (view.getId() == R.id.btnReg){
                Intent myIntent0 = new Intent(login.this,reg.class);
                startActivity(myIntent0);
            }
        });
        findViewById(R.id.btnLogin).setOnClickListener(view -> {
            String uname,upwd;

            if (view.getId() == R.id.btnLogin) {
                uname = sp.getString("username","");
                upwd = sp.getString("pwd","");
                if (etUname.getText().toString().trim().equals(""))
                    Toast.makeText(login.this,"用户名不能为空！",Toast.LENGTH_LONG).show();
                else if(etPwd.getText().toString().trim().equals(""))
                    Toast.makeText(login.this,"密码不能为空！",Toast.LENGTH_LONG).show();
                else if(!etUname.getText().toString().trim().equals(uname))
                    Toast.makeText(login.this,"用户名错误！请重新输入！",Toast.LENGTH_LONG).show();
                else if (!etPwd.getText().toString().trim().equals(upwd)) {
                    if(etPwd.getText().toString().trim().length() < 6)
                        Toast.makeText(login.this,"密码不足6位！请重新输入",Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(login.this,"密码错误！请重新输入",Toast.LENGTH_LONG).show();
                }
                else {
                    Intent myIntent1 = new Intent(login.this,MainActivity.class);
                    startActivity(myIntent1);
                }
            }
        });
        cbRem =findViewById(R.id.chRem);
        cbRem.setOnClickListener(view -> {
            editor.putString("etUname",etUname.getText().toString());
            editor.putString("etPwd",etPwd.getText().toString());
            editor.apply();

            if(cbRem.isChecked()){
                editor.putBoolean("chRem",true);
            }
            else {
                editor.putBoolean("chRem",false);
                editor.remove("etUname");
                editor.remove("etPwd");
            }
            editor.commit();
        });
        boolean isRember = sp.getBoolean("chRem",false);
        if(isRember) {
            cbRem.setChecked(true);
            etUname.setText(sp.getString("etUname",""));
            etPwd.setText(sp.getString("etPwd",""));
        }
    }


}