package com.skydoves.waterdays.ui.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.skydoves.waterdays.R
import com.skydoves.waterdays.WDApplication
import com.skydoves.waterdays.persistence.sqlite.SqliteManager
import com.skydoves.waterdays.ui.activities.main.MainActivity
import com.skydoves.waterdays.utils.DateUtils
import kotlinx.android.synthetic.main.layout_chart.*
import kotlinx.android.synthetic.main.layout_chart.next_1_btn
import kotlinx.android.synthetic.main.layout_chart.next_2_btn
import javax.inject.Inject

class Fragment_4_3D : Fragment() {

  @Inject
  lateinit var sqliteManager: SqliteManager

  private var rootView: View? = null
  private var dateCount = 0
  private var main: MainActivity? = null

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val rootView = inflater.inflate(R.layout.layout_chart, container, false)
    WDApplication.component.inject(this)
    this.rootView = rootView
    if (activity != null) { main = activity as MainActivity? }
    return rootView
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    // set dateCount
    dateCount = -DateUtils.getDateDay(DateUtils.getFarDay(0), DateUtils.dateFormat)

    next_1_btn.setOnClickListener { main?.clickNext(4) }
    next_2_btn.setOnClickListener { main?.clickNext(4) }
    back_1_chart_btn.setOnClickListener { main?.clickNext(2) }
    back_2_chart_btn.setOnClickListener { main?.clickNext(2) }
  }
}
