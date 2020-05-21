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
import kotlinx.android.synthetic.main.fragment_auditorium.*
import kotlinx.android.synthetic.main.fragment_schedule.*
import kotlinx.android.synthetic.main.fragment_teacher.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        fileProvider = FileProvider("temp_schedule.json", this)
        scheduleInstance = ScheduleContainer(fileProvider)
        scheduleInstance.loadData()
        settingsStorage = SettingsStorage(this, scheduleInstance.getGroups().first())


        bottom_menu.setOnItemSelectedListener { id ->

            when(id) {
                R.id.schedule -> {
                    mainPager.currentItem = 0
                    title = "Расписание"
                }
                R.id.teacher -> {
                    mainPager.currentItem = 1
                    title = "Поиск преподавателя"
                }
                R.id.auditorium -> {
                    mainPager.currentItem = 2
                    title = "Поиск свободной аудитории"
                }
                R.id.settings -> {
                    mainPager.currentItem = 3
                    title = "Настройки"
                }

            }
            currentDock = mainPager.currentItem
        }

        //mainPager.setOnTouchListener(View.OnTouchListener { v, event -> true })
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

    fun getChosenScheduleWeek(): Int {
        return scheduleWeekSpinner.selectedItem.toString().toInt()
    }
    fun getChosenTeacherWeek(): Int {
        return teacherWeekSpinner.selectedItem.toString().toInt()
    }
    fun getChosenAuditoriumWeek(): Int {
        return auditoriumWeekSpinner.selectedItem.toString().toInt()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun getCurrentDay() : Int{
        val calendar: Calendar = Calendar.getInstance()
        return when (calendar.get(Calendar.DAY_OF_WEEK)) {
            Calendar.MONDAY -> 0
            Calendar.TUESDAY -> 1
            Calendar.WEDNESDAY -> 2
            Calendar.THURSDAY -> 3
            Calendar.FRIDAY -> 4
            Calendar.SATURDAY -> 5
            Calendar.SUNDAY -> 0

            else -> 0
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

    private lateinit var fileProvider : FileProvider
    lateinit var scheduleInstance : ScheduleContainer
    lateinit var settingsStorage : SettingsStorage


    var currentDock : Int = 0
    var teacherSearch: String = ""
}



