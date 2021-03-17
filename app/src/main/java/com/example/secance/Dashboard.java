package com.example.secance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

public class Dashboard extends AppCompatActivity {
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        drawerLayout = findViewById(R.id.drawer_layout);
    }

    public void ClickMenu(View view) {
        PropertiesActivity.startDrawer(drawerLayout);
    }

    public void ClickLogo(View view) {
        PropertiesActivity.closeDrawer(drawerLayout);
    }

    public void ClickHome(View view) {
        PropertiesActivity.redirectActivity(this, PropertiesActivity.class);
    }

    public void ClickDashboard(View view) {
        recreate();
    }

    public void ClickAboutUs(View view) {
        PropertiesActivity.redirectActivity(this, AboutUs.class);
    }

    public void ClickLogout(View view) {
        PropertiesActivity.logout(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        PropertiesActivity.closeDrawer(drawerLayout);
    }
}