package com.example.coffeshop.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.coffeshop.Model.ItemsModel
import com.example.coffeshop.Repository.MainRepository

class MainViewModel: ViewModel() {
    private val repository = MainRepository()

    fun loadFiltered(id:Int):LiveData<MutableList<ItemsModel>>{
        return repository.loadFiltered(id)
    }
}