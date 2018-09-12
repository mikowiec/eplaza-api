package com.github.mikowiec.eplaza.admin.dao.impl

import com.github.mikowiec.eplaza.admin.dao.MerchantDao
import com.github.mikowiec.eplaza.model.Merchant
import org.hibernate.SessionFactory
import org.hibernate.criterion.DetachedCriteria
import org.hibernate.criterion.Restrictions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.orm.hibernate5.HibernateTemplate
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

import java.io.Serializable

@Repository
open class MerchantDaoImpl @Autowired
constructor(sessionFactory: SessionFactory) : MerchantDao {
    private val template: HibernateTemplate

    init {
        template = HibernateTemplate(sessionFactory)
    }

    override fun findAll(): List<Merchant> {
        val criteria = DetachedCriteria.forClass(Merchant::class.java)
        return template.findByCriteria(criteria).map {it as Merchant}
    }

    override fun findById(id: Int): Merchant? {
        val criteria = DetachedCriteria.forClass(Merchant::class.java)
        criteria.add(Restrictions.eq("id", id))
        val resultList = template.findByCriteria(criteria) as List<Merchant>
        return if (resultList.size === 0) {
            null
        } else resultList[0]
    }

    override fun findByPage(page: Int, pageSize: Int): List<Merchant> {
        val criteria = DetachedCriteria.forClass(Merchant::class.java)
        val offset = (page - 1) * pageSize
        return template.findByCriteria(criteria, offset, pageSize).map {it as Merchant}
    }

    override fun getMerchantByMerchantName(merchantName: String): List<Merchant> {
        val criteria = DetachedCriteria.forClass(Merchant::class.java)
        criteria.add(Restrictions.eq("merchantName", merchantName))
        return template.findByCriteria(criteria, 0, 1).map {it as Merchant}
    }

    @Transactional
    override fun save(merchant: Merchant): Int {
        val result = template.save(merchant)
        return (result as Integer).toInt()
    }

    @Transactional
    override fun update(merchant: Merchant) {
        template.update(merchant)
    }

    @Transactional
    override fun delete(merchant: Merchant) {
        template.delete(merchant)
    }

}
