package com.ianzb.zbclient;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.ComponentActivity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.color.DynamicColors;

public class MainActivity extends ComponentActivity {

    // 视图组件声明
    private TextView welcomeText;
    private Button actionButton;
    private Button navigateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DynamicColors.applyToActivitiesIfAvailable(getApplication());
        setContentView(R.layout.activity_main); // 加载布局文件

        // 初始化视图组件
        initViews();
        setupEventListeners();
    }

    private void initViews() {
        welcomeText = findViewById(R.id.welcome_text);
        actionButton = findViewById(R.id.action_button);
        navigateButton = findViewById(R.id.navigate_button);
    }

    private void setupEventListeners() {
        // 按钮点击事件
        actionButton.setOnClickListener(v -> {
            Toast.makeText(this, "执行操作", Toast.LENGTH_SHORT).show();
            updateWelcomeText("操作已执行");
        });

        navigateButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
        });
    }

    private void updateWelcomeText(String text) {
        welcomeText.setText(text);
    }

    // 菜单创建
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    // 菜单项选择处理
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            Toast.makeText(this, "打开设置", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // 生命周期方法示例
    @Override
    protected void onStart() {
        super.onStart();
        updateWelcomeText("Activity正在运行");
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateWelcomeText("Activity已恢复");
    }

    // 状态保存与恢复
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("welcome_text", welcomeText.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String savedText = savedInstanceState.getString("welcome_text", "");
        updateWelcomeText(savedText);
    }
}