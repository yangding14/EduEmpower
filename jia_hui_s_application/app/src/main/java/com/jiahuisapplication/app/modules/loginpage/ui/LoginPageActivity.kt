package com.jiahuisapplication.app.modules.loginpage.ui

import androidx.activity.viewModels
import com.jiahuisapplication.app.R
import com.jiahuisapplication.app.appcomponents.base.BaseActivity
import com.jiahuisapplication.app.databinding.ActivityLoginPageBinding
import com.jiahuisapplication.app.modules.forgotpasswordmessage.ui.ForgotPasswordMessageActivity
import com.jiahuisapplication.app.modules.loginpage.`data`.model.SpinnerGroupTwoModel
import com.jiahuisapplication.app.modules.loginpage.`data`.viewmodel.LoginPageVM
import kotlin.String
import kotlin.Unit

class LoginPageActivity : BaseActivity<ActivityLoginPageBinding>(R.layout.activity_login_page) {
  private val viewModel: LoginPageVM by viewModels<LoginPageVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    viewModel.spinnerGroupTwoList.value = mutableListOf(
    SpinnerGroupTwoModel("Item1"),
    SpinnerGroupTwoModel("Item2"),
    SpinnerGroupTwoModel("Item3"),
    SpinnerGroupTwoModel("Item4"),
    SpinnerGroupTwoModel("Item5")
    )
    val spinnerGroupTwoAdapter =
    SpinnerGroupTwoAdapter(this,R.layout.spinner_item,viewModel.spinnerGroupTwoList.value?:
    mutableListOf())
    binding.spinnerGroupTwo.adapter = spinnerGroupTwoAdapter
    binding.loginPageVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.txtForgotpassword.setOnClickListener {
      val destIntent = ForgotPasswordMessageActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "LOGIN_PAGE_ACTIVITY"

  }
}
