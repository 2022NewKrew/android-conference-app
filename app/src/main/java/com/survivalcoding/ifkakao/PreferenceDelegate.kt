package com.survivalcoding.ifkakao

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class PreferenceDelegate(
    private val preference: SharedPreferences,
    private val key: String,
    private val default: String,
) : ReadWriteProperty<PreferenceModel, String> {

    override fun getValue(thisRef: PreferenceModel, property: KProperty<*>): String {
        return preference.getString(key, default)!!
    }

    override fun setValue(thisRef: PreferenceModel, property: KProperty<*>, value: String) {
        preference.edit().putString(key, value).apply()
    }
}
