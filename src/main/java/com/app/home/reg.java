package com.app.home;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class reg extends AppCompatActivity implements View.OnClickListener {
    Spinner sp_bemale;
    int bemale = 0;
    EditText et_name,et_username,et_pw,et_dep,et_age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        this.setTitle("用户注册 210003111李嘉洋");
        String[] typeArray = {"男","女","保密"};
        ArrayAdapter<String> typeAdapter = new ArrayAdapter(reg.this,R.layout.item_select,typeArray);
        typeAdapter.setDropDownViewResource(R.layout.item_dropdown);
        sp_bemale = findViewById(R.id.gender);
        sp_bemale.setPrompt("请选择性别");
        sp_bemale.setAdapter(typeAdapter);
        sp_bemale.setSelection(0);
        findViewById(R.id.save).setOnClickListener(this);
        et_name = findViewById(R.id.etName);
        et_username = findViewById(R.id.etUserName);
        et_pw = findViewById(R.id.etPwd);
        et_dep = findViewById(R.id.etDep);
        et_age = findViewById(R.id.etAge);
        readData();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.save){
            if(et_name.getText().toString().trim().equals(""))
                Toast.makeText(reg.this,"姓名不能为空！",Toast.LENGTH_LONG).show();
            else if(et_username.getText().toString().trim().equals(""))
                Toast.makeText(reg.this,"用户名不能为空！",Toast.LENGTH_LONG).show();
            else if(et_pw.getText().toString().trim().equals(""))
                Toast.makeText(reg.this,"密码不能为空或前后有空格！",Toast.LENGTH_LONG).show();
            else if(et_dep.getText().toString().trim().equals(""))
                Toast.makeText(reg.this,"部门不能为空！",Toast.LENGTH_LONG).show();
            else if(et_age.getText().toString().trim().equals(""))
                Toast.makeText(reg.this,"年龄不能为空！",Toast.LENGTH_LONG).show();
            else if (et_pw.getText().toString().trim().length() < 6)
                Toast.makeText(reg.this,"密码不足6位！请重新输入",Toast.LENGTH_LONG).show();
            else {
                save();
            }
        }
    }

    void readData(){
        SharedPreferences sp = getSharedPreferences("SPData_File",MODE_PRIVATE);
        et_name.setText(sp.getString("name",""));
        et_username.setText(sp.getString("username",""));
        et_pw.setText(sp.getString("pwd",""));
        et_age.setText(sp.getString("age",""));
        et_dep.setText(sp.getString("department",""));
        bemale=sp.getInt("gender",0);
        if(bemale == 0)
            sp_bemale.setSelection(0);
        else if (bemale == 1)
            sp_bemale.setSelection(1);
        else if(bemale == 2)
            sp_bemale.setSelection(2);
    }

    void save(){
        SharedPreferences sp = getSharedPreferences("SPData_File",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("name",et_name.getText().toString().trim());
        editor.putString("username",et_username.getText().toString().trim());
        editor.putString("pwd",et_pw.getText().toString().trim());
        editor.putString("age",et_age.getText().toString().trim());
        editor.putString("department",et_dep.getText().toString().trim());
        bemale=sp_bemale.getSelectedItemPosition();
        editor.putInt("gender",bemale);
        editor.commit();
        finish();
    }
}