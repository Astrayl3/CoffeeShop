package com.example.coffeshop.Activity

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.coffeshop.Model.ItemsModel
import com.example.coffeshop.R
import com.example.coffeshop.databinding.ActivityDetailBinding
import android.content.Context
import org.chromium.base.Log

class DetailActivity : BaseActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var item: ItemsModel
    private lateinit var context: Context
    private var basePrice: Double = 0.0  // Default pirce size S
    private var selectedSize: String = "S" // Default size

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this

        bundle()
    }

    private fun bundle() {
        binding.apply {
            val receivedItem: ItemsModel? = intent.getParcelableExtra("object")
            if (receivedItem == null) {
                Log.e("DetailActivity", "Item received is NULL!")
                return
            } else {
                Log.d("DetailActivity", "Item received successfully: ${receivedItem.title}")
            }
            item = receivedItem

            // Base price for S
            basePrice = item.price
            pricetxt.text = "$$basePrice"

            // Item Information
            titletxt.text = item.title
            subtitletxt.text = item.extra
            descriptiontxt.text = item.description

            Glide.with(context)
                .load(item.picUrl[0])
                .apply(RequestOptions.bitmapTransform(RoundedCorners(1000)))
                .into(binding.img)

            backbtn.setOnClickListener {
                finish()
            }

            // If it coffee (categoryID = "1"), size visibility
            if (item.categoryID == "1") {
                enableSizeSelection()
            } else {
                disableSizeSelection()
            }
        }
    }

    private fun enableSizeSelection() {
        binding.apply {
            textView9.visibility = View.VISIBLE
            sizebtn1.visibility = View.VISIBLE
            sizebtn2.visibility = View.VISIBLE
            sizebtn3.visibility = View.VISIBLE

            sizebtn1.setOnClickListener {
                updateSizeSelection("S", basePrice)
            }
            sizebtn2.setOnClickListener {
                updateSizeSelection("M", basePrice * 1.2)
            }
            sizebtn3.setOnClickListener {
                updateSizeSelection("L", basePrice * 1.5)
            }
        }
    }

    private fun disableSizeSelection() {
        binding.apply {
            textView9.visibility = View.GONE
            sizebtn1.visibility = View.GONE
            sizebtn2.visibility = View.GONE
            sizebtn3.visibility = View.GONE
        }
    }

    private fun updateSizeSelection(size: String, newPrice: Double) {
        selectedSize = size
        binding.pricetxt.text = "$$newPrice"

        binding.apply {
            sizebtn1.setBackgroundDrawable(ContextCompat.getDrawable(context, if (size == "S") R.drawable.orange_stroke else R.drawable.grey_bg))
            sizebtn2.setBackgroundDrawable(ContextCompat.getDrawable(context, if (size == "M") R.drawable.orange_stroke else R.drawable.grey_bg))
            sizebtn3.setBackgroundDrawable(ContextCompat.getDrawable(context, if (size == "L") R.drawable.orange_stroke else R.drawable.grey_bg))

            sizebtn1.setTextColor(resources.getColor(if (size == "S") R.color.Orange else R.color.white))
            sizebtn2.setTextColor(resources.getColor(if (size == "M") R.color.Orange else R.color.white))
            sizebtn3.setTextColor(resources.getColor(if (size == "L") R.color.Orange else R.color.white))
        }
    }
}
