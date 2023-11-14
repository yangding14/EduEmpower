package com.jiahuisapplication.app.modules.forgotpasswordmessage.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.jiahuisapplication.app.R
import com.jiahuisapplication.app.appcomponents.base.BaseActivity
import com.jiahuisapplication.app.databinding.ActivityForgotPasswordMessageBinding
import com.jiahuisapplication.app.modules.forgotpasswordmessage.`data`.viewmodel.ForgotPasswordMessageVM
import kotlin.String
import kotlin.Unit

class ForgotPasswordMessageActivity :
    BaseActivity<ActivityForgotPasswordMessageBinding>(R.layout.activity_forgot_password_message) {
  private val viewModel: ForgotPasswordMessageVM by viewModels<ForgotPasswordMessageVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.forgotPasswordMessageVM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  companion object {
    const val TAG: String = "FORGOT_PASSWORD_MESSAGE_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, ForgotPasswordMessageActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
