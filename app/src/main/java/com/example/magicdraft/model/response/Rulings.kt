package com.example.magicdraft.model.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rulings(
    val text: String
): Parcelable
