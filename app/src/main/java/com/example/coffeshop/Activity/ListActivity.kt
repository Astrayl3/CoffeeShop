 package com.example.coffeshop.Activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffeshop.Adapter.ListItemAdapter
import com.example.coffeshop.R
import com.example.coffeshop.ViewModel.MainViewModel
import com.example.coffeshop.databinding.ActivityListBinding

 class ListActivity : BaseActivity() {

     private val viewModel = MainViewModel()
     private lateinit var bindding: ActivityListBinding
     private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindding = ActivityListBinding.inflate(layoutInflater)
        context = this@ListActivity
        setContentView(bindding.root)

        bindding.menubtn.setOnClickListener{
            startActivity(
                Intent(this@ListActivity, MainActivity::class.java)
            )
        }
        bindding.titletxt.text = intent.getStringExtra("title")
        val i = intent.getIntExtra("id", 0)
        when(i){
            1 -> bindding.catImg.setImageDrawable(
                ContextCompat.getDrawable(
                    context, R.drawable.coffeeicon
                )
            )
            2 -> bindding.catImg.setImageDrawable(
                ContextCompat.getDrawable(
                    context, R.drawable.cakeicon
                )
            )
        }
        bindding.progressBar.visibility = View.VISIBLE
        viewModel.loadFiltered(i).observe(this){ item ->
            if (item.isEmpty()){
                bindding.emptytxt.visibility = View.VISIBLE
            }else{
                bindding.emptytxt.visibility = View.GONE
                bindding.view.layoutManager = LinearLayoutManager(
                    this@ListActivity,
                    LinearLayoutManager.VERTICAL,
                    false)
                bindding.view.adapter = ListItemAdapter(item)
                bindding.progressBar.visibility = View.GONE
            }
        }
    }
}