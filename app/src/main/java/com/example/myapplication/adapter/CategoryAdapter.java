package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ItemCategoryBinding;
import com.example.myapplication.model.CategoriesItem;
import com.example.myapplication.model.Category;
import com.example.myapplication.viewModel.ArticleViewModel;
import com.example.myapplication.viewModel.CategoryViewModel;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {

    private ArticleViewModel mViewModel;
    private List<Category> mItems;

    public CategoryAdapter(ArticleViewModel mViewModel, List<Category> items) {
        this.mViewModel = mViewModel;
        mItems = items;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCategoryBinding itemCategoryBinding =
                DataBindingUtil.inflate(
                        LayoutInflater.from(mViewModel.getApplication()),
                        R.layout.item_category,
                        parent,
                        false);
        return new CategoryHolder(itemCategoryBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        holder.bindItem(mViewModel.getLiveDataCategory().getValue().get(position));
    }

    @Override
    public int getItemCount() {
        return mViewModel.getLiveDataCategory().getValue().size();
    }

    public class CategoryHolder extends RecyclerView.ViewHolder {

        private ItemCategoryBinding mBinding;

        public CategoryHolder(ItemCategoryBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mBinding.setViewModel(mViewModel);

            List<Category> categories = mItems;
            List<Integer> listIdsCategories = new ArrayList<>();
            for (int i = 0; i <categories.size() ; i++) {
                listIdsCategories.add(categories.get(i).getId());
            }
            mBinding.setCategoryId(listIdsCategories);
        }

        public void bindItem(Category category){
            mBinding.setCategory(category);
            mBinding.txtViewCategoryItem.setText(category.getNameCategory());

        }
    }
}
