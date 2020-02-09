package com.tapegram.coupons

import com.tapegram.coupons.models.Cart
import com.tapegram.coupons.models.Discount
import com.tapegram.coupons.models.Discounts
import com.tapegram.coupons.models.Item
import com.tapegram.coupons.models.Money
import com.tapegram.coupons.models.Tag
import java.util.Currency
import java.util.UUID

fun cart(block: CartBuilder.() -> Unit): Cart = CartBuilder().apply(block).build()

class CartBuilder {
    private lateinit var items: List<Item>
    private lateinit var discounts: Discounts

    fun items(block: ItemsBuilder.() -> Unit) {
        items = ItemsBuilder().apply(block).build()
    }

    fun discounts(block: DiscountsBuilder.() -> Unit) {
        discounts = DiscountsBuilder().apply(block).build()
    }

    fun build() = Cart(
        items = items,
        discounts = discounts
    )
}

class ItemsBuilder {
    private var items: MutableList<Item> = mutableListOf()

    fun item(block: ItemBuilder.() -> Unit) {
        items.add(ItemBuilder().apply(block).build())
    }

    fun build() =
        items.toList()
}

class ItemBuilder {
    lateinit var id: UUID
    private lateinit var tags: List<Tag>
    private lateinit var price: Money

    fun price(block: MoneyBuilder.() -> Unit) {
        price = MoneyBuilder().apply(block).build()
    }

    fun tags(block: TagsBuilder.() -> Unit) {
        tags = TagsBuilder().apply(block).build()
    }

    fun build() : Item =
        Item(
            id = id,
            tags = tags,
            price = price
        )
}

class TagsBuilder {
    private var tags: MutableList<Tag> = mutableListOf()

    fun tag(block: TagBuilder.() -> Unit) {
        tags.add(TagBuilder().apply(block).build())
    }

    fun build() =
        tags.toList()
}

class TagBuilder {
    lateinit var tag: Tag
    fun build() = tag
}

class MoneyBuilder {
    var amount: Int = 0
    lateinit var currency: String
    fun build(): Money =
        Money(amount = amount, currency = Currency.getInstance(currency))
}

class DiscountsBuilder {
    private var discounts: MutableList<Discount> = mutableListOf()

    fun discount(block: DiscountBuilder.() -> Unit) {
        discounts.add(DiscountBuilder().apply(block).build())
    }

    fun build() =
        Discounts(discounts.toList())
}

class DiscountBuilder {
    lateinit var targetId: UUID
    var dependencyId: UUID? = null
    private lateinit var value: Money

    fun value(block: MoneyBuilder.() -> Unit) {
        value = MoneyBuilder().apply(block).build()
    }

    fun build(): Discount =
        Discount(
            targetId = targetId,
            dependencyId = dependencyId,
            value = value
        )
}

