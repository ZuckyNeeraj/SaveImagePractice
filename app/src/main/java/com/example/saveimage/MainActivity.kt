package com.example.saveimage

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.saveimage.databinding.ActivityMainBinding
import java.io.File
import java.io.FileOutputStream

/**
 * This app is to learn to store image in the local storage.
 */

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageView = binding.imageView2
        val buttonSave = binding.saveBtn

        buttonSave.setOnClickListener {
            val drawable = imageView.drawable as BitmapDrawable
            val bitmap = drawable.bitmap

            // Check if external storage is available and writable
            if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
                // Save the bitmap to external storage
                val file = File(getExternalFilesDir(null), "image.png")
                val fos = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
                fos.flush()
                fos.close()

                Toast.makeText(this, "Image saved successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "External storage not available", Toast.LENGTH_SHORT).show()
            }
        }
    }
}