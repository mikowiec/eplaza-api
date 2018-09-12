package com.github.mikowiec.eplaza.model


import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import org.hibernate.annotations.GenericGenerator

import javax.persistence.*
import java.util.HashSet

/**
 * Product grouping
 *
 * Commodity grouping is provided to the merchants themselves
 *
 */
@Entity
@Table(name = "goods_group")
class GoodsGroup {

    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "native")
    var id: Int = 0

    var goodsName: String? = null

    @ManyToMany(mappedBy = "goodsGroups", cascade = arrayOf(CascadeType.ALL), fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    var goodses: Set<Goods> = HashSet()
}
