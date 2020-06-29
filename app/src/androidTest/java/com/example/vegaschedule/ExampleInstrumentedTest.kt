package com.example.vegaschedule

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.GeneralLocation
import androidx.test.espresso.action.GeneralSwipeAction
import androidx.test.espresso.action.Press
import androidx.test.espresso.action.Swipe
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.selects.select
import org.hamcrest.CoreMatchers.allOf
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */



//fun selectTabAtPosition(tabIndex: Int): ViewAction {
//    return object : ViewAction {
//        override fun getDescription() = "with tab at index $tabIndex"
//
//        override fun getConstraints() = allOf(isDisplayed(), isAssignableFrom(TabLayout::class.java))
//
//        override fun perform(uiController: UiController, view: View) {
//            val tabLayout = view as TabLayout
//            val tabAtIndex: TabLayout.Tab = tabLayout.getTabAt(tabIndex)
//                ?: throw PerformException.Builder()
//                    .withCause(Throwable("No tab at index $tabIndex"))
//                    .build()
//
//            tabAtIndex.select()
//        }
//    }
//}


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private val TAG = "MainActivityTest"
    private val testProvider = ServerProvider("https://raw.githubusercontent.com/BReakMyMinD/vega/master/db.json")
    private val testContainer = ScheduleContainer(testProvider)
    private var group = "КМБО-02-18"
    private var subgroup = 2



    private fun checkName(arg: Array<Par?>, num: Int) : String? {
        return if(arg[num - 1] != null) {
            arg[num - 1]?.name
        } else {
            ""
        }
    }

    private fun parNameCheck(r: Array<Par?>) {
        if(checkName(r, 1) != "") {
            onView(allOf(withId(R.id.pairName1), isDisplayed())).check(matches(withText(checkName(r, 1))))
        }
        if(checkName(r, 2) != "") {
            onView(allOf(withId(R.id.pairName2), isDisplayed())).check(matches(withText(checkName(r, 2))))
        }
        if(checkName(r, 3) != "") {
            onView(allOf(withId(R.id.pairName3), isDisplayed())).check(matches(withText(checkName(r, 3))))
        }
        if(checkName(r, 4) != "") {
            onView(allOf(withId(R.id.pairName4), isDisplayed())).check(matches(withText(checkName(r, 4))))
        }
        if(checkName(r, 5) != "") {
            onView(allOf(withId(R.id.viewPager), isDisplayed())).perform(swipeUp())
            onView(allOf(withId(R.id.pairName5), isDisplayed())).check(matches(withText(checkName(r, 5))))
        }
        if(checkName(r, 6) != "") {
            onView(allOf(withId(R.id.viewPager), isDisplayed())).perform(swipeUp())
            onView(allOf(withId(R.id.pairName6), isDisplayed())).check(matches(withText(checkName(r, 6))))
        }
    }

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)




    @Test
    fun test1() {
        onView(withId(R.id.settings)).perform(click());
        onView(withId(R.id.spinner3)).perform(click());
        onView(withText(group)).perform(click());
        onView(withId(R.id.spinner4)).perform(click());
        onView(withText(subgroup.toString())).perform(click());
        onView(withId(R.id.schedule)).perform(click());

        for(i in 1..5) {
            onView(allOf(withId(R.id.viewPager), isDisplayed())).perform(swipeRight())
        }
        testContainer.loadData()
        var r = testContainer.getDaySchedule(group, "ПН", 16, subgroup)
        parNameCheck(r)
        onView(allOf(withId(R.id.viewPager), isDisplayed())).perform(swipeLeft())
        r = testContainer.getDaySchedule(group, "ВТ", 16, subgroup)
        parNameCheck(r)

        onView(allOf(withId(R.id.viewPager), isDisplayed())).perform(swipeLeft())
        r = testContainer.getDaySchedule(group, "СР", 16, subgroup)
        parNameCheck(r)

        onView(allOf(withId(R.id.viewPager), isDisplayed())).perform(swipeLeft())
        r = testContainer.getDaySchedule(group, "ЧТ", 16, subgroup)
        parNameCheck(r)

        onView(allOf(withId(R.id.viewPager), isDisplayed())).perform(swipeLeft())
        r = testContainer.getDaySchedule(group, "ПТ", 16, subgroup)
        parNameCheck(r)

        onView(allOf(withId(R.id.viewPager), isDisplayed())).perform(swipeLeft())
        r = testContainer.getDaySchedule(group, "СБ", 16, subgroup)
        parNameCheck(r)
    }

    @Test
    fun test2() {
        onView(withId(R.id.settings)).perform(click());
        onView(withId(R.id.schedule)).perform(click())
        onView(allOf(withId(R.id.viewPager), isDisplayed())).perform(swipeLeft())
        onView(allOf(withId(R.id.viewPager), isDisplayed())).perform(swipeLeft())
        onView(withId(R.id.scheduleWeekSpinner)).perform(click())
        onView(withText("15")).perform(click())

        for(i in 1..5) {
            onView(allOf(withId(R.id.viewPager), isDisplayed())).perform(swipeRight())
        }
        testContainer.loadData()
        var r = testContainer.getDaySchedule(group, "ПН", 15, subgroup)
        parNameCheck(r)
        onView(allOf(withId(R.id.viewPager), isDisplayed())).perform(swipeLeft())
        r = testContainer.getDaySchedule(group, "ВТ", 15, subgroup)
        parNameCheck(r)

        onView(allOf(withId(R.id.viewPager), isDisplayed())).perform(swipeLeft())
        r = testContainer.getDaySchedule(group, "СР", 15, subgroup)
        parNameCheck(r)

        onView(allOf(withId(R.id.viewPager), isDisplayed())).perform(swipeLeft())
        r = testContainer.getDaySchedule(group, "ЧТ", 15, subgroup)
        parNameCheck(r)

        onView(allOf(withId(R.id.viewPager), isDisplayed())).perform(swipeLeft())
        r = testContainer.getDaySchedule(group, "ПТ", 15, subgroup)
        parNameCheck(r)

        onView(allOf(withId(R.id.viewPager), isDisplayed())).perform(swipeLeft())
        r = testContainer.getDaySchedule(group, "СБ", 15, subgroup)
        parNameCheck(r)
    }


    @Test
    fun test3() {
        val teacher = "Громова"
        onView(withId(R.id.teacher)).perform(click());
        onView(withId(R.id.editText)).perform(replaceText(teacher))
        onView(withId(R.id.teacherWeekSpinner)).perform(click())
        onView(withText("15")).perform(click());
        onView(allOf(withId(R.id.viewPager), isDisplayed())).perform(swipeLeft())
        for(i in 1..5) {
            onView(allOf(withId(R.id.viewPager), isDisplayed())).perform(swipeRight())
        }
        testContainer.loadData()
        var r = testContainer.getTeacherSchedule(teacher, "ПН", 15)
        parNameCheck(r)
        onView(allOf(withId(R.id.viewPager), isDisplayed())).perform(swipeLeft())
        r = testContainer.getTeacherSchedule(teacher, "ВТ", 15)
        parNameCheck(r)

        onView(allOf(withId(R.id.viewPager), isDisplayed())).perform(swipeLeft())
        r = testContainer.getTeacherSchedule(teacher, "СР", 15)
        parNameCheck(r)

        onView(allOf(withId(R.id.viewPager), isDisplayed())).perform(swipeLeft())
        r = testContainer.getTeacherSchedule(teacher, "ЧТ", 15)
        parNameCheck(r)

        onView(allOf(withId(R.id.viewPager), isDisplayed())).perform(swipeLeft())
        r = testContainer.getTeacherSchedule(teacher, "ПТ", 15)
        parNameCheck(r)

        onView(allOf(withId(R.id.viewPager), isDisplayed())).perform(swipeLeft())
        r = testContainer.getTeacherSchedule(teacher, "СБ", 15)
        parNameCheck(r)
    }

    @Test
    fun test4() {
        onView(withId(R.id.auditorium)).perform(click())
        onView(allOf(withId(R.id.viewPager), isDisplayed())).perform(swipeLeft())
        onView(allOf(withId(R.id.viewPager), isDisplayed())).perform(swipeLeft())
        onView(allOf(withId(R.id.viewPager), isDisplayed())).perform(swipeLeft())
        onView(withId(R.id.auditoriumWeekSpinner)).perform(click())
        onView(withText("16")).perform(click())

        for(i in 1..5) {
            onView(allOf(withId(R.id.viewPager), isDisplayed())).perform(swipeRight())
        }
        testContainer.loadData()
        var r = testContainer.getAuditoriumSchedule("ПН", 16)
        parNameCheck(r)
        onView(allOf(withId(R.id.viewPager), isDisplayed())).perform(swipeLeft())
        r = testContainer.getAuditoriumSchedule( "ВТ", 16)
        parNameCheck(r)

        onView(allOf(withId(R.id.viewPager), isDisplayed())).perform(swipeLeft())
        r = testContainer.getAuditoriumSchedule( "СР", 16)
        parNameCheck(r)

        onView(allOf(withId(R.id.viewPager), isDisplayed())).perform(swipeLeft())
        r = testContainer.getAuditoriumSchedule("ЧТ", 16)
        parNameCheck(r)

        onView(allOf(withId(R.id.viewPager), isDisplayed())).perform(swipeLeft())
        r = testContainer.getAuditoriumSchedule( "ПТ", 16)
        parNameCheck(r)

        onView(allOf(withId(R.id.viewPager), isDisplayed())).perform(swipeLeft())
        r = testContainer.getAuditoriumSchedule( "СБ", 16)
        parNameCheck(r)
    }
    }



