package com.example.converter.helpers

import android.provider.BaseColumns

object DBContarct {
    // Table contents are grouped together in an anonymous object.
    object HistoryDB : BaseColumns {
        const val TABLE_NAME = "historyDB"
        const val COLUMN_NAME_HISTORY = "history_text"
    }
}