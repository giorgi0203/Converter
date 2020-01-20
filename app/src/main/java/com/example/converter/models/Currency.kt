package com.example.converter.models


class Currencies {
    var currency: ArrayList<CurrencyItem> = ArrayList()
}

class CurrencyItem {
    var id = ""
    var cur_code = ""
    var cur_name = ""
    var cur_value = ""
    var cur_change = ""
    var cur_image = ""
}