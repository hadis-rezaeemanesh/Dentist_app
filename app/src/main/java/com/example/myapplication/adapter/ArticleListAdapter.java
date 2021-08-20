package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ItemRowEducationBinding;
import com.example.myapplication.model.ArticlesItem;
import com.example.myapplication.model.modelTest.Item;
import com.example.myapplication.viewModel.ArticleViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.EducationViewHolder> {

    private ArticleViewModel mViewModel;
    private List<ArticlesItem> mArticles;

    public ArticleListAdapter(ArticleViewModel viewModel, List<ArticlesItem> articles) {
        mViewModel = viewModel;
        mArticles = articles;
    }

    @NonNull
    @Override
    public EducationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemRowEducationBinding itemRowEducationBinding =
                DataBindingUtil.inflate(LayoutInflater.from(mViewModel.getApplication()),
                R.layout.item_row_education,
                parent,
                false);
        return new EducationViewHolder(itemRowEducationBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull EducationViewHolder holder, int position) {
        holder.bindItems(position, mViewModel.getLiveDataArticleByCategory().getValue().get(position));

    }

    @Override
    public int getItemCount() {
        return mViewModel.getLiveDataArticleByCategory().getValue().size();
    }

    public class EducationViewHolder extends RecyclerView.ViewHolder {

        private ItemRowEducationBinding mItemBinding;

        public EducationViewHolder(ItemRowEducationBinding itemBinding) {
            super(itemBinding.getRoot());
            mItemBinding = itemBinding;
            mItemBinding.setViewModel(mViewModel);
//            List<Item> mItems = mArticles;
//            List<Integer> mArticlesId = new ArrayList<>();
//
//            for (int i = 0; i < mItems.size(); i++) {
//                mArticlesId.add(mItems.get(i).getId());
//            }
//            mItemBinding.setArticleIdList(mArticlesId);
        }

        public void bindItems(int position, ArticlesItem articlesItem){

            mItemBinding.setPosition(position);
            mItemBinding.setItem(articlesItem);

            Picasso.get()
                    .load(articlesItem.getImage())
                    .placeholder(R.drawable.ic_dentist)
                    .into(mItemBinding.imgViewItemEducation);

        }
    }
}
