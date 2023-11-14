package com.jiahuisapplication.app.modules.pagetwo.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.jiahuisapplication.app.R
import com.jiahuisapplication.app.appcomponents.base.BaseActivity
import com.jiahuisapplication.app.databinding.ActivityPageTwoBinding
import com.jiahuisapplication.app.modules.pagetwo.`data`.viewmodel.PageTwoVM
import com.jiahuisapplication.app.modules.registerpage.ui.RegisterPageActivity
import kotlin.String
import kotlin.Unit

class PageTwoActivity : BaseActivity<ActivityPageTwoBinding>(R.layout.activity_page_two) {
  private val viewModel: PageTwoVM by viewModels<PageTwoVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.pageTwoVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.btnNext.setOnClickListener {
      val destIntent = RegisterPageActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.txtSkip.setOnClickListener {
      val destIntent = RegisterPageActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "PAGE_TWO_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, PageTwoActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
