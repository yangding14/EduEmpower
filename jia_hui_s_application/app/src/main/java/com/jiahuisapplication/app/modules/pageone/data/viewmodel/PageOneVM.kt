package com.jiahuisapplication.app.modules.pageone.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jiahuisapplication.app.modules.pageone.`data`.model.PageOneModel
import org.koin.core.KoinComponent

class PageOneVM : ViewModel(), KoinComponent {
  val pageOneModel: MutableLiveData<PageOneModel> = MutableLiveData(PageOneModel())

  var navArguments: Bundle? = null
}
