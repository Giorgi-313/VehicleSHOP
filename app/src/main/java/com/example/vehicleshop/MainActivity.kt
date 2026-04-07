package com.example.vehicleshop

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ── Vehicle data: name and price (Double for precision) ──────────────
        val vehicles = mapOf(
            R.id.itemSedan    to Pair("BMW M3(F80)",       40000.0),
            R.id.itemSuv      to Pair("Range Rover(SPORT)",         60000.0),
            R.id.itemTruck    to Pair("Tesla Model S",        25000.0),
            R.id.itemElectric to Pair("Porsche 911(991)", 125000.0)
        )

        // ── Attach an OnClickListener to each vehicle row ─────────────────────
        for ((viewId, vehicleInfo) in vehicles) {
            val (name, price) = vehicleInfo

            findViewById<android.view.View>(viewId).setOnClickListener {
                // Create an Intent to open OrderActivity
                val intent = Intent(this, OrderActivity::class.java)

                // Pass vehicle name and price to the next screen
                intent.putExtra("VEHICLE_NAME", name)
                intent.putExtra("VEHICLE_PRICE", price)

                // Navigate to OrderActivity
                startActivity(intent)
            }
        }
    }
}