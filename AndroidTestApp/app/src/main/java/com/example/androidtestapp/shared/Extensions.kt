package com.example.androidtestapp.shared

import org.json.JSONException
import org.json.JSONObject

/**
 * Created by akiyama on 2018/02/26.
 */
fun String.toJsonObject(): JSONObject? {
    val json = try {
        JSONObject(this)
    } catch (e: JSONException) {
        println(e.localizedMessage)
        return null
    }
    return json
}