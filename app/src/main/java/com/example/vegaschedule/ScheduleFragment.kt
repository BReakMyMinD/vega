package com.example.vegaschedule

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_schedule.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ScheduleFragment(private val activity : MainActivity) : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tabLayout = view.findViewById(R.id.tabLayout)
        weekPager = view.findViewById(R.id.viewPager)
        weekPager.adapter = DemoCollectionPagerAdapter(childFragmentManager, activity, this)

        tabLayout.setupWithViewPager(weekPager)
        weekPager.currentItem = activity.getCurrentDay()
        weekPager.offscreenPageLimit = 6

        if(scheduleWeekSpinner != null) {
            val adapter = ArrayAdapter(activity, android.R.layout.simple_spinner_item, activity.scheduleInstance.getWeeks())
            scheduleWeekSpinner.adapter = adapter
        }
        scheduleWeekSpinner.setSelection(activity.getCurrentWeek() - 1)



    }



    companion object{
        @JvmStatic
        fun newInstance(param : MainActivity) = ScheduleFragment(param) //pass external param for mainpagerid
    }
    lateinit var weekPager: ViewPager
    lateinit var tabLayout: TabLayout
}
