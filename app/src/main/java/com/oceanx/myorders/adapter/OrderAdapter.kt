package com.oceanx.myorders.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.oceanx.myorders.R
import com.oceanx.myorders.model.Order
import com.oceanx.myorders.model.OrderStatus

class OrderAdapter(private var orders: List<Order>) :
    RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvVehicleType: TextView = itemView.findViewById(R.id.tvVehicleType)
        val tvDateTime: TextView = itemView.findViewById(R.id.tvDateTime)
        val tvOrderId: TextView = itemView.findViewById(R.id.tvOrderId)
        val tvPickup: TextView = itemView.findViewById(R.id.tvPickup)
        val tvDrop: TextView = itemView.findViewById(R.id.tvDrop)
        val tvAmount: TextView = itemView.findViewById(R.id.tvAmount)
        val tvStatus: TextView = itemView.findViewById(R.id.tvStatus)
        val btnInvoice: Button = itemView.findViewById(R.id.btnInvoice)
        val btnBookAgain: Button = itemView.findViewById(R.id.btnBookAgain)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_order, parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orders[position]
        val context = holder.itemView.context

        holder.tvVehicleType.text = order.vehicleType
        holder.tvDateTime.text = order.dateTime
        holder.tvOrderId.text = "Order ID: #${order.orderId}"
        holder.tvPickup.text = order.pickupAddress
        holder.tvDrop.text = order.dropAddress
        holder.tvAmount.text = "₹ ${order.amount}"

        when (order.status) {
            OrderStatus.CANCELLED -> {
                holder.tvStatus.text = "CANCELLED"
                holder.tvStatus.setTextColor(ContextCompat.getColor(context, R.color.red))
                holder.tvStatus.background = ContextCompat.getDrawable(context, R.drawable.bg_status_cancelled)
            }
            OrderStatus.COMPLETED -> {
                holder.tvStatus.text = "COMPLETED"
                holder.tvStatus.setTextColor(ContextCompat.getColor(context, R.color.green))
                holder.tvStatus.background = ContextCompat.getDrawable(context, R.drawable.bg_status_completed)
            }
            OrderStatus.BOOKED_AGAIN -> {
                holder.tvStatus.text = "BOOKED AGAIN"
                holder.tvStatus.setTextColor(ContextCompat.getColor(context, R.color.yellow_primary))
                holder.tvStatus.background = ContextCompat.getDrawable(context, R.drawable.bg_status_booked)
            }
        }

        holder.btnInvoice.setOnClickListener {
            Toast.makeText(context, "Downloading Invoice for ${order.orderId}", Toast.LENGTH_SHORT).show()
        }

        holder.btnBookAgain.setOnClickListener {
            Toast.makeText(context, "Booking again for ${order.orderId}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount() = orders.size

    fun updateOrders(newOrders: List<Order>) {
        orders = newOrders
        notifyDataSetChanged()
    }
}
