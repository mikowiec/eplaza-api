package com.github.mikowiec.eplaza.model


import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.GenericGenerator

import javax.persistence.*

/**
 * Product package
 *
 */
@Entity
@Table(name = "goods_attr")
class GoodsAttr {

    // Unique ID
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "native")
    var id: Int = 0

    // The configuration information
    var attrTitle: String? = null

    // The configuration product thumbnail
    var attrImgUrl: String? = null

    // The configuration item number
    // (each attribute has a unique item number)
    var goodsCode: String? = null

    // The configured commodity price
    private var attrPrice: Double = 0.toDouble()

    // Whether the configuration item is on the shelves
    @ColumnDefault(value = "false")
    var onSale: Boolean? = null

    fun getAttrPrice(): Double {
        return attrPrice
    }

    fun setAttrPrice(attrPrice: Int) {
        this.attrPrice = attrPrice.toDouble()
    }

    fun setAttrPrice(attrPrice: Double) {
        this.attrPrice = attrPrice
    }
}
