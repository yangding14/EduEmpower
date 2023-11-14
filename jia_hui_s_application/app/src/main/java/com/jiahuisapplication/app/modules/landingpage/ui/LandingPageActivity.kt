package com.jiahuisapplication.app.modules.landingpage.ui

import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import com.jiahuisapplication.app.R
import com.jiahuisapplication.app.appcomponents.base.BaseActivity
import com.jiahuisapplication.app.databinding.ActivityLandingPageBinding
import com.jiahuisapplication.app.modules.landingpage.`data`.viewmodel.LandingPageVM
import com.jiahuisapplication.app.modules.pageone.ui.PageOneActivity
import kotlin.String
import kotlin.Unit

class LandingPageActivity : BaseActivity<ActivityLandingPageBinding>(R.layout.activity_landing_page)
    {
  private val viewModel: LandingPageVM by viewModels<LandingPageVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.landingPageVM = viewModel
    Handler(Looper.getMainLooper()).postDelayed( {
      val destIntent = PageOneActivity.getIntent(this, null)
      startActivity(destIntent)
      finish()
      }, 3000)
    }

    override fun setUpClicks(): Unit {
      binding.imageImageThree.setOnClickListener {
        val destIntent = PageOneActivity.getIntent(this, null)
        startActivity(destIntent)
      }
    }

    companion object {
      const val TAG: String = "LANDING_PAGE_ACTIVITY"

    }
  }
