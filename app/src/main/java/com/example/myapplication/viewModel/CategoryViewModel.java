package com.example.myapplication.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.myapplication.repository.Repository;
import com.example.myapplication.model.Category;

import java.util.List;

public class CategoryViewModel extends AndroidViewModel {

    private Repository mRepository;
    private final LiveData<List<Category>> mLiveDataCategory;

    public CategoryViewModel(@NonNull Application application) {
        super(application);

        this.mRepository = Repository.getInstance();
        mLiveDataCategory = getLiveDataCategory();
    }

    public LiveData<List<Category>> getLiveDataCategory() {
        return mLiveDataCategory;
    }


    public void getCategories(){
        mRepository.fetchCategory();
    }
}
