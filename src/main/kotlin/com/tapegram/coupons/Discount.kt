package com.tapegram.coupons

data class Discount(
    val target: Item,
    val dependency: Item,
    val value: Money
)