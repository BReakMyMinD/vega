package com.example.vegaschedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.fragment_collection_object.view.*

private const val ARG_PARAM = "param"

class mainPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {

    var list = ArrayList<String>()
    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> return ScheduleFragment.newInstance("")
            1 -> return TeacherFragment.newInstance("")
            2 -> return AuditoriumFragment.newInstance("")
            3 -> return SettingsFragment.newInstance("")
            else -> return ScheduleFragment.newInstance("")
        }
    }

    override fun getCount(): Int {
        return 4
    }

    class Child: Fragment() {
        private var param: String? = ""
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            arguments?.let{
                param = it.getString(ARG_PARAM)
            }
        }

        companion object{
            @JvmStatic
            fun newInstance(param: String) = Child().apply{
                arguments = Bundle().apply {
                    putString(ARG_PARAM, param)
                }
            }
        }

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?) : View? {
            var rootView = LayoutInflater.from(context).inflate(R.layout.fragment_collection_object,
                null, false)
          //  rootView.text1.text = param
            return rootView
        }
    }
}