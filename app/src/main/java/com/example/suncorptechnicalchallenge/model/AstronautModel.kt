package com.example.suncorptechnicalchallenge.model

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator

class AstronautModel : Parcelable {
    var astronautId: String?
    var astronautName: String?
    var astronautNationality: String?
    var astronautThumbnail: String?
    var astronautDob: String?
    var astronautBio: String?
    var astronautProfileIcon: String?


    constructor(
        astronautId: String,
        astronautName: String,
        astronautNationality: String,
        astronautThumbnail: String,
        astronautDob: String,
        astronautBio: String,
        astronautProfileIcon:String
    ) {
        this.astronautId = astronautId
        this.astronautName = astronautName
        this.astronautNationality = astronautNationality
        this.astronautThumbnail = astronautThumbnail
        this.astronautDob = astronautDob
        this.astronautBio = astronautBio
        this.astronautProfileIcon = astronautProfileIcon
    }

    private constructor(`in`: Parcel) {
        astronautId = `in`.readString()
        astronautName = `in`.readString()
        astronautNationality = `in`.readString()
        astronautThumbnail = `in`.readString()
        astronautDob = `in`.readString()
        astronautBio = `in`.readString()
        astronautProfileIcon = `in`.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(astronautId)
        dest.writeString(astronautName)
        dest.writeString(astronautNationality)
        dest.writeString(astronautThumbnail)
        dest.writeString(astronautDob)
        dest.writeString(astronautBio)
        dest.writeString(astronautProfileIcon)
    }

    companion object {
        @JvmField
        val CREATOR: Creator<AstronautModel?> = object : Creator<AstronautModel?> {
            override fun createFromParcel(`in`: Parcel): AstronautModel {
                return AstronautModel(`in`)
            }

            override fun newArray(size: Int): Array<AstronautModel?> {
                return arrayOfNulls(size)
            }
        }
    }
}