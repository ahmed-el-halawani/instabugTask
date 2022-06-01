package com.newcore.instabugtask.utils

import com.newcore.instabugtask.data.models.RequestUrl

object Utils {

    fun formatResponseString(header: String, body: String) = """header: $header
    *********************************************
    body: $body
    """.trim()

    fun formatResponseString(requestUrl: RequestUrl) = """header: ${requestUrl.header}
    *********************************************
    body: ${requestUrl.body}
    """.trim()


}