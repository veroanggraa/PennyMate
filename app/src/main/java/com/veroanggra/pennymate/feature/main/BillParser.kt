package com.veroanggra.pennymate.feature.main

import android.util.Log
import com.veroanggra.pennymate.data.BillDetails
import com.veroanggra.pennymate.data.BillItem

object BillParser {
    private fun String.sanitizePrice(): Double {
        return this.replace(",", "").toDoubleOrNull() ?: 0.0
    }

    fun parseScannedTextToBillDetails(rawScannedText: String): BillDetails? {
        val lines = rawScannedText.lines().map { it.trim() }.filter { it.isNotEmpty() }
        if (lines.size < 3) {
            return null
        }
        var dateTime = ""
        var restaurantName = ""
        val items = mutableListOf<BillItem>()
        var subTotal = 0.0
        var tax = 0.0
        var service = 0.0
        var discount = 0.0
        var others = 0.0
        var total = 0.0
        var currentIndex = 0

        dateTime = lines[currentIndex++]
        restaurantName = lines[currentIndex++]

        while (currentIndex < lines.size) {
            val currentLine = lines[currentIndex]
            when {
                currentLine.startsWith("Subtotal", ignoreCase = true) -> {
                    subTotal = currentLine.split(Regex("\\s+")).lastOrNull()?.sanitizePrice() ?: 0.0
                    currentIndex++
                }

                currentLine.startsWith("Tax", ignoreCase = true) -> {
                    tax = currentLine.split(Regex("\\s+")).lastOrNull()?.sanitizePrice() ?: 0.0
                    currentIndex++
                }

                currentLine.startsWith("Service", ignoreCase = true) -> {
                    service = currentLine.split(Regex("\\s+")).lastOrNull()?.sanitizePrice() ?: 0.0
                    currentIndex++
                }

                currentLine.startsWith("Discount", ignoreCase = true) -> {
                    discount = currentLine.split(Regex("\\s+")).lastOrNull()?.sanitizePrice() ?: 0.0
                    currentIndex++
                }

                currentLine.startsWith("Others", ignoreCase = true) -> {
                    others = currentLine.split(Regex("\\s+")).lastOrNull()?.sanitizePrice() ?: 0.0
                    currentIndex++
                }

                currentLine.startsWith("Total", ignoreCase = true) -> {
                    total = currentLine.split(Regex("\\s+")).lastOrNull()?.sanitizePrice() ?: 0.0
                    currentIndex++
                }

                (currentIndex + 1 < lines.size) -> {
                    val itemName = currentLine
                    val detailsLine = lines[currentIndex + 1]
                    val parts = detailsLine.split(Regex("\\s+")).filter { it.isNotBlank() }

                    if (parts.size >= 2) {
                        val unitPrice = parts[0].sanitizePrice()
                        val quantityString = parts[1].replace("x", "", ignoreCase = true)
                        val quantity = quantityString.toIntOrNull()
                        if (quantity != null && unitPrice > 0) {
                            val itemTotalPrice =
                                if (parts.size >= 3) parts[2].sanitizePrice() else unitPrice * quantity
                            items.add(
                                BillItem(
                                    name = itemName,
                                    unitPrice = unitPrice.toString(),
                                    quantity = quantity.toString(),
                                    totalPrice = itemTotalPrice.toString()
                                )
                            )
                            currentIndex += 2
                            continue
                        }
                    }
                    currentIndex++
                }

                else -> {
                    currentIndex++
                }
            }
        }

        if (items.isEmpty() && total == 0.0 && subTotal == 0.0 && tax == 0.0) {
            Log.w(
                "Bill Parser",
                "Parsing resulted in empty bill details. Restaurant: $restaurantName, DateTime: $dateTime"
            )
        }

        return BillDetails(
            dateTime = dateTime,
            restaurantName = restaurantName,
            items = items,
            subTotal = subTotal.toString(),
            tax = tax.toString(),
            service = service.toString(),
            discount = discount.toString(),
            others = others.toString(),
            total = total.toString()
        )
    }
}