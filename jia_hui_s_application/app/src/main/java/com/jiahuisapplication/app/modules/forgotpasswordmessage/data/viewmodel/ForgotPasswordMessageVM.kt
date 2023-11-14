package com.jiahuisapplication.app.modules.forgotpasswordmessage.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jiahuisapplication.app.modules.forgotpasswordmessage.`data`.model.ForgotPasswordMessageModel
import org.koin.core.KoinComponent

class ForgotPasswordMessageVM : ViewModel(), KoinComponent {
  val forgotPasswordMessageModel: MutableLiveData<ForgotPasswordMessageModel> =
      MutableLiveData(ForgotPasswordMessageModel())

  var navArguments: Bundle? = null
}
