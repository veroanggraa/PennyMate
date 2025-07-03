package com.veroanggra.pennymate.data

import kotlinx.serialization.Serializable

@Serializable
data class BillItem(
    val name: String,
    val unitPrice: String,
    val quantity: String,
    val totalPrice: String
)
