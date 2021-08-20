package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ItemBannerBinding;
import com.example.myapplication.model.BannersItem;
import com.example.myapplication.viewModel.ArticleViewModel;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderHolder> {

    private ArticleViewModel mViewModel;

    public SliderAdapter(ArticleViewModel mViewModel) {
        this.mViewModel = mViewModel;
    }

    @Override
    public SliderHolder onCreateViewHolder(ViewGroup parent) {
        ItemBannerBinding itemBannerBinding = DataBindingUtil.inflate(
                LayoutInflater.from(mViewModel.getApplication()),
                R.layout.item_banner,
                parent,
                false);
        return new SliderHolder(itemBannerBinding);
    }

    @Override
    public void onBindViewHolder(SliderHolder viewHolder, int position) {
        viewHolder.bindItems(mViewModel.getBannersLiveData().getValue().get(position));

    }

    @Override
    public int getCount() {
        return mViewModel.getBannersLiveData().getValue().size();
    }

    public class SliderHolder extends SliderViewAdapter.ViewHolder {
        private ItemBannerBinding mItemBinding;
        public SliderHolder(ItemBannerBinding itemBannerBinding){
            super(itemBannerBinding.getRoot());
            mItemBinding = itemBannerBinding;
        }

        public void bindItems(BannersItem item){
            Picasso.get()
                    .load(item.getImage())
                    .placeholder(R.drawable.ic_dentist)
                    .into(mItemBinding.imgViewBannerItem);
        }
    }
}
