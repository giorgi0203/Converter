package com.example.converter

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.converter.activities.Currency
import com.example.converter.activities.Height
import com.example.converter.activities.History
import com.example.converter.activities.Weight
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        goToCurrency.setOnClickListener {
            val intent = Intent(this, Currency::class.java)
            startActivity(intent)
        }
        goToHeightConverter.setOnClickListener {
            val intent = Intent(this, Height::class.java)
            startActivity(intent)
        }
        goToWeightConverter.setOnClickListener {
            val intent = Intent(this, Weight::class.java)
            startActivity(intent)
        }
        goToHistory.setOnClickListener {
            val intent = Intent(this, History::class.java)
            startActivity(intent)
        }
    }
}
