package com.example.myapplication.view.activity;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;

import com.example.myapplication.view.fragment.ArticleFragment;

public class ArticleActivity extends SingleFragmentActivity {

    public static final String EXTRA_ID_ARTICLE = "idArticle";

    public static Intent newIntent(Context context, int id){
        Intent intent = new Intent(context, ArticleActivity.class);
        intent.putExtra(EXTRA_ID_ARTICLE, id);
        return intent;
    }


    @Override
    public Fragment createFragment() {
        int id = getIntent().getExtras().getInt(EXTRA_ID_ARTICLE);
        return ArticleFragment.newInstance(id);
    }


}