package ir.androidcoder.mapper

import ir.androidcoder.entities.DynamicThemeEntity
import ir.androidcoder.local.dataClass.DynamicTheme

fun DynamicTheme.toThemeEntity(): DynamicThemeEntity = DynamicThemeEntity(

    id = this.id,
    dynamicThemeState = this.dynamicThemeState

)

fun DynamicThemeEntity.toThemeModel(): DynamicTheme = DynamicTheme(

    id = this.id,
    dynamicThemeState = this.dynamicThemeState

)