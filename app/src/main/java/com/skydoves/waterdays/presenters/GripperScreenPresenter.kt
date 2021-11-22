package com.skydoves.waterdays.presenters

import android.content.Context
import android.os.Bundle
import com.skydoves.waterdays.WDApplication
import com.skydoves.waterdays.compose.BasePresenter
import com.skydoves.waterdays.persistence.preference.PreferenceManager
import com.skydoves.waterdays.persistence.sqlite.SqliteManager
import com.skydoves.waterdays.viewTypes.GripperScreenActivityView
import javax.inject.Inject

class GripperScreenPresenter : BasePresenter<GripperScreenActivityView>()  {

    @Inject
    lateinit var sqliteManager: SqliteManager
    @Inject
    lateinit var preferenceManager: PreferenceManager

    override fun onCreate(context: Context, savedInstanceState: Bundle?) {
        super.onCreate(context, savedInstanceState)
        WDApplication.component.inject(this)
    }

}