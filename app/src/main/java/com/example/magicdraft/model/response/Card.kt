package com.example.magicdraft.model.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Card(
    val name: String,
    val manaCost: String?,
    val colors: List<String>?,
    val colorIdentity: List<String>?,
    val type: String,
    val rarity: String,
    val text: String?,
    val power: String?,
    val toughness: String?,
    val imageUrl: String?,
    val legalities: List<Format>?,
    val rulings: List<Rulings>?
): Parcelable
