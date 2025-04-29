package com.lawal.banji.yahewa.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId


@RequiresApi(Build.VERSION_CODES.O)
fun convertLongToLocalDateTime(timestamp: Long): LocalDateTime {
    return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault())
}
