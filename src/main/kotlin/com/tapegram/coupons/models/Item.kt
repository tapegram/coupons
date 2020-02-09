package com.tapegram.coupons.models

import java.util.UUID

data class Item(
    val id: UUID,
    val tags: List<Tag>,
    val price: Money
)
