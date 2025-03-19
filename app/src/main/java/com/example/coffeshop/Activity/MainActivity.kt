package com.example.coffeshop.Activity

import android.content.Intent
import android.os.Bundle
import com.example.coffeshop.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setVariable()
    }

    private fun setVariable() {
        binding.apply {
            btn1.setOnClickListener{
                starListActivity(1, "Coffe")
            }
            btn2.setOnClickListener{
                starListActivity(2, "Dessert")
            }
        }
    }

    private fun starListActivity(id: Int, title: String){
        val intent = Intent(this, ListActivity::class.java)
        intent.putExtra("id", id)
        intent.putExtra("title", title)
        startActivity(intent)
    }
}