package com.example.vegaschedule

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.fragment_schedule.*


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

        }
        mainPager.setOnTouchListener(View.OnTouchListener { v, event -> true })
        mainPager.adapter = mainPagerAdapter(supportFragmentManager).apply{
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
            .replace(R.id.content, fragment, fragment.javaClass.getSimpleName())
            .commit()
    }
}



