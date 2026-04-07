package com.example.vehicleshop

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val vehicles = mapOf(
            R.id.itemSedan    to Triple("BMW M3(F80)",        40000.0,  R.drawable.car_bmw),
            R.id.itemSuv      to Triple("Range Rover(SPORT)", 60000.0,  R.drawable.car_rangerover),
            R.id.itemTruck    to Triple("Tesla Model S",      25000.0,  R.drawable.car_tesla),
            R.id.itemElectric to Triple("Porsche 911(991)",   125000.0, R.drawable.car_porsche)
        )


        for ((viewId, vehicleInfo) in vehicles) {
            val (name, price, imageRes) = vehicleInfo


            findViewById<View>(viewId)?.setOnClickListener {
                val intent = Intent(this, OrderActivity::class.java)
                intent.putExtra("VEHICLE_NAME",  name)
                intent.putExtra("VEHICLE_PRICE", price)
                intent.putExtra("VEHICLE_IMAGE", imageRes)
                startActivity(intent)
            }
        }
    }
}