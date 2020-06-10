package com.example.vegaschedule

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_collection_object.*
import kotlinx.android.synthetic.main.fragment_teacher.*


class DemoCollectionPagerAdapter(fm: FragmentManager, private val activity: MainActivity, private val parent: Fragment) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int  = 6

    override fun getItem(i: Int): Fragment {
        val fragment = DayFragment(activity, parent)
        fragment.arguments = Bundle().apply {


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

private const val ARG_OBJECT = "view"
private const val ARG_MAIN = "main"

// Instances of this class are fragments representing a single
// object in our collection.
class DayFragment(private val activity: MainActivity, private val parent: Fragment) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_collection_object, container, false)
    }



    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) { //Класс иногда не пересоздается при переключении между вкладками, но должен
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

            fun setUI() {
                    println("setui called")
                    if(Card1 != null) Card1.visibility = View.VISIBLE
                    if(Card2 != null) Card2.visibility = View.VISIBLE
                    if(Card3 != null) Card3.visibility = View.VISIBLE
                    if(Card4 != null) Card4.visibility = View.VISIBLE
                    if(Card5 != null) Card5.visibility = View.VISIBLE
                    if(Card6 != null) Card6.visibility = View.VISIBLE
                    if(Card7 != null) Card7.visibility = View.VISIBLE
                    when (parent) {//баг вот тут был
                        is ScheduleFragment -> {

                            val dayPars = schedule.getDaySchedule(
                                settings.getGroup(),
                                day,
                                activity.getChosenScheduleWeek(),
                                settings.getSubgroup()
                            )
                            if (dayPars[0] != null && Card1 != null) {
                                startTime1.text = schedule.getPairTime(1)
                                finishTime1.text = schedule.getPairFinishTime(1)
                                pairType1.text = dayPars[0]?.type
                                pairName1.text = dayPars[0]?.name
                                auditoriumName1.text = dayPars[0]?.place
                                teacherName1.text = dayPars[0]?.pr
                            } else {
                                if(Card1 != null) Card1.visibility = View.GONE
                            }
                            if (dayPars[1] != null && Card2 != null) {
                                startTime2.text = schedule.getPairTime(2)
                                finishTime2.text = schedule.getPairFinishTime(2)
                                pairType2.text = dayPars[1]?.type
                                pairName2.text = dayPars[1]?.name
                                auditoriumName2.text = dayPars[1]?.place
                                teacherName2.text = dayPars[1]?.pr
                            } else {
                                if(Card2 != null) Card2.visibility = View.GONE
                            }
                            if (dayPars[2] != null && Card3 != null) {
                                startTime3.text = schedule.getPairTime(3)
                                finishTime3.text = schedule.getPairFinishTime(3)
                                pairType3.text = dayPars[2]?.type
                                pairName3.text = dayPars[2]?.name
                                auditoriumName3.text = dayPars[2]?.place
                                teacherName3.text = dayPars[2]?.pr
                            } else {
                                if(Card3 != null) Card3.visibility = View.GONE
                            }
                            if (dayPars[3] != null && Card4 != null) {
                                startTime4.text = schedule.getPairTime(4)
                                finishTime4.text = schedule.getPairFinishTime(4)
                                pairType4.text = dayPars[3]?.type
                                pairName4.text = dayPars[3]?.name
                                auditoriumName4.text = dayPars[3]?.place
                                teacherName4.text = dayPars[3]?.pr
                            } else {
                                if(Card4 != null) Card4.visibility = View.GONE
                            }
                            if (dayPars[4] != null && Card5 != null) {
                                startTime5.text = schedule.getPairTime(5)
                                finishTime5.text = schedule.getPairFinishTime(5)
                                pairType5.text = dayPars[4]?.type
                                pairName5.text = dayPars[4]?.name
                                auditoriumName5.text = dayPars[4]?.place
                                teacherName5.text = dayPars[4]?.pr
                            } else {
                                if(Card5 != null) Card5.visibility = View.GONE
                            }
                            if (dayPars[5] != null && Card6 != null) {
                                startTime6.text = schedule.getPairTime(6)
                                finishTime6.text = schedule.getPairFinishTime(6)
                                pairType6.text = dayPars[5]?.type
                                pairName6.text = dayPars[5]?.name
                                auditoriumName6.text = dayPars[5]?.place
                                teacherName6.text = dayPars[5]?.pr
                            } else {
                                if(Card6 != null) Card6.visibility = View.GONE
                            }
                            if (dayPars[6] != null && Card7 != null) {
                                startTime7.text = schedule.getPairTime(7)
                                finishTime7.text = schedule.getPairFinishTime(7)
                                pairType7.text = dayPars[6]?.type
                                pairName7.text = dayPars[6]?.name
                                auditoriumName7.text = dayPars[6]?.place
                                teacherName7.text = dayPars[6]?.pr
                            } else {
                                if(Card7 != null) Card7.visibility = View.GONE
                            }
                        }
                        is TeacherFragment -> {

                            val dayPars = schedule.getTeacherSchedule(
                                teacherSearchQuery,
                                day,
                                activity.getChosenTeacherWeek()
                            )
                            if (dayPars[0] != null && Card1 != null) {
                                startTime1.text = schedule.getPairTime(1)
                                finishTime1.text = schedule.getPairFinishTime(1)
                                pairType1.text = dayPars[0]?.type
                                pairName1.text = dayPars[0]?.name
                                auditoriumName1.text = dayPars[0]?.place
                                teacherName1.text = dayPars[0]?.pr
                            } else {
                                if(Card1 != null) Card1.visibility = View.GONE
                            }
                            if (dayPars[1] != null && Card2 != null) {
                                startTime2.text = schedule.getPairTime(2)
                                finishTime2.text = schedule.getPairFinishTime(2)
                                pairType2.text = dayPars[1]?.type
                                pairName2.text = dayPars[1]?.name
                                auditoriumName2.text = dayPars[1]?.place
                                teacherName2.text = dayPars[1]?.pr
                            } else {
                                if(Card2 != null) Card2.visibility = View.GONE
                            }
                            if (dayPars[2] != null && Card3 != null) {
                                startTime3.text = schedule.getPairTime(3)
                                finishTime3.text = schedule.getPairFinishTime(3)
                                pairType3.text = dayPars[2]?.type
                                pairName3.text = dayPars[2]?.name
                                auditoriumName3.text = dayPars[2]?.place
                                teacherName3.text = dayPars[2]?.pr
                            } else {
                                if(Card3 != null) Card3.visibility = View.GONE
                            }
                            if (dayPars[3] != null && Card4 != null) {
                                startTime4.text = schedule.getPairTime(4)
                                finishTime4.text = schedule.getPairFinishTime(4)
                                pairType4.text = dayPars[3]?.type
                                pairName4.text = dayPars[3]?.name
                                auditoriumName4.text = dayPars[3]?.place
                                teacherName4.text = dayPars[3]?.pr
                            } else {
                                if(Card4 != null) Card4.visibility = View.GONE
                            }
                            if (dayPars[4] != null && Card5 != null) {
                                startTime5.text = schedule.getPairTime(5)
                                finishTime5.text = schedule.getPairFinishTime(5)
                                pairType5.text = dayPars[4]?.type
                                pairName5.text = dayPars[4]?.name
                                auditoriumName5.text = dayPars[4]?.place
                                teacherName5.text = dayPars[4]?.pr
                            } else {
                                if(Card5 != null) Card5.visibility = View.GONE
                            }
                            if (dayPars[5] != null && Card6 != null) {
                                startTime6.text = schedule.getPairTime(6)
                                finishTime6.text = schedule.getPairFinishTime(6)
                                pairType6.text = dayPars[5]?.type
                                pairName6.text = dayPars[5]?.name
                                auditoriumName6.text = dayPars[5]?.place
                                teacherName6.text = dayPars[5]?.pr
                            } else {
                                if(Card6 != null) Card6.visibility = View.GONE
                            }
                            if (dayPars[6] != null && Card7 != null) {
                                startTime7.text = schedule.getPairTime(7)
                                finishTime7.text = schedule.getPairFinishTime(7)
                                pairType7.text = dayPars[6]?.type
                                pairName7.text = dayPars[6]?.name
                                auditoriumName7.text = dayPars[6]?.place
                                teacherName7.text = dayPars[6]?.pr
                            } else {
                                if(Card7 != null) Card7.visibility = View.GONE
                            }
                        }
                        is AuditoriumFragment -> {
                            val dayPars = schedule.getAuditoriumSchedule(
                                day,
                                activity.getChosenAuditoriumWeek()
                            )
                            if (dayPars[0] != null && Card1 != null) {
                                startTime1.text = schedule.getPairTime(1)
                                finishTime1.text = schedule.getPairFinishTime(1)
                                pairType1.text = dayPars[0]?.type
                                pairName1.text = dayPars[0]?.name
                                auditoriumName1.text = dayPars[0]?.place
                                teacherName1.visibility = View.GONE
                            } else {
                                if(Card1 != null) Card1.visibility = View.GONE
                            }
                            if (dayPars[1] != null && Card2 != null) {
                                startTime2.text = schedule.getPairTime(2)
                                finishTime2.text = schedule.getPairFinishTime(2)
                                pairType2.text = dayPars[1]?.type
                                pairName2.text = dayPars[1]?.name
                                auditoriumName2.text = dayPars[1]?.place
                                teacherName2.visibility = View.GONE
                            } else {
                                if(Card2 != null) Card2.visibility = View.GONE
                            }
                            if (dayPars[2] != null && Card3 != null) {
                                startTime3.text = schedule.getPairTime(3)
                                finishTime3.text = schedule.getPairFinishTime(3)
                                pairType3.text = dayPars[2]?.type
                                pairName3.text = dayPars[2]?.name
                                auditoriumName3.text = dayPars[2]?.place
                                teacherName3.visibility = View.GONE
                            } else {
                                if(Card3 != null) Card3.visibility = View.GONE
                            }
                            if (dayPars[3] != null && Card4 != null) {
                                startTime4.text = schedule.getPairTime(4)
                                finishTime4.text = schedule.getPairFinishTime(4)
                                pairType4.text = dayPars[3]?.type
                                pairName4.text = dayPars[3]?.name
                                auditoriumName4.text = dayPars[3]?.place
                                teacherName4.visibility = View.GONE
                            } else {
                                if(Card4 != null) Card4.visibility = View.GONE
                            }
                            if (dayPars[4] != null && Card5 != null) {
                                startTime5.text = schedule.getPairTime(5)
                                finishTime5.text = schedule.getPairFinishTime(5)
                                pairType5.text = dayPars[4]?.type
                                pairName5.text = dayPars[4]?.name
                                auditoriumName5.text = dayPars[4]?.place
                                teacherName5.visibility = View.GONE
                            } else {
                                if(Card5 != null) Card5.visibility = View.GONE
                            }
                            if (dayPars[5] != null && Card6 != null) {
                                startTime6.text = schedule.getPairTime(6)
                                finishTime6.text = schedule.getPairFinishTime(6)
                                pairType6.text = dayPars[5]?.type
                                pairName6.text = dayPars[5]?.name
                                auditoriumName6.text = dayPars[5]?.place
                                teacherName6.visibility = View.GONE
                            } else {
                                if(Card6 != null) Card6.visibility = View.GONE
                            }
                            if (dayPars[6] != null && Card7 != null) {
                                startTime7.text = schedule.getPairTime(7)
                                finishTime7.text = schedule.getPairFinishTime(7)
                                pairType7.text = dayPars[6]?.type
                                pairName7.text = dayPars[6]?.name
                                auditoriumName7.text = dayPars[6]?.place
                                teacherName7.visibility = View.GONE
                            } else {
                                if(Card7 != null) Card7.visibility = View.GONE
                            }
                        }
                    }

            }
            setUI()


            if(parent is TeacherFragment) {
                parent.editText.addTextChangedListener(object: TextWatcher {
                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                    }

                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {
                        //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun afterTextChanged(s: Editable?) {
                        teacherSearchQuery = s.toString()

                        setUI()
                    }
                })
            }

            }

        }
        private var teacherSearchQuery: String = ""
    }