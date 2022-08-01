package com.zpf.myplan.ui.plan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlanViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "text0"
    }
    val text: LiveData<String> = _text
}