/*
 * Copyright (C) 2016 skydoves
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.skydoves.waterdays.ui.activities.settings

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.jakewharton.rxbinding2.view.RxView
import com.skydoves.waterdays.R
import com.skydoves.waterdays.compose.BaseActivity
import com.skydoves.waterdays.compose.qualifiers.RequirePresenter
import com.skydoves.waterdays.consts.LocalNames
import com.skydoves.waterdays.presenters.SetLocalPresenter
import com.skydoves.waterdays.viewTypes.SetLocalActivityView
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_set_local.*

/**
 * Created by skydoves on 2016-10-15.
 * Updated by skydoves on 2017-08-17.
 * Copyright (c) 2017 skydoves rights reserved.
 */

@RequirePresenter(SetLocalPresenter::class)
class SetLocalActivity
  : BaseActivity<SetLocalPresenter, SetLocalActivityView>(), SetLocalActivityView
{

  private var index = -1

  @SuppressLint("CheckResult")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_set_local)
    initBaseView(this)

    RxView.clicks(findViewById(R.id.setlocal_btn_setlocal))
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { e -> setLocal() }

    RxView.clicks(findViewById(R.id.setlocal_btn_changelocal))
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { e -> selectDialogOption() }
  }

  override fun initializeUI() {
    setlocal_tv_location.text = LocalNames.getLocalName(presenter.localIndex)
  }

  private fun setLocal() {
    if (this.index != -1) {
      presenter.localIndex = this.index
      Toast.makeText(this, "????????? ?????????????????????.", Toast.LENGTH_SHORT).show()
      finish()
    } else
      Toast.makeText(this, "????????? ???????????? ???????????????.", Toast.LENGTH_SHORT).show()
  }

  private fun selectDialogOption() {
    val items = arrayOf("???????????????", "?????????", "?????????", "????????????", "????????????", "???????????????", "???????????????", "???????????????", "???????????????", "???????????????", "???????????????", "????????????", "????????????", "????????????", "????????????", "?????????????????????")
    val lastIndex = this.index
    this.index = 0
    val ab = AlertDialog.Builder(this)
    ab.setSingleChoiceItems(items, 0
    ) { dialog: DialogInterface, whichButton: Int -> this.index = whichButton }.setPositiveButton("????????????"
    ) { dialog: DialogInterface, whichButton: Int -> setlocal_tv_location.setText(LocalNames.getLocalName(this.index)) }.setNegativeButton("??????"
    ) { dialog: DialogInterface, whichButton: Int -> this.index = lastIndex }.show()
  }
}
