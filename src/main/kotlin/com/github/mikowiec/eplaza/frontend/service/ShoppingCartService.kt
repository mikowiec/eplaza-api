package com.github.mikowiec.eplaza.frontend.service

import com.github.mikowiec.eplaza.model.ShoppingCart

interface ShoppingCartService {
    fun saveCart(shoppingCart: ShoppingCart): Int
    fun getCartList(userid: Int): List<ShoppingCart>
    fun delCart(cart: IntArray)
}
