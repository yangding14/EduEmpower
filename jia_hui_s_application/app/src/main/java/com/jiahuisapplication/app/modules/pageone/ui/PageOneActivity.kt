package com.jiahuisapplication.app.modules.pageone.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.jiahuisapplication.app.R
import com.jiahuisapplication.app.appcomponents.base.BaseActivity
import com.jiahuisapplication.app.databinding.ActivityPageOneBinding
import com.jiahuisapplication.app.modules.pageone.`data`.viewmodel.PageOneVM
import com.jiahuisapplication.app.modules.pagetwo.ui.PageTwoActivity
import com.jiahuisapplication.app.modules.registerpage.ui.RegisterPageActivity
import kotlin.String
import kotlin.Unit

class PageOneActivity : BaseActivity<ActivityPageOneBinding>(R.layout.activity_page_one) {
  private val viewModel: PageOneVM by viewModels<PageOneVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.pageOneVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.txtSkip.setOnClickListener {
      val destIntent = RegisterPageActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.btnNext.setOnClickListener {
      val destIntent = PageTwoActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "PAGE_ONE_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, PageOneActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
