package com.github.mikowiec.eplaza.frontend.service

import com.github.mikowiec.eplaza.model.Order
import org.aspectj.weaver.ast.Or

interface OrderService {
    fun saveOrder(order: Order): Int
    fun getOrderList(userid: Int): List<Order>
}
