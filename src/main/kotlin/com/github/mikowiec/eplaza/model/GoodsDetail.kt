package com.github.mikowiec.eplaza.model


import org.hibernate.annotations.GenericGenerator

import javax.persistence.*

/**
 * Product information
 *
 * Product information is the multiple key-value relationship of the product
 * Such as Weight: 30kg, Function: Waterproof
 *
 */
@Entity
@Table(name = "goods_detail")
class GoodsDetail {

    // Unique ID
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "native")
    var id: Int = 0

    // Product details Key
    var detailKey: String? = null

    // Product details Value
    var detailValue: String? = null
}
