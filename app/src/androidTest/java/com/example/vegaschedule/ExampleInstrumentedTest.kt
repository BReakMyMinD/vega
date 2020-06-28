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






    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)




    @Test
    fun test1() {
        onView(withId(R.id.settings)).perform(click());
        onView(withId(R.id.spinner3)).perform(click());
        onView(withText("КМБО-02-18")).perform(click());
        onView(withId(R.id.spinner4)).perform(click());
        onView(withText("2")).perform(click());
        onView(withId(R.id.schedule)).perform(click());
    }

    @Test
    fun test2() {
        onView(withId(R.id.settings)).perform(click());
        onView(withId(R.id.schedule)).perform(click())
        onView(allOf(withId(R.id.viewPager), isDisplayed())).perform(swipeLeft())
        onView(allOf(withId(R.id.viewPager), isDisplayed())).perform(swipeLeft())
        onView(withId(R.id.scheduleWeekSpinner)).perform(click())
        onView(withText("15")).perform(click())
        onView(allOf(withId(R.id.viewPager), isDisplayed())).perform(swipeRight())
        onView(allOf(withId(R.id.viewPager), isDisplayed())).perform(swipeRight())
    }


    @Test
    fun test3() {
        onView(withId(R.id.teacher)).perform(click());
        onView(withId(R.id.editText)).perform(typeText("Gromova"))
        onView(withId(R.id.teacherWeekSpinner)).perform(click())
        onView(withText("15")).perform(click());
        onView(allOf(withId(R.id.viewPager), isDisplayed())).perform(swipeLeft())
        onView(allOf(withId(R.id.viewPager), isDisplayed())).perform(swipeLeft())
        onView(allOf(withId(R.id.viewPager), isDisplayed())).perform(swipeLeft())
        onView(allOf(withId(R.id.viewPager), isDisplayed())).perform(swipeLeft())
        Thread.sleep(1000)
    }

    @Test
    fun test4() {
        onView(withId(R.id.auditorium)).perform(click())
        onView(allOf(withId(R.id.viewPager), isDisplayed())).perform(swipeLeft())
        onView(allOf(withId(R.id.viewPager), isDisplayed())).perform(swipeLeft())
        onView(allOf(withId(R.id.viewPager), isDisplayed())).perform(swipeLeft())
    }
    }



