package com.example.myapplication.view.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.adapter.CategoryAdapter;
import com.example.myapplication.databinding.FragmentCategoryDialogBinding;
import com.example.myapplication.model.Category;
import com.example.myapplication.view.activity.EducationActivity;
import com.example.myapplication.viewModel.ArticleViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

public class CategoryDialogFragment extends BottomSheetDialogFragment {


    public static final String ARGS_ID_CLICKED_ON_CATEGORY = "idClickedOnCategory";
    private CategoryAdapter mAdapter;
    private ArticleViewModel mViewModel;
    private FragmentCategoryDialogBinding mBinding;
    private int id;


    public CategoryDialogFragment() {
        // Required empty public constructor
    }

    public static CategoryDialogFragment newInstance() {
        CategoryDialogFragment fragment = new CategoryDialogFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ArticleViewModel.class);
        mViewModel.fetchCategory();
        mViewModel.getLiveDataCategory().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                setAdapter(categories);
            }
        });
    }


    @Override
    public void setupDialog(@NonNull Dialog dialog, int style) {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_category_dialog, null, false);
        dialog.setContentView(mBinding.getRoot());
        ((View) mBinding.getRoot().getParent()).setBackgroundColor(getResources().getColor(android.R.color.transparent));
//navListeners();
setListeners();
    }

    private void setListeners() {
        mBinding.imageButtonCancelCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        mBinding.txtViewCategoryAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = EducationActivity.newIntent(getContext(), 0);
                startActivity(intent);
               /* mViewModel.getItems();
                mViewModel.getLiveDataArticleByCategory().observe( getActivity(), new Observer<List<ArticlesItem>>() {
                    @Override
                    public void onChanged(List<ArticlesItem> articlesItems) {


                    }
                });*/
            }
        });
    }

    private void setAdapter(List<Category> items){
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mBinding.recyclerViewCategory.setLayoutManager(llm);;
        mAdapter = new CategoryAdapter(mViewModel, items);
        mAdapter.notifyDataSetChanged();
        mBinding.recyclerViewCategory.setAdapter(mAdapter);

    }

    private void navListeners(){
        mViewModel.getClickedCategory().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Bundle bundle = new Bundle();
                bundle.putInt("BUNDLE_ID_CATEGORY", integer);
                Navigation.findNavController(mBinding.getRoot()).navigate(R.id.educationFragment, bundle);
            }
        });
    }
}