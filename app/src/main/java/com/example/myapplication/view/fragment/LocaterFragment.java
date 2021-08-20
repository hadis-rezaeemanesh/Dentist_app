package com.example.myapplication.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentLocatrBinding;

public class LocaterFragment extends Fragment {

    private FragmentLocatrBinding mBinding;
    public LocaterFragment() {
        // Required empty public constructor
    }

    public static LocaterFragment newInstance() {
        LocaterFragment fragment = new LocaterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_locatr, container, false);
        return mBinding.getRoot();
    }
}