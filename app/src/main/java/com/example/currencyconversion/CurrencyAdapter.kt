package com.example.currencyconversion

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class CurrencyAdapter(private val context: Context, private val currencies: List<String>) : BaseAdapter() {

    override fun getCount(): Int {
        return currencies.size
    }

    override fun getItem(position: Int): Any {
        return currencies[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_item, parent, false)
        val textView: TextView = view.findViewById(android.R.id.text1)
        textView.text = currencies[position]
        return view
    }

//    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
//        val view: View = convertView ?: LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false)
//        val textView: TextView = view.findViewById(android.R.id.text1)
//        textView.text = currencies[position]
//        return view
//    }
}