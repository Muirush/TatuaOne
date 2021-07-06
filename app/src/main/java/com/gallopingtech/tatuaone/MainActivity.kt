package com.gallopingtech.tatuaone

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.gallopingtech.tatuaone.databinding.ActivityMainBinding
import com.google.android.gms.instantapps.InstantApps
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private fun showInstallPrompt(){
        val install = Intent(Intent.ACTION_MAIN)
            .addCategory(android.content.Intent.CATEGORY_DEFAULT)
            .setPackage("com.gallopingtech.loginregisterroom")
        InstantApps.showInstallPrompt(this@MainActivity,install,100,null)
    }

    private  lateinit var binding: ActivityMainBinding
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.spin.setOnClickListener(){
            result()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun result() {


        if (binding.input.text.isEmpty() || binding.input.text.toString().toInt() > 5|| binding.input.text.toString()
                .toInt() <=0){
            Toast.makeText(this, "Not allowed", Toast.LENGTH_SHORT).show()
            binding.input.error = "Please a number between 1-5"

        }
        else{
            val x  = Random.nextInt(5)+1
            val y =  binding.input.text.toString().toInt()
            binding.result.text = x.toString()
            if (x===y){
                Toast.makeText(this, "You have won", Toast.LENGTH_SHORT).show()
                binding.apply {
                    spin.text = "Winner"
                    spin.isEnabled = false
                }
                showWin()

            }
        }
        val mgr: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        mgr.hideSoftInputFromWindow(binding.input.getWindowToken(), 0)


    }
    private fun showWin(){
        Snackbar.make(binding.layoutMain,"You have won",1500).show()
    }
}