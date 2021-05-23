package com.example.secance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

public class AboutUs extends AppCompatActivity {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        drawerLayout = findViewById(R.id.drawer_layout);
    }

    public void onClickMenu(View view) {
        PropertiesActivity.startDrawer(drawerLayout);
    }

    public void onClickLogo(View view) {
        PropertiesActivity.closeDrawer(drawerLayout);
    }

    public void onClickHome(View view) {
        PropertiesActivity.redirectActivity(this, PropertiesActivity.class);
    }

    public void onClickDashboard(View view) {
        PropertiesActivity.redirectActivity(this, Dashboard.class);

    }

    public void onClickAboutUs(View view) {
        PropertiesActivity.redirectActivity(this, AboutUs.class);
    }

    public void onClickLogout(View view) {
        PropertiesActivity.logout(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        PropertiesActivity.closeDrawer(drawerLayout);
    }
}