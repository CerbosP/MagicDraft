package com.example.magicdraft.utils

import android.content.Context
import com.example.magicdraft.model.response.BoosterResponse
import com.example.magicdraft.model.response.Card

fun String.formatDesc(): String {
    var desc = this

    if(this.contains("{T}")) {
        desc = this.replace("{T}"," Tap ")
    }

    if(this.contains("{R}")) {
        desc = this.replace("{R}"," (Red Mana) ")
    }

    if(this.contains("{B}")) {
        desc = this.replace("{B}"," (Black Mana) ")
    }

    if(this.contains("{W}")) {
        desc = this.replace("{W}"," (White Mana) ")
    }

    if(this.contains("{G}")) {
        desc = this.replace("{G}"," (Green Mana) ")
    }

    if(this.contains("{U}")) {
        desc = this.replace("{U}"," (Blue Mana) ")
    }

    return desc
}

fun BoosterResponse.toName(context: Context): List<String?> {
    return this.cards?.map { it.name }
        ?: listOf("Empty Response")
}