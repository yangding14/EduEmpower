package com.jiahuisapplication.app.modules.instructorregister.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import com.jiahuisapplication.app.R
import com.jiahuisapplication.app.appcomponents.base.BaseActivity
import com.jiahuisapplication.app.databinding.ActivityInstructorRegisterBinding
import com.jiahuisapplication.app.modules.instructorregister.`data`.viewmodel.InstructorRegisterVM
import kotlin.Boolean
import kotlin.String
import kotlin.Unit

class InstructorRegisterActivity :
    BaseActivity<ActivityInstructorRegisterBinding>(R.layout.activity_instructor_register) {
  private val viewModel: InstructorRegisterVM by viewModels<InstructorRegisterVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.instructorRegisterVM = viewModel
    setUpSearchViewGroupFourteenListener()
  }

  override fun setUpClicks(): Unit {
  }

  private fun setUpSearchViewGroupFourteenListener(): Unit {
    binding.searchViewGroupFourteen.setOnQueryTextListener(object :
    SearchView.OnQueryTextListener {
      override fun onQueryTextSubmit(p0 : String) : Boolean {
        // Performs search when user hit
        // the search button on the keyboard
        return false
      }
      override fun onQueryTextChange(p0 : String) : Boolean {
        // Start filtering the list as user
        // start entering the characters
        return false
      }
      })
    }

    companion object {
      const val TAG: String = "INSTRUCTOR_REGISTER_ACTIVITY"


      fun getIntent(context: Context, bundle: Bundle?): Intent {
        val destIntent = Intent(context, InstructorRegisterActivity::class.java)
        destIntent.putExtra("bundle", bundle)
        return destIntent
      }
    }
  }
