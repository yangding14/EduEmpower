package com.jiahuisapplication.app.modules.registerpage.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.jiahuisapplication.app.R
import com.jiahuisapplication.app.appcomponents.base.BaseActivity
import com.jiahuisapplication.app.databinding.ActivityRegisterPageBinding
import com.jiahuisapplication.app.modules.instructorregister.ui.InstructorRegisterActivity
import com.jiahuisapplication.app.modules.registerpage.`data`.viewmodel.RegisterPageVM
import com.jiahuisapplication.app.modules.studentregister.ui.StudentRegisterActivity
import kotlin.String
import kotlin.Unit

class RegisterPageActivity :
    BaseActivity<ActivityRegisterPageBinding>(R.layout.activity_register_page) {
  private val viewModel: RegisterPageVM by viewModels<RegisterPageVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.registerPageVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.viewInputFieldT.setOnClickListener {
      val destIntent = StudentRegisterActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.viewInputFieldTOne.setOnClickListener {
      val destIntent = InstructorRegisterActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "REGISTER_PAGE_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, RegisterPageActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
