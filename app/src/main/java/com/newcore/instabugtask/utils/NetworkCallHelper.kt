package com.newcore.instabugtask.utils

import android.net.Uri
import android.os.AsyncTask
import com.newcore.instabugtask.data.models.RequestUrl
import com.newcore.instabugtask.data.models.ResponseUrl
import com.newcore.instabugtask.utils.Utils.formatResponseString
import java.io.*
import java.net.HttpURLConnection
import java.net.URL


object NetworkCallHelper {
    private const val TAG = "NetworkCallHelper"

    internal class RequestTask(val onDone: (ResponseUrl) -> Unit, val onError: () -> Unit) :
        AsyncTask<RequestUrl, ResponseUrl, ResponseUrl>() {

        private fun readStream(inputStream: InputStream): String? {
            return try {
                val bo = ByteArrayOutputStream()
                var i = inputStream.read()
                while (i != -1) {
                    bo.write(i)
                    i = inputStream.read()
                }
                bo.toString()
            } catch (e: IOException) {
                ""
            }
        }

        override fun doInBackground(vararg requestUrl: RequestUrl): ResponseUrl {
            val responseString = ResponseUrl(
                formatResponseString(requestUrl[0]),
                if (requestUrl[0].isGetType) "GET" else "POST",
                requestUrl[0].url
            )
            try {

                val builder = Uri.Builder().path(requestUrl[0].url)

                requestUrl[0].params.forEach { builder.appendQueryParameter(it.key, it.value) }
                requestUrl[0].url = builder.build().path.toString()

                val url = URL(requestUrl[0].url)

                val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection

                urlConnection.requestMethod = if (requestUrl[0].isGetType) "GET" else "POST"

                urlConnection.setRequestProperty("Content-Type", "application/json")
                urlConnection.setRequestProperty("Accept", "application/json")
                requestUrl[0].header.forEach {
                    urlConnection.setRequestProperty(it.key, it.value)
                }

                if (!requestUrl[0].isGetType) {
                    urlConnection.doOutput = true
                    val os: OutputStream = urlConnection.outputStream
                    val osw = OutputStreamWriter(os, "UTF-8")
                    osw.write(requestUrl[0].body)
                    osw.flush()
                    osw.close()
                    os.close()
                }

                responseString.statusCode = urlConnection.responseCode.toString()
                responseString.header = urlConnection.headerFields.toString()
                try {
                    val inputStream: InputStream = BufferedInputStream(urlConnection.inputStream)
                    responseString.body = readStream(inputStream) ?: ""
                } finally {
                    urlConnection.disconnect()
                }
            } catch (e: IOException) {
                responseString.body = "" + e.localizedMessage
            } catch (e: Throwable) {
                responseString.body = "" + e.localizedMessage
            }
            return responseString
        }

        override fun onPostExecute(result: ResponseUrl) {
            super.onPostExecute(result)
            //Do anything with response..
            onDone(result)
        }


    }
}
