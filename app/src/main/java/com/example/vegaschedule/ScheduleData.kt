package com.example.vegaschedule

data class ScheduleData(
    val `const`: Const,
    val groups: List<Group>,
    val settings: Settings
)

data class Const(
    val colors: Colors,
    val fullDay: FullDay,
    val timePar: TimePar
)

data class Group(
    val days: List<Day>,
    val group: String
)

data class Settings(
    val firstWeekDate: String,
    val subgroupCount: Int,
    val maxPar: Int
)

data class Colors(
    val default: String,
    val math: String,
    val кафедра: String
)

data class FullDay(
    val ВТ: String,
    val ПН: String,
    val ПТ: String,
    val СБ: String,
    val СР: String,
    val ЧТ: String
)

data class TimePar(
    val `1`: String,
    val `2`: String,
    val `3`: String,
    val `4`: String,
    val `5`: String,
    val `6`: String,
    val `7`: String
)

data class Day(
    val day: String,
    val pars: List<Par>
)

data class Par(
    val name: String,
    val number: Int,
    val type: String? = null,
    val place: String? = null,//аудитория(если есть)
    val pr: String? = null,//преподаватель(если есть)
    val subgroup: Int? = null,//подгруппа(если есть)
    val weekSettings: String,//all, even, odd, except, only
    val weeks: List<Int>? = null
)
