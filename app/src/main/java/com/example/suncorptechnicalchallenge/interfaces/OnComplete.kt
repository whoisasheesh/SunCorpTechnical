package com.example.suncorptechnicalchallenge.interfaces

import com.example.suncorptechnicalchallenge.model.AstronautModel
import java.util.ArrayList

interface OnComplete {
    fun onComplete(isSuccess: Boolean, propertyModelArrayList: ArrayList<AstronautModel>?)
}