package com.feraxhp.billmate.extrendedFuntions.backups

import android.annotation.SuppressLint
import android.content.Context
import java.io.File

@SuppressLint("QueryPermissionsNeeded")
fun restoreBackup(context: Context) {
    val destinationPath = context.getDatabasePath("billmateDB").absolutePath.replace("/billmateDB", "")
    val sprefsDestination = destinationPath.replace("databases", "shared_prefs")

    val originBasePath = File(context.getExternalFilesDir(null), "").absolutePath

    copyInExternalDirectory("$originBasePath/billmateDB", "$destinationPath/billmateDB")
    copyInExternalDirectory("$originBasePath/billmateDB-shm", "$destinationPath/billmateDB-shm")
    copyInExternalDirectory("$originBasePath/billmateDB-wal", "$destinationPath/billmateDB-wal")
    copyInExternalDirectory("$originBasePath/User.xml", "$sprefsDestination/User.xml")

    File(context.getExternalFilesDir(null), "").deleteRecursively()
}