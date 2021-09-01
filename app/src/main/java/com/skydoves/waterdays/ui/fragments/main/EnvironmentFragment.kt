package com.skydoves.waterdays.ui.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.skydoves.waterdays.R
import com.skydoves.waterdays.ui.activities.main.MainActivity
import kotlinx.android.synthetic.main.layout_settings.next_1_btn
import kotlinx.android.synthetic.main.layout_settings.next_2_btn


class EnvironmentFragment : Fragment() {
  private var main: MainActivity? = null

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val rootView = inflater.inflate(R.layout.layout_settings, container, false)
    if (activity != null) { main = activity as MainActivity? }
    return rootView
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    next_2_btn.setOnClickListener {
      //TODO компиляция пдфки
    }
    next_1_btn.setOnClickListener {
      //TODO компиляция пдфки
    }
  }

}
