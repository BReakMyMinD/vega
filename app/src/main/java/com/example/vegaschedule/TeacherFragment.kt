package com.example.vegaschedule

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.core.widget.addTextChangedListener
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_schedule.*
import kotlinx.android.synthetic.main.fragment_teacher.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class TeacherFragment(private val activity : MainActivity) : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teacher, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tabLayout: TabLayout = view.findViewById(R.id.tabLayout)
        val viewPager: ViewPager = view.findViewById(R.id.viewPager)
        viewPager.adapter = DemoCollectionPagerAdapter(childFragmentManager, activity, this)
        tabLayout.setupWithViewPager(viewPager)
        //viewPager.offscreenPageLimit = 6


        if(teacherWeekSpinner != null) {
            val adapter = ArrayAdapter(activity, android.R.layout.simple_spinner_item, activity.scheduleInstance.getWeeks())
            teacherWeekSpinner.adapter = adapter
        }
        teacherWeekSpinner.setSelection(activity.getCurrentWeek() - 1)
    }
    companion object{
        @JvmStatic
        fun newInstance(param: MainActivity) = TeacherFragment(param)
    }
}
