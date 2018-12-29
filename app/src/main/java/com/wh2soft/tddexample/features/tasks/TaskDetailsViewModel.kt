package com.wh2soft.tddexample.features.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;

class TaskDetailsViewModel : ViewModel() {

    val liveData : LiveData<String> = MutableLiveData()

    fun title(): String {
        return "Texto de ejemplo"
    }
    // TODO: Implement the ViewModel
}
