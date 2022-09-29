package com.example.dogedex.dogdetail

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dogedex.Dog
import com.example.dogedex.R
import com.example.dogedex.databinding.ActivityDogDetailBinding

class DogDetailActivity : AppCompatActivity() {

    companion object {
        const val DOG_KEY = "dog"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDogDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dog = if (Build.VERSION.SDK_INT >= 33) {
            intent.extras?.getParcelable(DOG_KEY, Dog::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent?.extras?.getParcelable<Dog>(DOG_KEY)
        }

        if(dog == null) {
            Toast.makeText(this, R.string.error_dog_not_found, Toast.LENGTH_LONG).show()
            finish()
            return
        }

        binding.dogIndex.text = getString(R.string.dog_index_format, dog.index)
        binding.lifeExpectancy.text = getString(R.string.dog_life_expectancy_format, dog.lifeExpectancy)
        binding.dog = dog

    }
}