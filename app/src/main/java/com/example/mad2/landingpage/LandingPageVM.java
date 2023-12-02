package com.example.mad2.landingpage;

import android.os.Bundle;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LandingPageVM extends ViewModel {
    private MutableLiveData<LandingPageModel> landingPageModel = new MutableLiveData<>(new LandingPageModel());
    private Bundle navArguments;

    public MutableLiveData<LandingPageModel> getLandingPageModel() {
        return landingPageModel;
    }

    public void setLandingPageModel(MutableLiveData<LandingPageModel> landingPageModel) {
        this.landingPageModel = landingPageModel;
    }



    public Bundle getNavArguments() {
        return navArguments;
    }

    public void setNavArguments(Bundle navArguments) {
        this.navArguments = navArguments;
    }
}
