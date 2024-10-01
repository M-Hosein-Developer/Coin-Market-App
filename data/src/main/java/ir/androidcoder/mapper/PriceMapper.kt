package ir.androidcoder.mapper

import ir.androidcoder.entities.PriceEntity
import ir.androidcoder.local.dataClass.PriceResponse

fun PriceResponse.toPriceEntity(): PriceEntity = PriceEntity(
    d = this.d,
    dp = this.dp,
    dt = this.dt,
    h = this.h,
    l = this.l,
    p = this.p,
    t = this.t,
    t_g = this.t_g,
    t_en = this.t_en,
    ts = this.ts
)