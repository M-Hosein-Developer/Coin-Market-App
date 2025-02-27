package ir.androidcoder.mapper

import ir.androidcoder.entities.PriceEntity
import ir.androidcoder.local.dataClass.PriceResponse

fun PriceResponse.toPriceEntity(): PriceEntity = PriceEntity(
    h = this.h,
    l = this.l,
    p = this.p,
    ts = this.ts
)