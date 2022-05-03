package com.example.suncorptechnicalchallenge.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.suncorptechnicalchallenge.R
import com.example.suncorptechnicalchallenge.adaptar.AstronautAdapter
import com.example.suncorptechnicalchallenge.databinding.ActivityAstronautListingBinding
import com.example.suncorptechnicalchallenge.model.AstronautModel
import com.example.suncorptechnicalchallenge.viewmodel.AstronautListingViewModel
import com.example.suncorptechnicaltest.interfaces.RecyclerViewItemClickListener

class AstronautListingActivity : AppCompatActivity() {
    var astronautAdapter: AstronautAdapter? = null
    private var astronautListingViewModel: AstronautListingViewModel? = null
    private var binding: ActivityAstronautListingBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_astronaut_listing)
        binding?.toolbar?.title = "Available Astronauts"
        binding?.toolbar?.navigationIcon = null

        astronautListingViewModel = ViewModelProvider(this).get(
            AstronautListingViewModel::class.java
        )

        astronautListingViewModel?.listener?.observe(
            this
        ) {
            initRecyclerView()
        }
        astronautListingViewModel?.isLoading()?.observe(this) { isLoading: Boolean ->
            binding?.progressBar?.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
        astronautListingViewModel?.astronauts
    }

    private fun initRecyclerView() {
        binding?.rvAstronautLists?.layoutManager = LinearLayoutManager(applicationContext)
        binding?.rvAstronautLists?.isNestedScrollingEnabled = false

        astronautAdapter = AstronautAdapter(astronautListingViewModel?.value!!)
        astronautAdapter?.setClickListener(object : RecyclerViewItemClickListener {
            override fun onRecyclerViewItemClicked(position: Int, view: View?, `object`: Any?) {
                startActivity(
                    Intent(
                        applicationContext,
                        AstronautDetailsActivity::class.java
                    ).putExtra("astronaut", `object` as AstronautModel)
                )
            }
        })
        binding?.rvAstronautLists?.adapter = astronautAdapter
    }

    override fun onBackPressed() {
        finish()
    }
}