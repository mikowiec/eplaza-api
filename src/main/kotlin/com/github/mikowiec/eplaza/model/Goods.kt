package com.github.mikowiec.eplaza.model


import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import org.hibernate.annotations.GenericGenerator

import javax.persistence.*
import java.util.HashSet

/**
 * Product
 */
@Entity
@Table(name = "goods")
class Goods {

    // Unique ID
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "native")
    var id: Int = 0

    // product name
    var goodsName: String? = null

    // Multiple pieces of product image use `,` split, limit up to five
    var goodsImgs: String? = null

    // Product description
    // (only images can be used, rules and product images are the same)
    var goodsDesc: String? = null

    // Base price, package price changes on this basis
    var basicPrice: Double = 0.toDouble()

    // Total sales of goods
    @ColumnDefault(value = "0")
    var salesNum: Int = 0

    // Total inventory
    @ColumnDefault(value = "0")
    var skuNum: Int = 0

    // Product category ID
    @ManyToOne
    @JoinColumn(name = "goods_cat_id", foreignKey = ForeignKey(name = "GOODS_CAT_ID_FK"))
    var goodsCat: GoodsCat? = null

    // Product belongs to the merchant
    @ManyToOne
    @JoinColumn(name = "merchant_id", foreignKey = ForeignKey(name = "MERCHANT_ID_FK"))
    var merchant: Merchant? = null

    // Group of goods
    // (provided to the merchant to classify the goods themselves)
    @ManyToMany(cascade = arrayOf(CascadeType.ALL), fetch = FetchType.EAGER)
    @JoinTable(
            name = "goods_groups",
            joinColumns = arrayOf(JoinColumn(name = "goods_id")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "group_id")))
    @Fetch(value = FetchMode.SUBSELECT)
    var goodsGroups: Set<GoodsGroup> = HashSet()

    // Product package (price is defined here)
    @OneToMany(cascade = arrayOf(CascadeType.ALL), orphanRemoval = true, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    var goodsAttrs: Set<GoodsAttr> = HashSet()

    // Product detailed configuration information
    @OneToMany(cascade = arrayOf(CascadeType.ALL), orphanRemoval = true, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    var goodsDetails: Set<GoodsDetail> = HashSet()

    // product review
    @OneToMany(cascade = arrayOf(CascadeType.ALL), orphanRemoval = true, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    var goodsComments: Set<GoodsComment> = HashSet()
}
