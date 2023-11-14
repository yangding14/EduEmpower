package com.jiahuisapplication.app.modules.studentsurveyoninterest.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.jiahuisapplication.app.R
import com.jiahuisapplication.app.appcomponents.base.BaseActivity
import com.jiahuisapplication.app.databinding.ActivityStudentSurveyOnInterestBinding
import com.jiahuisapplication.app.modules.studentsurveyoninterest.`data`.viewmodel.StudentSurveyOnInterestVM
import kotlin.String
import kotlin.Unit

class StudentSurveyOnInterestActivity :
    BaseActivity<ActivityStudentSurveyOnInterestBinding>(R.layout.activity_student_survey_on_interest)
    {
  private val viewModel: StudentSurveyOnInterestVM by viewModels<StudentSurveyOnInterestVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.studentSurveyOnInterestVM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  companion object {
    const val TAG: String = "STUDENT_SURVEY_ON_INTEREST_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, StudentSurveyOnInterestActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
