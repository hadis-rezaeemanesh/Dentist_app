package com.example.myapplication.view.activity;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;

import com.example.myapplication.view.fragment.UserCommentsFragment;

public class CommentsActivity extends SingleFragmentActivity {

    public static final String EXTRA_ID_ARTICLE_CLICKED = "idArticle";

    public static Intent newIntent(Context context, int id){
        Intent intent = new Intent(context, CommentsActivity.class);
        intent.putExtra(EXTRA_ID_ARTICLE_CLICKED, id);
        return intent;

    }

    @Override
    public Fragment createFragment() {
        int id = getIntent().getIntExtra(EXTRA_ID_ARTICLE_CLICKED, 0);
        return UserCommentsFragment.newInstance(id);
    }

}