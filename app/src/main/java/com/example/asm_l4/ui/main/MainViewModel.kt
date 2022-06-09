package com.example.asm_l4.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private var _resText: MutableLiveData<String> = MutableLiveData("Your website will appear here")
    val resText: LiveData<String>
        get() = _resText





    fun setText(s: String?){
        _resText.value = s.toString()
    }
}