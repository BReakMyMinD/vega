package com.example.vegaschedule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

/**
 * A simple [Fragment] subclass.
 */
class AuditoriumFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_auditorium, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tabLayout: TabLayout = view.findViewById(R.id.tabLayout)
        val viewPager: ViewPager = view.findViewById(R.id.viewPager)
        viewPager.adapter = DemoCollectionPagerAdapter(childFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }

    companion object{
        @JvmStatic
        fun newInstance(param: String) = AuditoriumFragment().apply{
            arguments = Bundle().apply {

            }
        }
    }
}
