package com.example.eduempoweryd.login.registerpage;

import android.os.Bundle;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RegisterPageVM extends ViewModel {
    public MutableLiveData<RegisterPageModel> RegisterPageModel = new MutableLiveData<>(new RegisterPageModel());
    private MutableLiveData<Bundle> navArguments = new MutableLiveData<>();

    public MutableLiveData<RegisterPageModel> getPageTwoModel() {
        return RegisterPageModel;
    }

    public MutableLiveData<Bundle> getNavArguments() {
        return navArguments;
    }

    public void setNavArguments(Bundle navArguments) {
        this.navArguments.setValue(navArguments);
    }
}
