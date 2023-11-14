package com.jiahuisapplication.app.modules.loginpage.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jiahuisapplication.app.modules.loginpage.`data`.model.LoginPageModel
import com.jiahuisapplication.app.modules.loginpage.`data`.model.SpinnerGroupTwoModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class LoginPageVM : ViewModel(), KoinComponent {
  val loginPageModel: MutableLiveData<LoginPageModel> = MutableLiveData(LoginPageModel())

  var navArguments: Bundle? = null

  val spinnerGroupTwoList: MutableLiveData<MutableList<SpinnerGroupTwoModel>> = MutableLiveData()
}
