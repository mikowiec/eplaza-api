package com.github.mikowiec.eplaza.model


import org.hibernate.annotations.GenericGenerator

import javax.persistence.*
import java.util.Date

/**
 * Home promotion
 */
@Entity
@Table(name = "second_kill")
class SecondKill {

    // Unique ID
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "native")
    var id: Int = 0

    // max promotion picture
    var skImg: String? = null

    // max price
    var skPrice: Double = 0.toDouble()
        private set

    // max end time
    @Temporal(TemporalType.TIMESTAMP)
    private val endedAt: Date? = null

    // The product package corresponding to the spike (max)
    @OneToOne(cascade = arrayOf(CascadeType.ALL), fetch = FetchType.EAGER)
    @JoinColumn(name = "goods_attr_id")
    var goodsAttr: GoodsAttr? = null

    fun setSkPrice(skPrice: Int) {
        this.skPrice = skPrice.toDouble()
    }
}
