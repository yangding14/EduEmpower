package com.example.mad2.pagetwo;

import android.os.Bundle;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PageTwoVM extends ViewModel {
    public MutableLiveData<PageTwoModel> pageTwoModel = new MutableLiveData<>(new PageTwoModel());
    private MutableLiveData<Bundle> navArguments = new MutableLiveData<>();

    public MutableLiveData<PageTwoModel> getPageTwoModel() {
        return pageTwoModel;
    }

    public MutableLiveData<Bundle> getNavArguments() {
        return navArguments;
    }

    public void setNavArguments(Bundle navArguments) {
        this.navArguments.setValue(navArguments);
    }
}
