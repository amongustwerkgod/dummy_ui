package com.oceanx.myorders.model

data class Order(
    val orderId: String,
    val vehicleType: String,
    val dateTime: String,
    val pickupAddress: String,
    val dropAddress: String,
    val amount: Double,
    val status: OrderStatus
)

enum class OrderStatus {
    COMPLETED, CANCELLED, BOOKED_AGAIN
}
