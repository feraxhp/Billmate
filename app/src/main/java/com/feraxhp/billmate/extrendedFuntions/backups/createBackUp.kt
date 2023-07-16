package com.feraxhp.billmate.extrendedFuntions.backups

import android.annotation.SuppressLint
import android.content.Context
import java.io.File

@SuppressLint("QueryPermissionsNeeded")
fun backupDatabase(context: Context): String {
    try {
//        Permissions.ReadAndWriteExternalStorage(context)


        val baseDBFile = context.getDatabasePath("billmateDB").absoluteFile.toString()
        val shmDBFile = baseDBFile.replace("billmateDB", "billmateDB-shm")
        val walDBFile = baseDBFile.replace("billmateDB", "billmateDB-shm")

        val externalDataBaseDirectory = File(context.getExternalFilesDir(null), "")
        val backupBaseDBFile = externalDataBaseDirectory.absoluteFile.toString() + "/billmateDB-backup"
        val backupShmDBFile = backupBaseDBFile.replace("billmateDB-backup", "billmateDB-backup-shm")
        val backupWalDBFile = backupBaseDBFile.replace("billmateDB-backup", "billmateDB-backup-wal")

        val sprefs = baseDBFile.replace("databases/billmateDB", "shared_prefs/User.xml")
        val backUpSprefs = backupBaseDBFile.replace("billmateDB-backup", "User-backup.xml")

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
//fun restoreBackup(context: Context): String {
//
//
//    try {
//        val backupDBFile = File(context.getExternalFilesDir(null), "User-b.xml")
//        val destinationDBFile = context.getDatabasePath("User.xml")
//
//        val backupChannel = FileInputStream(backupDBFile).channel
//        val destinationChannel = FileOutputStream(destinationDBFile).channel
//
//        destinationChannel.transferFrom(backupChannel, 0, backupChannel.size())
//
//        backupChannel.close()
//        destinationChannel.close()
//
//        return if (destinationDBFile.exists()) {
//            "restaurado"
//        } else "algo salio mal"
//    } catch (e: Exception) {
//        // Manejar cualquier excepción que pueda ocurrir durante el proceso de restauración
//        e.printStackTrace()
//        return e.message.toString()
//    }
//}
