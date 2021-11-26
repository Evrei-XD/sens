package com.skydoves.waterdays.ui.model

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.ActivityManager
import android.graphics.PixelFormat
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.SeekBar
import android.widget.Toast
import com.jakewharton.rxbinding2.view.RxView
import com.skydoves.waterdays.R
import com.skydoves.waterdays.compose.BaseActivity
import com.skydoves.waterdays.compose.qualifiers.RequirePresenter
import com.skydoves.waterdays.persistence.preference.PreferenceKeys
import com.skydoves.waterdays.presenters.GripperScreenPresenter
import com.skydoves.waterdays.viewTypes.GripperScreenActivityView
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.model_tree_d.*
import kotlin.properties.Delegates


@Suppress("DEPRECATION")
@RequirePresenter(GripperScreenPresenter::class)
class GripperSettingsActivity : BaseActivity<GripperScreenPresenter, GripperScreenActivityView>(), GripperScreenActivityView{

    var selectionPartMass: ArrayList<Int> = ArrayList()

    private var renderer: GripperSettingsRenderer? = null
    companion object {
        lateinit var selectPartsNum: java.util.ArrayList<Int>
        var selectionState by Delegates.notNull<Boolean>()
    }

    @SuppressLint("NewApi", "CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.model_tree_d)
        initBaseView(this)
        window.navigationBarColor = resources.getColor(R.color.colorPrimaryDark)
        window.statusBarColor = this.resources.getColor(R.color.colorPrimaryDark, theme)
        selectionState = true
        selectPartsNum = selectionPartMass
        selectPartsNum.clear()

        RxView.clicks(findViewById(R.id.senses_one))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    System.err.println("senses_one")
                    characteristics_text_tv.text = "температура"
                    characteristics_tv.text = presenter.preferenceManager.getInt(PreferenceKeys.SENSES_ONE, 0).toString()
                    senses_one_tv.text = presenter.preferenceManager.getInt(PreferenceKeys.SENSES_ONE, 0).toString()
                    characteristics_sb.progress = presenter.preferenceManager.getInt(PreferenceKeys.SENSES_ONE, 0)
                }
        RxView.clicks(findViewById(R.id.senses_two))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    System.err.println("senses_two")
                    characteristics_text_tv.text = "давление"
                    characteristics_tv.text = presenter.preferenceManager.getInt(PreferenceKeys.SENSES_TWO, 0).toString()
                    senses_two_tv.text = presenter.preferenceManager.getInt(PreferenceKeys.SENSES_TWO, 0).toString()
                    characteristics_sb.progress = presenter.preferenceManager.getInt(PreferenceKeys.SENSES_TWO, 0)
                }
        RxView.clicks(findViewById(R.id.senses_three))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    System.err.println("senses_three")
                    characteristics_text_tv.text = "боль"
                    characteristics_tv.text = presenter.preferenceManager.getInt(PreferenceKeys.SENSES_THREE, 0).toString()
                    senses_three_tv.text = presenter.preferenceManager.getInt(PreferenceKeys.SENSES_THREE, 0).toString()
                    characteristics_sb.progress = presenter.preferenceManager.getInt(PreferenceKeys.SENSES_THREE, 0)
                }
        RxView.clicks(findViewById(R.id.motor_response))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    System.err.println("motor_response")
                    characteristics_text_tv.text = "моторный ответ"
                    characteristics_tv.text = presenter.preferenceManager.getInt(PreferenceKeys.MOTOR_RESPONSE, 0).toString()
                    motor_response_tv.text = presenter.preferenceManager.getInt(PreferenceKeys.MOTOR_RESPONSE, 0).toString()
                    characteristics_sb.progress = presenter.preferenceManager.getInt(PreferenceKeys.MOTOR_RESPONSE, 0)
                }

        RxView.clicks(findViewById(R.id.save_frame))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    //TODO сохранение кадра
                    selectionPartMass = retranslation(selectPartsNum)
                    for (i in selectionPartMass.indices) {
                        System.err.println("result: " + selectionPartMass[i])
                    }
                    presenter.preferenceManager.putInt(PreferenceKeys.SENSES_ONE, 0)
                    senses_one_tv.text = "0"
                    presenter.preferenceManager.putInt(PreferenceKeys.SENSES_TWO, 0)
                    senses_two_tv.text = "0"
                    presenter.preferenceManager.putInt(PreferenceKeys.SENSES_THREE, 0)
                    senses_three_tv.text = "0"
                    presenter.preferenceManager.putInt(PreferenceKeys.MOTOR_RESPONSE, 0)
                    motor_response_tv.text = "0"
                    characteristics_tv.text = "0"
                    characteristics_sb.progress = 0
                    selectionState = false

                    Toast.makeText(this, "кадр сохранён", Toast.LENGTH_SHORT).show()
                }

        RxView.clicks(findViewById(R.id.next_2_btn))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    presenter.preferenceManager.putInt(PreferenceKeys.NEXT_STAGE, 1)
                    finish()
                }

        RxView.clicks(findViewById(R.id.next_1_btn))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    presenter.preferenceManager.putInt(PreferenceKeys.NEXT_STAGE, 1)
                    finish()
                }

        RxView.clicks(findViewById(R.id.back_1_btn))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    presenter.preferenceManager.putInt(PreferenceKeys.NEXT_STAGE, 0)
                    finish()
                }

        RxView.clicks(findViewById(R.id.back_2_btn))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    presenter.preferenceManager.putInt(PreferenceKeys.NEXT_STAGE, 0)
                    finish()
                }

        characteristics_sb?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                characteristics_tv.text = characteristics_sb.progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (characteristics_text_tv.text.equals("температура")) {
                    presenter.preferenceManager.putInt(PreferenceKeys.SENSES_ONE, characteristics_sb.progress)
                    senses_one_tv.text = presenter.preferenceManager.getInt(PreferenceKeys.SENSES_ONE, 0).toString()
                }
                if (characteristics_text_tv.text.equals("давление")) {
                    presenter.preferenceManager.putInt(PreferenceKeys.SENSES_TWO, characteristics_sb.progress)
                    senses_two_tv.text = presenter.preferenceManager.getInt(PreferenceKeys.SENSES_TWO, 0).toString()
                }
                if (characteristics_text_tv.text.equals("боль")) {
                    presenter.preferenceManager.putInt(PreferenceKeys.SENSES_THREE, characteristics_sb.progress)
                    senses_three_tv.text = presenter.preferenceManager.getInt(PreferenceKeys.SENSES_THREE, 0).toString()
                }
                if (characteristics_text_tv.text.equals("моторный ответ")) {
                    presenter.preferenceManager.putInt(PreferenceKeys.MOTOR_RESPONSE, characteristics_sb.progress)
                    motor_response_tv.text = presenter.preferenceManager.getInt(PreferenceKeys.MOTOR_RESPONSE, 0).toString()
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

        senses_one_tv.text = presenter.preferenceManager.getInt(PreferenceKeys.SENSES_ONE, 0).toString()
        senses_two_tv.text = presenter.preferenceManager.getInt(PreferenceKeys.SENSES_TWO, 0).toString()
        senses_three_tv.text = presenter.preferenceManager.getInt(PreferenceKeys.SENSES_THREE, 0).toString()
        motor_response_tv.text = presenter.preferenceManager.getInt(PreferenceKeys.MOTOR_RESPONSE, 0).toString()
        characteristics_tv.text = presenter.preferenceManager.getInt(PreferenceKeys.SENSES_ONE, 0).toString()
        characteristics_sb.progress = presenter.preferenceManager.getInt(PreferenceKeys.SENSES_ONE, 0)
    }

    private fun retranslation(in_mass: ArrayList<Int>): ArrayList<Int>{
        val out_mass: ArrayList<Int> = ArrayList()
        for (i in in_mass ) {
            System.err.println("retranslation: $i")
            when (i) {
                1 -> out_mass.add(1)
                2 -> out_mass.add(2)
                3 -> out_mass.add(5)
                4 -> out_mass.add(4)
                5 -> out_mass.add(3)
                6 -> out_mass.add(6)
                7 -> out_mass.add(7)
                8 -> out_mass.add(8)
                9 -> out_mass.add(9)
                10 -> out_mass.add(10)
                11 -> out_mass.add(13)
                12 -> out_mass.add(12)
                13 -> out_mass.add(11)
                14 -> out_mass.add(18)
                15 -> out_mass.add(17)
                16 -> out_mass.add(14)
                17 -> out_mass.add(21)
                18 -> out_mass.add(16)
                19 -> out_mass.add(19)
                20 -> out_mass.add(22)
                21 -> out_mass.add(23)
                22 -> out_mass.add(24)
                23 -> out_mass.add(25)
                24 -> out_mass.add(26)
                25 -> out_mass.add(27)
                26 -> out_mass.add(28)
                27 -> out_mass.add(29)
                28 -> out_mass.add(30)
                29 -> out_mass.add(31)
                30 -> out_mass.add(32)
                31 -> out_mass.add(33)
                32 -> out_mass.add(34)
                33 -> out_mass.add(35)
                34 -> out_mass.add(20)
                35 -> out_mass.add(37)
                36 -> out_mass.add(38)
                37 -> out_mass.add(39)
                38 -> out_mass.add(40)
                39 -> out_mass.add(41)
                40 -> out_mass.add(42)
                41 -> out_mass.add(43)
                42 -> out_mass.add(44)
                43 -> out_mass.add(45)
                44 -> out_mass.add(46)
                45 -> out_mass.add(47)
                46 -> out_mass.add(48)
                47 -> out_mass.add(49)
                48 -> out_mass.add(50)
                49 -> out_mass.add(51)
                50 -> out_mass.add(52)
                51 -> out_mass.add(53)
                52 -> out_mass.add(54)
                53 -> out_mass.add(55)
                54 -> out_mass.add(58)
                55 -> out_mass.add(57)
                56 -> out_mass.add(36)
                57 -> out_mass.add(15)
                58 -> out_mass.add(56)
                59 -> out_mass.add(61)
                60 -> out_mass.add(62)
                61 -> out_mass.add(63)
                62 -> out_mass.add(64)
                63 -> out_mass.add(65)
                64 -> out_mass.add(66)
                65 -> out_mass.add(67)
                66 -> out_mass.add(68)
                67 -> out_mass.add(69)
                68 -> out_mass.add(70)
                69 -> out_mass.add(71)
                70 -> out_mass.add(84)
                71 -> out_mass.add(73)
                72 -> out_mass.add(74)
                73 -> out_mass.add(75)
                74 -> out_mass.add(76)
                75 -> out_mass.add(77)
                76 -> out_mass.add(78)
                77 -> out_mass.add(79)
                78 -> out_mass.add(80)
                79 -> out_mass.add(81)
                80 -> out_mass.add(82)
                81 -> out_mass.add(85)
                82 -> out_mass.add(72)
                83 -> out_mass.add(83)
                84 -> out_mass.add(86)
                85 -> out_mass.add(87)
                86 -> out_mass.add(88)
                87 -> out_mass.add(89)
                88 -> out_mass.add(90)
                89 -> out_mass.add(91)
                90 -> out_mass.add(92)
                91 -> out_mass.add(95)
                92 -> out_mass.add(94)
                93 -> out_mass.add(93)
                94 -> out_mass.add(96)
                95 -> out_mass.add(97)
                96 -> out_mass.add(98)
                97 -> out_mass.add(101)
                98 -> out_mass.add(100)
                99 -> out_mass.add(99)
                100 -> out_mass.add(102)
                101 -> out_mass.add(103)
                102 -> out_mass.add(104)
                103 -> out_mass.add(105)
                104 -> out_mass.add(106)
                105 -> out_mass.add(107)
                106 -> out_mass.add(108)
                107 -> out_mass.add(109)
                108 -> out_mass.add(110)
                109 -> out_mass.add(111)
                110 -> out_mass.add(112)
                111 -> out_mass.add(113)
                112 -> out_mass.add(114)
                113 -> out_mass.add(115)
                114 -> out_mass.add(116)
                115 -> out_mass.add(117)
                116 -> out_mass.add(118)
                117 -> out_mass.add(119)
                118 -> out_mass.add(120)
                119 -> out_mass.add(121)
                120 -> out_mass.add(122)
                121 -> out_mass.add(125)
                122 -> out_mass.add(124)
                123 -> out_mass.add(127)
                124 -> out_mass.add(126)
                125 -> out_mass.add(123)
                126 -> out_mass.add(128)
                127 -> out_mass.add(129)
                128 -> out_mass.add(130)
                129 -> out_mass.add(131)
                130 -> out_mass.add(132)
                131 -> out_mass.add(133)
                132 -> out_mass.add(134)
                133 -> out_mass.add(59)
                134 -> out_mass.add(60)
            }
        }
//        for (i in out_mass.indices) {
//            System.err.println("result: " + out_mass[i])
//        }
        return out_mass
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