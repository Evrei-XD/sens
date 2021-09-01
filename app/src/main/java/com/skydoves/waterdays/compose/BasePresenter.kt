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

package com.skydoves.waterdays.compose

import android.content.Context
import android.os.Bundle

import androidx.annotation.CallSuper

import timber.log.Timber

/**
 * Developed by skydoves on 2017-08-19.
 * Copyright (c) 2017 skydoves rights reserved.
 */

open class BasePresenter<ViewType : BaseView> {

  /**
   * get baseView
   *
   * @return baseView
   */
  lateinit var baseView: ViewType

  @CallSuper
  open fun onCreate(context: Context, savedInstanceState: Bundle?) {
    Timber.d("onCreate %s", this.toString())
  }

  @CallSuper
  fun onDestroy() {
    Timber.d("onDestroy %s", this.toString())
  }
}
