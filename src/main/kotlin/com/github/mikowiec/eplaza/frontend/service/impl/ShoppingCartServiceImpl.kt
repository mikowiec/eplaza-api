package com.github.mikowiec.eplaza.frontend.service.impl

import com.github.mikowiec.eplaza.frontend.dao.ShoppingCartDao
import com.github.mikowiec.eplaza.frontend.service.ShoppingCartService
import com.github.mikowiec.eplaza.model.ShoppingCart
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ShoppingCartServiceImpl : ShoppingCartService {
    @Autowired
    private val shoppingCartDao: ShoppingCartDao? = null

    override fun saveCart(shoppingCart: ShoppingCart): Int {
        return shoppingCartDao!!.saveShoppingCart(shoppingCart)
    }

    override fun getCartList(userid: Int): List<ShoppingCart> {
        return shoppingCartDao!!.getShoppingCartByUserId(userid)
    }

    override fun delCart(cart: IntArray) {
        shoppingCartDao!!.delShoppingCart(cart)
    }
}
