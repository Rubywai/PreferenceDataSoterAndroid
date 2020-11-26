package com.example.preferencedatasoterandroid

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel(application: Application) : AndroidViewModel(application) {


    private  val myDataStore  = MyDataStore(application)
    val readDataStore = myDataStore.readDataStore.asLiveData()
    fun write(str : String) = viewModelScope.launch(Dispatchers.IO) {
        myDataStore.write(str)

    }
}