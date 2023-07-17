package com.feraxhp.billmate.activitys

import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.feraxhp.billmate.activitys.MainActivity.Companion.viewController
import com.feraxhp.billmate.extrendedFuntions.backups.restoreBackup
import com.feraxhp.billmate.extrendedFuntions.zipUnzipFuntions.unzipFile
import com.feraxhp.billmate.ui.theme.BillmateTheme
import java.io.File

class getFilesActivity : ComponentActivity() {

//    private val requestPermissionLauncher =
//        registerForActivityResult(
//            ActivityResultContracts.RequestPermission()
//        ) { isGranted: Boolean ->
//            viewController.hasPermisionToStorage = isGranted
//            Toast.makeText(this, "Permission Granted: $isGranted", Toast.LENGTH_LONG).show()
//        }

    private val pickFileLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            val contentResolver = applicationContext.contentResolver
            val inputStream = contentResolver.openInputStream(it)
            val displayName = getFileNameFromUri(it)

            if (displayName != null) {
                val destinationFile = File(getExternalFilesDir(null), displayName)
                inputStream?.use { input ->
                    destinationFile.outputStream().use { output ->
                        input.copyTo(output)
                    }
                }
                val zipFilePath = destinationFile.absolutePath
                val outputFolderPath = zipFilePath.replace("/$displayName", "")

                unzipFile(zipFilePath, outputFolderPath)

                this.deleteSharedPreferences("User")
                restoreBackup(this)

                val message = "BackUp restored!!!!"
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

                viewController.finishActivityWithActualize(this)
            } else {
                Toast.makeText(this, "Unable to get file", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getFileNameFromUri(uri: Uri): String? {
        val cursor: Cursor? = contentResolver.query(uri, null, null, null, null)
        cursor?.use {
            val nameIndex = it.getColumnIndex(MediaStore.MediaColumns.DISPLAY_NAME)
            it.moveToFirst()
            return it.getString(nameIndex)
        }
        return null
    }

    @Composable
    fun PickFile() {
        pickFileLauncher.launch("application/zip")

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent(){

            BillmateTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PickFile()
                }
            }
        }

        WindowCompat.setDecorFitsSystemWindows(window, false)
    }




}