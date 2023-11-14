package com.jiahuisapplication.app.modules.studentregister.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jiahuisapplication.app.modules.studentregister.`data`.model.SpinnerDescriptionModel
import com.jiahuisapplication.app.modules.studentregister.`data`.model.StudentRegisterModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class StudentRegisterVM : ViewModel(), KoinComponent {
  val studentRegisterModel: MutableLiveData<StudentRegisterModel> =
      MutableLiveData(StudentRegisterModel())

  var navArguments: Bundle? = null

  val spinnerDescriptionList: MutableLiveData<MutableList<SpinnerDescriptionModel>> =
      MutableLiveData()
}
