package com.example.vegaschedule

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import kotlinx.android.synthetic.main.fragment_collection_object.*
import kotlinx.android.synthetic.main.fragment_collection_object.view.*


class DemoCollectionPagerAdapter(fm: FragmentManager, private val activity: MainActivity) : FragmentStatePagerAdapter(fm) {

    override fun getCount(): Int  = 6

    override fun getItem(i: Int): Fragment {
        val fragment = DemoObjectFragment(activity)
        fragment.arguments = Bundle().apply {
            // Our object is just an integer :-P
            putInt(ARG_OBJECT, i + 1)


        }
        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when(position) {
            0 -> "ПН"
            1 -> "ВТ"
            2 -> "СР"
            3 -> "ЧТ"
            4 -> "ПТ"
            5 -> "СБ"
            else -> ""
        }
    }
}

private const val ARG_OBJECT = "object"

// Instances of this class are fragments representing a single
// object in our collection.
class DemoObjectFragment(private val activity: MainActivity) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_collection_object, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            val pair = Card1
            val layout = pairLayout
            val schedule = activity.scheduleInstance
            val settings = activity.settingsStorage
            for (a in schedule.getDaySchedule(settings.getGroup(),activity.getCurrentDay(), activity.getCurrentWeek(), settings.getSubgroup())){
                if (a!= null) {
                    pair.startTime1.text = schedule.getPairTime(a.number)
                    pair.finishTime1.text = "test"
                    pair.pairType1.text = a.type
                    pair.pairName1.text = a.name
                    pair.auditoriumName1.text = a.place
                    pair.teacherName1.text =a.pr
                    layout.addView(pair)
                }
            }

            }
        }
    }