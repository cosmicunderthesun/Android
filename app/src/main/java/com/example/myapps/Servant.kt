package com.example.myapps

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Servant (
    val name: String,
    val kelas: String,
    val photo: String,
    val desc: String
):Parcelable