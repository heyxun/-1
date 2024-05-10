package com.app.home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sp = getSharedPreferences("SPData_File",MODE_PRIVATE);
        String name = sp.getString("name","不知名的用户");
        setTitle("欢迎"+name+"使用本系统 210003111李嘉洋");
        findViewById(R.id.btnChangePwd).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnChangePwd){
            Intent changePwdIntent = new Intent(MainActivity.this,changePwd.class);
            startActivity(changePwdIntent);
        }
    }
}