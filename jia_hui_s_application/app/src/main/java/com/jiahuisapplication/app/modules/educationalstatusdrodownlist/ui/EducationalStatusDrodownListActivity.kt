package com.jiahuisapplication.app.modules.educationalstatusdrodownlist.ui

import androidx.activity.viewModels
import com.jiahuisapplication.app.R
import com.jiahuisapplication.app.appcomponents.base.BaseActivity
import com.jiahuisapplication.app.databinding.ActivityEducationalStatusDrodownListBinding
import com.jiahuisapplication.app.modules.educationalstatusdrodownlist.`data`.viewmodel.EducationalStatusDrodownListVM
import kotlin.String
import kotlin.Unit

class EducationalStatusDrodownListActivity :
    BaseActivity<ActivityEducationalStatusDrodownListBinding>(R.layout.activity_educational_status_drodown_list)
    {
  private val viewModel: EducationalStatusDrodownListVM by
      viewModels<EducationalStatusDrodownListVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.educationalStatusDrodownListVM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  companion object {
    const val TAG: String = "EDUCATIONAL_STATUS_DRODOWN_LIST_ACTIVITY"

  }
}
