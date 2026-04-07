package com.example.vehicleshop

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class OrderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        // ── Step 1: Retrieve data passed from MainActivity ────────────────────
        val vehicleName  = intent.getStringExtra("VEHICLE_NAME") ?: "Unknown"
        val originalPrice = intent.getDoubleExtra("VEHICLE_PRICE", 0.0)

        // ── Step 2: Calculate the 5% discount ────────────────────────────────
        val discountAmount  = originalPrice * 0.05          // 5% of original
        val priceAfterDiscount = originalPrice - discountAmount

        // ── Step 3: Find all views by their IDs ──────────────────────────────
        val tvVehicleName   = findViewById<TextView>(R.id.tvVehicleName)
        val tvOriginalPrice = findViewById<TextView>(R.id.tvOriginalPrice)
        val tvDiscount      = findViewById<TextView>(R.id.tvDiscount)
        val tvShippingFee   = findViewById<TextView>(R.id.tvShippingFee)
        val tvTotal         = findViewById<TextView>(R.id.tvTotal)
        val radioGroup      = findViewById<RadioGroup>(R.id.radioGroupShipping)
        val btnPay          = findViewById<Button>(R.id.btnPay)

        // ── Step 4: Display vehicle info on screen ───────────────────────────
        tvVehicleName.text   = vehicleName
        tvOriginalPrice.text = "$%.2f".format(originalPrice)
        tvDiscount.text      = "-$%.2f".format(discountAmount)

        // ── Step 5: Calculate total based on selected shipping ────────────────
        fun updateTotal() {
            val shippingFee = when (radioGroup.checkedRadioButtonId) {
                R.id.radioExpress -> 1700.0   // Express: add $1,700
                else              -> 0.0      // Standard: free
            }

            val total = priceAfterDiscount + shippingFee

            // Update the shipping and total labels
            tvShippingFee.text = if (shippingFee > 0) "+$%.2f".format(shippingFee) else "Free"
            tvTotal.text       = "$%.2f".format(total)
        }

        // Run once on load to show initial totals
        updateTotal()

        // ── Step 6: Recalculate whenever the user changes shipping option ─────
        radioGroup.setOnCheckedChangeListener { _, _ ->
            updateTotal()
        }

        // ── Step 7: "Pay" button navigates to ConfirmationActivity ───────────
        btnPay.setOnClickListener {
            val intent = Intent(this, ConfirmationActivity::class.java)
            startActivity(intent)
        }
    }
}