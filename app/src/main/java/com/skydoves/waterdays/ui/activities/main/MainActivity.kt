package com.skydoves.waterdays.ui.activities.main

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import com.skydoves.waterdays.R
import com.skydoves.waterdays.common.ConstantManager.MAX_NUMBER_DETAILS
import com.skydoves.waterdays.compose.BaseActivity
import com.skydoves.waterdays.compose.qualifiers.RequirePresenter
import com.skydoves.waterdays.consts.IntentExtras
import com.skydoves.waterdays.events.rx.RxUpdateMainEvent
import com.skydoves.waterdays.persistence.preference.PreferenceKeys
import com.skydoves.waterdays.presenters.Load3DModelNew
import com.skydoves.waterdays.presenters.MainPresenter
import com.skydoves.waterdays.services.receivers.AlarmBootReceiver
import com.skydoves.waterdays.services.receivers.LocalWeatherReceiver
import com.skydoves.waterdays.ui.adapters.SectionsPagerAdapter
import com.skydoves.waterdays.utils.NavigationUtils
import com.skydoves.waterdays.viewTypes.MainActivityView
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*
import net.ozaydin.serkan.easy_csv.EasyCsv
import net.ozaydin.serkan.easy_csv.FileCallback
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

@Suppress("DEPRECATION")
@RequirePresenter(MainPresenter::class)
class MainActivity : BaseActivity<MainPresenter, MainActivityView>(), MainActivityView {

  private lateinit var mSectionsPagerAdapter: SectionsPagerAdapter
  private var mLoad3DModelNew = Load3DModelNew(this)
  private var threadFunction = arrayOfNulls<Thread>(MAX_NUMBER_DETAILS + MAX_NUMBER_DETAILS)
  var timeNow = ""
  private var easyCsv = EasyCsv(this)
  private var dataList: MutableList<String> = ArrayList()


  @SuppressLint("CheckResult", "NewApi")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    initBaseView(this)
    window.navigationBarColor = resources.getColor(R.color.colorPrimary)

    // auto weather alarm
    weatherAlarm()

    // set boot receiver
    val receiver = ComponentName(this, AlarmBootReceiver::class.java)
    val pm = packageManager
    pm.setComponentEnabledSetting(receiver, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP)

    RxUpdateMainEvent.getInstance().observable
        .compose(bindToLifecycle<Boolean>())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { flag ->
          if (!flag) showBadge(1)
          mSectionsPagerAdapter.notifyDataSetChanged()
        }

    easyCsv =  EasyCsv(this@MainActivity)

