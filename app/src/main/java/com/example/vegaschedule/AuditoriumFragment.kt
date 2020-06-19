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
import kotlinx.android.synthetic.main.fragment_auditorium.*
import kotlinx.android.synthetic.main.fragment_teacher.*
import kotlinx.android.synthetic.main.fragment_teacher.editText

/**
 * A simple [Fragment] subclass.
 */
class AuditoriumFragment(private val activity : MainActivity) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_auditorium, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tabLayout = view.findViewById(R.id.tabLayout)
        weekPager = view.findViewById(R.id.viewPager)
        weekPager.adapter = DemoCollectionPagerAdapter(childFragmentManager, activity, this)
        weekPager.currentItem = activity.getCurrentDay()
        tabLayout.setupWithViewPager(weekPager)
        weekPager.offscreenPageLimit = 6


        if(auditoriumWeekSpinner != null) {
            val adapter = ArrayAdapter(activity, android.R.layout.simple_spinner_item, activity.scheduleInstance.getWeeks())
            auditoriumWeekSpinner.adapter = adapter
        }
        auditoriumWeekSpinner.setSelection(activity.getCurrentWeek() - 1)
    }

    companion object{
        @JvmStatic
        fun newInstance(param: MainActivity) = AuditoriumFragment(param)
    }
    lateinit var weekPager: ViewPager
    lateinit var tabLayout: TabLayout
}
