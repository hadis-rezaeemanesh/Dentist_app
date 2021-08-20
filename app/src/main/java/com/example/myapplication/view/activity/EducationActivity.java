package com.example.myapplication.view.activity;


import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.example.myapplication.view.fragment.EducationFragment;

public class EducationActivity extends SingleFragmentActivity {


    public static final String EXTRA_ID_ARTICLES_BY_CATEGORY = "idArticlesByCategory";

    public static Intent newIntent(Context context, int id){
        Intent intent = new Intent(context, EducationActivity.class);
        intent.putExtra(EXTRA_ID_ARTICLES_BY_CATEGORY, id);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        int id = getIntent().getIntExtra(EXTRA_ID_ARTICLES_BY_CATEGORY, 0);
        return EducationFragment.newInstance(id);
    }


}