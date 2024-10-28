package com.example.currencyconversion

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var sourceAmount: EditText
    private lateinit var targetAmount: EditText
    private lateinit var sourceCurrency: Spinner
    private lateinit var targetCurrency: Spinner
    private lateinit var sourceInput: String
    private lateinit var targetInput: String

    private val Currencies = mapOf(
        "USD" to 1.0,
        "EUR" to 0.85,
        "JPY" to 110.0,
        "VND" to 23000.0
    )

    private var isUpdating = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sourceAmount = findViewById(R.id.sourceAmount)
        targetAmount = findViewById(R.id.targetAmount)
        sourceCurrency = findViewById(R.id.sourceCurrency)
        targetCurrency = findViewById(R.id.targetCurrency)

        val currencies = listOf("USD", "EUR", "JPY", "VND")
        val adapter = CurrencyAdapter(this, currencies)
        sourceCurrency.adapter = adapter
        targetCurrency.adapter = adapter

        fun convert(amount: Double, fromCurrency: String, toCurrency: String): String {
            val sourceRate = Currencies[fromCurrency] ?: 1.0
            val targetRate = Currencies[toCurrency] ?: 1.0
            val result = ((amount / sourceRate) * targetRate)
            val output = "%.2f".format(result)1
            return output
        }

        sourceAmount.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (isUpdating) return
                isUpdating = true
                val value = sourceAmount.text.toString().toDoubleOrNull()
                if (value != null) {
                    val convertedValue = convert(value, sourceInput, targetInput)
                    targetAmount.setText(convertedValue)
                }
                isUpdating = false
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        targetAmount.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (isUpdating) return
                isUpdating = true
                val value = targetAmount.text.toString().toDoubleOrNull()
                if (value != null) {
                    val convertedValue = convert(value, targetInput, sourceInput)
                    sourceAmount.setText(convertedValue)
                }
                isUpdating = false
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        sourceCurrency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>, p1: View?, p2: Int, p3: Long) {
                sourceInput = p0.getItemAtPosition(p2).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        targetCurrency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>, p1: View?, p2: Int, p3: Long) {
                targetInput = p0.getItemAtPosition(p2).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }
}
