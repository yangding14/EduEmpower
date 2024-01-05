package com.example.eduempoweryd.login.loginpage;

import android.os.Bundle;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginPageVM extends ViewModel {
    public MutableLiveData<LoginPageModel> pageOneModel = new MutableLiveData<>(new LoginPageModel());
    private MutableLiveData<Bundle> navArguments = new MutableLiveData<>();

//    public MutableLiveData<LoginPageModel> getLoginPageModel() {
//        return LoginPageModel;
//    }

    public MutableLiveData<Bundle> getNavArguments() {
        return navArguments;
    }

    public void setNavArguments(Bundle navArguments) {
        this.navArguments.setValue(navArguments);
    }

}
