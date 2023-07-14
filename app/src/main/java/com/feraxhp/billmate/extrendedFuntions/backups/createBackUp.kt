package com.feraxhp.billmate.extrendedFuntions.backups

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Environment
import android.app.Activity
import android.content.Intent
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

@SuppressLint("QueryPermissionsNeeded")
fun backupDatabase(context: Context): String {
    try {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(context as Activity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE), 0)
        }

        val sourceDBFile = context.getDatabasePath("User.xml")
        val backupDBFile = File(context.getExternalFilesDir(null), "User-b.xml")

        val sourceChannel = FileInputStream(sourceDBFile).channel
        val backupChannel = FileOutputStream(backupDBFile).channel

        backupChannel.transferFrom(sourceChannel, 0, sourceChannel.size())

        sourceChannel.close()
        backupChannel.close()

        val backupDBUri = FileProvider.getUriForFile(context, context.packageName + ".fileprovider", backupDBFile)

        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "application/octet-stream"
        shareIntent.putExtra(Intent.EXTRA_STREAM, backupDBUri)
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

        val chooserIntent = Intent.createChooser(shareIntent, "Compartir archivo de respaldo")

        if (shareIntent.resolveActivity(context.packageManager) != null) {
            context.startActivity(chooserIntent)
        }

        return ""
        // Mostrar mensaje o realizar otras acciones después de la copia de seguridad exitosa
    } catch (e: Exception) {
        // Manejar cualquier excepción que pueda ocurrir durante la copia de seguridad
        return e.message.toString()
    }
}
fun restoreBackup(context: Context): String {


    try {
        val backupDBFile = File(context.getExternalFilesDir(null), "User-b.xml")
        val destinationDBFile = context.getDatabasePath("User.xml")

        val backupChannel = FileInputStream(backupDBFile).channel
        val destinationChannel = FileOutputStream(destinationDBFile).channel

        destinationChannel.transferFrom(backupChannel, 0, backupChannel.size())

        backupChannel.close()
        destinationChannel.close()

        return if (destinationDBFile.exists()) {
            "restaurado"
        } else "algo salio mal"
    } catch (e: Exception) {
        // Manejar cualquier excepción que pueda ocurrir durante el proceso de restauración
        e.printStackTrace()
        return e.message.toString()
    }
}
