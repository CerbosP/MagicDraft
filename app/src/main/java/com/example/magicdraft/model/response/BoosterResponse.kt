package com.example.magicdraft.model.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BoosterResponse(
    val cards: List<Card>?
): Parcelable
