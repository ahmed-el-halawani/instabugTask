package com.newcore.instabugtask.data.models

import java.io.Serializable

data class ResponseUrl(
    val requestUrl: String,
    val requestType: String,
    val requestLink: String,
    var statusCode: String = "",
    var header: String = "",
    var body: String = "",
) : Serializable {

}