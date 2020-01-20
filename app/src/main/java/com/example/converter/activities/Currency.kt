package com.example.converter.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.converter.R
import kotlinx.android.synthetic.main.activity_currency.*

class Currency : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency)

        init()
    }

    private fun init(){
        goToCurrencyConverter.setOnClickListener {
            val intent = Intent(this, CurrencyConverter::class.java)
            startActivity(intent)
        }
        goToChart.setOnClickListener {
            val intent = Intent(this, Chart::class.java)
            startActivity(intent)
        }
    }
}
