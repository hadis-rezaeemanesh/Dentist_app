package com.example.myapplication.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.adapter.RelatedArticlesAdapter;
import com.example.myapplication.databinding.FragmentArticleBinding;
import com.example.myapplication.model.ArticlesItem;
import com.example.myapplication.model.modelTest.Item;
import com.example.myapplication.model.modelTest.RelatedArticlesItem;
import com.example.myapplication.view.activity.CommentsActivity;
import com.example.myapplication.viewModel.ArticleViewModel;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;

import java.util.List;

public class ArticleFragment extends Fragment {

    public static final String ARGS_ID_ARTICLE = "idArticle";
    private RelatedArticlesAdapter mAdapter;
    private ArticleViewModel mViewModel;
    private FragmentArticleBinding mBinding;
    private int id;

    public ArticleFragment() {
        // Required empty public constructor
    }

    public static ArticleFragment newInstance(int id) {
        ArticleFragment fragment = new ArticleFragment();
        Bundle args = new Bundle();
        args.putInt(ARGS_ID_ARTICLE, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(ArticleViewModel.class);
        id = getArguments().getInt(ARGS_ID_ARTICLE);
        mViewModel.getLiveDataArticle().observe(this, new Observer<Item>() {
            @Override
            public void onChanged(Item item) {
                initViews(item);
            }
        });
        mViewModel.getLiveDataRelatedArticles().observe(this, new Observer<List<ArticlesItem>>() {
            @Override
            public void onChanged(List<ArticlesItem> articlesItems) {
                setAdapter(articlesItems);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_article, container, false);
        setListeners();
        return mBinding.getRoot();
    }

    private void setListeners() {
      /*  mBinding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = CommentsActivity.newIntent(getActivity());
                startActivity(intent);
            }
        });*/
    }

    private void setAdapter(List<ArticlesItem> articles){
        mBinding.recyclerViewArticle.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new RelatedArticlesAdapter(mViewModel, articles);
        mBinding.recyclerViewArticle.setAdapter(mAdapter);

    }

    private void initViews(Item article){
        mBinding.setItem(article);
        mBinding.setViewModel(mViewModel);

        Picasso.get()
                .load(article.getImage())
                .placeholder(R.drawable.ic_dentist)
                .into(mBinding.imgViewArticle);

        mBinding.txtViewDesc.setText(Jsoup.parse(article.getDescription()).text());


    }
}