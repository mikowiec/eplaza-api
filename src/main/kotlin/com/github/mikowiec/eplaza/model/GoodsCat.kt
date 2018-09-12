package com.github.mikowiec.eplaza.model


import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.GenericGenerator

import javax.persistence.*

/**
 * Categories
 */
@Entity
@Table(name = "goods_cat")
class GoodsCat {

    // Unique ID
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "native")
    var id: Int = 0

    // father ID
    @ColumnDefault(value = "0")
    var parentId: Int = 0

    // Category Name
    var catName: String? = null

    @Transient
    var childrenCats: List<GoodsCat>? = null
}
