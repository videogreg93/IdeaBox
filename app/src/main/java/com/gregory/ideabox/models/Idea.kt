package com.gregory.ideabox.models

import android.os.Parcel
import android.os.Parcelable

data class Idea(var name: String = "", var category: String = "") : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(category)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return name
    }

    companion object CREATOR : Parcelable.Creator<Idea> {
        override fun createFromParcel(parcel: Parcel): Idea {
            return Idea(parcel)
        }

        override fun newArray(size: Int): Array<Idea?> {
            return arrayOfNulls(size)
        }
    }
}