package com.example.vegaschedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.fragment_collection_object.view.*



class mainPagerAdapter(fm: FragmentManager, private val activity : MainActivity): FragmentPagerAdapter(fm) {

    var list = ArrayList<String>()
    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> ScheduleFragment.newInstance(activity)
            1 -> TeacherFragment.newInstance(activity)
            2 -> AuditoriumFragment.newInstance(activity)
            3 -> SettingsFragment.newInstance(activity)
            else -> ScheduleFragment.newInstance(activity)
        }
    }

    override fun getCount(): Int {
        return 4
    }
}