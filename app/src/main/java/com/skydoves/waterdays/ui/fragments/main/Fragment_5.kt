package com.skydoves.waterdays.ui.fragments.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.skydoves.waterdays.R
import com.skydoves.waterdays.WDApplication
import com.skydoves.waterdays.persistence.preference.PreferenceKeys
import com.skydoves.waterdays.persistence.preference.PreferenceManager
import com.skydoves.waterdays.ui.activities.main.MainActivity
import com.skydoves.waterdays.ui.model.GripperSettingsActivity
import kotlinx.android.synthetic.main.layout_chart.next_1_btn
import kotlinx.android.synthetic.main.layout_chart.next_2_btn
import kotlinx.android.synthetic.main.layout_comment.*
import javax.inject.Inject


class Fragment_5 : Fragment() {

  @Inject
  lateinit var preferenceManager: PreferenceManager

  private var rootView: View? = null
  private var mContext: Context? = null
  private var main: MainActivity? = null

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val rootView = inflater.inflate(R.layout.layout_comment, container, false)
    WDApplication.component.inject(this)
    this.rootView = rootView
    this.mContext = context
    if (activity != null) { main = activity as MainActivity? }
    return rootView
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    next_2_btn.setOnClickListener {
      //TODO компиляция csv
      System.err.println("Time now: " + main?.getCurrentTimeStamp())
      main?.createCSV()
    }
    next_1_btn.setOnClickListener {
      //TODO компиляция csv
      System.err.println("Time now: " + main?.getCurrentTimeStamp())
      main?.createCSV()
    }
    back_1_settings_btn.setOnClickListener {
      val intent = Intent(context, GripperSettingsActivity::class.java)
      startActivity(intent)
    }
    back_2_settings_btn.setOnClickListener {
      val intent = Intent(context, GripperSettingsActivity::class.java)
      startActivity(intent)
    }

    preferenceManager.putInt(PreferenceKeys.NEXT_STAGE, 0)
  }

  override fun onResume() {
    super.onResume()
    if (preferenceManager.getInt(PreferenceKeys.NEXT_STAGE, 0) == 0) {
      main?.clickNext(2)
    }
  }
}
