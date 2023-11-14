package com.jiahuisapplication.app.modules.studentregister.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.jiahuisapplication.app.R
import com.jiahuisapplication.app.appcomponents.base.BaseActivity
import com.jiahuisapplication.app.databinding.ActivityStudentRegisterBinding
import com.jiahuisapplication.app.modules.studentregister.`data`.model.SpinnerDescriptionModel
import com.jiahuisapplication.app.modules.studentregister.`data`.viewmodel.StudentRegisterVM
import com.jiahuisapplication.app.modules.studentsurveyoninterest.ui.StudentSurveyOnInterestActivity
import kotlin.String
import kotlin.Unit

class StudentRegisterActivity :
    BaseActivity<ActivityStudentRegisterBinding>(R.layout.activity_student_register) {
  private val viewModel: StudentRegisterVM by viewModels<StudentRegisterVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    viewModel.spinnerDescriptionList.value = mutableListOf(
    SpinnerDescriptionModel("Item1"),
    SpinnerDescriptionModel("Item2"),
    SpinnerDescriptionModel("Item3"),
    SpinnerDescriptionModel("Item4"),
    SpinnerDescriptionModel("Item5")
    )
    val spinnerDescriptionAdapter =
    SpinnerDescriptionAdapter(this,R.layout.spinner_item,viewModel.spinnerDescriptionList.value?:
    mutableListOf())
    binding.spinnerDescription.adapter = spinnerDescriptionAdapter
    binding.studentRegisterVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.btnSignUp.setOnClickListener {
      val destIntent = StudentSurveyOnInterestActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "STUDENT_REGISTER_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, StudentRegisterActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
