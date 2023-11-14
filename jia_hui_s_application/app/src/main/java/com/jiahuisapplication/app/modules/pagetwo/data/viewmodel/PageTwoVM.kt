package com.jiahuisapplication.app.modules.pagetwo.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jiahuisapplication.app.modules.pagetwo.`data`.model.PageTwoModel
import org.koin.core.KoinComponent

class PageTwoVM : ViewModel(), KoinComponent {
  val pageTwoModel: MutableLiveData<PageTwoModel> = MutableLiveData(PageTwoModel())

  var navArguments: Bundle? = null
}
