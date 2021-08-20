package com.example.myapplication.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.adapter.SliderAdapter;
import com.example.myapplication.databinding.FragmentHomeBinding;
import com.example.myapplication.model.BannersItem;
import com.example.myapplication.view.activity.EducationActivity;
import com.example.myapplication.viewModel.ArticleViewModel;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;

import java.util.List;

public class HomeFragment extends Fragment {

    public static final String ID_CATEGORY = "idCategory";
    private FragmentHomeBinding mBinding;
    private ArticleViewModel mViewModel;
    private SliderAdapter mAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }

        mViewModel = new ViewModelProvider(this).get(ArticleViewModel.class);
        mViewModel.getBanners();
        mViewModel.getBannersLiveData().observe(this, new Observer<List<BannersItem>>() {
            @Override
            public void onChanged(List<BannersItem> bannersItems) {
                setAdapters();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        setListeners();
        return mBinding.getRoot();

    }

    private void setListeners() {
        mBinding.btnEducationPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt(ID_CATEGORY, 0);
                Navigation.findNavController(mBinding.getRoot()).navigate(R.id.educationFragment, bundle);
                /*Intent intent = EducationActivity.newIntent(getContext(), 0);
                startActivity(intent);*/

            }
        });
    }

    private void navListeners(){

    }

    private void setAdapters(){
        mAdapter = new SliderAdapter(mViewModel);
        mBinding.cardViewHomePage.setSliderAdapter(mAdapter);
        mBinding.cardViewHomePage.setIndicatorAnimation(IndicatorAnimationType.WORM);
        mBinding.cardViewHomePage.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        mBinding.cardViewHomePage.startAutoCycle();
    }
}