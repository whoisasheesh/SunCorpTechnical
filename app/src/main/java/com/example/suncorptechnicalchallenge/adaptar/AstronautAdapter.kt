package com.example.suncorptechnicalchallenge.adaptar

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.suncorptechnicalchallenge.R
import com.example.suncorptechnicalchallenge.databinding.ItemAstronautBinding
import com.example.suncorptechnicalchallenge.model.AstronautModel
import com.example.suncorptechnicaltest.interfaces.RecyclerViewItemClickListener
import com.squareup.picasso.Picasso


class AstronautAdapter(private val astronautLists: ArrayList<AstronautModel>) :
    RecyclerView.Adapter<AstronautAdapter.ViewHolder>() {
    private var itemClickListener: RecyclerViewItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AstronautAdapter.ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_astronaut,
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: AstronautAdapter.ViewHolder, position: Int) {
        val astronautModel = astronautLists[position]
        holder.binding.tvAstronautName.text = "Astronaut Name: " + astronautModel.astronautName
        holder.binding.tvAstronautNationality.text =
            "Nationality: " + astronautModel.astronautNationality
        holder.binding.tvAstronautDob.text = "Date of birth: " + astronautModel.astronautDob

        Picasso.get().load(astronautModel.astronautThumbnail)
            .error(R.drawable.ic_image_not_available)
            .into(holder.binding.ivAstronautProfileIcon)
    }

    override fun getItemCount(): Int {
        return astronautLists.size
    }

    fun setClickListener(clickListener: RecyclerViewItemClickListener?) {
        this.itemClickListener = clickListener
    }

    fun updateData(astronautLists: ArrayList<AstronautModel?>?) {
        astronautLists?.clear()
        astronautLists?.addAll(astronautLists)
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


