package com.example.app.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.app.data.model.passenger.Passenger
import com.example.app.databinding.ItemViewBinding
import com.example.app.utils.loadImage

class PassengersAdapter :
    PagingDataAdapter<Passenger, PassengersAdapter.PassengersViewHolder>(PassengersComparator) {

    inner class PassengersViewHolder(private val binding: ItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindPassengers(item: Passenger) = with(binding) {
            imageViewAvatar.loadImage(item.airline[0].logo)
            textViewUserName.text = "${item.name},${item.trips} Trips"
            textViewUserEmail.text = item.airline[0].head_quaters
            /*Glide.with(imageViewAvatar.context).load(item.airline[0].logo)
                .into(imageViewAvatar)*/

        }
    }

    object PassengersComparator : DiffUtil.ItemCallback<Passenger>() {
        override fun areItemsTheSame(oldItem: Passenger, newItem: Passenger): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(oldItem: Passenger, newItem: Passenger): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: PassengersViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { passenger ->
            holder.bindPassengers(passenger)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PassengersViewHolder {
        return PassengersViewHolder(
            ItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}