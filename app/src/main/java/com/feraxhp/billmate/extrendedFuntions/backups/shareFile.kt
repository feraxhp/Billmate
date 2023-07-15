package com.feraxhp.billmate.extrendedFuntions.backups

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import java.io.File

@SuppressLint("QueryPermissionsNeeded")
fun shareFile(context: Context, file: File) {
    val fileUri = FileProvider.getUriForFile(context, context.packageName + ".fileprovider", file)

    val shareIntent = Intent(Intent.ACTION_SEND)
    shareIntent.type = "application/zip"
    shareIntent.putExtra(Intent.EXTRA_STREAM, fileUri)
    shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

    val chooserIntent = Intent.createChooser(shareIntent, "Share backup files")

    if (shareIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(chooserIntent)
    }
}