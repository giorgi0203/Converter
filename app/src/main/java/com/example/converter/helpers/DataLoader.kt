package com.example.converter.helpers

import android.content.Context
import com.google.gson.JsonObject
import com.koushikdutta.async.future.FutureCallback
import com.koushikdutta.ion.Ion

class DataLoader {

    companion object {
        private const val endpoint = "https://any.ge/currency/api.php?info=yvela"
        fun getData(context: Context?, jsonObject: JsonObject?, callback: FutureCallback<JsonObject>) {
            Ion.with(context).load("GET", endpoint)
//                .addHeader("Content-Type","application/json")
                .asJsonObject()
                .setCallback(callback)
        }
    }
}