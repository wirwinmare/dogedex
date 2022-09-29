package com.example.dogedex.doglist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dogedex.Dog
import com.example.dogedex.databinding.DogListItemBinding
import com.example.dogedex.doglist.DogAdapter.DogViewHolder

class DogAdapter: ListAdapter<Dog, DogViewHolder>(DiffCallback) {

    companion object DiffCallback: DiffUtil.ItemCallback<Dog>() {
        override fun areItemsTheSame(oldItem: Dog, newItem: Dog): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Dog, newItem: Dog): Boolean {
            return oldItem == newItem
        }

    }

    private var onItemClickListener: ((Dog) -> Unit)? = null

    fun setOnItemClickListener(onItemClickListener: ((Dog) -> Unit)) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val binding = DogListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return DogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        val dog = getItem(position)
        holder.bind(dog)
    }

    inner class DogViewHolder(private val binding: DogListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(dog: Dog) {
            binding.dogName.text = dog.name
            binding.dogName.setOnClickListener {
                onItemClickListener?.invoke(dog)
            }
        }

    }

}