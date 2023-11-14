package com.jiahuisapplication.app.modules.pageone.`data`.model

import com.jiahuisapplication.app.R
import com.jiahuisapplication.app.appcomponents.di.MyApp
import kotlin.String

data class PageOneModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtExploreyourne: String? =
      MyApp.getInstance().resources.getString(R.string.msg_explore_your_ne)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtYoucanlearnv: String? =
      MyApp.getInstance().resources.getString(R.string.msg_you_can_learn_v)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtSkip: String? = MyApp.getInstance().resources.getString(R.string.lbl_skip)

)
