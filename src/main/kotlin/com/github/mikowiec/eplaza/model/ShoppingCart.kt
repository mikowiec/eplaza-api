package com.github.mikowiec.eplaza.model

import org.hibernate.annotations.GenericGenerator

import javax.persistence.*

// shopping cart
@Entity
@Table(name = "shopping_cart")
class ShoppingCart {
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "native")
    var id: Int = 0 // Shopping cart id
    // product picture
    var img: String? = null
    // Product ID
    @Column(name = "goods_id")
    var goodsId: Int = 0
    @Column(name = "user_id")
    var userId: Int = 0
    // Merchant ID
    @Column(name = "merchant_id")
    var merchantId: Int = 0
    // Package ID
    @Column(name = "attr_id")
    var attrId: Int = 0
    // Commodity price
    var price: Double = 0.toDouble()
    // Number of products
    var count: Int = 0
    // product description
    var title: String? = null
    // Package description
    @Column(name = "attr_title")
    var attrTitle: String? = null
    // Product code
    @Column(name = "attr_code")
    var goodsCode: String? = null
}
