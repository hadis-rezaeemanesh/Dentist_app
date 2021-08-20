package com.example.myapplication.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.myapplication.R;

public abstract class SingleFragmentActivity extends AppCompatActivity {

    public abstract Fragment createFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         DataBindingUtil.setContentView(this, R.layout.activity_fragment);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.nav_home_fragment);

        if (fragment == null){
            fragmentManager
                    .beginTransaction()
                    .add(R.id.nav_home_fragment, createFragment())
                    .commit();
        }
}

}