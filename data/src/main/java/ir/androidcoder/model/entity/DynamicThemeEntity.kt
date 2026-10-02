package ir.androidcoder.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DynamicThemeEntity(

    @PrimaryKey
    val id : Int,
    val dynamicThemeState : Boolean

)