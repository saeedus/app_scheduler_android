package com.meldcx.appscheduler.util

import java.text.SimpleDateFormat
import java.util.Locale

object TimeUtil {
    fun parseTime(timeInMillis: Long): String {
        val formatter = SimpleDateFormat("hh:mm a", Locale.getDefault())
        return formatter.format(timeInMillis)
    }
}