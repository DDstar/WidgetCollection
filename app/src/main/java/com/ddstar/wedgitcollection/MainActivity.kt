package com.ddstar.wedgitcollection

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ddstar.widgetlibs.TestClass

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun what() {
        TestClass().sayHello("fuck")
    }
}
