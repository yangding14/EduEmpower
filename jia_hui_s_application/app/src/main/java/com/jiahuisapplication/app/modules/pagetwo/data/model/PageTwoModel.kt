package com.jiahuisapplication.app.modules.pagetwo.`data`.model

import com.jiahuisapplication.app.R
import com.jiahuisapplication.app.appcomponents.di.MyApp
import kotlin.String

data class PageTwoModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtEmpoweryoured: String? =
      MyApp.getInstance().resources.getString(R.string.msg_empower_your_ed)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguage: String? = MyApp.getInstance().resources.getString(R.string.msg_learn_new_thing)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtSkip: String? = MyApp.getInstance().resources.getString(R.string.lbl_skip)

)
