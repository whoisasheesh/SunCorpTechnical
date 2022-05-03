package com.example.suncorptechnicalchallenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.suncorptechnicalchallenge.interfaces.OnComplete
import com.example.suncorptechnicalchallenge.model.AstronautModel
import com.example.suncorptechnicalchallenge.repository.AstronautRepository

class AstronautListingViewModel : ViewModel() {
    var mAstronautLists: MutableLiveData<ArrayList<AstronautModel>>? = MutableLiveData()
    private val isLoading = MutableLiveData<Boolean>()
    private var astronautRepository: AstronautRepository? = AstronautRepository.instance
    val astronauts: Unit
        get() {
            isLoading.value = true
            astronautRepository?.fetchAstronauts(object : OnComplete {
                override fun onComplete(
                    isSuccess: Boolean,
                    astronautModelArrayList: ArrayList<AstronautModel>
                ) {
                    mAstronautLists?.value = astronautModelArrayList
                    isLoading.value = false
                }
            })
        }

    fun isLoading(): LiveData<Boolean> {
        return isLoading
    }

    fun getAstronautLists(): MutableLiveData<ArrayList<AstronautModel>>? {
        return mAstronautLists
    }

    override fun onCleared() {
        super.onCleared()
        mAstronautLists = null
        astronautRepository?.astronautLists?.clear()
        astronautRepository = null
    }

    val listener: LiveData<ArrayList<AstronautModel>>?
        get() = mAstronautLists
    val value: ArrayList<AstronautModel>
        get() = mAstronautLists?.value!!


}