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

package com.skydoves.waterdays

import com.skydoves.waterdays.presenters.*
import com.skydoves.waterdays.ui.activities.intro.StartActivity
import com.skydoves.waterdays.ui.activities.settings.SetBubbleColorActivity
import com.skydoves.waterdays.ui.fragments.main.Fragment_1
import com.skydoves.waterdays.ui.fragments.main.Fragment_4_3D
import com.skydoves.waterdays.ui.fragments.main.Fragment_2
import com.skydoves.waterdays.ui.fragments.main.Fragment_3
import com.skydoves.waterdays.ui.model.GripperSettingsActivity
import com.skydoves.waterdays.utils.AlarmUtils

interface ApplicationGraph {
  fun inject(target_: StartActivity)

  fun inject(target_: SetGoalPresenter)

  fun inject(target_: SetLocalPresenter)

  fun inject(target_: AlarmScreenPresenter)

  fun inject(target_: MakeAlarmPresenter)

  fun inject(target_: MainPresenter)

  fun inject(target_: Fragment_1)

  fun inject(target_: AlarmUtils)

  fun inject(target_: Fragment_4_3D)

  fun inject(target_: Fragment_3)

  fun inject(target_: Fragment_2)

  fun inject(target_: SelectDrinkPresenter)

  fun inject(target_: SetBubbleColorActivity)

  fun inject(target_: GripperScreenPresenter)
}
