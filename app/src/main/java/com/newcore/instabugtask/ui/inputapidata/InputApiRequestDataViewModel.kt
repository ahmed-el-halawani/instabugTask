package com.newcore.instabugtask.ui.inputapidata

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.newcore.instabugtask.data.local.SqlDb
import com.newcore.instabugtask.data.models.KeyValueRequest
import com.newcore.instabugtask.data.models.RequestUrl
import com.newcore.instabugtask.data.models.ResponseUrl
import com.newcore.instabugtask.data.remote.TestUrlRepo
import com.newcore.instabugtask.utils.State

class InputApiRequestDataViewModel(application: Application) : AndroidViewModel(application) {
    private val TAG = "InputApiRequestDataView"

    val sqlDb = SqlDb.getInstance(application)

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

    fun testUrl(onSuccess: (State<ResponseUrl>) -> Unit, onError: () -> Unit) {
        testUrlRepo.testUrl(RequestUrl(
            url,
            isGetType,
            header.value?.filter { it.key != "" || it.value != "" }?.toList() ?: emptyList(),
            params.value?.filter { it.key != "" || it.value != "" }?.toList() ?: emptyList(),
            body.value ?: ""
        ), {
            onSuccess(State.Success(it))
            sqlDb.setData(it)
            Log.e(TAG, "onSuccess: " + it)
        }, {
            onError()
            Log.e(TAG, "onError: ")
        })
    }


}