    RxUpdateMainEvent.getInstance().listToCSVObservable
            .compose(bindToLifecycle())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { parameters ->
              System.err.println("=========================")
              dataList.add(parameters.listToCSV)
              for (i in dataList.indices) {
                System.err.println("dataList: " + dataList[i])
              }
            }
//    createCSV()
  }

  override fun initializeUI() {
    mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
    mainactivity_viewpager.adapter = mSectionsPagerAdapter
    mainactivity_viewpager.offscreenPageLimit = 4
    NavigationUtils.setComponents(baseContext, mainactivity_viewpager, mainactivity_navi)
    init3D()
  }

  private fun init3D() {
    Load3DModelNew.model[0]   = mLoad3DModelNew.readData("HAND_new/HAND_new_1_formatting.obj")
    Load3DModelNew.model[1]   = mLoad3DModelNew.readData("HAND_new/HAND_new_2_formatting.obj")
    Load3DModelNew.model[2]   = mLoad3DModelNew.readData("HAND_new/HAND_new_5_formatting.obj")
    Load3DModelNew.model[3]   = mLoad3DModelNew.readData("HAND_new/HAND_new_4_formatting.obj")
    Load3DModelNew.model[4]   = mLoad3DModelNew.readData("HAND_new/HAND_new_3_formatting.obj")
    Load3DModelNew.model[5]   = mLoad3DModelNew.readData("HAND_new/HAND_new_6_formatting.obj")
    Load3DModelNew.model[6]   = mLoad3DModelNew.readData("HAND_new/HAND_new_7_formatting.obj")
    Load3DModelNew.model[7]   = mLoad3DModelNew.readData("HAND_new/HAND_new_8_formatting.obj")
    Load3DModelNew.model[8]   = mLoad3DModelNew.readData("HAND_new/HAND_new_9_formatting.obj")
    Load3DModelNew.model[9]   = mLoad3DModelNew.readData("HAND_new/HAND_new_10_formatting.obj")
    Load3DModelNew.model[10]  = mLoad3DModelNew.readData("HAND_new/HAND_new_13_formatting.obj")
    Load3DModelNew.model[11]  = mLoad3DModelNew.readData("HAND_new/HAND_new_12_formatting.obj")
    Load3DModelNew.model[12]  = mLoad3DModelNew.readData("HAND_new/HAND_new_11_formatting.obj")
    Load3DModelNew.model[13]  = mLoad3DModelNew.readData("HAND_new/HAND_new_18_formatting.obj")
    Load3DModelNew.model[14]  = mLoad3DModelNew.readData("HAND_new/HAND_new_17_formatting.obj")
    Load3DModelNew.model[15]  = mLoad3DModelNew.readData("HAND_new/HAND_new_14_formatting.obj")
    Load3DModelNew.model[16]  = mLoad3DModelNew.readData("HAND_new/HAND_new_21_formatting.obj")
    Load3DModelNew.model[17]  = mLoad3DModelNew.readData("HAND_new/HAND_new_16_formatting.obj")
    Load3DModelNew.model[18]  = mLoad3DModelNew.readData("HAND_new/HAND_new_19_formatting.obj")
    Load3DModelNew.model[19]  = mLoad3DModelNew.readData("HAND_new/HAND_new_22_formatting.obj")
    Load3DModelNew.model[20]  = mLoad3DModelNew.readData("HAND_new/HAND_new_23_formatting.obj")
    Load3DModelNew.model[21]  = mLoad3DModelNew.readData("HAND_new/HAND_new_24_formatting.obj")
    Load3DModelNew.model[22]  = mLoad3DModelNew.readData("HAND_new/HAND_new_25_formatting.obj")
    Load3DModelNew.model[23]  = mLoad3DModelNew.readData("HAND_new/HAND_new_26_formatting.obj")
    Load3DModelNew.model[24]  = mLoad3DModelNew.readData("HAND_new/HAND_new_27_formatting.obj")
    Load3DModelNew.model[25]  = mLoad3DModelNew.readData("HAND_new/HAND_new_28_formatting.obj")
    Load3DModelNew.model[26]  = mLoad3DModelNew.readData("HAND_new/HAND_new_29_formatting.obj")
    Load3DModelNew.model[27]  = mLoad3DModelNew.readData("HAND_new/HAND_new_30_formatting.obj")
    Load3DModelNew.model[28]  = mLoad3DModelNew.readData("HAND_new/HAND_new_31_formatting.obj")
    Load3DModelNew.model[29]  = mLoad3DModelNew.readData("HAND_new/HAND_new_32_formatting.obj")
    Load3DModelNew.model[30]  = mLoad3DModelNew.readData("HAND_new/HAND_new_33_formatting.obj")
    Load3DModelNew.model[31]  = mLoad3DModelNew.readData("HAND_new/HAND_new_34_formatting.obj")
    Load3DModelNew.model[32]  = mLoad3DModelNew.readData("HAND_new/HAND_new_35_formatting.obj")
    Load3DModelNew.model[33]  = mLoad3DModelNew.readData("HAND_new/HAND_new_20_formatting.obj")
    Load3DModelNew.model[34]  = mLoad3DModelNew.readData("HAND_new/HAND_new_37_formatting.obj")
    Load3DModelNew.model[35]  = mLoad3DModelNew.readData("HAND_new/HAND_new_38_formatting.obj")
    Load3DModelNew.model[36]  = mLoad3DModelNew.readData("HAND_new/HAND_new_39_formatting.obj")
    Load3DModelNew.model[37]  = mLoad3DModelNew.readData("HAND_new/HAND_new_40_formatting.obj")
    Load3DModelNew.model[38]  = mLoad3DModelNew.readData("HAND_new/HAND_new_41_formatting.obj")
    Load3DModelNew.model[39]  = mLoad3DModelNew.readData("HAND_new/HAND_new_42_formatting.obj")
    Load3DModelNew.model[40]  = mLoad3DModelNew.readData("HAND_new/HAND_new_43_formatting.obj")
    Load3DModelNew.model[41]  = mLoad3DModelNew.readData("HAND_new/HAND_new_44_formatting.obj")
    Load3DModelNew.model[42]  = mLoad3DModelNew.readData("HAND_new/HAND_new_45_formatting.obj")
    Load3DModelNew.model[43]  = mLoad3DModelNew.readData("HAND_new/HAND_new_46_formatting.obj")
    Load3DModelNew.model[44]  = mLoad3DModelNew.readData("HAND_new/HAND_new_47_formatting.obj")
    Load3DModelNew.model[45]  = mLoad3DModelNew.readData("HAND_new/HAND_new_48_formatting.obj")
    Load3DModelNew.model[46]  = mLoad3DModelNew.readData("HAND_new/HAND_new_49_formatting.obj")
    Load3DModelNew.model[47]  = mLoad3DModelNew.readData("HAND_new/HAND_new_50_formatting.obj")
    Load3DModelNew.model[48]  = mLoad3DModelNew.readData("HAND_new/HAND_new_51_formatting.obj")
    Load3DModelNew.model[49]  = mLoad3DModelNew.readData("HAND_new/HAND_new_52_formatting.obj")
    Load3DModelNew.model[50]  = mLoad3DModelNew.readData("HAND_new/HAND_new_53_formatting.obj")
    Load3DModelNew.model[51]  = mLoad3DModelNew.readData("HAND_new/HAND_new_54_formatting.obj")
    Load3DModelNew.model[52]  = mLoad3DModelNew.readData("HAND_new/HAND_new_55_formatting.obj")
    Load3DModelNew.model[53]  = mLoad3DModelNew.readData("HAND_new/HAND_new_58_formatting.obj")
    Load3DModelNew.model[54]  = mLoad3DModelNew.readData("HAND_new/HAND_new_57_formatting.obj")
    Load3DModelNew.model[55]  = mLoad3DModelNew.readData("HAND_new/HAND_new_36_formatting.obj")
    Load3DModelNew.model[56]  = mLoad3DModelNew.readData("HAND_new/HAND_new_15_formatting.obj")
    Load3DModelNew.model[57]  = mLoad3DModelNew.readData("HAND_new/HAND_new_56_formatting.obj")
    Load3DModelNew.model[58]  = mLoad3DModelNew.readData("HAND_new/HAND_new_61_formatting.obj")
    Load3DModelNew.model[59]  = mLoad3DModelNew.readData("HAND_new/HAND_new_62_formatting.obj")
    Load3DModelNew.model[60]  = mLoad3DModelNew.readData("HAND_new/HAND_new_63_formatting.obj")
    Load3DModelNew.model[61]  = mLoad3DModelNew.readData("HAND_new/HAND_new_64_formatting.obj")
    Load3DModelNew.model[62]  = mLoad3DModelNew.readData("HAND_new/HAND_new_65_formatting.obj")
    Load3DModelNew.model[63]  = mLoad3DModelNew.readData("HAND_new/HAND_new_66_formatting.obj")
    Load3DModelNew.model[64]  = mLoad3DModelNew.readData("HAND_new/HAND_new_67_formatting.obj")
    Load3DModelNew.model[65]  = mLoad3DModelNew.readData("HAND_new/HAND_new_68_formatting.obj")
    Load3DModelNew.model[66]  = mLoad3DModelNew.readData("HAND_new/HAND_new_69_formatting.obj")
    Load3DModelNew.model[67]  = mLoad3DModelNew.readData("HAND_new/HAND_new_70_formatting.obj")
    Load3DModelNew.model[68]  = mLoad3DModelNew.readData("HAND_new/HAND_new_71_formatting.obj")
    Load3DModelNew.model[69]  = mLoad3DModelNew.readData("HAND_new/HAND_new_84_formatting.obj")
    Load3DModelNew.model[70]  = mLoad3DModelNew.readData("HAND_new/HAND_new_73_formatting.obj")
    Load3DModelNew.model[71]  = mLoad3DModelNew.readData("HAND_new/HAND_new_74_formatting.obj")
    Load3DModelNew.model[72]  = mLoad3DModelNew.readData("HAND_new/HAND_new_75_formatting.obj")
    Load3DModelNew.model[73]  = mLoad3DModelNew.readData("HAND_new/HAND_new_76_formatting.obj")
    Load3DModelNew.model[74]  = mLoad3DModelNew.readData("HAND_new/HAND_new_77_formatting.obj")
    Load3DModelNew.model[75]  = mLoad3DModelNew.readData("HAND_new/HAND_new_78_formatting.obj")
    Load3DModelNew.model[76]  = mLoad3DModelNew.readData("HAND_new/HAND_new_79_formatting.obj")
    Load3DModelNew.model[77]  = mLoad3DModelNew.readData("HAND_new/HAND_new_80_formatting.obj")
    Load3DModelNew.model[78]  = mLoad3DModelNew.readData("HAND_new/HAND_new_81_formatting.obj")
    Load3DModelNew.model[79]  = mLoad3DModelNew.readData("HAND_new/HAND_new_82_formatting.obj")
    Load3DModelNew.model[80]  = mLoad3DModelNew.readData("HAND_new/HAND_new_85_formatting.obj")
    Load3DModelNew.model[81]  = mLoad3DModelNew.readData("HAND_new/HAND_new_72_formatting.obj")
    Load3DModelNew.model[82]  = mLoad3DModelNew.readData("HAND_new/HAND_new_83_formatting.obj")
    Load3DModelNew.model[83]  = mLoad3DModelNew.readData("HAND_new/HAND_new_86_formatting.obj")
    Load3DModelNew.model[84]  = mLoad3DModelNew.readData("HAND_new/HAND_new_87_formatting.obj")
    Load3DModelNew.model[85]  = mLoad3DModelNew.readData("HAND_new/HAND_new_88_formatting.obj")
    Load3DModelNew.model[86]  = mLoad3DModelNew.readData("HAND_new/HAND_new_89_formatting.obj")
    Load3DModelNew.model[87]  = mLoad3DModelNew.readData("HAND_new/HAND_new_90_formatting.obj")
    Load3DModelNew.model[88]  = mLoad3DModelNew.readData("HAND_new/HAND_new_91_formatting.obj")
    Load3DModelNew.model[89]  = mLoad3DModelNew.readData("HAND_new/HAND_new_92_formatting.obj")
    Load3DModelNew.model[90]  = mLoad3DModelNew.readData("HAND_new/HAND_new_95_formatting.obj")
    Load3DModelNew.model[91]  = mLoad3DModelNew.readData("HAND_new/HAND_new_94_formatting.obj")
    Load3DModelNew.model[92]  = mLoad3DModelNew.readData("HAND_new/HAND_new_93_formatting.obj")
    Load3DModelNew.model[93]  = mLoad3DModelNew.readData("HAND_new/HAND_new_96_formatting.obj")
    Load3DModelNew.model[94]  = mLoad3DModelNew.readData("HAND_new/HAND_new_97_formatting.obj")
    Load3DModelNew.model[95]  = mLoad3DModelNew.readData("HAND_new/HAND_new_98_formatting.obj")
    Load3DModelNew.model[96]  = mLoad3DModelNew.readData("HAND_new/HAND_new_101_formatting.obj")
    Load3DModelNew.model[97]  = mLoad3DModelNew.readData("HAND_new/HAND_new_100_formatting.obj")
    Load3DModelNew.model[98]  = mLoad3DModelNew.readData("HAND_new/HAND_new_99_formatting.obj")
    Load3DModelNew.model[99]  = mLoad3DModelNew.readData("HAND_new/HAND_new_102_formatting.obj")
    Load3DModelNew.model[100] = mLoad3DModelNew.readData("HAND_new/HAND_new_103_formatting.obj")
    Load3DModelNew.model[101] = mLoad3DModelNew.readData("HAND_new/HAND_new_104_formatting.obj")
    Load3DModelNew.model[102] = mLoad3DModelNew.readData("HAND_new/HAND_new_105_formatting.obj")
    Load3DModelNew.model[103] = mLoad3DModelNew.readData("HAND_new/HAND_new_106_formatting.obj")
    Load3DModelNew.model[104] = mLoad3DModelNew.readData("HAND_new/HAND_new_107_formatting.obj")
    Load3DModelNew.model[105] = mLoad3DModelNew.readData("HAND_new/HAND_new_108_formatting.obj")
    Load3DModelNew.model[106] = mLoad3DModelNew.readData("HAND_new/HAND_new_109_formatting.obj")
    Load3DModelNew.model[107] = mLoad3DModelNew.readData("HAND_new/HAND_new_110_formatting.obj")
    Load3DModelNew.model[108] = mLoad3DModelNew.readData("HAND_new/HAND_new_111_formatting.obj")
    Load3DModelNew.model[109] = mLoad3DModelNew.readData("HAND_new/HAND_new_112_formatting.obj")
    Load3DModelNew.model[110] = mLoad3DModelNew.readData("HAND_new/HAND_new_113_formatting.obj")
    Load3DModelNew.model[111] = mLoad3DModelNew.readData("HAND_new/HAND_new_114_formatting.obj")
    Load3DModelNew.model[112] = mLoad3DModelNew.readData("HAND_new/HAND_new_115_formatting.obj")
    Load3DModelNew.model[113] = mLoad3DModelNew.readData("HAND_new/HAND_new_116_formatting.obj")
    Load3DModelNew.model[114] = mLoad3DModelNew.readData("HAND_new/HAND_new_117_formatting.obj")
    Load3DModelNew.model[115] = mLoad3DModelNew.readData("HAND_new/HAND_new_118_formatting.obj")
    Load3DModelNew.model[116] = mLoad3DModelNew.readData("HAND_new/HAND_new_119_formatting.obj")
    Load3DModelNew.model[117] = mLoad3DModelNew.readData("HAND_new/HAND_new_120_formatting.obj")
    Load3DModelNew.model[118] = mLoad3DModelNew.readData("HAND_new/HAND_new_121_formatting.obj")
    Load3DModelNew.model[119] = mLoad3DModelNew.readData("HAND_new/HAND_new_122_formatting.obj")
    Load3DModelNew.model[120] = mLoad3DModelNew.readData("HAND_new/HAND_new_125_formatting.obj")
    Load3DModelNew.model[121] = mLoad3DModelNew.readData("HAND_new/HAND_new_124_formatting.obj")
    Load3DModelNew.model[122] = mLoad3DModelNew.readData("HAND_new/HAND_new_127_formatting.obj")
    Load3DModelNew.model[123] = mLoad3DModelNew.readData("HAND_new/HAND_new_126_formatting.obj")
    Load3DModelNew.model[124] = mLoad3DModelNew.readData("HAND_new/HAND_new_123_formatting.obj")
    Load3DModelNew.model[125] = mLoad3DModelNew.readData("HAND_new/HAND_new_128_formatting.obj")
    Load3DModelNew.model[126] = mLoad3DModelNew.readData("HAND_new/HAND_new_129_formatting.obj")
    Load3DModelNew.model[127] = mLoad3DModelNew.readData("HAND_new/HAND_new_130_formatting.obj")
    Load3DModelNew.model[128] = mLoad3DModelNew.readData("HAND_new/HAND_new_131_formatting.obj")
    Load3DModelNew.model[129] = mLoad3DModelNew.readData("HAND_new/HAND_new_132_formatting.obj")
    Load3DModelNew.model[130] = mLoad3DModelNew.readData("HAND_new/HAND_new_133_formatting.obj")
    Load3DModelNew.model[131] = mLoad3DModelNew.readData("HAND_new/HAND_new_134_formatting.obj")
    Load3DModelNew.model[132] = mLoad3DModelNew.readData("HAND_new/HAND_new_59_formatting.obj")
    Load3DModelNew.model[133] = mLoad3DModelNew.readData("HAND_new/HAND_new_60_formatting.obj")

    for (j in 0 until MAX_NUMBER_DETAILS) {
      //MAX_NUMBER_DETAILS
      val finalJ: Int = j
      threadFunction[j] = Thread { mLoad3DModelNew.loadSTR2(finalJ) }
      threadFunction[j]?.start()
    }
  }

  /**
   * show badge with delay
   * @param position
   */
  private fun showBadge(position: Int) {
    mainactivity_navi.postDelayed({
      val model = mainactivity_navi.models[position]
      mainactivity_navi.postDelayed({ model.showBadge() }, 100)
    }, 200)
  }

  override fun onResume() {
    super.onResume()

    val i = Intent(this, javaClass)
    i.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
    PendingIntent.getActivity(this, 0, i, 0)
  }
  override fun onNewIntent(intent: Intent) {
    super.onNewIntent(intent)
    setIntent(intent)
  }
  private fun weatherAlarm() {
    if (!presenter.weatherAlarm) {
      val mCalendar = GregorianCalendar()
      val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
      alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, mCalendar.timeInMillis, (1200 * 1000).toLong(), pendingIntent(IntentExtras.ALARM_PENDING_REQUEST_CODE))
      presenter.weatherAlarm = true
    }
  }
  private fun pendingIntent(requestCode: Int): PendingIntent {
    val intent = Intent(this, LocalWeatherReceiver::class.java)
    intent.putExtra(IntentExtras.ALARM_PENDING_REQUEST, requestCode)
    return PendingIntent.getBroadcast(this, requestCode, intent, 0)
  }

  fun clickNext(position: Int) {
    NavigationUtils.setNextView(baseContext, mainactivity_viewpager, mainactivity_navi, position)
  }

  fun createCSV() {
    val headerList: MutableList<String> = ArrayList()
    headerList.add("number frame.selected fragments hand.feeling temperature.feeling pressure.feeling pain.motor impact.doctor comments-")

    easyCsv.setSeparatorColumn(".")
    easyCsv.setSeperatorLine("-")

    val fileName = "sens_test__"+getCurrentTimeStamp()

    easyCsv.createCsvFile(fileName, headerList, dataList, PreferenceKeys.WRITE_PERMISSON_REQUEST_CODE, object : FileCallback {
      override fun onSuccess(file: File) {
        Toast.makeText(this@MainActivity, "Saved file", Toast.LENGTH_SHORT).show()
      }

      override fun onFail(err: String) {
        Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
      }
    })
  }

  @SuppressLint("SimpleDateFormat")
  fun getCurrentTimeStamp(): String? {
    val sdfDate = SimpleDateFormat("HH:mm:ss") //dd/MM/yyyy
    val now = Date()
    return sdfDate.format(now)
  }
}
