package com.feraxhp.billmate.extrendedFuntions

import android.icu.util.Calendar
import android.icu.util.TimeZone
import java.text.DecimalFormat
import java.time.ZoneId
import java.time.ZonedDateTime

fun Double.toMoneyFormat(decimals: Int = 0, default: Boolean = false, decimalDot: String = ","): String {
    val intNumber = this.toInt()
    val numberOfDecimals = (this - intNumber).toString().split(".")[1].length
    val numberOfRepeats = if (default) numberOfDecimals.coerceAtMost(4) else decimals
    val formatter = "#,###" + if (numberOfRepeats > 0) "." + "#".repeat(numberOfRepeats) else ""
    return if (decimalDot == ".") DecimalFormat(formatter).format(this)
    else DecimalFormat(formatter)
        .format(this)
        .replace(".","a")
        .replace(",",".")
        .replace("a",",")
}
fun String.noDescrition(): String {
    return if (this == "") "No description" else this
}

fun changeFrom24hto12h(hour: Int, minute: Int): String {
    var string_hour = hour.toString().padStart(2, '0')
    val string_minute = minute.toString().padStart(2, '0')

    val returnTime = when (hour) {
        12 -> "$string_hour:$string_minute M"
        in 13..23 -> {
            string_hour = (hour - 12).toString().padStart(2, '0')
            "$string_hour:$string_minute PM"
        }
        0 -> "12:$string_minute AM"
        else -> "$string_hour:$string_minute AM"
    }

    return returnTime
}

fun Long.dateFormat(): String {
    val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC${ZonedDateTime.now(ZoneId.systemDefault()).offset}"))
    calendar.timeInMillis = this

    val days = calendar[Calendar.DAY_OF_MONTH].toString().padStart(2, '0')
    val months = (calendar[Calendar.MONTH] + 1).toString().padStart(2, '0')
    val years = calendar[Calendar.YEAR].toString().padStart(4, '0')

    return "${days}-${months}-${years}"
}

fun String.timeFormat(is24Hour: Boolean = true): String {
    return if (is24Hour) {
        this.split(":")[0].padStart(2, '0') +
                ":" +
                this.split(":")[1].padStart(2, '0')

    } else changeFrom24hto12h(this.split(":")[0].toInt(), this.split(":")[1].substring(0,2).toInt())
}