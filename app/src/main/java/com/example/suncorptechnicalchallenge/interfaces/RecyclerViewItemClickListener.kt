package com.example.suncorptechnicaltest.interfaces

import android.view.View

interface RecyclerViewItemClickListener {
    fun onRecyclerViewItemClicked(position: Int, view: View?, `object`: Any?)
}