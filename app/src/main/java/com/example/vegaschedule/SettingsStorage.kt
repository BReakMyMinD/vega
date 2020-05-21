package com.example.vegaschedule

import android.content.Context
import android.content.SharedPreferences

class SettingsStorage(private val context: Context, private val defaultGroup: String) {
    //сохраняет заданные настройки в локальном хранилище устройства
    fun saveGroup(group: String) {
        val editor = pref.edit()
        editor.putString("Group", group)
        editor.apply()
    }
    fun saveSubgroup(subgroup: Int) {
        val editor = pref.edit()
        editor.putInt("Subgroup", subgroup)
        editor.apply()
    }

    //чтение сохраненных настроек
    fun getGroup(): String {
        val res = pref.getString("Group", defaultGroup)
        return res.toString()
    }
    fun getSubgroup(): Int {
        return pref.getInt("Subgroup", 1)
    }

    private val pref: SharedPreferences = context.getSharedPreferences("SETTINGS_PREFERENCE",
        Context.MODE_PRIVATE)
}