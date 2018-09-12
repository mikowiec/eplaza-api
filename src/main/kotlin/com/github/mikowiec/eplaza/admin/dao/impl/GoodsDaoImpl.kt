package com.github.mikowiec.eplaza.admin.dao.impl

import com.github.mikowiec.eplaza.admin.dao.GoodsDao
import com.github.mikowiec.eplaza.model.Goods
import com.github.mikowiec.eplaza.model.GoodsCat
import com.github.mikowiec.eplaza.model.Merchant
import org.hibernate.SessionFactory
import org.hibernate.criterion.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.orm.hibernate5.HibernateTemplate
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

import java.io.Serializable

@Repository
open class GoodsDaoImpl @Autowired
constructor(sessionFactory: SessionFactory) : GoodsDao {

    private val template: HibernateTemplate

    override val count: Int
        get() {
            val criteria = DetachedCriteria.forClass(Goods::class.java)
            criteria.setProjection(Projections.rowCount())
            val obj = template.findByCriteria(criteria).get(0)
            val longObj = obj as Long
            return longObj.toInt()  //intValue()
        }

    init {
        template = HibernateTemplate(sessionFactory)
    }

    override fun findAll(orderKeys: String): List<Goods> {
        val criteria = DetachedCriteria.forClass(Goods::class.java)

        setOrder(criteria, orderKeys)
        return template.findByCriteria(criteria).map {it as Goods}
    }

    override fun findByGoodsName(goodsName: String, orderKeys: String): List<Goods> {
        val criteria = DetachedCriteria.forClass(Goods::class.java)

        setOrder(criteria, orderKeys)
        criteria.add(Restrictions.like("goodsName", "%$goodsName%"))
        return template.findByCriteria(criteria).map {it as Goods}
    }

    override fun findByGoodsNameAndPage(goodsName: String, page: Int, pageSize: Int, orderKeys: String): List<Goods> {
        val criteria = DetachedCriteria.forClass(Goods::class.java)

        setOrder(criteria, orderKeys)
        criteria.add(Restrictions.like("goodsName", "%$goodsName%"))
        val offset = (page - 1) * pageSize
        return template.findByCriteria(criteria, offset, pageSize).map {it as Goods}
    }

    override fun findByMerchantId(merchantId: Int, orderKeys: String): List<Goods> {
        val criteria = DetachedCriteria.forClass(Goods::class.java)

        setOrder(criteria, orderKeys)

        val merchant = Merchant()
        merchant.id = merchantId
        criteria.add(Restrictions.eq("merchant", merchant))
        return template.findByCriteria(criteria).map {it as Goods}
    }

    override fun findByMerchantIdAndPage(merchantId: Int, page: Int, pageSize: Int, orderKeys: String): List<Goods> {
        val criteria = DetachedCriteria.forClass(Goods::class.java)

        setOrder(criteria, orderKeys)

        val merchant = Merchant()
        merchant.id = merchantId
        criteria.add(Restrictions.eq("merchant", merchant))

        val offset = (page - 1) * pageSize
        return template.findByCriteria(criteria, offset, pageSize).map {it as Goods}
    }

    override fun findByCatId(catId: Int, orderKeys: String): List<Goods> {
        val criteria = DetachedCriteria.forClass(Goods::class.java)

        setOrder(criteria, orderKeys)

        val goodsCat = GoodsCat()
        goodsCat.id = catId
        criteria.add(Restrictions.eq("goodsCat", goodsCat))
        return template.findByCriteria(criteria).map {it as Goods}
    }

    override fun findByCatIdAndPage(catId: Int, page: Int, pageSize: Int, orderKeys: String): List<Goods> {
        val criteria = DetachedCriteria.forClass(Goods::class.java)

        setOrder(criteria, orderKeys)

        val goodsCat = GoodsCat()
        goodsCat.id = catId
        criteria.add(Restrictions.eq("goodsCat", goodsCat))
        val offset = (page - 1) * pageSize
        return template.findByCriteria(criteria, offset, pageSize).map {it as Goods}
    }

    override fun findByPage(page: Int, pageSize: Int, orderKeys: String): List<Goods> {
        val criteria = DetachedCriteria.forClass(Goods::class.java)

        setOrder(criteria, orderKeys)
        val offset = (page - 1) * pageSize
        return template.findByCriteria(criteria, offset, pageSize).map {it as Goods}
    }

    override fun getCountByGoodsName(goodsName: String): Int {
        val criteria = DetachedCriteria.forClass(Goods::class.java)

        criteria.add(Restrictions.like("goodsName", "%$goodsName%"))
        criteria.setProjection(Projections.rowCount())

        val obj = template.findByCriteria(criteria).get(0)
        val longObj = obj as Long
        return longObj.toInt()  //intValue()
    }

    override fun getCountByMerchantId(merchantId: Int): Int {
        val criteria = DetachedCriteria.forClass(Goods::class.java)

        val merchant = Merchant()
        merchant.id = merchantId
        criteria.add(Restrictions.eq("merchant", merchant))
        criteria.setProjection(Projections.rowCount())

        val obj = template.findByCriteria(criteria).get(0)
        val longObj = obj as Long
        return longObj.toInt()
    }

    override fun getCountByCatId(catId: Int): Int {
        val criteria = DetachedCriteria.forClass(Goods::class.java)

        val goodsCat = GoodsCat()
        goodsCat.id = catId
        criteria.add(Restrictions.eq("goodsCat", goodsCat))
        criteria.setProjection(Projections.rowCount())

        val obj = template.findByCriteria(criteria).get(0)
        val longObj = obj as Long
        return longObj.toInt()
    }

    override fun findById(id: Int): Goods? {
        val criteria = DetachedCriteria.forClass(Goods::class.java)
        criteria.add(Restrictions.eq("id", id))
        val resultList = template.findByCriteria(criteria) as List<Goods>
        return if (resultList.size === 0) {
            null
        } else resultList[0]
    }

    @Transactional
    override fun save(goods: Goods): Int {
        val result = template.save(goods)
        return (result as Integer).toInt()
    }

    @Transactional
    override fun update(goods: Goods) {
        template.update(goods)
    }

    @Transactional
    override fun delete(goods: Goods) {
        template.delete(goods)
    }

    // Set the sorting method
    private fun setOrder(criteria: DetachedCriteria, orderKeys: String) {
        // With a - in front of the direction
        if (orderKeys.indexOf("-") === -1) {
            criteria.addOrder(Order.asc(orderKeys))
        } else {
            criteria.addOrder(Order.desc(orderKeys.substring(1)))
        }
    }

}
