package com.oceanx.myorders.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.oceanx.myorders.R
import com.oceanx.myorders.adapter.OrderAdapter
import com.oceanx.myorders.model.Order
import com.oceanx.myorders.model.OrderStatus

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: OrderAdapter
    private lateinit var tabLayout: TabLayout
    private lateinit var etSearch: EditText
    private lateinit var tvInfoBanner: View
    private lateinit var btnCloseBanner: ImageButton

    private val allOrders = listOf(
        Order("ORD12345", "Four Wheeler", "05 Feb, 4:46 PM", "741, Gumanwara",
            "00, Main Rd, Shivaji Nagar, Jhansi, Uttar Pradesh 284001, India", 229.0, OrderStatus.CANCELLED),
        Order("ORD12346", "Four Wheeler", "05 Feb, 4:46 PM", "741, Gumanwara",
            "00, Main Rd, Shivaji Nagar, Jhansi, Uttar Pradesh 284001, India", 229.0, OrderStatus.CANCELLED),
        Order("ORD12347", "Four Wheeler", "05 Feb, 4:46 PM", "332, Gumanwara",
            "GC72+GGV, Kamrari, Madhya Pradesh 475661, India", 1515.0, OrderStatus.CANCELLED),
        Order("ORD12348", "Four Wheeler", "05 Feb, 4:46 PM", "332, Gumanwara",
            "GC72+GGV, Kamrari, Madhya Pradesh 475661, India", 1634.0, OrderStatus.COMPLETED),
        Order("ORD12349", "Four Wheeler", "06 Feb, 10:20 AM", "741, Gumanwara",
            "Sector 5, Jhansi, Uttar Pradesh 284003, India", 450.0, OrderStatus.COMPLETED),
        Order("ORD12350", "Four Wheeler", "07 Feb, 2:00 PM", "332, Gumanwara",
            "GC72+GGV, Kamrari, Madhya Pradesh 475661, India", 899.0, OrderStatus.BOOKED_AGAIN)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViews()
        setupRecyclerView()
        setupTabs()
        setupSearch()
        setupBottomNavigation()
        setupBanner()
    }

    private fun setupViews() {
        recyclerView = findViewById(R.id.recyclerView)
        tabLayout = findViewById(R.id.tabLayout)
        etSearch = findViewById(R.id.etSearch)
        tvInfoBanner = findViewById(R.id.bannerLayout)
        btnCloseBanner = findViewById(R.id.btnCloseBanner)
    }

    private fun setupRecyclerView() {
        adapter = OrderAdapter(allOrders)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun setupTabs() {
        tabLayout.addTab(tabLayout.newTab().setText("All Orders"))
        tabLayout.addTab(tabLayout.newTab().setText("Completed"))
        tabLayout.addTab(tabLayout.newTab().setText("Cancelled"))
        tabLayout.addTab(tabLayout.newTab().setText("Booked Again"))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                filterOrders(tab?.position ?: 0)
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun filterOrders(tabPosition: Int) {
        val query = etSearch.text.toString().trim()
        val filtered = when (tabPosition) {
            1 -> allOrders.filter { it.status == OrderStatus.COMPLETED }
            2 -> allOrders.filter { it.status == OrderStatus.CANCELLED }
            3 -> allOrders.filter { it.status == OrderStatus.BOOKED_AGAIN }
            else -> allOrders.toList()
        }.filter { order ->
            if (query.isEmpty()) true
            else order.orderId.contains(query, ignoreCase = true) ||
                    order.pickupAddress.contains(query, ignoreCase = true) ||
                    order.dropAddress.contains(query, ignoreCase = true)
        }
        adapter.updateOrders(filtered)
    }

    private fun setupSearch() {
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                filterOrders(tabLayout.selectedTabPosition)
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun setupBottomNavigation() {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNav.selectedItemId = R.id.nav_orders
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> true
                R.id.nav_orders -> true
                R.id.nav_payments -> true
                R.id.nav_account -> true
                else -> false
            }
        }
    }

    private fun setupBanner() {
        btnCloseBanner.setOnClickListener {
            tvInfoBanner.visibility = View.GONE
        }
    }
}
