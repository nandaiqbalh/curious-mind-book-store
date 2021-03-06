package com.nandaiqbalh.tugaspbb.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.nandaiqbalh.tugaspbb.R;
import com.nandaiqbalh.tugaspbb.auth.SignInActivity;
import com.nandaiqbalh.tugaspbb.helper.SharedPrefs;
import com.nandaiqbalh.tugaspbb.rest.ApiConfig;
import com.nandaiqbalh.tugaspbb.utils.login.LoginRequest;
import com.nandaiqbalh.tugaspbb.utils.login.LoginResponse;
import com.nandaiqbalh.tugaspbb.utils.userprofile.UserProfileRequest;
import com.nandaiqbalh.tugaspbb.utils.userprofile.UserProfileResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    ImageView menu1, menu2, menu3;

    SharedPrefs sharedPrefs;

    UserProfileRequest userProfileRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // default fragment
        Fragment dashboardFragment = new DashboardFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_frame, dashboardFragment).commit();

        // init
        inisialisasi();

        // button
        mainButton();

        // helper
        obBoardingStatusHelper();

    }

    private void inisialisasi(){
        menu1 = (ImageView) findViewById(R.id.iv_menu1);
        menu2 = (ImageView) findViewById(R.id.iv_menu2);
        menu3 = (ImageView) findViewById(R.id.iv_menu3);

        sharedPrefs = new SharedPrefs(this);

        userProfileRequest = new UserProfileRequest();
    }

    private void obBoardingStatusHelper(){

        sharedPrefs.setValues("onboarding", "1");
    }

    private void mainButton(){

        // menu1 di-klik
        menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // default fragment
                Fragment dashboardFragment = new DashboardFragment();
                setFragment(dashboardFragment);

                // change icon botton navigation icon
                changeIcon(menu1, R.drawable.ic_home_active);
                changeIcon(menu2, R.drawable.ic_history);
                changeIcon(menu3, R.drawable.ic_profile);
            }
        });

        // menu1 di-klik
        menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment historyFragment = new HistoryFragment();
                setFragment(historyFragment);

                // change icon botton navigation icon
                changeIcon(menu1, R.drawable.ic_home);
                changeIcon(menu2, R.drawable.ic_history_active);
                changeIcon(menu3, R.drawable.ic_profile);
            }
        });

        // menu1 di-klik
        menu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment profileFragment = new ProfileFragment();

                // handling status login
                if (sharedPrefs.getStatusLogin()){
                    setFragment(profileFragment);

                    // change icon botton navigation icon
                    changeIcon(menu1, R.drawable.ic_home);
                    changeIcon(menu2, R.drawable.ic_history);
                    changeIcon(menu3, R.drawable.ic_profile_active);

                } else {

                    // jika belum login, maka akan ke login
                    Intent intent = new Intent(HomeActivity.this, SignInActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void changeIcon(ImageView imageView, int icon){
        imageView.setImageResource(icon);
    }

    private void setFragment(Fragment fragment) {

        FragmentManager fragmentManager;
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_frame, fragment).commit();
    }
}