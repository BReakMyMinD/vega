package com.example.vegaschedule

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import java.time.DayOfWeek




class ScheduleContainer {
    //возвращает расписание на выбранный день в виде массива пар
    //аргументы на русском языке, как в json-файле
    fun getDaySchedule(group: String, day: String, week: Int): Array<PairDetails> {
        val schedule: Array<PairDetails> = emptyArray()
        val groups = data["groups"] as JsonArray<JsonObject>
        val days = groups.find{
            it.string("group") == group
        }!!["days"] as JsonArray<JsonObject>
        val pars = days.find{
            it.string("day") == day
        }!!["pars"] as JsonArray<JsonObject>
        fun pairBeginTime(number: Int): String {
            val const = data["const"] as JsonObject
            val times = const["timePar"] as JsonObject
            return times.string(number.toString())!!.split("-").first()
        }
        for(item in pars) {
            val pairNum = item["number"] as Int
            schedule[pairNum - 1] = PairDetails(item["name"] as String,
                pairBeginTime(pairNum), "no data", item["place"] as String,
                item["type"] as String)
        }
        return schedule
    }
    //возвращает список групп
    fun getGroups(): MutableList<String>{
        val groupList: MutableList<String> = arrayListOf()
        val groups = data["groups"] as JsonArray<JsonObject>
        for(item in groups) {
            val groupName = item["group"] as String
            groupList.add(groupName)
        }
        return groupList
    }
    //загружает с сервера актуальное расписание в формате json
    fun loadData(requestPath: String) {
        fun parse(name: String) : Any? {
            val cls = Parser::class.java
            return cls.getResourceAsStream(name)?.let { inputStream ->
                return Parser.default().parse(inputStream)
            }
        }
        //временная заглушка:
        //путь к файлу мб неправильный
        data = parse("./../../../res/temp_data.json") as JsonObject
    }
    private lateinit var data: JsonObject
}