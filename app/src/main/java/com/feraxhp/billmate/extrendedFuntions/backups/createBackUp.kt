package com.feraxhp.billmate.extrendedFuntions.backups

import android.annotation.SuppressLint
import android.content.Context
import com.feraxhp.billmate.extrendedFuntions.zipUnzipFuntions.compressDirectoryToZip
import java.io.File

@SuppressLint("QueryPermissionsNeeded")
fun backupDatabase(context: Context): String {
    try {
//        Permissions.ReadAndWriteExternalStorage(context)


        val baseDBFile = context.getDatabasePath("billmateDB").absoluteFile.toString()
        val shmDBFile = baseDBFile.replace("billmateDB", "billmateDB-shm")
        val walDBFile = baseDBFile.replace("billmateDB", "billmateDB-shm")

        val externalDataBaseDirectory = File(context.getExternalFilesDir(null), "")
        val backupBaseDBFile = externalDataBaseDirectory.absoluteFile.toString() + "/billmateDB"
        val backupShmDBFile = backupBaseDBFile.replace("billmateDB", "billmateDB-shm")
        val backupWalDBFile = backupBaseDBFile.replace("billmateDB", "billmateDB-wal")

        val sprefs = baseDBFile.replace("databases/billmateDB", "shared_prefs/User.xml")
        val backUpSprefs = backupBaseDBFile.replace("billmateDB-backup", "User.xml")

        copyInExternalDirectory(baseDBFile, backupBaseDBFile)
        copyInExternalDirectory(shmDBFile, backupShmDBFile)
        copyInExternalDirectory(walDBFile, backupWalDBFile)
        copyInExternalDirectory(sprefs, backUpSprefs)

        val zipName = "billmateBackup.zip"

        compressDirectoryToZip(context, externalDataBaseDirectory, externalDataBaseDirectory, zipName)

        shareFile(context, File(context.getExternalFilesDir(null), zipName))

        return "All backups have been created"

    } catch (e: Exception) {

        return "error: " + e.message.toString()
    }
}
