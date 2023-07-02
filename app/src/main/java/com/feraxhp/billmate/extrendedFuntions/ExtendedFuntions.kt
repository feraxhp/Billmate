package com.feraxhp.billmate.extrendedFuntions

import java.text.DecimalFormat

fun Double.toPointingString(decimals: Int = 0, default: Boolean = false, decimalDot: String = ","): String {
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

fun changeFrom24hto12h(hour: Int, minute: Int): String {
    var returnTime = "${hour.toString().padStart(2, '0')}:${minute.toString().padStart(2, '0')} AM"
    if (hour > 12) {
        returnTime =
            "${(hour - 12).toString().padStart(2, '0')}:${minute.toString().padStart(2, '0')} PM"
    }
    if (hour == 12) {
        returnTime = "${hour.toString().padStart(2, '0')}:${minute.toString().padStart(2, '0')} M"
    }
    if (hour == 0) {
        returnTime =
            "${(12).toString().padStart(2, '0')}:${minute.toString().padStart(2, '0')} AM"
    }
    return returnTime
}