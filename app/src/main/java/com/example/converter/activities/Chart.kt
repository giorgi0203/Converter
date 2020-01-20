package com.example.converter.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.converter.R
import com.example.converter.models.ChartModel
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.activity_charts.*

class Chart : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_charts)

        init()
    }

    private fun init(){
        var dataObjects: ArrayList<ChartModel> = ArrayList()
        dataObjects.add(ChartModel(2.86f,1f))
        dataObjects.add(ChartModel(2.84f,2f))
        dataObjects.add(ChartModel(2.87f,3f))
        dataObjects.add(ChartModel(2.87f,4f))
        dataObjects.add(ChartModel(2.88f,5f))
        dataObjects.add(ChartModel(2.22f,6f))
        dataObjects.add(ChartModel(2.24f,7f))
        dataObjects.add(ChartModel(2.6f,1f))
        dataObjects.add(ChartModel(2.68f,2f))
        dataObjects.add(ChartModel(2.86f,3f))
        dataObjects.add(ChartModel(2.86f,4f))
        dataObjects.add(ChartModel(2.86f,5f))
        var entries: ArrayList<Entry>  = ArrayList()
        dataObjects.forEach {
            entries.add(Entry(it.x, it.y))
        }
        var dataSet = LineDataSet(entries, "Label")

        var lineData = LineData(dataSet)
        chart.data = lineData
        chart.invalidate()
    }
}
