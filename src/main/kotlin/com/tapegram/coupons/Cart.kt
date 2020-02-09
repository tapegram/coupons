package com.tapegram.coupons

data class Cart(
    val items: List<Item>,
    val discounts: Discounts
)