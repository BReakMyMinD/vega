package com.example.vegaschedule


data class ScheduleData(
    val const: Const,
    val groups: List<Group>,
    val patterns: List<Pattern>,
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

data class Pattern(
    val color: String? = null,
    val comment: String? = null,
    val pattern: String,
    val place: String? = null,
    val pr: String? = null,
    val prLink: String? = null,
    val search: Any?
)

data class Settings(
    val firstWeekDate: String,
    val maxPar: Int,
    val subgroupCount: Int
)

data class Colors(
    val default: String,
    val lang: String,
    val ttt: String,
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
    val even: Int? = null,
    val length: String? = null,
    val name: String,
    val number: Int,
    val pr: String? = null,
    val place: String? = null,
    val subgroup: Int? = null,
    val type: String? = null,
    var isVega: Boolean = false,
    val weekSettings: String? = null,//except/only
    val weeks: List<Int>? = null
)