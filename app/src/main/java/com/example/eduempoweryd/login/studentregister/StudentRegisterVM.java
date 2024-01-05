package com.example.eduempoweryd.login.studentregister;

import android.os.Bundle;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class StudentRegisterVM extends ViewModel {
    public MutableLiveData<StudentRegisterModel> StudentRegisterModel = new MutableLiveData<>(new StudentRegisterModel());
    private MutableLiveData<Bundle> navArguments = new MutableLiveData<>();

    public MutableLiveData<StudentRegisterModel> getPageTwoModel() {
        return StudentRegisterModel;
    }

    public MutableLiveData<Bundle> getNavArguments() {
        return navArguments;
    }

    public void setNavArguments(Bundle navArguments) {
        this.navArguments.setValue(navArguments);
    }
}
