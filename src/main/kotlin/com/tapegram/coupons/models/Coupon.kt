package com.tapegram.coupons.models

data class Coupon(
    val targets: List<Tag>,
    val dependencies: List<Tag>
)