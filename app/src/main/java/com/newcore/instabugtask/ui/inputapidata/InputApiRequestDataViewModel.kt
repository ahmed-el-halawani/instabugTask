package com.newcore.instabugtask.ui.inputapidata

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.newcore.instabugtask.data.models.KeyValueRequest
import com.newcore.instabugtask.data.models.RequestUrl
import com.newcore.instabugtask.data.remote.TestUrlRepo

class InputApiRequestDataViewModel : ViewModel() {
    private val TAG = "InputApiRequestDataView"

    private val testUrlRepo by lazy {
        TestUrlRepo()
    }

    val params: MutableLiveData<ArrayList<KeyValueRequest>> = MutableLiveData(ArrayList<KeyValueRequest>().apply {
        add(KeyValueRequest("", ""))
    })
    val header: MutableLiveData<ArrayList<KeyValueRequest>> = MutableLiveData(ArrayList<KeyValueRequest>().apply {
        add(KeyValueRequest("", ""))
    })
    val body: MutableLiveData<String> = MutableLiveData("")

    var url: String = ""

    var isGetType: Boolean = true

    //    val callUrl = MutableLiveData<State<ResponseUrl>>()

    fun callUrl() {
        testUrlRepo.testUrl(RequestUrl(
            url,
            isGetType,
            header.value?.filter { it.key != "" || it.value != "" }?.toList() ?: emptyList(),
            params.value?.filter { it.key != "" || it.value != "" }?.toList() ?: emptyList(),
            body.value ?: ""
        ), {
            Log.e(TAG, "onSuccess: " + it)
        }, {
            Log.e(TAG, "onError: ")
        })
    }


}