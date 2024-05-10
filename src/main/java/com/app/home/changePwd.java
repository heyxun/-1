package com.app.home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class changePwd extends AppCompatActivity implements View.OnClickListener{
    EditText etUserName,etPwd,etNewPwd,etNewPwdAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pwd);
        this.setTitle("修改密码 210003111李嘉洋");
        etUserName = findViewById(R.id.etUserName);
        etPwd = findViewById(R.id.etUrPwd);
        etNewPwd = findViewById(R.id.etNewPwd);
        etNewPwdAgain = findViewById(R.id.etNewPwdAgain);

        findViewById(R.id.btn_save).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        SharedPreferences sp = getSharedPreferences("SPData_File",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        if (view.getId() == R.id.btn_save){
            if(etUserName.getText().toString().trim().equals(""))
                Toast.makeText(changePwd.this,"用户名不能为空！",Toast.LENGTH_LONG).show();
            else if(etPwd.getText().toString().trim().equals(""))
                Toast.makeText(changePwd.this,"原密码不能为空或前后有空格！",Toast.LENGTH_LONG).show();
            else if(etNewPwd.getText().toString().trim().equals(""))
                Toast.makeText(changePwd.this,"新密码不能为空或前后有空格！",Toast.LENGTH_LONG).show();
            else if(etNewPwdAgain.getText().toString().trim().equals(""))
                Toast.makeText(changePwd.this,"确认新密码不能为空或前后有空格！",Toast.LENGTH_LONG).show();
            else if (etPwd.getText().toString().trim().length() < 6)
                Toast.makeText(changePwd.this,"原密码不足6位！请重新输入",Toast.LENGTH_LONG).show();
            else if (etNewPwd.getText().toString().trim().length() < 6)
                Toast.makeText(changePwd.this,"新密码不足6位！请重新输入",Toast.LENGTH_LONG).show();
            else if (etNewPwdAgain.getText().toString().trim().length() < 6)
                Toast.makeText(changePwd.this,"确认新密码不足6位！请重新输入",Toast.LENGTH_LONG).show();
            else if(!etUserName.getText().toString().trim().equals(sp.getString("username","")))
                Toast.makeText(changePwd.this,"用户名错误！请重新输入",Toast.LENGTH_LONG).show();
            else if(!etPwd.getText().toString().trim().equals(sp.getString("pwd","")))
                Toast.makeText(changePwd.this,"原密码错误！请重新输入",Toast.LENGTH_LONG).show();
            else if (!etNewPwdAgain.getText().toString().trim().equals(etNewPwd.getText().toString().trim()))
                Toast.makeText(changePwd.this,"确认密码与新密码不匹配！请重新输入",Toast.LENGTH_LONG).show();
            else if (etNewPwd.getText().toString().trim().equals(etPwd.getText().toString().trim())
                || sp.getString("pwd","").equals(etNewPwd.getText().toString().trim()))
                Toast.makeText(changePwd.this,"原密码与新密码一致！请重新输入",Toast.LENGTH_LONG).show();
            else if(etNewPwdAgain.getText().toString().trim().equals(etPwd.getText().toString().trim())
                    || sp.getString("pwd","").equals(etNewPwdAgain.getText().toString().trim())
            )
                Toast.makeText(changePwd.this,"原密码与确认密码一致！请重新输入",Toast.LENGTH_LONG).show();
            else {
                editor.putString("pwd",etNewPwd.getText().toString().trim());
                editor.commit();
                Intent myIntent = new Intent(changePwd.this,login.class);
                startActivity(myIntent);
                finish();
            }
        }

    }
}