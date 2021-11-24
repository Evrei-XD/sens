package com.skydoves.waterdays.ui.model

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.ActivityManager
import android.graphics.PixelFormat
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.SeekBar
import com.jakewharton.rxbinding2.view.RxView
import com.skydoves.waterdays.R
import com.skydoves.waterdays.compose.BaseActivity
import com.skydoves.waterdays.compose.qualifiers.RequirePresenter
import com.skydoves.waterdays.persistence.preference.PreferenceKeys
import com.skydoves.waterdays.persistence.preference.PreferenceManager
import com.skydoves.waterdays.presenters.GripperScreenPresenter
import com.skydoves.waterdays.viewTypes.GripperScreenActivityView
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.model_tree_d.*
import javax.inject.Inject


@RequirePresenter(GripperScreenPresenter::class)
class GripperSettingsActivity : BaseActivity<GripperScreenPresenter, GripperScreenActivityView>(), GripperScreenActivityView{


    private var renderer: GripperSettingsRenderer? = null

    @SuppressLint("NewApi", "CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.model_tree_d)
        initBaseView(this)
        window.navigationBarColor = resources.getColor(R.color.colorPrimaryDark)
        window.statusBarColor = this.resources.getColor(R.color.colorPrimaryDark, theme)

        RxView.clicks(findViewById(R.id.senses_one))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    System.err.println("senses_one")
                    characteristics_text_tv.text = "температура"
                    characteristics_tv.text = presenter.preferenceManager.getInt(PreferenceKeys.SENSES_ONE, 0).toString()
                    characteristics_sb.progress = presenter.preferenceManager.getInt(PreferenceKeys.SENSES_ONE, 0)
                }
        RxView.clicks(findViewById(R.id.senses_two))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    System.err.println("senses_two")
                    characteristics_text_tv.text = "давление"
                    characteristics_tv.text = presenter.preferenceManager.getInt(PreferenceKeys.SENSES_TWO, 0).toString()
                    characteristics_sb.progress = presenter.preferenceManager.getInt(PreferenceKeys.SENSES_TWO, 0)
                }
        RxView.clicks(findViewById(R.id.senses_three))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    System.err.println("senses_three")
                    characteristics_text_tv.text = "боль"
                    characteristics_tv.text = presenter.preferenceManager.getInt(PreferenceKeys.SENSES_THREE, 0).toString()
                    characteristics_sb.progress = presenter.preferenceManager.getInt(PreferenceKeys.SENSES_THREE, 0)
                }

        characteristics_sb?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                characteristics_tv.text = characteristics_sb.progress.toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (characteristics_text_tv.text.equals("температура")){
                    presenter.preferenceManager.putInt( PreferenceKeys.SENSES_ONE, characteristics_sb.progress)
                }
                if (characteristics_text_tv.text.equals("давление")){
                    presenter.preferenceManager.putInt( PreferenceKeys.SENSES_TWO, characteristics_sb.progress)
                }
                if (characteristics_text_tv.text.equals("боль")){
                    presenter.preferenceManager.putInt( PreferenceKeys.SENSES_THREE, characteristics_sb.progress)
                }

            }
        })
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