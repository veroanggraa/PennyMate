package com.veroanggra.pennymate.data

import kotlinx.serialization.Serializable

@Serializable
data class BillDetails(
    val dateTime: String,
    val restaurantName: String,
    val items: List<BillItem>,
    val subTotal: String,
    val tax: String,
    val service: String,
    val discount: String,
    val others: String,
    val total: String
)
