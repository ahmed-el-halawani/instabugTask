package com.newcore.instabugtask.ui.history

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.newcore.instabugtask.data.local.SqlDb
import com.newcore.instabugtask.data.models.ResponseUrl

class HistoryDataViewModel(val app: Application) : AndroidViewModel(app) {
    private val TAG = "SqlDb"

    val livedata = MutableLiveData<List<ResponseUrl>>()


    fun getList() {
        livedata.value = SqlDb.getInstance(app).getResponsesList().also {
            Log.e(TAG, ": $it")
        }
    }
}