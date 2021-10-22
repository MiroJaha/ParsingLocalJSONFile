package com.example.parsinglocaljsonfile

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class ImageShow : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.image_show)

        val imageShow= findViewById<ImageView>(R.id.imageShow)
        val exitImage= findViewById<ImageView>(R.id.exitImage)

        this.title= intent.extras?.getString("title")
        val photoLink= intent.extras?.getString("photoID")

        Glide.with(this)
            .load(photoLink)
            .into(imageShow)

        exitImage.setOnClickListener{
            finish()
        }

    }
}
