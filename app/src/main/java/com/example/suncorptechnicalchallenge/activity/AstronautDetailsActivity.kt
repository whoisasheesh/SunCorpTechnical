package com.example.suncorptechnicalchallenge.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.suncorptechnicalchallenge.R
import com.example.suncorptechnicalchallenge.databinding.ActivityAstronautDetailsBinding
import com.example.suncorptechnicalchallenge.model.AstronautModel
import com.squareup.picasso.Picasso

class AstronautDetailsActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val astronautModel: AstronautModel? = intent.getParcelableExtra("astronaut")
        val binding: ActivityAstronautDetailsBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_astronaut_details)
        binding.astronaut = astronautModel
        binding.toolbar.title = astronautModel?.astronautName
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.tvAstronautName.text = astronautModel?.astronautName
        binding.tvAstronautNationality.text = astronautModel?.astronautNationality
        binding.tvAstronautBio.text = astronautModel?.astronautBio
        binding.tvAstronautDob.text = astronautModel?.astronautDob

        Picasso.get()
            .load(astronautModel?.astronautThumbnail)
            .error(R.drawable.ic_image_not_available)
            .into(binding.ivProfileThumbnail)
    }
}