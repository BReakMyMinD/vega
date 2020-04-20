package com.example.vegaschedule

import com.beust.klaxon.Klaxon
import java.lang.NullPointerException



class ScheduleContainer {
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
    //загружает с сервера актуальное расписание в формате json
    fun loadData(requestPath: String) {

        data = Klaxon().parse<ScheduleData>(temp)
    }
    private var data: ScheduleData? = null
    //временная заглушка:
    private val temp: String = """
        {
          "settings": {
            "firstWeekDate": "02.08.2019",
            "maxPar": 7
          },
          "const": {
            "colors": {
              "кафедра": "rgb(165, 235, 60)",
              "math": "rgb(255, 225, 90)",
              "default": "rgb(135, 225, 235)"
            },
            "fullDay": {
              "ПН": "Понедельник",
              "ВТ": "Вторник",
              "СР": "Среда",
              "ЧТ": "Четверг",
              "ПТ": "Пятница",
              "СБ": "Суббота"
            },
            "timePar": {
              "one": "9:00-10:30",
              "two": "10:40-12:10",
              "three": "13:10-14:40",
              "four": "14:50-16:20",
              "five": "16:40-18:00",
              "six": "18:10-19:40",
              "seven": "19:50-21:20"
            }
          },
          "groups": [
            {
              "group": "КМБО-02-19",
              "days": [
                {
                  "day": "ПН",
                  "pars": [
                    {
                      "name": "кмбо0219 пн 3",
                      "type": "лк",
                      "number": 3,
                      "place": "A-16",
                      "pr": "Артамкин"
                    },
                    {
                      "name": "кмбо0219 пн 4 1пг чет",
                      "type": "лаб",
                      "number": 4,
                      "place": "Б-209",
                      "subgroup": 1,
                      "typeWeek": 2
                    },
                    {
                      "name": "кмбо0219 пн 4 2пг нечет",
                      "type": "лаб",
                      "number": 4,
                      "place": "Б-209",
                      "subgroup": 2,
                      "typeWeek": 1
                    }
                  ]
                },
                {
                  "day": "ВТ",
                  "pars": [
                    {
                      "name": "кмбо0219 2 вт",
                      "number": 2,
                      "place": "Б-209",
                      "type": "лк"
                    },
                    {
                      "name": "кмбо0219 3 вт",
                      "type": "пр",
                      "number": 3,
                      "place": "A-215"
                    }
                    {
                      "name": "кмбо0219 5 вт 1пг",
                      "type": "пр",
                      "number": 5,
                      "place": "Б-209"
                      "subgroup": 1
                    }
                  ]
                },
                {
                  "day": "СР",
                  "pars": [
                    {
                      "name": "кмбо0219 5 ср 1пг",
                      "number": 5,
                      "place": "Б-209",
                      "type": "лк",
                      "subgroup": 1
                    },
                    {
                      "name": "кмбо0219 6 ср 2пг",
                      "number": 6,
                      "place": "Б-209",
                      "type": "лк",
                      "subgroup": 2
                    },
                    {
                      "name": "кмбо0219 3 ср нечет",
                      "type": "зачет",
                      "number": 3,
                      "place": "Г-102",
                      "typeWeek": 1
                    }
                  ]
                },
                {
                  "day": "ЧТ",
                  "pars": [
                    {
                      "name": "кмбо0219 чт 3",
                      "number": 3,
                      "type": "пр",
                      "place": "Б-402"
                    },
                    {
                      "name": "кмбо0219 чт 4",
                      "number": 4,
                      "type": "пр"
                    },
                    {
                      "name": "кмбо0219 чт 5 кроме 10н",
                      "number": 5,
                      "type": "лк",
                      "place": "А-10"
                      "excludedWeeks": [10]
                    }
                  ]
                },
                {
                  "day": "ПТ",
                  "pars": [
                    {
                      "name": "кмбо0219 пт 1 нечет",
                      "number": 1,
                      "type": "пр",
                      "typeWeek": 1,
                      "place": "Б-402"
                    },
                    {
                      "name": "кмбо0219 пт 1 чет",
                      "number": 1,
                      "type": "пр",
                      "typeWeek": 2
                    },
                    {
                      "name": "кмбо0219 пт 2 2пг",
                      "number": 2,
                      "type": "лк",
                      "place": "Б-209",
                      "subgroup": 2
                    }
                  ]
                }
              ]
            },
            {
              "group": "КМБО-05-19",
              "days": [
                {
                  "day": "ПН",
                  "pars": [
                    {
                      "name": "кмбо0519 пн 1",
                      "number": 1,
                      "type": "пр",
                      "place": "Б-402",
                      "pr": "Гриценко С.А."
                    },
                    {
                      "name": "кмбо0519 пн 2 чет",
                      "number": 2,
                      "type": "лк",
                      "place": "А-126",
                      "typeWeek": 2
                      "pr": "Иванова Е.А."
                    },
                    {
                      "name": "кмбо0519 пн 3 нечет",
                      "type": "пр",
                      "number": 3,
                      "place": "Б-209",
                      "typeWeek": 1
                    }
                  ]
                },
                {
                  "day": "ВТ",
                  "pars": [
                    {
                      "name": "кмбо0519 1 вт 1пг",
                      "number": 1,
                      "place": "Б-209",
                      "type": "лк",
                      "subgroup": 1
                    },
                    {
                      "name": "кмбо0219 2 вт 2пг",
                      "number": 2,
                      "place": "Б-209",
                      "type": "лк",
                      "subgroup": 2
                    },
                    {
                      "name": "кмбо0219 3 вт нечет кроме 9 и 11",
                      "type": "лк",
                      "number": 3,
                      "place": "Г-102",
                      "typeWeek": 1
                      "excludedWeeks": [9, 11]
                    }
                  ]
                },
                {
                  "day": "СР",
                  "pars": [
                    {
                      "name": "кмбо0519 ср 3 1пг",
                      "number": 3,
                      "place": "Б-209",
                      "type": "пр",
                      "subgroup": 1
                    },
                    {
                      "name": "кмбо0519 ср 4 2 пг",
                      "number": 4,
                      "place": "Б-209",
                      "type": "пр",
                      "subgroup": 2
                    },
                    {
                      "name": "кмбо0519 5 ср",
                      "number": 5,
                      "place": "Б-209",
                      "type": "лк"
                    }
                  ]
                },
                {
                  "day": "ЧТ",
                  "pars": [
                    {
                      "name": "кмбо0519 чт 3",
                      "number": 3,
                      "type": "пр",
                      "place": "Б-402"
                    },
                    {
                      "name": "кмбо0519 чт 4",
                      "number": 4,
                      "type": "пр"
                    },
                    {
                      "name": "кмбо0519 чт 5",
                      "number": 5,
                      "type": "лк",
                      "place": "А-10"
                    }
                  ]
                },
                {
                  "day": "ПТ",
                  "pars": [
                    {
                      "name": "кмбо0519 пт 1",
                      "number": 1,
                      "type": "лк"
                      "place": "A-15"
                    },
                    {
                      "name": "кмбо0519 пт 2",
                      "type": "пр",
                      "number": 2,
                      "place": "А-174"
                    },
                    {
                      "name": "кмбо0519 пт 3 чет",
                      "type": "лк",
                      "number": 3,
                      "place": "А-10"
                      "typeWeek": 2
                    },
                    {
                      "name": "кмбо0519 пт 4",
                      "type": "пр",
                      "number": 4,
                      "place": "Б-209"
                    }
                  ]
                }
              ]
            }
          ]
        }
    """
}