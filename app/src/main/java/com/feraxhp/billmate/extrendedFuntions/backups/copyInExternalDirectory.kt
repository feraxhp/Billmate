package com.feraxhp.billmate.extrendedFuntions.backups

import java.io.FileInputStream
import java.io.FileOutputStream

fun copyInExternalDirectory(origin_path: String, dest_path: String): Boolean {
    return try {
        val sourceChannel = FileInputStream(origin_path).channel
         val destinyChannel = FileOutputStream(dest_path).channel

        destinyChannel.transferFrom(sourceChannel, 0, sourceChannel.size())

        sourceChannel.close()
        destinyChannel.close()

        true
    }catch (e_exception: Exception) {
        e_exception.printStackTrace()
        throw e_exception
    }
}