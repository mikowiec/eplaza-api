package com.github.mikowiec.eplaza.admin.dao.impl

import com.github.mikowiec.eplaza.admin.dao.AdminGroupDao
import com.github.mikowiec.eplaza.model.AdminGroup
import org.hibernate.SessionFactory
import org.hibernate.criterion.DetachedCriteria
import org.hibernate.criterion.Restrictions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.orm.hibernate5.HibernateTemplate
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

import java.io.Serializable

@Repository
open class AdminGroupDaoImpl @Autowired
constructor(sessionFactory: SessionFactory) : AdminGroupDao {

    private val template: HibernateTemplate

    init {
        template = HibernateTemplate(sessionFactory)
    }

    override fun findAll(): List<AdminGroup> {
        val criteria = DetachedCriteria.forClass(AdminGroup::class.java)
           //return template.findByCriteria(criteria)
        return template.findByCriteria(criteria).map {it as AdminGroup}
    }

    override fun findById(id: Int): AdminGroup? {
        val criteria = DetachedCriteria.forClass(AdminGroup::class.java)
        criteria.add(Restrictions.eq("id", id))
        val resultList = template.findByCriteria(criteria) as List<AdminGroup>

        return if (resultList.size === 0) {
            null
        } else resultList[0]
    }

    override fun findByPage(page: Int, pageSize: Int): List<AdminGroup> {
        val criteria = DetachedCriteria.forClass(AdminGroup::class.java)
        val offset = (page - 1) * pageSize
        return template.findByCriteria(criteria, offset, pageSize).map {it as AdminGroup}
    }

    @Transactional
    override fun save(adminGroup: AdminGroup): Int {
        val result = template.save(adminGroup)
        return (result as Integer).toInt()
    }

    @Transactional
    override fun update(adminGroup: AdminGroup) {
        template.update(adminGroup)
    }

    @Transactional
    override fun delete(adminGroup: AdminGroup) {
        template.delete(adminGroup)
    }
}
