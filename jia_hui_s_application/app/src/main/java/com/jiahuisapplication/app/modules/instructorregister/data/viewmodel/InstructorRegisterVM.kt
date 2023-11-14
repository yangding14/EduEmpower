package com.jiahuisapplication.app.modules.instructorregister.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jiahuisapplication.app.modules.instructorregister.`data`.model.InstructorRegisterModel
import org.koin.core.KoinComponent

class InstructorRegisterVM : ViewModel(), KoinComponent {
  val instructorRegisterModel: MutableLiveData<InstructorRegisterModel> =
      MutableLiveData(InstructorRegisterModel())

  var navArguments: Bundle? = null
}
