package com.frost.perifericspractice

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Take a Picture Practice"
        setCaptureButton()
        setGalleryButton()
    }

    private fun setGalleryButton() {
        btGallery.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 120)
        }
    }

    private fun setCaptureButton() {
        btCapture.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.resolveActivity(this.packageManager)?.let {  startActivityForResult(intent, 100) }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            100 -> { imageView.setImageBitmap(data?.extras?.get("data") as Bitmap) }
            120 -> { imageView.setImageURI(data?.data) }
        }
    }
}
