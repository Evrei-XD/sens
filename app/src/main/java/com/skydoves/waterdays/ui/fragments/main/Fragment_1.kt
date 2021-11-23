package com.skydoves.waterdays.ui.fragments.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.skydoves.waterdays.R
import com.skydoves.waterdays.WDApplication
import com.skydoves.waterdays.persistence.sqlite.SqliteManager
import com.skydoves.waterdays.ui.activities.main.MainActivity
import com.skydoves.waterdays.ui.model.GripperSettingsActivity
import com.skydoves.waterdays.utils.AlarmUtils
import kotlinx.android.synthetic.main.layout_sens_select.*
import javax.inject.Inject


class Fragment_1 : Fragment() {

  @Inject
  lateinit var sqliteManager: SqliteManager
  @Inject
  lateinit var alarmUtils: AlarmUtils

  private var rootView: View? = null
  private var main: MainActivity? = null

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val rootView = inflater.inflate(R.layout.layout_sens_select, container, false)
    WDApplication.component.inject(this)
    this.rootView = rootView
    if (activity != null) { main = activity as MainActivity? }
    return rootView
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    InitializeUI()
  }

  private fun InitializeUI() {
    val intent = Intent(context, GripperSettingsActivity::class.java)
    startActivity(intent)
    next_2_btn.setOnClickListener { main?.clickNext(1) }
    next_1_btn.setOnClickListener { main?.clickNext(1) }

  }
}
