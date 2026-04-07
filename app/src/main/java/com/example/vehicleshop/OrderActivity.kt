package com.example.vehicleshop

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class OrderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)


        val vehicleName   = intent.getStringExtra("VEHICLE_NAME") ?: "Unknown Vehicle"
        val originalPrice = intent.getDoubleExtra("VEHICLE_PRICE", 0.0)
        val vehicleImage  = intent.getIntExtra("VEHICLE_IMAGE", -1)

        val discountAmount     = originalPrice * 0.05
        val priceAfterDiscount = originalPrice - discountAmount


        val ivVehicleImage  = findViewById<ImageView>(R.id.ivVehicleImage)
        val tvVehicleName   = findViewById<TextView>(R.id.tvVehicleName)
        val tvOriginalPrice = findViewById<TextView>(R.id.tvOriginalPrice)
        val tvDiscount      = findViewById<TextView>(R.id.tvDiscount)
        val tvShippingFee   = findViewById<TextView>(R.id.tvShippingFee)
        val tvTotal         = findViewById<TextView>(R.id.tvTotal)
        val radioGroup      = findViewById<RadioGroup>(R.id.radioGroupShipping)
        val btnPay          = findViewById<Button>(R.id.btnPay)

        tvVehicleName.text = vehicleName
        if (vehicleImage != -1) {
            ivVehicleImage.setImageResource(vehicleImage)
        }


        tvOriginalPrice.text = String.format(Locale.US, "$%.2f", originalPrice)
        tvDiscount.text      = String.format(Locale.US, "-$%.2f", discountAmount)


        fun updateTotal() {
            val shippingFee = if (radioGroup.checkedRadioButtonId == R.id.radioExpress) 1700.0 else 0.0
            val total = priceAfterDiscount + shippingFee

            tvShippingFee.text = if (shippingFee > 0) String.format(Locale.US, "+$%.2f", shippingFee) else "Free"
            tvTotal.text       = String.format(Locale.US, "$%.2f", total)
        }


        updateTotal()
        radioGroup.setOnCheckedChangeListener { _, _ -> updateTotal() }

        btnPay.setOnClickListener {
            startActivity(Intent(this, ConfirmationActivity::class.java))
        }
    }
}