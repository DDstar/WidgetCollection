package com.ddstar.wedgitcollection

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ddstar.widgetlibs.TestClass
import com.ddstar.widgetlibs.photoselect.PhotoSelector

class MainActivity : AppCompatActivity() {
    lateinit var photoSelector: PhotoSelector
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        photoSelector = findViewById(R.id.photo_selector)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        photoSelector.onActivityResult(requestCode, resultCode, data)
    }

    fun what() {
        TestClass().sayHello("fuck")
        val selectedPhotoList = photoSelector.mAdapter.getSelectedPhotoList()
    }
}
