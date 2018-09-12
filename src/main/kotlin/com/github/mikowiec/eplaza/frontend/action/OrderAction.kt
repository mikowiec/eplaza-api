package com.github.mikowiec.eplaza.frontend.action

import com.github.mikowiec.eplaza.frontend.service.OrderService
import com.github.mikowiec.eplaza.frontend.service.ShoppingCartService
import com.github.mikowiec.eplaza.model.Order
import com.github.mikowiec.eplaza.model.User
import com.github.mikowiec.eplaza.utils.ResponseTemplate
import org.apache.struts2.ServletActionContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

import java.util.HashMap

@Controller(value = "orderAction")
class OrderAction : BaseAction() {
    @Autowired
    private val orderService: OrderService? = null
    @Autowired
    private val shoppingCartService: ShoppingCartService? = null

    var order: Order? = null

    private var cart: IntArray? = null

    fun setCart(cart: IntArray) {
        this.cart = cart
    }

    fun add(): String {
        val tokenData = ServletActionContext.getRequest().getAttribute("tokenData") as User
        System.out.println(this.cart!!.toString())
        order!!.userid = tokenData.userid
        shoppingCartService!!.delCart(this.cart!!)
        val map: HashMap<String, Any> = HashMap()
        val result = orderService!!.saveOrder(order!!)
        map.put("data", orderService!!)
        if (result >= 0) {
            jsonResult = ResponseTemplate.success(map)
        } else {
            jsonResult = ResponseTemplate.error(501, "Add Order Failed")
        }
        return SUCCESS
    }

    fun list(): String {
        val tokenData = ServletActionContext.getRequest().getAttribute("tokenData") as User
        val list = orderService!!.getOrderList(tokenData.userid)
        val map: HashMap<String, Any> = HashMap()
        map.put("data", list)
        jsonResult = ResponseTemplate.success(map)
        return SUCCESS
    }
}
