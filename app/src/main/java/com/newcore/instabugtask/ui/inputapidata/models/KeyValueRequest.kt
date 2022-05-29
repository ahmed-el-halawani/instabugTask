package com.newcore.instabugtask.ui.inputapidata.models

import java.util.*

data class KeyValueRequest(
    var key: String, var value: String,
    val id: String = UUID.randomUUID().toString(),
)