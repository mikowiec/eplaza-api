package com.github.mikowiec.eplaza.frontend.dao

import com.github.mikowiec.eplaza.model.ShoppingCart

interface ShoppingCartDao {
    fun saveShoppingCart(shoppingCart: ShoppingCart): Int
    fun getShoppingCartByUserId(userid: Int): List<ShoppingCart>
    fun delShoppingCart(cart: IntArray)
}
