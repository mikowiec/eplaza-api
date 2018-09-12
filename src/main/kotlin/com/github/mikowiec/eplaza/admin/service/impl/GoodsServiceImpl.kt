package com.github.mikowiec.eplaza.admin.service.impl

import com.github.mikowiec.eplaza.admin.dao.GoodsDao
import com.github.mikowiec.eplaza.admin.service.GoodsService
import com.github.mikowiec.eplaza.model.Goods
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GoodsServiceImpl : GoodsService {

    @Autowired
    private val goodsDao: GoodsDao? = null

    override val count: Int
        get() = goodsDao!!.count

    override fun findAll(orderKeys: String): List<Goods> {
        return goodsDao!!.findAll(orderKeys)
    }

    override fun findByGoodsName(goodsName: String, orderKeys: String): List<Goods> {
        return goodsDao!!.findByGoodsName(goodsName, orderKeys)
    }

    override fun findByGoodsNameAndPage(goodsName: String, page: Int, pageSize: Int, orderKeys: String): List<Goods> {
        return goodsDao!!.findByGoodsNameAndPage(goodsName, page, pageSize, orderKeys)
    }

    override fun findByMerchantId(merchantId: Int, orderKeys: String): List<Goods> {
        return goodsDao!!.findByMerchantId(merchantId, orderKeys)
    }

    override fun findByMerchantIdAndPage(merchantId: Int, page: Int, pageSize: Int, orderKeys: String): List<Goods> {
        return goodsDao!!.findByMerchantIdAndPage(merchantId, page, pageSize, orderKeys)
    }

    override fun findByCatId(catId: Int, orderKeys: String): List<Goods> {
        return goodsDao!!.findByCatId(catId, orderKeys)
    }

    override fun findByCatIdAndPage(catId: Int, page: Int, pageSize: Int, orderKeys: String): List<Goods> {
        return goodsDao!!.findByCatIdAndPage(catId, page, pageSize, orderKeys)
    }

    override fun findByPage(offset: Int, pageSize: Int, orderKeys: String): List<Goods> {
        return goodsDao!!.findByPage(offset, pageSize, orderKeys)
    }

    override fun getCountByGoodsName(goodsName: String): Int {
        return goodsDao!!.getCountByGoodsName(goodsName)
    }

    override fun getCountByMerchantId(merchantId: Int): Int {
        return goodsDao!!.getCountByMerchantId(merchantId)
    }

    override fun getCountByCatId(catId: Int): Int {
        return goodsDao!!.getCountByCatId(catId)
    }

    override fun findById(id: Int): Goods? {
        return goodsDao!!.findById(id)
    }

    override fun save(goods: Goods): Int {
        return goodsDao!!.save(goods)
    }

    override fun update(goods: Goods) {
        goodsDao!!.update(goods)
    }

    override fun delete(goods: Goods) {
        goodsDao!!.delete(goods)
    }
}
