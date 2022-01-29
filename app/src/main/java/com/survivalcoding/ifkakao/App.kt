package com.survivalcoding.ifkakao

import android.app.Application
import com.survivalcoding.ifkakao.presentation.util.FragmentInformation
import dagger.hilt.android.HiltAndroidApp
import java.util.*

@HiltAndroidApp
class App : Application() {
    val fragmentStack = Stack<FragmentInformation>()
}