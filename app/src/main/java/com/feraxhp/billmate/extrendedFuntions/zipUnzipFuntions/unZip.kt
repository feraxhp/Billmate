package com.feraxhp.billmate.extrendedFuntions.zipUnzipFuntions

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.zip.ZipInputStream

fun unzipFile(zipFilePath: String, outputFolderPath: String) {
    try {
        val buffer = ByteArray(1024)
        val zipInputStream = ZipInputStream(FileInputStream(zipFilePath))

        var zipEntry = zipInputStream.nextEntry
        while (zipEntry != null) {
            val newFile = File(outputFolderPath, zipEntry.name)

            if (zipEntry.isDirectory) {
                newFile.mkdirs()
            } else {
                val fileOutputStream = FileOutputStream(newFile)

                var len: Int
                while (zipInputStream.read(buffer).also { len = it } > 0) {
                    fileOutputStream.write(buffer, 0, len)
                }

                fileOutputStream.close()
            }

            zipEntry = zipInputStream.nextEntry
        }

        zipInputStream.closeEntry()
        zipInputStream.close()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}