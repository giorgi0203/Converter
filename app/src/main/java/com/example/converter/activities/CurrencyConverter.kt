package com.example.converter.activities

import android.content.ContentValues
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.converter.R
import com.example.converter.helpers.DBContarct
import com.example.converter.helpers.DBContarct.HistoryDB.TABLE_NAME
import com.example.converter.helpers.DBHelper
import com.example.converter.helpers.DataLoader
import com.example.converter.models.Currencies
import com.example.converter.models.CurrencyItem
import com.google.gson.Gson
import com.koushikdutta.async.future.FutureCallback
import kotlinx.android.synthetic.main.activity_currency_converter.*

class CurrencyConverter : AppCompatActivity() {

    var currencyList: ArrayList<CurrencyItem> = ArrayList()
    var dbHelper: DBHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency_converter)

        init()
    }

    private fun init() {
        // Gets the data repository in write mode
        dbHelper = DBHelper(this)
        DataLoader.getData(
            applicationContext,
            null,
            FutureCallback { e, result ->
                val data = Gson().fromJson(result, Currencies::class.java)
                val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item,data.currency.map { it.cur_code }.toList())
                currencyList = data.currency
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                currency_selector_1.adapter = adapter
                currency_selector_2.adapter = adapter
            })

        convert_button.setOnClickListener {
            if (
                currency_from_value.text.isNotEmpty()
                &&
                currency_selector_1.selectedItem.toString().isNotEmpty() && currency_selector_2.selectedItem.toString().isNotEmpty()
            ){
                convertCurrency()
            }
        }
    }

    private fun convertCurrency() {
        val currencyForm = currency_selector_1.selectedItem.toString()
        val currencyTo = currency_selector_2.selectedItem.toString()

        // ar imushavebs sworad
        val rate = getRate(currencyForm,currencyTo)
        val calculated = (rate*currency_from_value.text.toString().toFloat())

        currency_to_value.setText(calculated.toString())

        val db = dbHelper?.writableDatabase

        val stringToWrite = "${currency_from_value.text} $currencyForm - ${calculated} $currencyTo"
        val values = ContentValues().apply {
            put(DBContarct.HistoryDB.COLUMN_NAME_HISTORY,stringToWrite)
        }

// Insert the new row, returning the primary key value of the new row
        val newRowId = db?.insert(TABLE_NAME, null, values)
    }

    private fun getRate(currencyForm: String, currencyTo: String): Float {
        var rate = 0f
        currencyList.forEach {
            if (it.cur_code == currencyTo) rate = it.cur_value.toFloat()
        }
        return rate
    }
}
