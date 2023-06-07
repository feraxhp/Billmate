package com.feraxhp.billmate.extrendedFuntions

fun Double.toPointingString(decimals: Int = 0, rounded: Boolean = false, default: Boolean = false, decimalDot: List<String> = listOf(".", ",")) : String {
    var isDefault = default
    var resultString = if (rounded) {
        String.format("%.${decimals}f", this@toPointingString)
    }else{
        this@toPointingString.toString()
    }
    val split = resultString.split(".")
    var decimalString = split[1]
    resultString = split[0].reversed()

    if (decimalString.length < decimals && !rounded) {
        decimalString += "0".repeat(decimals - decimalString.length)
    }

    for(i in resultString.indices) {
        if (i%3 == 0 && i != 0) {
            resultString = resultString.substring(0,i) + decimalDot[0] + resultString.substring(i)
        }
    }
    if (decimalString.length == 1 && decimalString == "0") isDefault = false

    return if (decimals != 0 || isDefault) resultString.reversed() + decimalDot[1] + decimalString else resultString.reversed()
}
