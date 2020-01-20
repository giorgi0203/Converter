package com.example.converter.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.support.v7.widget.LinearLayoutManager
import com.example.converter.R
import com.example.converter.adapters.HistoryAdapter
import com.example.converter.helpers.DBContarct.HistoryDB.COLUMN_NAME_HISTORY
import com.example.converter.helpers.DBContarct.HistoryDB.TABLE_NAME
import com.example.converter.helpers.DBHelper
import com.example.converter.models.HistoryModel
import kotlinx.android.synthetic.main.activity_history.*

class History : AppCompatActivity() {
    var adapter: HistoryAdapter? = null
    var historyItems: ArrayList<HistoryModel> = ArrayList()

    val dbHelper = DBHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        init()
    }

    private fun init (){
        history_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        //:D
        adapter = HistoryAdapter(this,historyItems)

        history_list.adapter = adapter

        readDB()

        history_clear.setOnClickListener {
            val db = dbHelper.writableDatabase
            db.delete(TABLE_NAME, null, null)
            adapter!!.notifyDataSetChanged()
        }
    }

    private fun readDB() {
        val db = dbHelper.readableDatabase

        val projection = arrayOf(BaseColumns._ID, COLUMN_NAME_HISTORY)


        val sortOrder = "${COLUMN_NAME_HISTORY} ASC"

        val cursor = db.query(
            TABLE_NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            null,              // The columns for the WHERE clause
            null,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            sortOrder               // The sort order
        )
        with(cursor) {
            while (moveToNext()) {
                val text = getString(getColumnIndexOrThrow(COLUMN_NAME_HISTORY))
                val id = getLong(getColumnIndexOrThrow(BaseColumns._ID)).toString()
                val item = HistoryModel(text,id)
                historyItems.add(item)
                adapter!!.notifyDataSetChanged()
            }
        }
    }
}
