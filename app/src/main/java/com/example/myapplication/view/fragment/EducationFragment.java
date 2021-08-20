package com.example.myapplication.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ArticleListAdapter;
import com.example.myapplication.adapter.CategoryAdapter;
import com.example.myapplication.databinding.FragmentEducationBinding;
import com.example.myapplication.model.ArticlesItem;
import com.example.myapplication.model.modelTest.Item;
import com.example.myapplication.viewModel.ArticleViewModel;

import java.util.List;


public class EducationFragment extends Fragment {

    public static final String TAG_CATEGORY_BOTTOM_SHEET_DIALOG = "categoryBottomSheetDialog";
    public static final String ARGS_ID_CATEGORY_FOR_ARTICLES = "idCategoryForArticles";
    public static final String BUNDLE_ID_ARTICLE_WITH_CLICKED = "idArticleWithClicked";
    private ArticleViewModel mViewModel;
    private ArticleListAdapter mArticleAdapter;
    private FragmentEducationBinding mBinding;
    private CategoryAdapter mCategoryAdapter;
    private int mIdCategory;

    public EducationFragment() {
        // Required empty public constructor
    }

    public static EducationFragment newInstance(int id) {
        EducationFragment fragment = new EducationFragment();
        Bundle args = new Bundle();
        args.putInt(ARGS_ID_CATEGORY_FOR_ARTICLES, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mIdCategory = getArguments().getInt(HomeFragment.ID_CATEGORY);
        mViewModel = new ViewModelProvider(this).get(ArticleViewModel.class);
       mViewModel.fetchArticleByCategory(mIdCategory);
       mViewModel.getLiveDataArticleByCategory().observe(this, new Observer<List<ArticlesItem>>() {
           @Override
           public void onChanged(List<ArticlesItem> items) {
               setAdapter(items);
           }
       });
       mViewModel.getSearchedLiveData().observe(this, new Observer<List<ArticlesItem>>() {
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
                .inflate(inflater, R.layout.fragment_education, container, false);
        customizeSearchBox();
        setListeners();
        initViews();
        return mBinding.getRoot();


    }

    private void customizeSearchBox() {
        setSearchHintIcon(R.drawable.ic_search_dark);
    }

    private void setSearchHintIcon(int resourceId) {
        ImageView searchHintIcon = (ImageView) findViewById(mBinding.searchEducation,
                "android:id/search_mag_icon");
        searchHintIcon.setImageResource(resourceId);
    }

    private View findViewById(View v, String id) {
        return v.findViewById(v.getContext().getResources().
                getIdentifier(id, null, null));
    }


    private void setListeners() {
        mBinding.btnCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CategoryDialogFragment categoryDialogFragment = CategoryDialogFragment.newInstance();
                categoryDialogFragment.show(
                        getActivity().getSupportFragmentManager(),
                        TAG_CATEGORY_BOTTOM_SHEET_DIALOG);
            }
        });
        mBinding.searchEducation.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                mViewModel.getSearchedItems(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

    }

    private void initViews() {
        mBinding.recyclerViewEducation.setLayoutManager(new LinearLayoutManager(getContext()));

    }
    private void setAdapter(List<ArticlesItem> articles){
//        mCategoryAdapter = new CategoryAdapter(mViewModel, );
        mArticleAdapter = new ArticleListAdapter(mViewModel, articles);
       mBinding.recyclerViewEducation.setAdapter(mArticleAdapter);
    }

    private void navListeners(){
        mViewModel.getClickedCategory().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Bundle bundle = new Bundle();
                bundle.putInt("BUNDLE_ID_CATEGORY", integer);
                Navigation.findNavController(mBinding.getRoot()).navigate(R.id.categoryDialogFragment, bundle);
            }
        });
    }

}