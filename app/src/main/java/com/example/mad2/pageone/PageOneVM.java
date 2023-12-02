package com.example.mad2.pageone;

import android.os.Bundle;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PageOneVM extends ViewModel {
    public MutableLiveData<PageOneModel> pageOneModel = new MutableLiveData<>(new PageOneModel());
    private MutableLiveData<Bundle> navArguments = new MutableLiveData<>();

    public MutableLiveData<PageOneModel> getPageOneModel() {
        return pageOneModel;
    }

    public MutableLiveData<Bundle> getNavArguments() {
        return navArguments;
    }

    public void setNavArguments(Bundle navArguments) {
        this.navArguments.setValue(navArguments);
    }

}
