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