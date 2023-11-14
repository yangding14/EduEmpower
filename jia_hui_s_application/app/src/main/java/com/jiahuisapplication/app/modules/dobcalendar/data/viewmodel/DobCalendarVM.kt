package com.jiahuisapplication.app.modules.dobcalendar.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jiahuisapplication.app.modules.dobcalendar.`data`.model.DobCalendarModel
import org.koin.core.KoinComponent

class DobCalendarVM : ViewModel(), KoinComponent {
  val dobCalendarModel: MutableLiveData<DobCalendarModel> = MutableLiveData(DobCalendarModel())

  var navArguments: Bundle? = null
}
