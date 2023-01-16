package com.vaidilya.sih;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {



    BottomNavigationView bottomNavigationView;


    String[] permissions = {"android.permission.WRITE_EXTERNAL_STORAGE"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Data d=new Data();



//        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
//        WindowInsetsControllerCompat windowInsetsController =
//                ViewCompat.getWindowInsetsController(getWindow().getDecorView());
//        if (windowInsetsController == null) {
//            return;
//        }

        //windowInsetsController.setAppearanceLightNavigationBars(true);

//        requestPermissions(permissions, RC_PERMISSIONS);

        bottomNavigationView=findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                new HomeFragment()).commit();

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.page_1:
                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                            new HomeFragment()).commit();
                    break;
                case R.id.page_2:
                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                            new ProfileFragment()).commit();
                    break;

                case R.id.page_3:
                    Intent newi=new Intent(MainActivity.this,Form.class);
                    startActivity(newi);
                    break;
            }
            return true;
        });

    }

}



