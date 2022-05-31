package com.newcore.instabugtask.data.models


data class RequestUrl(
    var url: String, val isGetType: Boolean, val header: List<KeyValueRequest>,
    val params: List<KeyValueRequest>,
    val body: String,
)