package com.jiahuisapplication.app.modules.instructorregister.`data`.model

import com.jiahuisapplication.app.R
import com.jiahuisapplication.app.appcomponents.di.MyApp
import kotlin.String

data class InstructorRegisterModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtREGISTER: String? = MyApp.getInstance().resources.getString(R.string.lbl_register)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtDescription: String? =
      MyApp.getInstance().resources.getString(R.string.msg_full_name_email)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtConfirmation: String? =
      MyApp.getInstance().resources.getString(R.string.msg_already_have_an3)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var etGroupFifteenValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etGroupThirteenValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etInputFieldTValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etInputFieldTOneValue: String? = null
)
