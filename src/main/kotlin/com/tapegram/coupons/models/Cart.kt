package com.tapegram.coupons.models

data class Cart(
    val items: List<Item>,
    val discounts: Discounts
)