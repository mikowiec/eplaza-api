package com.github.mikowiec.eplaza.admin.dao.impl

import com.github.mikowiec.eplaza.admin.dao.HomeExtendDao
import com.github.mikowiec.eplaza.model.HomeExtend
import org.hibernate.SessionFactory
import org.hibernate.criterion.DetachedCriteria
import org.hibernate.criterion.Restrictions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.orm.hibernate5.HibernateTemplate
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

import java.io.Serializable

@Repository
open class HomeExtendDaoImpl @Autowired
constructor(sessionFactory: SessionFactory) : HomeExtendDao {
    private val template: HibernateTemplate

    init {
        template = HibernateTemplate(sessionFactory)
    }

    override fun findAll(): List<HomeExtend> {
        val criteria = DetachedCriteria.forClass(HomeExtend::class.java)
        return template.findByCriteria(criteria).map {it as HomeExtend}
    }

    override fun findById(id: Int): HomeExtend? {
        val criteria = DetachedCriteria.forClass(HomeExtend::class.java)
        criteria.add(Restrictions.eq("id", id))
        val resultList = template.findByCriteria(criteria) as List<HomeExtend>
        return if (resultList.size === 0) {
            null
        } else resultList[0]
    }

    override fun findByPage(page: Int, pageSize: Int): List<HomeExtend> {
        val criteria = DetachedCriteria.forClass(HomeExtend::class.java)
        val offset = (page - 1) * pageSize
        return template.findByCriteria(criteria, offset, pageSize).map {it as HomeExtend}
    }

    @Transactional
    override fun save(homeExtend: HomeExtend): Int {
        val result = template.save(homeExtend)
        return (result as Integer).toInt()
    }

    @Transactional
    override fun update(homeExtend: HomeExtend) {
        template.update(homeExtend)
    }

    @Transactional
    override fun delete(homeExtend: HomeExtend) {
        template.delete(homeExtend)
    }

}
