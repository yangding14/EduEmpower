package com.jiahuisapplication.app.modules.studentsurveyoninterest.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jiahuisapplication.app.modules.studentsurveyoninterest.`data`.model.StudentSurveyOnInterestModel
import org.koin.core.KoinComponent

class StudentSurveyOnInterestVM : ViewModel(), KoinComponent {
  val studentSurveyOnInterestModel: MutableLiveData<StudentSurveyOnInterestModel> =
      MutableLiveData(StudentSurveyOnInterestModel())

  var navArguments: Bundle? = null
}
