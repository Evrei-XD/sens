package com.skydoves.waterdays.ui.fragments.main

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.res.Configuration
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.skydoves.waterdays.R
import com.skydoves.waterdays.WDApplication
import com.skydoves.waterdays.consts.CapacityDrawable
import com.skydoves.waterdays.events.rx.RxUpdateMainEvent
import com.skydoves.waterdays.models.Drink
import com.skydoves.waterdays.persistence.preference.PreferenceKeys
import com.skydoves.waterdays.persistence.preference.PreferenceManager
import com.skydoves.waterdays.persistence.sqlite.SqliteManager
import com.skydoves.waterdays.ui.activities.main.MainActivity
import com.skydoves.waterdays.ui.adapters.DailyDrinkAdapter
import com.skydoves.waterdays.ui.viewholders.DailyDrinkViewHolder
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.layout_sens_setup.*
import javax.inject.Inject

class Fragment_2 : Fragment() {

  @Inject
  lateinit var preferenceManager: PreferenceManager
  @Inject
  lateinit var sqliteManager: SqliteManager

  private var rootView: View? = null
  private val adapter: DailyDrinkAdapter by lazy { DailyDrinkAdapter(delegate) }

  private var main: MainActivity? = null

  override fun onAttach(context: Context) {
    WDApplication.component.inject(this)
    super.onAttach(context)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    this.rootView  = inflater.inflate(R.layout.layout_sens_setup, container, false)
    if (activity != null) { main = activity as MainActivity? }
    return rootView
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    initializeUI()
  }

  @SuppressLint("CheckResult")
  private fun initializeUI() {
    dailyrecord_listview.adapter = adapter
    RxUpdateMainEvent.getInstance().observable
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe {
          adapter.clear()
        }

    next_1_btn.setOnClickListener { main?.clickNext(2) }
    next_2_btn.setOnClickListener { main?.clickNext(2) }
    back_1_btn.setOnClickListener { main?.clickNext(0) }
    back_2_btn.setOnClickListener { main?.clickNext(0) }

    preferenceManager.putInt(PreferenceKeys.NEXT_STAGE, 0)
  }

  /**
   * daily drink viewHolder delegate
   */
  private val delegate = object : DailyDrinkViewHolder.Delegate {
    @Suppress("DEPRECATION")
    override fun onClick(view: View, drink: Drink) {
      val alert = AlertDialog.Builder(context!!)
      alert.setTitle(getString(R.string.title_edit_capacity))
      val input = EditText(context)
      input.inputType = InputType.TYPE_CLASS_NUMBER
      input.setRawInputType(Configuration.KEYBOARD_12KEY)
      alert.setView(input)
      alert.setPositiveButton(getString(R.string.yes)) { _: DialogInterface, _: Int ->
        try {
          val amount = Integer.parseInt(input.text.toString())
          if (amount in 1..2999) {
            sqliteManager.updateRecordAmount(drink.index, amount)
            val drink_edited = Drink(drink.index, amount.toString() + "ml", drink.date, ContextCompat.getDrawable(context!!, CapacityDrawable.getLayout(amount))!!)
            val position = adapter.getPosition(drink)
            if (position != -1) {
              adapter.updateDrinkItem(position, drink_edited)
              RxUpdateMainEvent.getInstance().sendEvent()
              Toast.makeText(context, R.string.msg_edited_capacity, Toast.LENGTH_SHORT).show()
            }
          } else
            Toast.makeText(context, R.string.msg_invalid_input, Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
          e.printStackTrace()
        }
      }

      alert.setNegativeButton(getString(R.string.no)) { _: DialogInterface, _: Int -> }
      alert.show()
      val mgr = context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
      mgr.showSoftInputFromInputMethod(input.applicationWindowToken, InputMethodManager.SHOW_FORCED)
    }

    override fun onConfirm(drink: Drink) {
      sqliteManager.deleteRecord(drink.index)
      adapter.remove(drink)
      RxUpdateMainEvent.getInstance().sendEvent()
    }
  }
}
