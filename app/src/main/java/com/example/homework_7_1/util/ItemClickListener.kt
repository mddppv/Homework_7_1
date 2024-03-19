package com.example.homework_7_1.util

interface ItemClickListener {
    fun onItemClicked(position: Int)
    fun onEditClicked(position: Int)
    fun onDeleteClicked(position: Int)
    fun onFavoriteClicked(position: Int)
}