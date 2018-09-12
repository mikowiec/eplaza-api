package com.github.mikowiec.eplaza.frontend.service.impl

import com.github.mikowiec.eplaza.frontend.dao.OrderDao
import com.github.mikowiec.eplaza.frontend.service.OrderService
import com.github.mikowiec.eplaza.model.Order
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class OrderServiceImpl : OrderService {

    @Autowired
    private val orderDao: OrderDao? = null

    override fun saveOrder(order: Order): Int {
        return orderDao!!.save(order)
    }

    override fun getOrderList(userid: Int): List<Order> {
        return orderDao!!.list(userid)
    }
}
