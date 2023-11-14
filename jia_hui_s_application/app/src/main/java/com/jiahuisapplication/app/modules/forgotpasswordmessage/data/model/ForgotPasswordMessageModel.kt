package com.jiahuisapplication.app.modules.forgotpasswordmessage.`data`.model

import com.jiahuisapplication.app.R
import com.jiahuisapplication.app.appcomponents.di.MyApp
import kotlin.String

data class ForgotPasswordMessageModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtDescription: String? =
      MyApp.getInstance().resources.getString(R.string.msg_forgot_password2)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtCancel: String? = MyApp.getInstance().resources.getString(R.string.lbl_cancel)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtSend: String? = MyApp.getInstance().resources.getString(R.string.lbl_send)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var etEmailValue: String? = null
)
