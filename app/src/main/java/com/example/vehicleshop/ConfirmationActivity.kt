package com.example.vehicleshop

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

// Page 3: Final confirmation screen — no calculations needed here
class ConfirmationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)
        // The layout displays the success message — nothing else to do here!
    }
}