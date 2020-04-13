package com.example.vegaschedule

import com.beust.klaxon.Klaxon
import java.lang.NullPointerException



class ScheduleContainer {
    //возвращает расписание на выбранный день в виде упорядоченного массива пар
    //индекс в массиве - порядковый номер пары минус 1
    //аргументы на русском языке, как в json-файле
    fun getDaySchedule(currentGroup: String, currentDay: String, currentWeek: Int): Array<Par?> {
        val arr: Array<Par?> = arrayOfNulls(6)
        val groupObj: Group? = data?.groups?.find{ it.group == currentGroup }
        val dayObj: Day? = groupObj?.days?.find{ it.day == currentDay }
        try {
            for (par in dayObj?.pars!!) {
                arr[par.number - 1] = par
            }
        } finally {
            return arr
        }
    }
    //возвращает список групп
    fun getGroups(): MutableList<String>{
        val list: MutableList<String> = arrayListOf()
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
                      "name": "Введение в ПД",
                      "type": "зачет",
                      "number": 3,
                      "place": "Б-209",
                      "whiteWeek": 17
                    }
                  ]
                },
                {
                  "day": "ВТ",
                  "pars": [
                    {
                      "name": "Программирование в ЗР",
                      "number": 2,
                      "place": "Б-209",
                      "type": "зачет",
                      "whiteWeek": 17
                    },
                    {
                      "name": "Физкультура и спорт",
                      "type": "зачет",
                      "number": 5,
                      "whiteWeek": 17
                    }
                  ]
                },
                {
                  "day": "СР",
                  "pars": [
                    {
                      "name": "Введение в РПО",
                      "number": 5,
                      "place": "Б-209",
                      "type": "зачет",
                      "subgroup": 1,
                      "whiteWeek": 17
                    },
                    {
                      "name": "Введение в РПО",
                      "number": 6,
                      "place": "Б-209",
                      "type": "зачет",
                      "subgroup": 2,
                      "whiteWeek": 17
                    },
                    {
                      "name": "Мет. мат. ан.",
                      "type": "зачет",
                      "number": 3,
                      "place": "Г-102",
                      "whiteWeek": 17
                    }
                  ]
                },
                {
                  "day": "ЧТ",
                  "pars": [
                    {
                      "name": "Иностранный язык",
                      "number": 3,
                      "type": "зачет",
                      "place": "И-311, Б-402",
                      "whiteWeek": 17
                    },
                    {
                      "name": "Физ-ра",
                      "number": 5,
                      "type": "зачет",
                      "whiteWeek": 17
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
                      "name": "Ин. язык",
                      "number": 1,
                      "type": "зачет",
                      "place": "Б-402",
                      "whiteWeek": 17,
                      "pr": "Гриценко С.А."
                    },
                    {
                      "name": "Ин. язык",
                      "number": 2,
                      "type": "зачет",
                      "place": "А-126",
                      "whiteWeek": 17,
                      "pr": "Иванова Е.А."
                    },
                    {
                      "name": "Введение в ПД",
                      "type": "зачет",
                      "number": 4,
                      "place": "Б-209",
                      "whiteWeek": 17
                    }
                  ]
                },
                {
                  "day": "ВТ",
                  "pars": [
                    {
                      "name": "Программирование в ЗР",
                      "number": 1,
                      "place": "Б-209",
                      "type": "зачет",
                      "whiteWeek": 17
                    },
                    {
                      "name": "Ин. язык",
                      "number": 4,
                      "type": "зачет",
                      "place": "И-305, И-307",
                      "whiteWeek": 17,
                      "pr": "Прокопчук А.Р.,<br>Гаврилова Е.А."
                    }
                  ]
                },
                {
                  "day": "СР",
                  "pars": [
                    {
                      "name": "Введение в РПО",
                      "number": 3,
                      "place": "Б-209",
                      "type": "зачет",
                      "subgroup": 1,
                      "whiteWeek": 17
                    },
                    {
                      "name": "Введение в РПО",
                      "number": 4,
                      "place": "Б-209",
                      "type": "зачет",
                      "subgroup": 2,
                      "whiteWeek": 17
                    }
                  ]
                },
                {
                  "day": "ЧТ",
                  "pars": [
                    {
                      "name": "Физ-ра",
                      "number": 5,
                      "type": "зачет",
                      "whiteWeek": 17
                    }
                  ]
                },
                {
                  "day": "ПТ",
                  "pars": [
                    {
                      "name": "Физ-ра",
                      "number": 2,
                      "type": "зачет",
                      "whiteWeek": 17
                    },
                    {
                      "name": "Мет. мат. ан.",
                      "type": "зачет",
                      "number": 3,
                      "place": "А-307",
                      "whiteWeek": 17
                    }
                  ]
                }
              ]
            },
            {
              "group": "КМБО-02-18",
              "days": [
                {
                  "day": "ПН",
                  "pars": [
                    {
                      "name": "Языки и МП",
                      "number": 5,
                      "place": "Б-209",
                      "type": "зачет,<br>к/р",
                      "whiteWeek": 17
                    },
                    {
                      "name": "Экономика",
                      "type": "зачет",
                      "number": 3,
                      "place": "Д-208",
                      "whiteWeek": 17
                    }
                  ]
                },
                {
                  "day": "ВТ",
                  "pars": [
                    {
                      "name": "Ин. яз.",
                      "number": 1,
                      "place": "И-320",
                      "type": "зачет",
                      "whiteWeek": 17
                    },
                    {
                      "name": "Мат. анализ",
                      "type": "зачет",
                      "number": 4,
                      "place": "А-150",
                      "whiteWeek": 17
                    }
                  ]
                },
                {
                  "day": "СР",
                  "pars": [
                    {
                      "name": "Базы данных",
                      "type": "зачет",
                      "number": 2,
                      "place": "Б-209",
                      "whiteWeek": 17
                    },
                    {
                      "name": "Физ-ра",
                      "number": 5,
                      "type": "зачет",
                      "whiteWeek": 17
                    }
                  ]
                },
                {
                  "day": "ЧТ",
                  "pars": [
                    {
                      "name": "Физ-ра",
                      "number": 2,
                      "type": "зачет",
                      "whiteWeek": 17
                    }
                  ]
                }
              ]
            },
            {
              "group": "КМБО-05-18",
              "days": [
                {
                  "day": "ПН",
                  "pars": [
                    {
                      "name": "Языки и МП",
                      "type": "зачет,<br>к/р",
                      "number": 6,
                      "place": "Б-209",
                      "whiteWeek": 17
                    }
                  ]
                },
                {
                  "day": "ВТ",
                  "pars": [
                    {
                      "name": "Ин. яз.",
                      "number": 3,
                      "place": "А-126, Б-408",
                      "type": "зачет",
                      "whiteWeek": 17,
                      "pr": "Нанай Ф.А.,<br>Гриценко С.А."
                    },
                    {
                      "name": "Мат. анализ",
                      "type": "зачет",
                      "number": 5,
                      "place": "А-150",
                      "whiteWeek": 17,
                      "pr": "Шелепин А.Л."
                    }
                  ]
                },
                {
                  "day": "СР",
                  "pars": [
                    {
                      "name": "Базы данных",
                      "type": "зачет",
                      "number": 1,
                      "place": "Б-209",
                      "whiteWeek": 17
                    },
                    {
                      "name": "Физ-ра",
                      "number": 4,
                      "type": "зачет",
                      "whiteWeek": 17
                    }
                  ]
                },
                {
                  "day": "ЧТ",
                  "pars": [
                    {
                      "name": "Физ-ра",
                      "number": 2,
                      "type": "зачет",
                      "whiteWeek": 17
                    }
                  ]
                },
                {
                  "day": "ПТ",
                  "pars": [
                    {
                      "name": "Экономика",
                      "type": "зачет",
                      "number": 5,
                      "place": "А-208",
                      "whiteWeek": 17
                    }
                  ]
                }
              ]
            },
            {
              "group": "КМБО-02-17",
              "days": [
                {
                  "day": "ПН",
                  "pars": [
                    {
                      "name": "Физ-ра",
                      "number": 5,
                      "type": "зачет",
                      "whiteWeek": 17
                    }
                  ]
                },
                {
                  "day": "ВТ",
                  "pars": [
                    {
                      "name": "Числ. методы",
                      "number": 2,
                      "place": "А-204",
                      "type": "зачет",
                      "subgroup": 1,
                      "whiteWeek": 17
                    },
                    {
                      "name": "Системный анализ ПО",
                      "type": "зачет",
                      "number": 4,
                      "place": "Б-209",
                      "whiteWeek": 17
                    },
                    {
                      "name": "Числ. методы",
                      "number": 6,
                      "place": "Б-209",
                      "type": "зачет",
                      "subgroup": 2,
                      "whiteWeek": 17
                    }
                  ]
                },
                {
                  "day": "ПТ",
                  "pars": [
                    {
                      "name": "ОС",
                      "number": 4,
                      "place": "Б-209",
                      "type": "зачет",
                      "whiteWeek": 17
                    }
                  ]
                }
              ]
            },
            {
              "group": "КМБО-05-17",
              "days": [
                {
                  "day": "ВТ",
                  "pars": [
                    {
                      "name": "Числ. методы",
                      "number": 1,
                      "place": "Б-209",
                      "type": "зачет",
                      "whiteWeek": 17
                    },
                    {
                      "name": "Системный анализ ПО",
                      "type": "зачет",
                      "number": 3,
                      "place": "Б-209",
                      "whiteWeek": 17
                    }
                  ]
                },
                {
                  "day": "ЧТ",
                  "pars": [
                    {
                      "name": "Физ-ра",
                      "number": 4,
                      "type": "зачет",
                      "whiteWeek": 17
                    }
                  ]
                },
                {
                  "day": "ПТ",
                  "pars": [
                    {
                      "name": "ОС",
                      "number": 5,
                      "place": "Б-209",
                      "type": "зачет",
                      "whiteWeek": 17
                    }
                  ]
                }
              ]
            },
            {
              "group": "КМБО-02-16",
              "days": [
                {
                  "day": "ПН",
                  "pars": [
                    {
                      "name": "Системы АП",
                      "number": 2,
                      "place": "Б-209",
                      "type": "зачет",
                      "whiteWeek": 17
                    },
                    {
                      "name": "Гр. оборона",
                      "number": 4,
                      "place": "А-11",
                      "type": "зачет",
                      "whiteWeek": 17
                    }
                  ]
                },
                {
                  "day": "ВТ",
                  "pars": [
                    {
                      "name": "Теория игр",
                      "number": 3,
                      "place": "А-177-1",
                      "type": "зачет",
                      "whiteWeek": 17
                    },
                    {
                      "name": "УРПО",
                      "number": 5,
                      "place": "Б-209",
                      "type": "к/п",
                      "whiteWeek": 17
                    }
                  ]
                },
                {
                  "day": "ПТ",
                  "pars": [
                    {
                      "name": "Произв. обучение",
                      "number": 2,
                      "place": "Б-209",
                      "type": "зачет",
                      "whiteWeek": 17,
                      "pr": "Крыжановский Ю.М."
                    }
                  ]
                }
              ]
            }
          ]
        }
    """
}