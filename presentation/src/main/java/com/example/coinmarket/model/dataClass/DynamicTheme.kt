package com.example.coinmarket.model.dataClass

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DynamicTheme(

    @PrimaryKey
    val id : Int,
    val dynamicThemeState : Boolean

)
