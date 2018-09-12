package com.github.mikowiec.eplaza.model


import org.hibernate.annotations.GenericGenerator

import javax.persistence.*

/**
 * Home promotion
 */
@Entity
@Table(name = "home_extend")
class HomeExtend {

    // Unique ID
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "native")
    var id: Int = 0

    // Promotion title
    var extendTitle: String? = null

    // Promotion image address
    var extendImgUrl: String? = null

    // Promotion group
    @OneToOne(cascade = arrayOf(CascadeType.ALL), fetch = FetchType.EAGER)
    @JoinColumn(name = "goods_cat_id")
    var goodsCat: GoodsCat? = null
}
