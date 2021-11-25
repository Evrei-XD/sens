package com.skydoves.waterdays.ui.fragments.main

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import com.skydoves.waterdays.R
import com.skydoves.waterdays.WDApplication
import com.skydoves.waterdays.events.rx.RxUpdateMainEvent
import com.skydoves.waterdays.persistence.preference.PreferenceKeys
import com.skydoves.waterdays.persistence.preference.PreferenceManager
import com.skydoves.waterdays.persistence.sqlite.SqliteManager
import com.skydoves.waterdays.ui.activities.main.MainActivity
import com.skydoves.waterdays.ui.model.GripperSettingsActivity
import com.skydoves.waterdays.utils.DateUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.layout_parameters_simulation.*
import javax.inject.Inject

class Fragment_3 : Fragment() {

  @Inject
  lateinit var preferenceManager: PreferenceManager
  @Inject
  lateinit var sqliteManager: SqliteManager

  private var rootView: View? = null
  private var mContext: Context? = null
  private var main: MainActivity? = null

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val rootView = inflater.inflate(R.layout.layout_parameters_simulation, container, false)
    WDApplication.component.inject(this)
    this.rootView = rootView
    this.mContext = context
    if (activity != null) { main = activity as MainActivity? }
    return rootView
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initializeUI()
  }

  @SuppressLint("SetTextI18n", "CheckResult")
  private fun initializeUI() {
    next_1_btn.setOnClickListener {
      val intent = Intent(context, GripperSettingsActivity::class.java)
      startActivity(intent)
    }
    next_2_btn.setOnClickListener {
      val intent = Intent(context, GripperSettingsActivity::class.java)
      startActivity(intent)
    }
    back_1_main_btn.setOnClickListener { main?.clickNext(1) }
    back_2_main_btn.setOnClickListener { main?.clickNext(1) }
  }

  override fun onResume() {
    super.onResume()
    if (preferenceManager.getInt(PreferenceKeys.NEXT_STAGE, 0) == 1) {
      main?.clickNext(3)
    }
  }

}
