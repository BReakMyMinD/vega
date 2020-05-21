package com.example.vegaschedule

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import kotlinx.android.synthetic.main.fragment_collection_object.*
import kotlinx.android.synthetic.main.fragment_collection_object.view.*
import kotlinx.android.synthetic.main.fragment_teacher.*


class DemoCollectionPagerAdapter(fm: FragmentManager, private val activity: MainActivity) : FragmentPagerAdapter(fm) {

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
            val layout = pairLayout
            val schedule = activity.scheduleInstance
            val settings = activity.settingsStorage
            val day = when(getInt(ARG_OBJECT)) {
                1 -> "ПН"
                2 -> "ВТ"
                3 -> "СР"
                4 -> "ЧТ"
                5 -> "ПТ"
                6 -> "СБ"
                else -> "ПН"
            }
            when(activity.currentDock) {
                0 -> {
                    val dayPars = schedule.getDaySchedule(settings.getGroup(), day, activity.getChosenScheduleWeek(), settings.getSubgroup())
                    if(dayPars[0] != null) {
                        startTime1.text = schedule.getPairTime(1)
                        finishTime1.text = "test"
                        pairType1.text = dayPars[0]?.type
                        pairName1.text = dayPars[0]?.name
                        auditoriumName1.text = dayPars[0]?.place
                        teacherName1.text = dayPars[0]?.pr
                    }
                    else {
                        Card1.visibility = View.GONE
                    }
                    if(dayPars[1] != null) {
                        startTime2.text = schedule.getPairTime(2)
                        finishTime2.text = "test"
                        pairType2.text = dayPars[1]?.type
                        pairName2.text = dayPars[1]?.name
                        auditoriumName2.text = dayPars[1]?.place
                        teacherName2.text = dayPars[1]?.pr
                    }
                    else {
                        Card2.visibility = View.GONE
                    }
                    if(dayPars[2] != null) {
                        startTime3.text = schedule.getPairTime(3)
                        finishTime3.text = "test"
                        pairType3.text = dayPars[2]?.type
                        pairName3.text = dayPars[2]?.name
                        auditoriumName3.text = dayPars[2]?.place
                        teacherName3.text = dayPars[2]?.pr
                    }
                    else {
                        Card3.visibility = View.GONE
                    }
                    if(dayPars[3] != null) {
                        startTime4.text = schedule.getPairTime(4)
                        finishTime4.text = "test"
                        pairType4.text = dayPars[3]?.type
                        pairName4.text = dayPars[3]?.name
                        auditoriumName4.text = dayPars[3]?.place
                        teacherName4.text = dayPars[3]?.pr
                    }
                    else {
                        Card4.visibility = View.GONE
                    }
                    if(dayPars[4] != null) {
                        startTime5.text = schedule.getPairTime(5)
                        finishTime5.text = "test"
                        pairType5.text = dayPars[4]?.type
                        pairName5.text = dayPars[4]?.name
                        auditoriumName5.text = dayPars[4]?.place
                        teacherName5.text = dayPars[4]?.pr
                    }
                    else {
                        Card5.visibility = View.GONE
                    }
                    if(dayPars[5] != null) {
                        startTime6.text = schedule.getPairTime(6)
                        finishTime6.text = "test"
                        pairType6.text = dayPars[5]?.type
                        pairName6.text = dayPars[5]?.name
                        auditoriumName6.text = dayPars[5]?.place
                        teacherName6.text = dayPars[5]?.pr
                    }
                    else {
                        Card6.visibility = View.GONE
                    }
                    if(dayPars[6] != null) {
                        startTime7.text = schedule.getPairTime(7)
                        finishTime7.text = "test"
                        pairType7.text = dayPars[6]?.type
                        pairName7.text = dayPars[6]?.name
                        auditoriumName7.text = dayPars[6]?.place
                        teacherName7.text = dayPars[6]?.pr
                    }
                    else {
                        Card7.visibility = View.GONE
                    }
                }
                1 -> {
                    val dayPars = schedule.getTeacherSchedule(activity.teacherSearch, day, activity.getChosenTeacherWeek())
                    if(dayPars[0] != null) {
                        startTime1.text = schedule.getPairTime(1)
                        finishTime1.text = "test"
                        pairType1.text = dayPars[0]?.type
                        pairName1.text = dayPars[0]?.name
                        auditoriumName1.text = dayPars[0]?.place
                        teacherName1.text = dayPars[0]?.pr
                    }
                    else {
                        Card1.visibility = View.GONE
                    }
                    if(dayPars[1] != null) {
                        startTime2.text = schedule.getPairTime(2)
                        finishTime2.text = "test"
                        pairType2.text = dayPars[1]?.type
                        pairName2.text = dayPars[1]?.name
                        auditoriumName2.text = dayPars[1]?.place
                        teacherName2.text = dayPars[1]?.pr
                    }
                    else {
                        Card2.visibility = View.GONE
                    }
                    if(dayPars[2] != null) {
                        startTime3.text = schedule.getPairTime(3)
                        finishTime3.text = "test"
                        pairType3.text = dayPars[2]?.type
                        pairName3.text = dayPars[2]?.name
                        auditoriumName3.text = dayPars[2]?.place
                        teacherName3.text = dayPars[2]?.pr
                    }
                    else {
                        Card3.visibility = View.GONE
                    }
                    if(dayPars[3] != null) {
                        startTime4.text = schedule.getPairTime(4)
                        finishTime4.text = "test"
                        pairType4.text = dayPars[3]?.type
                        pairName4.text = dayPars[3]?.name
                        auditoriumName4.text = dayPars[3]?.place
                        teacherName4.text = dayPars[3]?.pr
                    }
                    else {
                        Card4.visibility = View.GONE
                    }
                    if(dayPars[4] != null) {
                        startTime5.text = schedule.getPairTime(5)
                        finishTime5.text = "test"
                        pairType5.text = dayPars[4]?.type
                        pairName5.text = dayPars[4]?.name
                        auditoriumName5.text = dayPars[4]?.place
                        teacherName5.text = dayPars[4]?.pr
                    }
                    else {
                        Card5.visibility = View.GONE
                    }
                    if(dayPars[5] != null) {
                        startTime6.text = schedule.getPairTime(6)
                        finishTime6.text = "test"
                        pairType6.text = dayPars[5]?.type
                        pairName6.text = dayPars[5]?.name
                        auditoriumName6.text = dayPars[5]?.place
                        teacherName6.text = dayPars[5]?.pr
                    }
                    else {
                        Card6.visibility = View.GONE
                    }
                    if(dayPars[6] != null) {
                        startTime7.text = schedule.getPairTime(7)
                        finishTime7.text = "test"
                        pairType7.text = dayPars[6]?.type
                        pairName7.text = dayPars[6]?.name
                        auditoriumName7.text = dayPars[6]?.place
                        teacherName7.text = dayPars[6]?.pr
                    }
                    else {
                        Card7.visibility = View.GONE
                    }
                }
                2 -> {
                    val dayPars = schedule.getAuditoriumSchedule("Б-209", day, activity.getChosenAuditoriumWeek())
                    if(dayPars[0] == null) {
                        startTime1.text = schedule.getPairTime(1)
                        finishTime1.text = "test"
                        pairType1.text = "Левая/правая/целая"
                        pairName1.text = "Аудитория свободна"
                        auditoriumName1.text = "Б-209"
                        teacherName1.visibility = View.GONE
                    }
                    else {
                        Card1.visibility = View.GONE
                    }
                    if(dayPars[1] != null) {
                        startTime2.text = schedule.getPairTime(2)
                        finishTime2.text = "test"
                        pairType2.text = dayPars[1]?.type
                        pairName2.text = dayPars[1]?.name
                        auditoriumName2.text = dayPars[1]?.place
                        teacherName2.text = dayPars[1]?.pr
                    }
                    else {
                        Card2.visibility = View.GONE
                    }
                    if(dayPars[2] != null) {
                        startTime3.text = schedule.getPairTime(3)
                        finishTime3.text = "test"
                        pairType3.text = dayPars[2]?.type
                        pairName3.text = dayPars[2]?.name
                        auditoriumName3.text = dayPars[2]?.place
                        teacherName3.text = dayPars[2]?.pr
                    }
                    else {
                        Card3.visibility = View.GONE
                    }
                    if(dayPars[3] != null) {
                        startTime4.text = schedule.getPairTime(4)
                        finishTime4.text = "test"
                        pairType4.text = dayPars[3]?.type
                        pairName4.text = dayPars[3]?.name
                        auditoriumName4.text = dayPars[3]?.place
                        teacherName4.text = dayPars[3]?.pr
                    }
                    else {
                        Card4.visibility = View.GONE
                    }
                    if(dayPars[4] != null) {
                        startTime5.text = schedule.getPairTime(5)
                        finishTime5.text = "test"
                        pairType5.text = dayPars[4]?.type
                        pairName5.text = dayPars[4]?.name
                        auditoriumName5.text = dayPars[4]?.place
                        teacherName5.text = dayPars[4]?.pr
                    }
                    else {
                        Card5.visibility = View.GONE
                    }
                    if(dayPars[5] != null) {
                        startTime6.text = schedule.getPairTime(6)
                        finishTime6.text = "test"
                        pairType6.text = dayPars[5]?.type
                        pairName6.text = dayPars[5]?.name
                        auditoriumName6.text = dayPars[5]?.place
                        teacherName6.text = dayPars[5]?.pr
                    }
                    else {
                        Card6.visibility = View.GONE
                    }
                    if(dayPars[6] != null) {
                        startTime7.text = schedule.getPairTime(7)
                        finishTime7.text = "test"
                        pairType7.text = dayPars[6]?.type
                        pairName7.text = dayPars[6]?.name
                        auditoriumName7.text = dayPars[6]?.place
                        teacherName7.text = dayPars[6]?.pr
                    }
                    else {
                        Card7.visibility = View.GONE
                    }
                }
            }
            /*val pair = Card1


            var pairNum = 0
            var cardHeight = 122
            val dayPars
            for (a in ){
                if (a!= null) {
                    pairNum++
                    pair.startTime1.text = schedule.getPairTime(a.number)
                    pair.finishTime1.text = "test"
                    pair.pairType1.text = a.type
                    pair.pairName1.text = a.name
                    pair.auditoriumName1.text = a.place
                    pair.teacherName1.text =a.pr
                    pair.y += (pairNum - 1)*cardHeight

                    layout.addView(pair)
                }
            }
            */
            }
        }
    }