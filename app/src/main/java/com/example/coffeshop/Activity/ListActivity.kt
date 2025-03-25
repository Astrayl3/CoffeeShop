 package com.example.coffeshop.Activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffeshop.Adapter.ListItemAdapter
import com.example.coffeshop.R
import com.example.coffeshop.ViewModel.MainViewModel
import com.example.coffeshop.databinding.ActivityListBinding

 class ListActivity : BaseActivity() {

     private val viewModel = MainViewModel()
     private lateinit var binding: ActivityListBinding
     private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        context = this@ListActivity
        setContentView(binding.root)

        binding.menubtn.setOnClickListener{
            startActivity(
                Intent(this@ListActivity, MainActivity::class.java)
            )
        }
        binding.titletxt.text = intent.getStringExtra("title")
        val i = intent.getIntExtra("id", 0)
        when(i){
            1 -> binding.catImg.setImageDrawable(
                ContextCompat.getDrawable(
                    context, R.drawable.coffeeicon
                )
            )
            2 -> binding.catImg.setImageDrawable(
                ContextCompat.getDrawable(
                    context, R.drawable.cakeicon
                )
            )
        }
        binding.progressBar.visibility = View.VISIBLE
        viewModel.loadFiltered(i).observe(this) { item ->
            Log.d("RecyclerViewCheck", "Total items received from ViewModel: ${item.size}")

            if (item.isEmpty()) {
                binding.emptytxt.visibility = View.VISIBLE
            } else {
                binding.emptytxt.visibility = View.GONE
                binding.view.layoutManager = LinearLayoutManager(
                    this@ListActivity, LinearLayoutManager.VERTICAL, false
                )
                binding.view.adapter = ListItemAdapter(item)
                binding.progressBar.visibility = View.GONE
            }
        }
    }
}