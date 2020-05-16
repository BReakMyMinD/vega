package com.example.vegaschedule

import android.content.Context
import com.beust.klaxon.Klaxon
import java.net.URL
import java.util.concurrent.TimeoutException
import kotlin.concurrent.thread


interface Provider {
    fun getSchedule(): ScheduleData?
}

class FileProvider(private val name: String, private val context: Context) : Provider {
    override fun getSchedule(): ScheduleData? {
        val inputStream = context.assets.open(name)
        return Klaxon().parse<ScheduleData>(inputStream)
    }
}

class ServerProvider(private val path: String) : Provider {
    override fun getSchedule(): ScheduleData? {
        var jsonString: String = "null"
        var requestTime: Int = 0

        thread(start=true){
            jsonString = URL(path).readText()
        }
        while (jsonString == "null") {
            if (requestTime < 10) {
                requestTime++
                Thread.sleep(1000)
            } else {
                throw TimeoutException("server does not respond")
            }
        }
        return Klaxon().parse<ScheduleData>(jsonString)
    }
}