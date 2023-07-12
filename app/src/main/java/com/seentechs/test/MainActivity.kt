package com.seentechs.test

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.slider.LabelFormatter
import com.google.android.material.slider.Slider
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn = findViewById<Button>(R.id.checkPermission)
        checkPermission()
        val recorder = Recorder(applicationContext)
        btn.setOnClickListener {
            File(cacheDir, "test.mp3").also {

               /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    recorder.start(it)
                } // recording the audio from mic and saving it in the file

                // Visual indication to the user that audio is being recorded
                Toast.makeText(applicationContext, "Recording Has Begun", Toast.LENGTH_SHORT)
                    .show()
                //Toast.makeText(applicationContext, audioRelPath, Toast.LENGTH_LONG).show()*/

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
                    MediaRecorder(this).apply{
                        setAudioSource(MediaRecorder.AudioSource.MIC) // what records the audio
                        setOutputFormat(MediaRecorder.OutputFormat.MPEG_4) // apparently MPEG_4 = MP3
                        setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
                        setOutputFile(FileOutputStream( File(cacheDir, "test.mp3")).fd) // where the file path will be stored
                        prepare() // preparing to record
                        start() // starts the recording
                        Handler(Looper.getMainLooper()).postDelayed({
                            try {
                                stop()
                            } catch (e: Exception) {
                                Log.d("TAG", "Mic is Disable")
                            }
                        },100)
                    }
                }
            }
        }


    }

    val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Toast.makeText(applicationContext, "All granted", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(applicationContext, "Not granted", Toast.LENGTH_SHORT).show()
            }
        }
    fun checkPermission() {
        // Check if the permission is already granted
            // The permission is not granted, so request it
        when {
            ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.RECORD_AUDIO
            ) == PackageManager.PERMISSION_GRANTED -> {
                // You can use the API that requires the permission.
                Toast.makeText(applicationContext, "All granted", Toast.LENGTH_SHORT).show()
            }
            else -> {
                // You can directly ask for the permission.
                // The registered ActivityResultCallback gets the result of this request.
                requestPermissionLauncher.launch(
                    Manifest.permission.RECORD_AUDIO)
            }
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        // Check if the permission request was granted
        if (requestCode == 101) {
            val permissionGranted = grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED

            if (permissionGranted) {
                // The permission was granted, so do the action
                Toast.makeText(applicationContext, "All granted", Toast.LENGTH_SHORT).show()
            } else {
                // The permission was denied, so show a message
                Toast.makeText(applicationContext, "All Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun createRecorder(): MediaRecorder {
        return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
            MediaRecorder(this)
        } else MediaRecorder()
    }

}