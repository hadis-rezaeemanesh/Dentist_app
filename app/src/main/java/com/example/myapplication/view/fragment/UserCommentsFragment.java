package com.example.myapplication.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.adapter.CommentsAdapter;
import com.example.myapplication.databinding.FragmentUserCommentsBinding;
import com.example.myapplication.model.modelTest.CommentsItem;
import com.example.myapplication.viewModel.ArticleViewModel;

import org.w3c.dom.Comment;

import java.util.List;

public class UserCommentsFragment extends Fragment {

    public static final String ARGS_ID_ARTICLE_CLICKED_TO_COMMENTS = "idArticleClickedToComments";
    private ArticleViewModel mViewModel;
    private CommentsAdapter mAdapter;
    private FragmentUserCommentsBinding mBinding;
    private int id;


    public UserCommentsFragment() {
        // Required empty public constructor
    }

    public static UserCommentsFragment newInstance(int id) {
        UserCommentsFragment fragment = new UserCommentsFragment();
        Bundle args = new Bundle();
        args.putInt(ARGS_ID_ARTICLE_CLICKED_TO_COMMENTS, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        id = getArguments().getInt(ARGS_ID_ARTICLE_CLICKED_TO_COMMENTS);
        mViewModel = new ViewModelProvider(this).get(ArticleViewModel.class);
        mViewModel.getCommentsLiveData().observe(this, new Observer<List<CommentsItem>>() {
            @Override
            public void onChanged(List<CommentsItem> commentsItems) {
                setAdapters();
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_comments, container, false);
        return mBinding.getRoot();
    }

    private void setAdapters(){
        mBinding.recyclerViewCommentUsers.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new CommentsAdapter(mViewModel);
        mBinding.recyclerViewCommentUsers.setAdapter(mAdapter);
    }

}