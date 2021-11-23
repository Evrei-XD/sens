package com.skydoves.waterdays.ui.model

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.ActivityManager
import android.graphics.PixelFormat
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import com.skydoves.waterdays.R
import com.skydoves.waterdays.compose.BaseActivity
import com.skydoves.waterdays.compose.qualifiers.RequirePresenter
import com.skydoves.waterdays.presenters.GripperScreenPresenter
import com.skydoves.waterdays.viewTypes.GripperScreenActivityView
import kotlinx.android.synthetic.main.layout_gripper_settings_le_without_encoders.*


@RequirePresenter(GripperScreenPresenter::class)
class GripperSettingsActivity : BaseActivity<GripperScreenPresenter, GripperScreenActivityView>(), GripperScreenActivityView{

    private var renderer: GripperSettingsRenderer? = null

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_gripper_settings_le_without_encoders)
        initBaseView(this)
        window.navigationBarColor = resources.getColor(R.color.colorPrimaryDark)
        window.statusBarColor = this.resources.getColor(R.color.colorPrimaryDark, theme)
    }

    override fun initializeUI() {
        val activityManager = getSystemService(ACTIVITY_SERVICE) as ActivityManager
        val configurationInfo = activityManager.deviceConfigurationInfo
        val supportsEs2 = configurationInfo.reqGlEsVersion >= 0x20000

        if (supportsEs2) {
            gl_surface_view.setEGLConfigChooser(8, 8, 8, 8, 16, 0)
            gl_surface_view.holder?.setFormat(PixelFormat.TRANSLUCENT)
            gl_surface_view.setBackgroundResource(R.drawable.gradient_background)
            gl_surface_view.setZOrderOnTop(true)

            gl_surface_view.setEGLContextClientVersion(2)

            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(displayMetrics)
            renderer = GripperSettingsRenderer(this, gl_surface_view)
            gl_surface_view.setRenderer(renderer, displayMetrics.density)
        }
    }

    override fun onResume() {
        super.onResume()
        gl_surface_view.onResume()
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    override fun onPause() {
        super.onPause()
        gl_surface_view.preserveEGLContextOnPause = true
        gl_surface_view.onPause()
    }


}