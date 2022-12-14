package com.example.magicdraft.model.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Format(
    val format: String,
    val legality: String
): Parcelable
