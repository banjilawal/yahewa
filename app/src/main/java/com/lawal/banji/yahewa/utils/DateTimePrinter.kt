package com.lawal.banji.yahewa.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
fun Long.toDateTimeString(): String {
    return Instant.ofEpochMilli(this)
        .atZone(ZoneId.systemDefault())
        .format(DateTimeFormatter.ofPattern("EEEE, dd MMMM"))
}

fun convertUnixToDateString(unixLong: Long): String {
    // Convert from milliseconds to a readable date.
    val date = Date(unixLong)
    val format = SimpleDateFormat("yyyy-MMMM-dd") // Example: October 26, 2023
    return format.format(date)
}

