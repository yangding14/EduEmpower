package com.jiahuisapplication.app.modules.dobcalendar.ui

import androidx.activity.viewModels
import com.jiahuisapplication.app.R
import com.jiahuisapplication.app.appcomponents.base.BaseActivity
import com.jiahuisapplication.app.databinding.ActivityDobCalendarBinding
import com.jiahuisapplication.app.modules.dobcalendar.`data`.viewmodel.DobCalendarVM
import kotlin.String
import kotlin.Unit

class DobCalendarActivity : BaseActivity<ActivityDobCalendarBinding>(R.layout.activity_dob_calendar)
    {
  private val viewModel: DobCalendarVM by viewModels<DobCalendarVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.dobCalendarVM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  companion object {
    const val TAG: String = "DOB_CALENDAR_ACTIVITY"

  }
}
