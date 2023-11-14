package com.jiahuisapplication.app.modules.registerpage.`data`.model

import com.jiahuisapplication.app.R
import com.jiahuisapplication.app.appcomponents.di.MyApp
import kotlin.String

data class RegisterPageModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtREGISTERAS: String? = MyApp.getInstance().resources.getString(R.string.lbl_register_as)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtStudent: String? = MyApp.getInstance().resources.getString(R.string.lbl_student)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtInstructor: String? = MyApp.getInstance().resources.getString(R.string.lbl_instructor)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtConfirmation: String? =
      MyApp.getInstance().resources.getString(R.string.msg_already_have_an)

)
