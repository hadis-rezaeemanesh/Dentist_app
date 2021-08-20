package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ItemUserCommentBinding;
import com.example.myapplication.model.modelTest.CommentsItem;
import com.example.myapplication.viewModel.ArticleViewModel;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentsHolder> {
    private ArticleViewModel mViewModel;

    public CommentsAdapter(ArticleViewModel mViewModel) {
        this.mViewModel = mViewModel;
    }

    @NonNull
    @Override
    public CommentsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemUserCommentBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(mViewModel.getApplication()),
                R.layout.item_user_comment,
                parent,
                false);
        return new CommentsHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsHolder holder, int position) {
        holder.bindItem(mViewModel.getCommentsLiveData().getValue().get(position));

    }

    @Override
    public int getItemCount() {
        return mViewModel.getCommentsLiveData().getValue().size();
    }

    public class CommentsHolder extends RecyclerView.ViewHolder {

        private ItemUserCommentBinding mBinding;

        public CommentsHolder(ItemUserCommentBinding userCommentBinding) {
            super(userCommentBinding.getRoot());

            mBinding = userCommentBinding;
        }

        public void bindItem(CommentsItem commentsItem){
            mBinding.setComment(commentsItem);

            mBinding.ratingBarComments.setRating(Float.parseFloat(commentsItem.getStar()));

        }
    }
}
