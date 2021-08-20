package com.example.myapplication.view.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityFragmentBinding;
import com.example.myapplication.view.fragment.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    private ActivityFragmentBinding mBinding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_fragment);
        setNavController();

    }

    private void setNavController() {
        NavController navController = Navigation.findNavController(this, R.id.nav_home_fragment);
        NavigationUI.setupWithNavController(mBinding.bottomNavigation, navController);

        mBinding.bottomNavigation.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.profileFragment:
                                Navigation.findNavController(
                                        HomeActivity.this,
                                        R.id.nav_home_fragment).navigate(R.id.profileFragment);
                                break;
                            case R.id.locaterFragment:
                                Navigation.findNavController(
                                        HomeActivity.this,
                                        R.id.nav_home_fragment).navigate(R.id.locaterFragment);
                                break;
                            case R.id.homeFragment:
                                Navigation.findNavController(
                                        HomeActivity.this,
                                        R.id.nav_home_fragment).navigate(R.id.homeFragment);
                                break;
                            case R.id.ticketFragment:
                                Navigation.findNavController(HomeActivity.this,
                                        R.id.nav_home_fragment).navigate(R.id.ticketFragment);
                                break;

                            case R.id.searchFragment:
                                Navigation.findNavController(HomeActivity.this,
                                        R.id.nav_home_fragment).navigate(R.id.searchFragment);
                                break;
                        }
                        return true;
                    }
                });
    }
}