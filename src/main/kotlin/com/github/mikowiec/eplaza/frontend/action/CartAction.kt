package com.github.mikowiec.eplaza.frontend.action

import com.github.mikowiec.eplaza.frontend.service.ShoppingCartService
import com.github.mikowiec.eplaza.model.ShoppingCart
import com.github.mikowiec.eplaza.model.User
import com.github.mikowiec.eplaza.utils.ResponseTemplate
import org.apache.struts2.ServletActionContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

import java.util.HashMap

@Controller(value = "cartAction")
class CartAction : BaseAction() {
    @Autowired
    private val shoppingCartService: ShoppingCartService? = null
    private var shoppingCart: ShoppingCart? = null

    fun setShoppingCart(shoppingCart: ShoppingCart) {
        this.shoppingCart = shoppingCart
    }

    fun add(): String {
        val tokenData = ServletActionContext.getRequest().getAttribute("tokenData") as User
        shoppingCart!!.userId = tokenData.userid
        val result = shoppingCartService!!.saveCart(shoppingCart!!)
        val map: HashMap<String, Any> = HashMap()
        map.put("data", shoppingCart!!.userId )
        if (result > 0) {
            jsonResult = ResponseTemplate.success(map)
        } else {
            jsonResult = ResponseTemplate.error(301, "Add to Cart failed")
        }
        return SUCCESS
    }

    fun list(): String {
        val tokenData = ServletActionContext.getRequest().getAttribute("tokenData") as User
        val list = shoppingCartService!!.getCartList(tokenData.userid)
        val map: HashMap<String, Any> = HashMap()
        map.put("data", list)
        jsonResult = ResponseTemplate.success(map)
        return SUCCESS
    }
}
