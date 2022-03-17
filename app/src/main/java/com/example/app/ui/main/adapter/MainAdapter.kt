package com.example.app.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.app.R
import com.example.app.data.model.User
import com.example.app.databinding.ItemViewBinding

class MainAdapter(private val usersList: ArrayList<User>) :  RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    lateinit var context: Context

    inner class MainViewHolder(val binding: ItemViewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ItemViewBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
        return MainViewHolder(binding)
    }


    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        with(holder) {
            binding.textViewUserEmail.text = usersList[position].email
            binding.textViewUserName.text = usersList[position].name
            Glide.with(context).load(usersList[position].avatar).into(binding.imageViewAvatar)
        }
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    fun addUsers(users: List<User>) {
        this.usersList.apply {
            clear()
            addAll(users)
        }
    }
}