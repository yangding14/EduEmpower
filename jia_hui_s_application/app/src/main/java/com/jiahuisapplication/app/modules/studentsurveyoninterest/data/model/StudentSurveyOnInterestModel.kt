package com.jiahuisapplication.app.modules.studentsurveyoninterest.`data`.model

import com.jiahuisapplication.app.R
import com.jiahuisapplication.app.appcomponents.di.MyApp
import kotlin.String

data class StudentSurveyOnInterestModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtSURVEY: String? = MyApp.getInstance().resources.getString(R.string.lbl_survey)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPleaseselectb: String? =
      MyApp.getInstance().resources.getString(R.string.msg_please_select_b)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLabel: String? = MyApp.getInstance().resources.getString(R.string.lbl_marketing)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLabelOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_architecture)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLabelTwo: String? = MyApp.getInstance().resources.getString(R.string.lbl_pyschology)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLabelThree: String? = MyApp.getInstance().resources.getString(R.string.lbl_programming)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLabelFour: String? = MyApp.getInstance().resources.getString(R.string.lbl_photography)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLabelFive: String? = MyApp.getInstance().resources.getString(R.string.msg_web_developm)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLabelSix: String? = MyApp.getInstance().resources.getString(R.string.lbl_business)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLabelSeven: String? = MyApp.getInstance().resources.getString(R.string.msg_human_resour)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLabelEight: String? = MyApp.getInstance().resources.getString(R.string.lbl_science)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var etChipValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etChipOneValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etChipTwoValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etLanguageValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etLanguageOneValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etChipThreeValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etLanguageTwoValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etChipFourValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etChipFiveValue: String? = null
)
