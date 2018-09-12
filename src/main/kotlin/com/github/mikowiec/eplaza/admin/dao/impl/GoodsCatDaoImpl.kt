package com.github.mikowiec.eplaza.admin.dao.impl

import com.github.mikowiec.eplaza.admin.dao.GoodsCatDao
import com.github.mikowiec.eplaza.model.GoodsCat
import org.hibernate.SessionFactory
import org.hibernate.criterion.DetachedCriteria
import org.hibernate.criterion.Restrictions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.orm.hibernate5.HibernateTemplate
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

import java.io.Serializable

@Repository
open class GoodsCatDaoImpl @Autowired
constructor(sessionFactory: SessionFactory) : GoodsCatDao {
    private val template: HibernateTemplate

    init {
        template = HibernateTemplate(sessionFactory)
    }

    override fun findAll(): List<GoodsCat> {
        val criteria = DetachedCriteria.forClass(GoodsCat::class.java)
        return template.findByCriteria(criteria).map {it as GoodsCat}
    }

    override fun findByParentId(parentId: Int): List<GoodsCat>? {
        val criteria = DetachedCriteria.forClass(GoodsCat::class.java)
        criteria.add(Restrictions.eq("parentId", parentId))
        val resultList = template.findByCriteria(criteria) as List<GoodsCat>
        return if (resultList.size === 0) {
            null
        } else resultList
    }

    override fun findById(id: Int): GoodsCat? {
        val criteria = DetachedCriteria.forClass(GoodsCat::class.java)
        criteria.add(Restrictions.eq("id", id))
        val resultList = template.findByCriteria(criteria) as List<GoodsCat>
        return if (resultList.size === 0) {
            null
        } else resultList[0]
    }

    @Transactional
    override fun save(goodsCat: GoodsCat): Int {
        val result = template.save(goodsCat)
        return (result as Integer).toInt()
    }

    @Transactional
    override fun update(goodsCat: GoodsCat) {
        template.update(goodsCat)
    }

    @Transactional
    override fun delete(goodsCat: GoodsCat) {
        template.delete(goodsCat)
    }

}
