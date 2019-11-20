package com.lyichao.play;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    private View headerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {
                Log.d("MainActivity", "onDrawerSlide: 滑动");
            }

            @Override
            public void onDrawerOpened(@NonNull View view) {
                Log.d("MainActivity", "onDrawerSlide: 打开");
            }

            @Override
            public void onDrawerClosed(@NonNull View view) {
                Log.d("MainActivity", "onDrawerSlide: 关闭");
            }

            @Override
            public void onDrawerStateChanged(int i) {
                Log.d("MainActivity", "onDrawerSlide: 状态改变");
            }
        });

        headerView = navigationView.getHeaderView(0);
        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "headerView OnClick", Toast.LENGTH_SHORT).show();
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_app_update:
                        Toast.makeText(MainActivity.this, "应用更新", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_app_message:
                        Toast.makeText(MainActivity.this, "消息中心", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_app_setting:
                        Toast.makeText(MainActivity.this, "设置", Toast.LENGTH_SHORT).show();
                        break;

                }
                return false;
            }
        });

    }
}
