package com.example.converter.activities

import android.content.ContentValues
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.converter.R
import com.example.converter.helpers.DBContarct
import com.example.converter.helpers.DBContarct.HistoryDB.TABLE_NAME
import com.example.converter.helpers.DBHelper
import com.example.converter.models.WeightModel
import kotlinx.android.synthetic.main.activity_weight.*

class Weight : AppCompatActivity() {

    var massList: ArrayList<WeightModel> = ArrayList()
    var dbHelper: DBHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weight)

        init()
    }
    private fun init(){
        dbHelper = DBHelper(this)
        // all compared to Kilogram
        massList.add(WeightModel("Tonne",1000f))
        massList.add(WeightModel("Kilogram",1f))
        massList.add(WeightModel("Gram",0.001f))
        massList.add(WeightModel("Milligram",1e-6f))
        massList.add(WeightModel("Microgram",1e-9f))
        massList.add(WeightModel("Imperial ton",1016.05f))
        massList.add(WeightModel("US ton",907.185f))
        massList.add(WeightModel("Stone",6.35029f))
        massList.add(WeightModel("Pound", 0.453592f))
        massList.add(WeightModel("Ounce",0.0283495f))

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, massList.map { it.code }.toList())
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Apply the adapter to the spinner
        weight_selector_1.adapter = adapter
        weight_selector_2.adapter = adapter


        weight_convert_button.setOnClickListener {
            if (
                weight_from_value.text.isNotEmpty()
                &&
                weight_selector_1.selectedItem.toString().isNotEmpty() && weight_selector_1.selectedItem.toString().isNotEmpty()
            ){
                convertWeight()
            }
        }
    }

    private fun convertWeight() {
        val heightForm = weight_selector_1.selectedItem.toString()
        val heightTo = weight_selector_2.selectedItem.toString()

        // ar imushavebs sworad
        val rate = getRate(heightForm,heightTo)
        val calculated = (rate*weight_from_value.text.toString().toFloat())

        weight_to_value.setText(calculated.toString())

        val db = dbHelper?.writableDatabase

        val stringToWrite = "${weight_from_value.text} $heightForm - $calculated $heightTo"
        val values = ContentValues().apply {
            put(DBContarct.HistoryDB.COLUMN_NAME_HISTORY,stringToWrite)
        }
        db?.insert(TABLE_NAME, null, values)
    }

    private fun getRate(heightForm: String, heightTo: String): Float {
        var fromValue = 0f
        var toValue = 0f
        massList.forEach {
            if (it.code == heightForm) fromValue = it.value
            if (it.code == heightTo) toValue = it.value
        }
        return fromValue/toValue
    }
}
