package com.example.ejercicio04t7

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

    }

    private fun llamar(){
        val permiso: String = "android.permission.CALL_PHONE"
        if (ContextCompat.checkSelfPermission(this, permiso) == PackageManager.PERMISSION_DENIED){
            val intentCall: Intent = Intent(Intent.ACTION_CALL)
            intentCall.setData(Uri.parse("tel:609077260"))
            startActivity(intentCall)
        } else {
            Toast.makeText(applicationContext, R.string.no_permisos_msg, Toast.LENGTH_SHORT).show()
            ActivityCompat.requestPermissions(this, arrayOf(permiso), 0)
        }
    }
}