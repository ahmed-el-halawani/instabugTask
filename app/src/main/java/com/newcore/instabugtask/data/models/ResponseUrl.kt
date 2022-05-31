package com.newcore.instabugtask.data.models

data class ResponseUrl(
    val requestUrl: RequestUrl,
    var statusCode: String = "",
    var header: String = "",
    var body: String = "",
)