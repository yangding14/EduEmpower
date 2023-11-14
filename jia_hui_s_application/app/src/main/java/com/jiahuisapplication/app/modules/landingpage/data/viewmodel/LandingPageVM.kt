package com.jiahuisapplication.app.modules.landingpage.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jiahuisapplication.app.modules.landingpage.`data`.model.LandingPageModel
import org.koin.core.KoinComponent

class LandingPageVM : ViewModel(), KoinComponent {
  val landingPageModel: MutableLiveData<LandingPageModel> = MutableLiveData(LandingPageModel())

  var navArguments: Bundle? = null
}
