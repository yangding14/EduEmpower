package com.jiahuisapplication.app.modules.educationalstatusdrodownlist.`data`.model

import com.jiahuisapplication.app.R
import com.jiahuisapplication.app.appcomponents.di.MyApp
import kotlin.String

data class EducationalStatusDrodownListModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtGroupSeven: String? = MyApp.getInstance().resources.getString(R.string.lbl_primary_school)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtGroupSix: String? = MyApp.getInstance().resources.getString(R.string.lbl_middle_school)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtGroupFive: String? = MyApp.getInstance().resources.getString(R.string.lbl_high_school)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtGroupFour: String? = MyApp.getInstance().resources.getString(R.string.lbl_college)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtGroupThree: String? = MyApp.getInstance().resources.getString(R.string.lbl_high_school)

)
