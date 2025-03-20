package com.ianzb.zbclient;

import android.os.Bundle;
import androidx.activity.ComponentActivity;

import com.google.android.material.color.DynamicColors;

public class SecondActivity extends ComponentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DynamicColors.applyToActivitiesIfAvailable(getApplication());
        setContentView(R.layout.activity_second);
    }
}