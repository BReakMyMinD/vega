package com.example.vegaschedule

import android.annotation.SuppressLint
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.fragment_schedule.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        bottom_menu.setOnItemSelectedListener { id ->
            when(id) {
                R.id.schedule -> {
                    mainPager.currentItem = 0

                }
                R.id.teacher -> {
                    mainPager.currentItem = 1

                }
                R.id.auditorium -> {
                    mainPager.currentItem = 2

                }
                R.id.settings -> {
                    mainPager.currentItem = 3

                }

            }
            currentDock = mainPager.currentItem
        }
        mainPager.setOnTouchListener(View.OnTouchListener { v, event -> true })
        mainPager.adapter = mainPagerAdapter(supportFragmentManager, this).apply{
            list = ArrayList<String>().apply {
                add("Расписание")
                add("Преподаватель")
                add("Аудитория")
                add("Настройки")
            }
        }





        /*val pager: ViewPager = findViewById(R.id.viewPager)
        val tabLayout: TabLayout = findViewById(R.id.tabLayout)


        pager.adapter = DemoCollectionPagerAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(pager)*/
    }
    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
            .replace(R.id.content, fragment, fragment.javaClass.simpleName)
            .commit()
    }
    private val fileProvider : FileProvider = FileProvider("temp_schedule.json", this)
    val scheduleInstance : ScheduleContainer = ScheduleContainer(fileProvider)
    val settingsStorage : SettingsStorage = SettingsStorage(this, scheduleInstance.getGroups().first())

    @RequiresApi(Build.VERSION_CODES.N)
    fun getCurrentDay() : String{
        val calendar: Calendar = Calendar.getInstance()
        return when (calendar.get(Calendar.DAY_OF_WEEK)) {
            Calendar.MONDAY -> "ПН"
            Calendar.TUESDAY -> "ВТ"
            Calendar.WEDNESDAY -> "СР"
            Calendar.THURSDAY -> "ЧТ"
            Calendar.FRIDAY -> "ПТ"
            Calendar.SATURDAY -> "СБ"
            Calendar.SUNDAY -> "ПН"

            else -> "ERR"
        }
    }

    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.N)
    fun getCurrentWeek() : Int{
        val calendar = Calendar.getInstance()
        val firstWeek = scheduleInstance.getFirstWeek() as String
        val firstWeekDate = java.text.SimpleDateFormat("dd.MM.yyyy").parse(firstWeek)
        var weeks = calendar.fieldDifference(firstWeekDate, Calendar.WEEK_OF_YEAR)
        if(weeks < 0) {
            weeks = -weeks
            weeks += 1
        }
        else {
            weeks = 0
        }
        return weeks
    }
    var currentDock : Int = 0
}



