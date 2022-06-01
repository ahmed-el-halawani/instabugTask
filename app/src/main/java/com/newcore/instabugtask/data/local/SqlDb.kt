package com.newcore.instabugtask.data.local

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.newcore.instabugtask.data.models.ResponseUrl

class SqlDb(context: Context) : SQLiteOpenHelper(context, "db", null, 2) {
        private val TAG = "SqlDb"
    private val table = "ResponseUrl"

    companion object {
        var sqlDb: SqlDb? = null
        val Lock = "lock";

        fun getInstance(context: Context): SqlDb {
            return sqlDb ?: synchronized(Lock) {
                return SqlDb(context).also {
                    sqlDb = it
                }
            }
        }
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("""
            CREATE TABLE IF NOT EXISTS $table (
            	requestUrl TEXT NOT NULL,
            	requestType TEXT NOT NULL,
            	requestLink TEXT NOT NULL,
            	statusCode TEXT NOT NULL,
            	header TEXT NOT NULL,
            	body TEXT NOT NULL
            )
        """.trimIndent())
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE $table")
        onCreate(p0)
    }


    fun setData(responseUrl: ResponseUrl) {
        val db = this.writableDatabase

        val contentValue = ContentValues()
        contentValue.put("requestUrl", responseUrl.requestUrl)
        contentValue.put("requestType", responseUrl.requestType)
        contentValue.put("requestLink", responseUrl.requestLink)
        contentValue.put("statusCode", responseUrl.statusCode)
        contentValue.put("header", responseUrl.header)
        contentValue.put("body", responseUrl.body)

        db.insert(table, null, contentValue).also {
            Log.e(TAG, ": $it")
        }
    }

    fun getResponsesList(): List<ResponseUrl> {
        val db = this.readableDatabase
        val coursor = db.rawQuery("select * from $table", null)
        val outList = ArrayList<ResponseUrl>()
        while (coursor.moveToNext()) {
            outList.add(
                ResponseUrl(
                    coursor.getString(0),
                    coursor.getString(1),
                    coursor.getString(2),
                    coursor.getString(3),
                    coursor.getString(4),
                    coursor.getString(5),
                )
            )
        }

        return outList
    }

}