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
    val one: String,
    val two: String,
    val three: String,
    val four: String,
    val five: String,
    val six: String,
    val seven: String
)

data class Day(
    val day: String,
    val pars: List<Par>
)

data class Par(
    val name: String,
    val number: Int,
    val place: String? = null,//аудитория(если есть)
    val pr: String? = null,//преподаватель(если есть)
    val subgroup: Int? = null,//подгруппа(если есть)
    val typeWeek: Int? = null,//1 если по нечетным неделям, 2 если по четным, null если по обеим
    val excludedWeeks: List<Int>? = null,//кроме данных недель(если есть)
    val type: String
)
