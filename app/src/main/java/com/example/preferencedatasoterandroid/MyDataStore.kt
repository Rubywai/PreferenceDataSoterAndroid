package com.example.preferencedatasoterandroid

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException


class MyDataStore(context : Context) {
    private object Key{
        val key  = preferencesKey<String>("myKey")
        val preference_name = "myPreference"
    }
    private val dataStore : DataStore<Preferences> =
        context.createDataStore(name = Key.preference_name)

    suspend fun write(str : String){
        dataStore.edit { preference ->
            preference[Key.key] = str
        }
    }
    val readDataStore : Flow<String> = dataStore.data
        .catch { error ->
            if(error is IOException){
                emit(emptyPreferences())
            }
            else{
                throw error
            }
        }
        .map {preference ->
            val str = preference[Key.key] ?: "no data"
            str
        }


}