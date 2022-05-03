package com.example.suncorptechnicalchallenge.interfaces

import com.example.suncorptechnicalchallenge.model.AstronautModel

interface OnComplete {
    fun onComplete(isSuccess: Boolean, astronautModelArrayList: ArrayList<AstronautModel>)
}