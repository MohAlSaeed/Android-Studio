package com.example.hello

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.hello.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //1- val binding = ActivityMainBinding.inflate(layoutInflater)
        //2- setContentView(binding.root)
        //3- you need to commit the below line to activate the above one
        setContentView(R.layout.activity_main)
        //first lecture plying/Changing text in your application

        //the first way --> provide type and null safety
        //findViewById method
        val tv = findViewById<TextView>(R.id.tv1)
        //tv.setText("Welcome Android Studio!") --> we do not have to hard code so we will put thin in the String resources as in below step.
        tv.text = getString(R.string.welcome)
        tv.textSize = 40F

        //the second way
        //Kotlin extension synthetic -->(Artificial) properties (you have to ask about the binding way )
        //instead of finding by ID we can access via identifiers so we need to update the Module build.gradle in (Gradle Scripts folder)
        //so we add 'kotlin-android-extensions' in the --> plugins section
        tv1.text = getString(R.string.welcome)
        tv1.textSize = 60F

        //the third way --> provide type and null safety
        //View Binding method --> we need to add in android section in Module build.gradle in (Gradle Scripts folder) a sub section which is
        //buildFeatures ,,, now we will change and add this below steps from (1-) which is now // so to make it active modify and test it .
        //4- binding.tv1.text = getString(R.string.welcome)
        //5- binding.tv1.textSize = 15F
    }
}