package com.feraxhp.billmate.extrendedFuntions.backups

import android.content.Context
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream


fun compressDirectoryToZip(sourceDir: File, baseDir: File, zipOutputStream: ZipOutputStream, backupZipFile: File) {
    val fileList = sourceDir.listFiles() ?: return

    for (file in fileList) {
        if (file == backupZipFile) continue
        if (file.isDirectory) {
            compressDirectoryToZip(file, baseDir, zipOutputStream, backupZipFile) // Llamada recursiva con los mismos parámetros
        } else {
            val relativePath = file.absolutePath.substring(baseDir.absolutePath.length + 1)

            val zipEntry = ZipEntry(relativePath)
            zipOutputStream.putNextEntry(zipEntry)

            val inputStream = FileInputStream(file)
            inputStream.copyTo(zipOutputStream, DEFAULT_BUFFER_SIZE)

            zipOutputStream.closeEntry()
            inputStream.close()
        }
    }
    zipOutputStream.close()
}

fun compressDirectoryToZip(context: Context,sourceDir: File, baseDir: File, zipName: String) {
    val backupZipFile = File(context.getExternalFilesDir(null), zipName)
    val zipOutputStream = ZipOutputStream(BufferedOutputStream(FileOutputStream(backupZipFile)))

    compressDirectoryToZip(sourceDir, baseDir, zipOutputStream, backupZipFile)

}