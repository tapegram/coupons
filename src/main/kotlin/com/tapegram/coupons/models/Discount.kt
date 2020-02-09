package com.tapegram.coupons.models

import java.util.UUID

data class Discount(
    val targetId: UUID,
    val dependencyId: UUID? = null,
    val value: Money
)