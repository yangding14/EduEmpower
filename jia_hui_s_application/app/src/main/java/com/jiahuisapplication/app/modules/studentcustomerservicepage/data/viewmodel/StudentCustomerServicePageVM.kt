package com.jiahuisapplication.app.modules.studentcustomerservicepage.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jiahuisapplication.app.modules.studentcustomerservicepage.`data`.model.StudentCustomerServicePageModel
import org.koin.core.KoinComponent

class StudentCustomerServicePageVM : ViewModel(), KoinComponent {
  val studentCustomerServicePageModel: MutableLiveData<StudentCustomerServicePageModel> =
      MutableLiveData(StudentCustomerServicePageModel())

  var navArguments: Bundle? = null
}
