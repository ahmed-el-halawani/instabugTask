package com.newcore.instabugtask.data.remote

import com.newcore.instabugtask.data.models.RequestUrl
import com.newcore.instabugtask.data.models.ResponseUrl
import com.newcore.instabugtask.utils.NetworkCallHelper

class TestUrlRepo {
    //test
    fun testUrl(requestUrl: RequestUrl, onSuccess: (ResponseUrl) -> Unit, onError: () -> Unit) {
        NetworkCallHelper.RequestTask(onSuccess, onError).execute(requestUrl)
    }
}