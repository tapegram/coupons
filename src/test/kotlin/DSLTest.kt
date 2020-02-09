package com.tapegram.coupons

import com.tapegram.coupons.models.Cart
import com.tapegram.coupons.models.Discount
import com.tapegram.coupons.models.Discounts
import com.tapegram.coupons.models.Item
import com.tapegram.coupons.models.Money
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test
import java.util.Currency
import java.util.UUID

class DSLTest {
    @Test
    fun `create a cart`() {
        val equipmentId = UUID.randomUUID()
        val accessoriesId = UUID.randomUUID()

        Cart(
            items = listOf(
                Item(
                    id = equipmentId,
                    tags = listOf("equipment", "accessories"),
                    price = Money(
                        amount = 199500,
                        currency = Currency.getInstance("USD")
                    )
                ),
                Item(
                    id = accessoriesId,
                    tags = listOf("accessories"),
                    price = Money(
                        amount = 12500,
                        currency = Currency.getInstance("USD")
                    )
                )
            ),
            discounts = Discounts(
                listOf(
                    Discount(
                        targetId = equipmentId,
                        dependencyId = equipmentId,
                        value = Money(
                            amount = 10000,
                            currency = Currency.getInstance("USD")
                        )
                    ),
                    Discount(
                        targetId = accessoriesId,
                        value = Money(
                            amount = 10000,
                            currency = Currency.getInstance("USD")
                        )
                    )
                )
            )
        ) shouldBeEqualTo cart {
            discounts {
                discount {
                    targetId = equipmentId
                    dependencyId = equipmentId
                    value {
                        amount = 10000
                        currency = "USD"
                    }
                }
                discount {
                    targetId = accessoriesId
                    value {
                        amount = 10000
                        currency = "USD"
                    }
                }
            }
            items {
                item {
                    id = equipmentId
                    tags {
                        tag { tag = "equipment" }
                        tag { tag = "accessories" }
                    }
                    price {
                        amount = 199500
                        currency = "USD"
                    }
                }
                item {
                    id = accessoriesId
                    tags {
                        tag { tag = "accessories" }
                    }
                    price {
                        amount = 12500
                        currency = "USD"
                    }
                }
            }
        }
    }
}