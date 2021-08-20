package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ItemRowEducationBinding;
import com.example.myapplication.model.ArticlesItem;
import com.example.myapplication.model.modelTest.RelatedArticlesItem;
import com.example.myapplication.viewModel.ArticleViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RelatedArticlesAdapter extends RecyclerView.Adapter<RelatedArticlesAdapter.RelatedArticlesHolder> {
    private ArticleViewModel mViewModel;
    private List<ArticlesItem> mArticles;

    public RelatedArticlesAdapter(ArticleViewModel mViewModel, List<ArticlesItem> articles) {
        this.mViewModel = mViewModel;
        mArticles = articles;
    }

    @NonNull
    @Override
    public RelatedArticlesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRowEducationBinding itemRowEducationBinding =
                DataBindingUtil.inflate(LayoutInflater.from(mViewModel.getApplication()),
                        R.layout.item_row_education,
                        parent,
                        false);
        return new RelatedArticlesHolder(itemRowEducationBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RelatedArticlesHolder holder, int position) {
        holder.BindItems(mViewModel.getLiveDataRelatedArticles().getValue().get(position), position);
    }

    @Override
    public int getItemCount() {
        return mViewModel.getLiveDataRelatedArticles().getValue().size();
    }

    public class RelatedArticlesHolder extends RecyclerView.ViewHolder {

        private ItemRowEducationBinding mBinding;


        public RelatedArticlesHolder(ItemRowEducationBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mBinding.setViewModel(mViewModel);
            List<ArticlesItem> mItems = mArticles;
            List<Integer> mArticlesId = new ArrayList<>();

            for (int i = 0; i < mItems.size(); i++) {
                mArticlesId.add(mItems.get(i).getId());
            }
            mBinding.setArticleIdList(mArticlesId);
        }
        public void BindItems(ArticlesItem item, int position){
            mBinding.setPosition(position);
            mBinding.setItem(item);


            Picasso.get()
                    .load(item.getImage())
                    .placeholder(R.drawable.ic_dentist)
                    .into(mBinding.imgViewItemEducation);

        }
    }
}
