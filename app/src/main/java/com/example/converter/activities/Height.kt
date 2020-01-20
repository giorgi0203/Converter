package com.example.converter.activities

import android.content.ContentValues
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.converter.R
import com.example.converter.helpers.DBContarct
import com.example.converter.helpers.DBContarct.HistoryDB.TABLE_NAME
import com.example.converter.helpers.DBHelper
import com.example.converter.models.CurrencyItem
import com.example.converter.models.HeightModel
import com.example.converter.models.WeightModel
import kotlinx.android.synthetic.main.activity_height.*

class Height : AppCompatActivity() {

    var heightList: ArrayList<HeightModel> = ArrayList()

    var dbHelper: DBHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_height)



        init()

    }

    private fun init() {
        dbHelper = DBHelper(this)
        // all compared to meters
        heightList.add(HeightModel("Kilometre",1000f))
        heightList.add(HeightModel("Metre",1f))
        heightList.add(HeightModel("Centimetre",0.01f))
        heightList.add(HeightModel("Millimetre",0.001f))
        heightList.add(HeightModel("Micrometre",1e-6f))
        heightList.add(HeightModel("Nanometre",1e-9f))
        heightList.add(HeightModel("Mile",1609.34f))
        heightList.add(HeightModel("Yard",0.9144f))
        heightList.add(HeightModel("Foot", 0.3048f))
        heightList.add(HeightModel("Inch",0.0254f))
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, heightList.map { it.code }.toList())
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Apply the adapter to the spinner
        height_selector_1.adapter = adapter
        height_selector_2.adapter = adapter


        height_convert_button.setOnClickListener {
            if (
                height_from_value.text.isNotEmpty()
                &&
                height_selector_1.selectedItem.toString().isNotEmpty() && height_selector_1.selectedItem.toString().isNotEmpty()
            ){
                convertHeight()
            }
        }
    }

    private fun convertHeight() {
        val heightForm = height_selector_1.selectedItem.toString()
        val heightTo = height_selector_2.selectedItem.toString()

        // ar imushavebs sworad
        val rate = getRate(heightForm,heightTo)
        val calculated = (rate*height_from_value.text.toString().toFloat())

        height_to_value.setText(calculated.toString())

        val db = dbHelper?.writableDatabase

        val stringToWrite = "${height_from_value.text} $heightForm - $calculated $heightTo"
        val values = ContentValues().apply {
            put(DBContarct.HistoryDB.COLUMN_NAME_HISTORY,stringToWrite)
        }
        db?.insert(TABLE_NAME, null, values)
    }

    private fun getRate(heightForm: String, heightTo: String): Float {
        var fromValue = 0f
        var toValue = 0f
        heightList.forEach {
            if (it.code == heightForm) fromValue = it.value
            if (it.code == heightTo) toValue = it.value
        }
        return fromValue/toValue
    }


}
