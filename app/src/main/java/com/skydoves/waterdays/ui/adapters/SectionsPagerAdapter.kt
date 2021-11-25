package com.skydoves.waterdays.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.skydoves.waterdays.ui.fragments.main.Fragment_1
import com.skydoves.waterdays.ui.fragments.main.Fragment_2
import com.skydoves.waterdays.ui.fragments.main.Fragment_5
import com.skydoves.waterdays.ui.fragments.main.Fragment_3


class SectionsPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

  override fun getItem(position: Int): Fragment {
    var fragment: Fragment = Fragment_3()
    when (position) {
      0 -> fragment = Fragment_1()
      1 -> fragment = Fragment_2()
      2 -> fragment = Fragment_3()
      3 -> fragment = Fragment_5()//Fragment_4_3D()
//      4 -> fragment = Fragment_5()
    }
    return fragment
  }

  override fun getCount(): Int = COUNT_PAGERS

  companion object {
    const val COUNT_PAGERS = 5
  }
}
