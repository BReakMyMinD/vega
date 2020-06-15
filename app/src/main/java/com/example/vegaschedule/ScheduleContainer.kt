package com.example.vegaschedule

import java.lang.NullPointerException


class ScheduleContainer(private val provider: Provider) {
    //возвращает расписание на выбранный день в виде упорядоченного массива пар
    //индекс в массиве - порядковый номер пары минус 1
    //аргументы на русском языке, как в json-файле
    fun getDaySchedule(currentGroup: String, currentDay: String, currentWeek: Int, subgroup: Int): Array<Par?> {
        val maxPar = data?.settings?.maxPar ?: 6
        val arr: Array<Par?> = arrayOfNulls(maxPar)
        val groupObj: Group? = data?.groups?.find{ it.group == currentGroup }
        val dayObj: Day? = groupObj?.days?.find{ it.day == currentDay }

        try {
            loop@ for (par in dayObj?.pars!!) {
                if (par.subgroup != null && par.subgroup != subgroup) {
                    continue@loop
                }
                if(checkPar(par, currentDay, currentWeek)) {
                    arr[par.number - 1] = par
                }
                else {
                    continue@loop
                }
            }
        } finally {
            return arr
        }
    }
    //возвращает массив пар которые ведет выбранный преподаватель в данный день
    fun getTeacherSchedule(name: String, day: String, week: Int): Array<Par?> {
        val maxPar = data?.settings?.maxPar ?: 6
        val arr: Array<Par?> = arrayOfNulls(maxPar)
        if(name.isEmpty()) {
            return arr
        }
        try {
            loop@ for (group in data?.groups!!) {
                val dayObj = group.days.find{ it.day == day }
                val parList = dayObj?.pars?.filter{ it.pr?.contains(name) ?: false
                        && checkPar(it, day, week) }
                if(parList.isNullOrEmpty()) {
                    continue@loop
                }
                else {
                    for(par in parList) {
                        arr[par.number - 1] = par
                    }
                    break@loop
                }
            }
        } finally {
            return arr
        }
    }
    //возвращает пары, когда аудитория Б209 свободна
    fun getAuditoriumSchedule(day: String, week: Int): Array<Par?> {
        val maxPar = data?.settings?.maxPar ?: 6
        val arr: Array<Par?> = arrayOfNulls(maxPar)

        try {
            loop@ for (group in data?.groups!!) {
                val dayObj = group.days.find{ it.day == day }
                val parList = dayObj?.pars?.filter{ it.place?.contains("Б-209") ?: false
                        && checkPar(it, day, week) }

                if (!parList.isNullOrEmpty()) {
                    var firstParNum: Int = maxPar
                    var lastParNum: Int = 0
                    for(par in parList) {
                        if(firstParNum >= par.number) {
                            firstParNum = par.number
                        }
                        if(lastParNum <= par.number) {
                            lastParNum = par.number
                        }
                    }
                    for(par in parList) {

                        if(par.place == "Б-209л"){
                            val emptyParObj: Par = Par("Аудитория свободна", par.number, "Правая", "Б-209", null, null, "all", null)
                            arr[par.number - 1] = emptyParObj
                        }
                        else if(par.place == "Б-209п") {
                            val emptyParObj: Par = Par("Аудитория свободна", par.number, "Левая", "Б-209", null, null, "all", null)
                            arr[par.number - 1] = emptyParObj
                        }
                    }
                    for(parNum in 1..maxPar) {

                        if(arr[parNum] == null && lastParNum > parNum && firstParNum < parNum) {
                            val emptyParObj: Par = Par("Аудитория свободна", parNum, "Целиком", "Б-209", null, null, "all", null)
                            arr[parNum - 1] = emptyParObj
                        }
                    }
                }

            }
        } finally {
            return arr
        }
    }

    //fun teacherWorkingDays(name: String)
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

    fun getSubgroups(): MutableList<Int> {
        val list = arrayListOf<Int>()
        for(count in 1..data?.settings?.subgroupCount as Int) {
            list.add(count)
        }
        return list
    }
    //возвращает время начала пары
    fun getPairTime(number: Int): String {
        val times = data?.const?.timePar.toString().split(',')
        return times[number - 1].substringAfter('=').split('-').first()
    }
    fun getPairFinishTime(number: Int): String {
        val times = data?.const?.timePar.toString().split(',')
        return times[number - 1].substringAfter('=').split('-').last()
    }

    fun getFirstWeek(): String? {
        return data?.settings?.firstWeekDate
    }
    fun getWeeks(): MutableList<Int> {
        val weeks = arrayListOf<Int>()
        for(num in 0..15){
            weeks.add(num + 1)
        }
        return weeks
    }

    fun loadData() {
        data = provider.getSchedule()
    }

    private fun checkPar(par: Par, day: String, week: Int): Boolean {
        val weekType : Int
        var res: Boolean = true
        if (week%2 == 0) {
            weekType = 2
        }
        else {
            weekType = 1
        }
        when (par.weekSettings) {
            "even" -> if (weekType == 1) {
                res = false
            }
            "odd" -> if (weekType == 2) {
                res = false
            }
            "except" -> if (par.weeks != null && par.weeks.contains(week)) {
                res = false
            }
            "only" -> if (par.weeks != null && !par.weeks.contains(week)) {
                res = false
            }
            else -> {
                res = true
            }
        }
        return res
    }

    private var data: ScheduleData? = null
}