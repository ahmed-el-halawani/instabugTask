package com.newcore.instabugtask.ui.inputapidata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.newcore.instabugtask.ui.inputapidata.models.KeyValueRequest

class InputApiRequestDataViewModel : ViewModel() {
    val params: MutableLiveData<ArrayList<KeyValueRequest>> = MutableLiveData(ArrayList<KeyValueRequest>().apply {
        add(KeyValueRequest("", ""))
    })
    val header: MutableLiveData<ArrayList<KeyValueRequest>> = MutableLiveData(ArrayList<KeyValueRequest>().apply {
        add(KeyValueRequest("", ""))
    })
    val body: MutableLiveData<String> = MutableLiveData("")
}