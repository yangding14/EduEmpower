package com.jiahuisapplication.app.modules.studentcustomerservicepage.ui

import androidx.activity.viewModels
import com.jiahuisapplication.app.R
import com.jiahuisapplication.app.appcomponents.base.BaseActivity
import com.jiahuisapplication.app.databinding.ActivityStudentCustomerServicePageBinding
import com.jiahuisapplication.app.modules.studentcustomerservicepage.`data`.viewmodel.StudentCustomerServicePageVM
import kotlin.String
import kotlin.Unit

class StudentCustomerServicePageActivity :
    BaseActivity<ActivityStudentCustomerServicePageBinding>(R.layout.activity_student_customer_service_page)
    {
  private val viewModel: StudentCustomerServicePageVM by viewModels<StudentCustomerServicePageVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.studentCustomerServicePageVM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  companion object {
    const val TAG: String = "STUDENT_CUSTOMER_SERVICE_PAGE_ACTIVITY"

  }
}
