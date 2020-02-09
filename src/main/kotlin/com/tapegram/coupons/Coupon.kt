package com.tapegram.coupons

data class Coupon(
    val targets: List<Tag>,
    val dependencies: List<Tag>,
)