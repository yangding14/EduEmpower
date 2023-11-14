package com.jiahuisapplication.app.modules.educationalstatusdrodownlist.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jiahuisapplication.app.modules.educationalstatusdrodownlist.`data`.model.EducationalStatusDrodownListModel
import org.koin.core.KoinComponent

class EducationalStatusDrodownListVM : ViewModel(), KoinComponent {
  val educationalStatusDrodownListModel: MutableLiveData<EducationalStatusDrodownListModel> =
      MutableLiveData(EducationalStatusDrodownListModel())

  var navArguments: Bundle? = null
}
