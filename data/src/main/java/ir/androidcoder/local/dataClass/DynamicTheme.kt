package ir.androidcoder.local.dataClass

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DynamicTheme(

    @PrimaryKey
    val id : Int,
    val dynamicThemeState : Boolean

)
