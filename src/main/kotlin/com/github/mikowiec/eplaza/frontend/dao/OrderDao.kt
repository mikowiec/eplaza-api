package com.github.mikowiec.eplaza.frontend.dao

import com.github.mikowiec.eplaza.model.Order

interface OrderDao {
    fun save(order: Order): Int
    fun list(userid: Int): List<Order>
}
