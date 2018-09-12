package com.github.mikowiec.eplaza.admin.dao


import com.github.mikowiec.eplaza.model.Goods

interface GoodsDao {

    val count: Int

    fun findAll(orderKeys: String): List<Goods>

    fun findByGoodsName(goodsName: String, orderKeys: String): List<Goods>

    fun findByGoodsNameAndPage(goodsName: String, page: Int, pageSize: Int, oderKeys: String): List<Goods>

    fun findByMerchantId(merchantId: Int, orderKeys: String): List<Goods>

    fun findByMerchantIdAndPage(merchantId: Int, page: Int, pageSize: Int, orderKeys: String): List<Goods>

    fun findByCatId(catId: Int, orderKeys: String): List<Goods>

    fun findByCatIdAndPage(catId: Int, page: Int, pageSize: Int, orderKeys: String): List<Goods>

    fun findByPage(page: Int, pageSize: Int, orderKeys: String): List<Goods>

    fun getCountByGoodsName(goodsName: String): Int

    fun getCountByMerchantId(merchantId: Int): Int

    fun getCountByCatId(catId: Int): Int

    fun findById(id: Int): Goods?

    fun save(goods: Goods): Int

    fun update(goods: Goods)

    fun delete(goods: Goods)

}
