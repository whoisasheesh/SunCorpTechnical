package com.example.suncorptechnicalchallenge.adaptar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.suncorptechnicalchallenge.databinding.ItemAstronautBinding
import com.example.suncorptechnicalchallenge.model.AstronautModel
import com.example.suncorptechnicaltest.interfaces.RecyclerViewItemClickListener
import java.util.ArrayList

class AstronautAdapter(private val astronautLists: ArrayList<AstronautModel>) :
    RecyclerView.Adapter<AstronautAdapter.ViewHolder>() {
    private var itemClickListener: RecyclerViewItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AstronautAdapter.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: AstronautAdapter.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return astronautLists.size
    }

    fun setClickListener(clickListener: RecyclerViewItemClickListener?) {
        this.itemClickListener = clickListener
    }

    inner class ViewHolder(var binding: ItemAstronautBinding) :
        RecyclerView.ViewHolder(
            binding.root
        ) {
        init {
            binding.root.setOnClickListener { v: View? ->
                if (itemClickListener != null) {
                    itemClickListener!!.onRecyclerViewItemClicked(
                        layoutPosition,
                        v,
                        astronautLists[layoutPosition]
                    )
                }
            }
        }
    }
}