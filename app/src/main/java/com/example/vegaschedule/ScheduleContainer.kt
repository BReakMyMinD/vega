package com.example.vegaschedule

import java.lang.NullPointerException



class ScheduleContainer(private val provider: Provider) {
    //возвращает расписание на выбранный день в виде упорядоченного массива пар
    //индекс в массиве - порядковый номер пары минус 1
    //аргументы на русском языке, как в json-файле
    fun getDaySchedule(currentGroup: String, currentDay: String, currentWeek: Int, subgroup: Int): Array<Par?> {
        val arr: Array<Par?> = arrayOfNulls(6)
        val groupObj: Group? = data?.groups?.find{ it.group == currentGroup }
        val dayObj: Day? = groupObj?.days?.find{ it.day == currentDay }
        try {
            loop@ for (par in dayObj?.pars!!) {
                if(par.subgroup != null) {
                    if(par.subgroup != subgroup) {
                        continue@loop
                    }
                }
                if(par.weekType != null) {
                    if(par.weekType != currentWeek%2) {
                        continue@loop
                    }
                }
                if(par.excludedWeeks != null) {
                    if(currentWeek in par.excludedWeeks) {
                        continue@loop
                    }
                }
                arr[par.number - 1] = par
            }
        } finally {
            return arr
        }
    }
    //возвращает список групп
    fun getGroups(): MutableList<String>{
        val list = arrayListOf<String>()
        try {
            for (group in data?.groups!!) {
                list.add(group.group)
            }
        }
        catch(e: NullPointerException) {
            list.add("Corrupted data")
        }
        return list
    }
    //возвращает время начала пары
    fun getPairTime(number: Int): String {
        var time: String = ""
        when (number) {
            1 -> time = data?.const?.timePar?.one.toString()
            2 -> time = data?.const?.timePar?.two.toString()
            3 -> time = data?.const?.timePar?.three.toString()
            4 -> time = data?.const?.timePar?.four.toString()
            5 -> time = data?.const?.timePar?.five.toString()
            6 -> time = data?.const?.timePar?.six.toString()
            7 -> time = data?.const?.timePar?.seven.toString()
        }
        return time.split("-").first()
    }

    fun getFirstWeek(): String? {
        return data?.settings?.firstWeekDate
    }
    fun getWeeks(): MutableList<Int> {
        val weeks = arrayListOf<Int>()
        for(num in 1..16){
            weeks.add(num)
        }
        return weeks
    }

    fun loadData() {
        data = provider.getSchedule()
    }

    private var data: ScheduleData? = null
}