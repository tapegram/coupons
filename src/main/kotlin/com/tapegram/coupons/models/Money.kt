package com.tapegram.coupons.models

import java.util.Currency

data class Money(
    val amount: Int,
    val currency: Currency
)