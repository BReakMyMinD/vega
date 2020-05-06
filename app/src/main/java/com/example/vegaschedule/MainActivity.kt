package com.example.vegaschedule


import android.annotation.SuppressLint
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val weekSpinner: Spinner = findViewById(R.id.weekSpinner)
        val groupSpinner: Spinner = findViewById(R.id.groupSpinner)
        var chosenDay : String = getCurrentDay()
        //val provider = FileProvider("temp_schedule.json", this)
        //or
        val provider = ServerProvider("https://my-json-server.typicode.com/BReakMyMinD/vega/db", this)
        schedule = ScheduleContainer(provider)
        schedule.loadData()
        if (groupSpinner != null) {
            val adapter =
                ArrayAdapter(this, android.R.layout.simple_spinner_item, schedule.getGroups())
            groupSpinner.adapter = adapter
        }
        if (weekSpinner != null) {
            val adapter =
                ArrayAdapter(this,android.R.layout.simple_spinner_item, schedule.getWeeks())
            weekSpinner.adapter = adapter
        }
        weekSpinner.setSelection(getCurrentWeek() - 1)

        setSchedule(getCurrentDay(),getChosenGroup(),getChosenWeek())
        dayButtonMonday.setOnClickListener{
            setSchedule("ПН",getChosenGroup(),getChosenWeek())
            chosenDay = "ПН"
        }
        dayButtonTuesday.setOnClickListener{
            setSchedule("ВТ",getChosenGroup(),getChosenWeek())
            chosenDay = "ВТ"
        }
        dayButtonWednesday.setOnClickListener{
            setSchedule("СР",getChosenGroup(),getChosenWeek())
            chosenDay = "СР"
        }
        dayButtonThursday.setOnClickListener{
            setSchedule("ЧТ",getChosenGroup(),getChosenWeek())
            chosenDay = "ЧТ"
        }
        dayButtonFriday.setOnClickListener{
            setSchedule("ПТ",getChosenGroup(),getChosenWeek())
            chosenDay = "ПТ"
        }
        dayButtonSaturday.setOnClickListener {
            setSchedule("СБ", getChosenGroup(), getChosenWeek())
            chosenDay = "СБ"
        }
        groupSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                setSchedule(getCurrentDay(),selectedItem,getChosenWeek())
            }

        }
        weekSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                //println("onItemSelected - begin")
                setSchedule(chosenDay,getChosenGroup(),selectedItem.toInt())
                //println("onItemSelected - final")
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun getCurrentDay() : String{
        val calendar: Calendar = Calendar.getInstance()
        return when (calendar.get(Calendar.DAY_OF_WEEK)) {
            Calendar.MONDAY ->  "ПН"
            Calendar.TUESDAY ->  "ВТ"
            Calendar.WEDNESDAY ->  "СР"
            Calendar.THURSDAY ->  "ЧТ"
            Calendar.FRIDAY ->  "ПТ"
            Calendar.SATURDAY ->  "СБ"
            Calendar.SUNDAY ->  "ПН"

            else -> "ERR"
        }
    }

    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.N)
    fun getCurrentWeek() : Int{
        val calendar = Calendar.getInstance()
        val firstWeek = schedule.getFirstWeek() as String
        val firstWeekDate = java.text.SimpleDateFormat("dd.MM.yyyy").parse(firstWeek)
        var weeks = calendar.fieldDifference(firstWeekDate, Calendar.WEEK_OF_YEAR)
        if(weeks < 0) {
            weeks = -weeks
            weeks += 1
        }
        else {
            weeks = 0
        }
        return weeks
    }

    fun getChosenGroup() : String {
        return groupSpinner.selectedItem.toString()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun getChosenWeek() : Int {
        return weekSpinner.selectedItem.toString().toInt()

    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun setSchedule(day:String, group:String, week:Int){
        val paraName1 = findViewById<TextView>(R.id.paraName1) as TextView
        val paraName2 = findViewById<TextView>(R.id.paraName2) as TextView
        val paraName3 = findViewById<TextView>(R.id.paraName3) as TextView
        val paraName4 = findViewById<TextView>(R.id.paraName4) as TextView
        val paraName5 = findViewById<TextView>(R.id.paraName5) as TextView
        val paraName6 = findViewById<TextView>(R.id.paraName6) as TextView
        val audName1 = findViewById<TextView>(R.id.audName1) as TextView
        val audName2 = findViewById<TextView>(R.id.audName2) as TextView
        val audName3 = findViewById<TextView>(R.id.audName3) as TextView
        val audName4 = findViewById<TextView>(R.id.audName4) as TextView
        val audName5 = findViewById<TextView>(R.id.audName5) as TextView
        val audName6 = findViewById<TextView>(R.id.audName6) as TextView
        val time1 = findViewById<TextView>(R.id.TextTime1) as TextView
        val time2 = findViewById<TextView>(R.id.TextTime2) as TextView
        val time3 = findViewById<TextView>(R.id.TextTime3) as TextView
        val time4 = findViewById<TextView>(R.id.TextTime4) as TextView
        val time5 = findViewById<TextView>(R.id.TextTime5) as TextView
        val time6 = findViewById<TextView>(R.id.TextTime6) as TextView


        //val schedule = ScheduleContainer()
        //schedule.loadData("test")


        val scheduleArray: Array<Par?> = schedule.getDaySchedule(currentGroup = group, currentDay = day , currentWeek = week, subgroup = 1)
        paraName1.text = scheduleArray[0]?.name
        paraName2.text = scheduleArray[1]?.name
        paraName3.text = scheduleArray[2]?.name
        paraName4.text = scheduleArray[3]?.name
        paraName5.text = scheduleArray[4]?.name
        paraName6.text = scheduleArray[5]?.name
        audName1.text = scheduleArray[0]?.place
        audName2.text = scheduleArray[1]?.place
        audName3.text = scheduleArray[2]?.place
        audName4.text = scheduleArray[3]?.place
        audName5.text = scheduleArray[4]?.place
        audName6.text = scheduleArray[5]?.place
        if (scheduleArray[0]?.number != 1) {
            paraName1.text = "------"
            audName1.text = "------"
        }
        if (scheduleArray[1]?.number != 2) {
            paraName2.text = "------"
            audName2.text = "------"
        }
        if (scheduleArray[2]?.number != 3) {
            paraName3.text = "------"
            audName3.text = "------"
        }
        if (scheduleArray[3]?.number != 4) {
            paraName4.text = "------"
            audName4.text = "------"
        }
        if (scheduleArray[4]?.number != 5) {
            paraName5.text = "------"
            audName5.text = "------"
        }
        if (scheduleArray[5]?.number != 6) {
            paraName6.text = "------"
            audName6.text = "------"
        }
        time1.text = schedule.getPairTime(1)
        time2.text = schedule.getPairTime(2)
        time3.text = schedule.getPairTime(3)
        time4.text = schedule.getPairTime(4)
        time5.text = schedule.getPairTime(5)
        time6.text = schedule.getPairTime(6)
    }

    private lateinit var schedule: ScheduleContainer
}



