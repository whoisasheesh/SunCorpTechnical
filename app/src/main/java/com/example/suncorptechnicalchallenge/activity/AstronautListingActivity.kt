package com.example.suncorptechnicalchallenge.activity

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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
    lateinit var astronautAdapter: AstronautAdapter
    lateinit var astronautListingViewModel: AstronautListingViewModel
    private lateinit var binding: ActivityAstronautListingBinding
    private lateinit var menuItemSortByName: MenuItem

    @SuppressLint("NotifyDataSetChanged", "UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_astronaut_listing)
        binding.toolbar.title = "Available Astronauts"
        binding.toolbar.navigationIcon = null
        binding.toolbar.inflateMenu(R.menu.sort_button)
        binding.toolbar.post {
            menuItemSortByName = binding.toolbar.menu.findItem(R.id.sort_by_name)
        }

        astronautListingViewModel = ViewModelProvider(this).get(
            AstronautListingViewModel::class.java
        )

        astronautListingViewModel.listener?.observe(
            this
        ) {
            initRecyclerView()
        }

        //observing progressbar
        astronautListingViewModel.isLoading().observe(this) { isLoading: Boolean ->
            binding.progressBar.visibility = if (isLoading) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }

        astronautListingViewModel.astronauts

        astronautListingViewModel.isLoading().observe(this) {
            if (::menuItemSortByName.isInitialized) {
                menuItemSortByName.isVisible = true
            }

        }

        binding.toolbar.setOnMenuItemClickListener {
            when (menuItemSortByName.title) {
                resources.getString(R.string.sort_by_name) -> {
                    astronautListingViewModel.getAstronautLists()?.value?.sortBy { it.astronautName }
                    astronautAdapter.notifyDataSetChanged()
                    Toast.makeText(this, "Sorted by Name", Toast.LENGTH_SHORT)
                        .show()
                    menuItemSortByName.icon =
                        ContextCompat.getDrawable(this, R.drawable.ic_nationality)
                    menuItemSortByName.title = resources.getString(R.string.sort_by_nationality)
                }

                resources.getString(R.string.sort_by_nationality) -> {
                    astronautListingViewModel.getAstronautLists()?.value?.sortBy { it.astronautNationality }
                    astronautAdapter.notifyDataSetChanged()
                    Toast.makeText(this, "Sorted by Nationality", Toast.LENGTH_SHORT)
                        .show()
                    menuItemSortByName.icon =
                        ContextCompat.getDrawable(this, R.drawable.ic_date_of_birth)
                    menuItemSortByName.title = resources.getString(R.string.sort_by_date_of_birth)
                }

                resources.getString(R.string.sort_by_date_of_birth) -> {
                    astronautListingViewModel.getAstronautLists()?.value?.sortBy { it.astronautDob }
                    astronautAdapter.notifyDataSetChanged()
                    Toast.makeText(this, "Sorted by Date of birth", Toast.LENGTH_SHORT)
                        .show()
                    menuItemSortByName.icon =
                        ContextCompat.getDrawable(this, R.drawable.ic_sort_by_name)
                    menuItemSortByName.title = resources.getString(R.string.sort_by_name)
                }
            }
            true
        }
    }

    private fun initRecyclerView() {
        binding.rvAstronautLists.layoutManager = LinearLayoutManager(applicationContext)
        binding.rvAstronautLists.isNestedScrollingEnabled = false

        astronautAdapter = AstronautAdapter(astronautListingViewModel.value)
        astronautAdapter.setClickListener(object : RecyclerViewItemClickListener {
            override fun onRecyclerViewItemClicked(position: Int, view: View?, `object`: Any?) {
                startActivity(
                    Intent(
                        applicationContext,
                        AstronautDetailsActivity::class.java
                    ).putExtra("astronaut", `object` as AstronautModel)
                )
            }
        })
        binding.rvAstronautLists.adapter = astronautAdapter
    }

    override fun onBackPressed() {
        val alert =
            AlertDialog.Builder(this)
                .create()
        alert.setTitle("Exit Application")
        alert.setMessage("Are you sure you want to exit?")
        alert.setIcon(R.drawable.ic_warning)
        alert.setCancelable(false)
        alert.setCanceledOnTouchOutside(false)
        alert.setButton(
            DialogInterface.BUTTON_POSITIVE, "OK"
        ) { _, _ -> finish() }
        alert.setButton(
            DialogInterface.BUTTON_NEGATIVE, "Cancel"
        ) { _, _ -> alert.dismiss() }
        alert.setOnCancelListener { }
        alert.show()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}

