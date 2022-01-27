package com.survivalcoding.ifkakao

import android.content.Context

class UserSettings(override val context: Context) : PreferenceModel {
    var userName by PreferenceLoader("")
    var email by PreferenceLoader("aa@aa.com")
}
