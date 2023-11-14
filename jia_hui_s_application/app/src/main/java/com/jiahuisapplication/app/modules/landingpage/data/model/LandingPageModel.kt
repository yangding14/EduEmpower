package com.jiahuisapplication.app.modules.landingpage.`data`.model

import com.jiahuisapplication.app.R
import com.jiahuisapplication.app.appcomponents.di.MyApp
import kotlin.String

data class LandingPageModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtEDUEMPOWER: String? = MyApp.getInstance().resources.getString(R.string.lbl_eduempower)

)
