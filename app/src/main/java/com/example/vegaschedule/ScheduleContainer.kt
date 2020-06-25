package com.example.vegaschedule

import java.lang.NullPointerException


class ScheduleContainer(private val provider: Provider) {
    //возвращает расписание на выбранный день в виде упорядоченного массива пар
    //индекс в массиве - порядковый номер пары минус 1
    //аргументы на русском языке, как в json-файле
    fun getDaySchedule(currentGroup: String, currentDay: String, currentWeek: Int, subgroup: Int): Array<Par?> {
        val maxPar = data?.settings?.maxPar ?: 7
        val arr: Array<Par?> = arrayOfNulls(maxPar)
        val cafedra = data?.patterns?.find{ it.color == "kafedra"}?.search as List<String>?
        if (cafedra != null) {
            cafedra.forEach{ it.replace("*", "")}
        }
        val groupObj: Group? = data?.groups?.find{ it.group == currentGroup }
        val dayObj: Day? = groupObj?.days?.find{ it.day == currentDay }

        try {
            loop@ for (par in dayObj?.pars!!) {
                if (par.subgroup != null && par.subgroup != subgroup) {
                    continue@loop
                }
                if(checkPar(par, currentDay, currentWeek)) {
                    if(cafedra != null) {
                        if(cafedra.contains(par.name)){
                            par.isVega = true
                        }
                    }
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
        val maxPar = data?.settings?.maxPar ?: 7
        val cafedra = data?.patterns?.find{ it.color == "кафедра"}?.search as List<String>?
        if (cafedra != null) {
            cafedra.forEach{ it.replace("*", "")}
        }
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
                        if(cafedra != null) {
                            if(cafedra.contains(par.name)){
                                par.isVega = true
                            }
                        }
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
        val maxPar = data?.settings?.maxPar ?: 7
        val arr: Array<Par?> = arrayOfNulls(maxPar)

        try {
            loop@ for (group in data?.groups!!) {
                val dayObj = group.days.find{ it.day == day }
                val parList = dayObj?.pars?.filter{ it.place?.contains("Б-209") ?: false
                        && checkPar(it, day, week) }

                if (parList.isNullOrEmpty()) {
                    continue@loop
                }
                else {
                    for(par in parList) {
                        if(arr[par.number - 1] != null) {
                            arr[par.number - 1] = Par(par.even, par.length, par.name, par.number, par.pr,
                                "Б-209", par.subgroup, par.type, par.isVega, par.weekSettings, par.weeks)
                        }
                        else {
                            arr[par.number - 1] = par
                        }
                    }
                }
            }
            var firstParNum: Int = maxPar
            var lastParNum: Int = 0
            for(par in arr) {
                if(par != null) {
                    if(firstParNum >= par.number) {
                        firstParNum = par.number
                    }
                    if(lastParNum <= par.number) {
                        lastParNum = par.number
                    }
                }
            }

            for(parNum in 1..maxPar) {
                var par = arr[parNum - 1]
                if(par != null) {
                    if(par.place == "Б-209л"){
                        val emptyParObj: Par = Par(null, null,"Аудитория свободна", par.number, null, "Б-209", null, "Правая", true, null, null)
                        arr[par.number - 1] = emptyParObj
                    }
                    else if(par.place == "Б-209п") {
                        val emptyParObj: Par = Par(null, null,"Аудитория свободна", par.number, null, "Б-209", null, "Левая", true, null, null)
                        arr[par.number - 1] = emptyParObj
                    }
                    else {
                        arr[par.number - 1] = null
                    }
                }
                else if (lastParNum > parNum && firstParNum < parNum){
                    val emptyParObj: Par = Par(null, null,"Аудитория свободна", parNum, null, "Б-209", null, "Целиком", true, null, null)
                    arr[parNum - 1] = emptyParObj
                }
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
        weekType = if (week%2 == 0) {
            2
        } else {
            1
        }
        if(par.even != null){
            res = weekType == par.even
        }
        when (par.weekSettings) {
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