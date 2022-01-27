package com.survivalcoding.ifkakao

import android.content.Context
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class PreferenceLoader(private val default: String) {
   operator fun provideDelegate(
           thisRef: PreferenceModel,
           property: KProperty<*>
   ): ReadWriteProperty<PreferenceModel, String> {

       return PreferenceDelegate(
               thisRef.context.getSharedPreferences(
                       thisRef.javaClass.simpleName,
                       Context.MODE_PRIVATE
               ),
               property.name,
               default
       )
   }
}
